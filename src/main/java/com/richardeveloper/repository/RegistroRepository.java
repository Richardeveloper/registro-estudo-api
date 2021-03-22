package com.richardeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.richardeveloper.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>, JpaSpecificationExecutor<Registro>{
	
}
