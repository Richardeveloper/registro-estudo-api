package com.richardeveloper.models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.richardeveloper.enums.Periodo;

@Entity
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@NotNull(message = "Disciplina não pode ser um campo vazio")
	private String disciplina;

	@NotNull(message = "Data não pode ser um campo vazio")
	private LocalDate data;
	
	@NotNull(message = "Início não pode ser um campo vazio")
	private LocalTime inicio;
	
	@NotNull(message = "Fim não pode ser um campo vazio")
	private LocalTime fim;

	private LocalTime duracao;
	
	@NotNull(message = "Período não pode ser um campo vazio")
	private Integer periodo;
	
	public Registro() {
	
	}

	public Registro(String disciplina, LocalDate data, LocalTime inicio, LocalTime fim, Periodo periodo) {
		this.disciplina = disciplina;
		this.data = data;
		this.inicio = inicio;
		this.fim = fim;
		this.duration();
		this.setPeriodo(periodo);
	}

	public Long getId() {
		return this.id;
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

	public Periodo getPeriodo() {
		return Periodo.getPeriodo(this.periodo);
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo.getCode();
	}

	public LocalTime getDuracao() {
		return duracao;
	}

	public void duration() {
		Long duration = Duration.between(this.getInicio(), this.getFim()).toSeconds();
		this.duracao = LocalTime.ofSecondOfDay(duration);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Registro [disciplina=" + disciplina + ", data=" + data + ", inicio=" + inicio + ", fim=" + fim
				+ ", duracao=" + duracao + ", periodo=" + periodo + "]";
	}	
}

