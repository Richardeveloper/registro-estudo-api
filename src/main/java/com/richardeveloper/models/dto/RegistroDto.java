package com.richardeveloper.models.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.richardeveloper.enums.Periodo;
import com.richardeveloper.models.Registro;

public class RegistroDto {
	private Long id;
	private String disciplina;
	private LocalDate data;
	private LocalTime inicio;
	private LocalTime fim;
	private LocalTime duracao;
	private Periodo periodo;
	
	public RegistroDto() {

	}
	
	public RegistroDto(Registro registro) {
		this.id = registro.getId();
		this.disciplina = registro.getDisciplina();
		this.data = registro.getData();
		this.inicio = registro.getInicio();
		this.fim = registro.getFim();
		this.duracao = registro.getDuracao();
		this.periodo = registro.getPeriodo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}

	public LocalTime getFim() {
		return fim;
	}

	public void setFim(LocalTime fim) {
		this.fim = fim;
	}

	public LocalTime getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	
}
