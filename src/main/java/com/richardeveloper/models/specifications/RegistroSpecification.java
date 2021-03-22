package com.richardeveloper.models.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.richardeveloper.models.Registro;
import com.richardeveloper.resources.exceptions.ResourceNotFoundException;
import com.richardeveloper.services.converter.StringToEnumConverter;

public class RegistroSpecification {

	public static Specification<Registro> disciplina(String disciplina){
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("disciplina"), "%" + disciplina + "%");
	}
	
	public static Specification<Registro> data(LocalDate data){
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("data"), data);	
	}
	
	public static Specification<Registro> betweenData(LocalDate inicio, LocalDate fim){
		return (root, criteriaQuery, criteriaBuilder) ->
		criteriaBuilder.between(root.get("data"), inicio, fim);	
	}

	public static Specification<Registro> periodo(String periodo){
		StringToEnumConverter converter = new StringToEnumConverter();
		try {
			Integer periodoCode = converter.convert(periodo).getCode();
			return (root, criteriaQuery, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("periodo"), periodoCode);
		}
		catch (NullPointerException e) {
			throw new ResourceNotFoundException("Period does not exist or was not entered correctly");
		}
	}
	
}
