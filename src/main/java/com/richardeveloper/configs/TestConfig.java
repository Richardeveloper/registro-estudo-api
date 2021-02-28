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
		
		Registro r1 = new Registro("Desenvolvimento Back-End API Rest",
								    LocalDate.parse("2021-02-26"),
								    LocalTime.of(9, 17),
								    LocalTime.of(12, 49),
								    Periodo.MATUTINO);
		
		Registro r2 = new Registro("Desenvolvimento Back-End API Rest",
				LocalDate.parse("2021-02-26"),
				LocalTime.of(13, 01),
				LocalTime.of(17, 33),
				Periodo.VESPERTINO);
		
		Registro r3 = new Registro("Desenvolvimento Front-End API Rest",
				LocalDate.parse("2021-02-27"),
				LocalTime.of(7, 13),
				LocalTime.of(11, 22),
				Periodo.MATUTINO);
		
		Registro r4 = new Registro("Desenvolvimento Front-End API Rest",
				LocalDate.parse("2021-02-27"),
				LocalTime.of(12, 11),
				LocalTime.of(16, 58),
				Periodo.VESPERTINO);
		
		
		registroRepository.saveAll(Arrays.asList(r1, r2, r3, r4));
	}

}
