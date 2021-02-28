package com.richardeveloper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardeveloper.models.Registro;
import com.richardeveloper.repository.RegistroRepository;
import com.richardeveloper.resources.exceptions.ResourceNotFoundException;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository repository;
	
	public List<Registro> findAll(){
		List<Registro> registros = repository.findAll();
		return registros;
	}
	
	public Registro findById(Long id) {
		Optional<Registro> registro = repository.findById(id);
		return registro.orElseThrow( () -> new ResourceNotFoundException("Registro não existe."));
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
