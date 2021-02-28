package com.richardeveloper.resources.errors;

public class InsertValidationError {

	private String field;
	private String error;
	
	public InsertValidationError() {
	
	}

	public InsertValidationError(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
	
}
