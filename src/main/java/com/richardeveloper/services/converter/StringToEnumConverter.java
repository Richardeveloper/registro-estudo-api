package com.richardeveloper.services.converter;


import org.springframework.core.convert.converter.Converter;

import com.richardeveloper.enums.Periodo;

public class StringToEnumConverter implements Converter<String, Periodo>{

	@Override
	public Periodo convert(String source) {
		try {
			return Periodo.valueOf(source.toUpperCase());
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}

}
