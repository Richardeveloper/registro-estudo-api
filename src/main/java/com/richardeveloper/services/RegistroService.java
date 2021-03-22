package com.richardeveloper.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.richardeveloper.models.Registro;
import com.richardeveloper.models.specifications.RegistroSpecification;
import com.richardeveloper.repository.RegistroRepository;
import com.richardeveloper.resources.exceptions.ResourceNotFoundException;

@Service
public class RegistroService {

//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
	
	@Autowired
	private RegistroRepository repository;
	
	public List<Registro> findAll(){
		List<Registro> registros = repository.findAll();
		return registros;
	}

	public Page<Registro> findAllPagination(int page, int size) {
		Pageable pagination = PageRequest.of(page, size, Sort.by("data"));
		Page<Registro> registros = repository.findAll(pagination);
		return registros;
	}	
	
	public Registro findById(Long id) {
		Optional<Registro> registro = repository.findById(id);
		return registro.orElseThrow( () -> new ResourceNotFoundException("Registro não existe."));
	}
	
	public List<Registro> findByDisciplina(String disciplina){
		List<Registro> registros = repository.findAll(RegistroSpecification.disciplina(disciplina));
		return registros;
	}
	
	public List<Registro> findByData(LocalDate data){
		List<Registro> registros = repository.findAll(RegistroSpecification.data(data));
		return registros;
	}
	
	public List<Registro> findBetweenData(LocalDate inicio, LocalDate fim){
		List<Registro> registros = repository.findAll(RegistroSpecification.betweenData(inicio, fim));
		return registros;
	}
	
	public List<Registro> findByPeriodo(String periodo){
		List<Registro> registros = repository.findAll(RegistroSpecification.periodo(periodo));
		return registros;
	}

	public Registro save(Registro registro) {
		registro.duration();
		return repository.save(registro);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Registro update(Registro obj, Long id) {
		return repository.findById(id)
				.map( (registro) -> {
					updateData(registro, obj);
					return save(registro);
			}).orElseThrow( () -> new ResourceNotFoundException("ID não existe."));
	}
	
	private void updateData(Registro registro, Registro obj) {
		registro.setDisciplina(obj.getDisciplina());
		registro.setData(obj.getData());
		registro.setInicio(obj.getInicio());
		registro.setFim(obj.getFim());
		registro.setPeriodo(obj.getPeriodo());
	}

	
}
