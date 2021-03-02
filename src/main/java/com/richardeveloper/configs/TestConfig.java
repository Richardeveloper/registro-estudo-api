package com.richardeveloper.configs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.richardeveloper.enums.Periodo;
import com.richardeveloper.models.Registro;
import com.richardeveloper.repository.RegistroRepository;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private RegistroRepository registroRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Registro r1 = new Registro("Curso TypeScript Parte 1",
				LocalDate.parse("2021-02-13"),
				LocalTime.of(9, 17),
				LocalTime.of(12, 49),
				Periodo.MATUTINO);
		
		Registro r2 = new Registro("Curso Java Exceções",
				LocalDate.parse("2021-02-13"),
				LocalTime.of(13, 01),
				LocalTime.of(17, 33),
				Periodo.VESPERTINO);
		
		Registro r3 = new Registro("Curso TypeScript Parte 1",
				LocalDate.parse("2021-02-14"),
				LocalTime.of(7, 13),
				LocalTime.of(11, 22),
				Periodo.MATUTINO);
		
		Registro r4 = new Registro("Curso Java Exceções",
				LocalDate.parse("2021-02-14"),
				LocalTime.of(12, 11),
				LocalTime.of(16, 58),
				Periodo.VESPERTINO);
		
		Registro r5 = new Registro("Curso TypeScript Parte 2",
				LocalDate.parse("2021-02-15"),
				LocalTime.of(8, 02),
				LocalTime.of(11, 01),
				Periodo.MATUTINO);
		
		Registro r6 = new Registro("Desenvolvimento Api Rest Java | Angular",
				LocalDate.parse("2021-02-15"),
				LocalTime.of(13, 00),
				LocalTime.of(16, 12),
				Periodo.VESPERTINO);
		
		Registro r7 = new Registro("Curso TypeScript Parte 2",
				LocalDate.parse("2021-02-16"),
				LocalTime.of(8, 23),
				LocalTime.of(10, 25),
				Periodo.MATUTINO);
		
		Registro r8 = new Registro("Desenvolvimento Api Rest Java | Angular",
				LocalDate.parse("2021-02-16"),
				LocalTime.of(13, 11),
				LocalTime.of(17, 34),
				Periodo.VESPERTINO);
		
		Registro r9 = new Registro("Curso Angular Fundamentos",
				LocalDate.parse("2021-02-17"),
				LocalTime.of(8, 47),
				LocalTime.of(12, 15),
				Periodo.MATUTINO);
		
		Registro r10 = new Registro("Curso Spring Data JPA",
				LocalDate.parse("2021-02-17"),
				LocalTime.of(14, 02),
				LocalTime.of(18, 55),
				Periodo.VESPERTINO);
		
		Registro r11 = new Registro("Curso Angular Fundamentos",
				LocalDate.parse("2021-02-18"),
				LocalTime.of(8, 25),
				LocalTime.of(13, 45),
				Periodo.MATUTINO);
		
		Registro r12 = new Registro("Curso Spring Data JPA",
				LocalDate.parse("2021-02-18"),
				LocalTime.of(14, 11),
				LocalTime.of(17, 33),
				Periodo.VESPERTINO);
		
		
		registroRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12));
	}

}
