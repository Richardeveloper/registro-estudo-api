package com.richardeveloper.enums;

import org.springframework.web.client.ResourceAccessException;

public enum Periodo {

	MATUTINO(0),
	VESPERTINO(1),
	NOTURNO(2);
	
	private int code;
	
	private Periodo(int value) {
		this.code = value;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Periodo getPeriodo(int value) {
		for (Periodo item : Periodo.values()) {
			if(item.getCode() == value) {
				return item;
			}
		}
		throw new ResourceAccessException("Period does not exists");
	}
	
}
