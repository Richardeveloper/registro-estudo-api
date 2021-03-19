package com.richardeveloper.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	@GetMapping
	public ResponseEntity<List<RegistroDto>> findAll(){
		List<Registro> registros = service.findAll();
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping(params = {"page", "size"})
	public ResponseEntity<Page<RegistroDto>> findAllPagination(@RequestParam("page") int page, @RequestParam("size") int size){
		Page<Registro> registros = service.findAllPagination(page, size);
		Page<RegistroDto> dto = registros.map(registro -> modelMapper.map(registro, RegistroDto.class));
		return new ResponseEntity<Page<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegistroDto> findById(@PathVariable Long id){
		Registro registro = service.findById(id);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping(params = {"disciplina"})
	public ResponseEntity<List<RegistroDto>> findByDisciplina(@RequestParam("disciplina") String disciplina){
		List<Registro> registros = service.findByDisciplina(disciplina);
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/matutino")
	public ResponseEntity<List<RegistroDto>> findByPeriodoMatutino(){
		List<Registro> registros = service.findByPeriodoMatutino();
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/vespertino")
	public ResponseEntity<List<RegistroDto>> findByPeriodoVespertino(){
		List<Registro> registros = service.findByPeriodoVespertino();
		List<RegistroDto> dto = Arrays.asList(modelMapper.map(registros, RegistroDto[].class));
		return new ResponseEntity<List<RegistroDto>>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RegistroDto> save(@RequestBody @Valid Registro obj){
		Registro registro = service.save(obj);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RegistroDto> update(@RequestBody @Valid Registro obj, @PathVariable Long id){
		Registro registro = service.update(obj, id);
		RegistroDto dto = modelMapper.map(registro, RegistroDto.class);
		return new ResponseEntity<RegistroDto>(dto, HttpStatus.OK);
	}
	
}
