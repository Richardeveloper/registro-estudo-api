package com.richardeveloper.models.projections;

import java.time.LocalTime;

public interface RegistroProjection {
	
	public Long getId();
	public String getDisciplina();
	public LocalTime getDuracao();
	
}
