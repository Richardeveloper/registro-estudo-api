package com.richardeveloper.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richardeveloper.dto.RegistroDto;
import com.richardeveloper.models.Registro;
import com.richardeveloper.services.RegistroService;

@RestController
@RequestMapping(value = "/registros")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

	@Autowired
	private RegistroService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	@Cacheable(value = "ListaRegistros")
	public ResponseEntity<Page<RegistroDto>> findAllPagination(@PageableDefault(page = 0, size = 10, sort = "data") Pageable pagination){
		Page<Registro> registros = service.findAllPagination(pagination);
		Page<RegistroDto> dto = registros.map(registro -> modelMapper.map(registro, RegistroDto.class));
		return new ResponseEntity<Page<RegistroDto>>(dto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegistroDto> findById(@PathVariable Long id){
		Registro registro = service.findById(id);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/filter", params = {"disciplina"})
	public ResponseEntity<List<RegistroDto>> findByDisciplina(@RequestParam("disciplina") String disciplina){
		List<Registro> registros = service.findByDisciplina(disciplina);
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter", params = {"data"})
	public ResponseEntity<List<RegistroDto>> findByData(@RequestParam("data") 
				@DateTimeFormat(iso = ISO.DATE_TIME , pattern = "dd/MM/yyyy") LocalDate data){
		List<Registro> registros = service.findByData(data);
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter", params = {"inicio", "fim"})
	public ResponseEntity<List<RegistroDto>> findBetweenData(@DateTimeFormat(iso = ISO.DATE_TIME , pattern = "dd/MM/yyyy") 
								@RequestParam("inicio") LocalDate inicio, @RequestParam("fim") LocalDate fim){ 
		List<Registro> registros = service.findBetweenData(inicio, fim);
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter", params = {"periodo"})
	public ResponseEntity<List<RegistroDto>> findByPeriodo(@RequestParam("periodo") String periodo){
		List<Registro> registros = service.findByPeriodo(periodo);
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	@CacheEvict(value = "ListaRegistros", allEntries = true)
	public ResponseEntity<RegistroDto> save(@RequestBody @Valid Registro obj){
		Registro registro = service.save(obj);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "ListaRegistros", allEntries = true)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "ListaRegistros", allEntries = true)
	public ResponseEntity<RegistroDto> update(@RequestBody @Valid Registro obj, @PathVariable Long id){
		Registro registro = service.update(obj, id);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.OK);
	}
	
}
