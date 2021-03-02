package com.richardeveloper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.richardeveloper.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>{

	@Query("SELECT r FROM Registro r WHERE r.disciplina like %?1%")
	public List<Registro> findByDisciplina(String disciplina);
	
	@Query("SELECT r FROM Registro r WHERE r.periodo = 0")
	public List<Registro> findByPeriodoMatutino();
	
	@Query("SELECT r FROM Registro r WHERE r.periodo = 1")
	public List<Registro> findByPeriodoVespertino();
	
	
}
