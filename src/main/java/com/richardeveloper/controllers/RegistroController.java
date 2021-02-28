package com.richardeveloper.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richardeveloper.models.Registro;
import com.richardeveloper.services.RegistroService;

@RestController
@RequestMapping(value = "/registros")
public class RegistroController {

	@Autowired
	private RegistroService service;
	
	@GetMapping
	public ResponseEntity<List<Registro>> findAll(){
		List<Registro> registros = service.findAll();
		return new ResponseEntity<List<Registro>>(registros, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Registro> findById(@PathVariable Long id){
		Registro registro = service.findById(id);
		return new ResponseEntity<Registro>(registro, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Registro> save(@RequestBody @Valid Registro obj){
		Registro registro = service.save(obj);
		return new ResponseEntity<Registro>(registro, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Registro> update(@RequestBody @Valid Registro obj, @PathVariable Long id){
		Registro registro = service.update(obj, id);
		return new ResponseEntity<Registro>(registro, HttpStatus.OK);
	}
	
}
