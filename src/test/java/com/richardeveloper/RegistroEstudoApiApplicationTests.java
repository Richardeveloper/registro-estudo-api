package com.richardeveloper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.richardeveloper.enums.Periodo;
import com.richardeveloper.models.Registro;

@SpringBootTest
class RegistroEstudoApiApplicationTests {

	@Test
	void testCalculcationDuration() {
		
		Registro registro = new Registro("Java",
										LocalDate.parse("2021-03-13"),
										LocalTime.parse("08:30"),
										LocalTime.parse("11:30"),
										Periodo.MATUTINO
										);
		
		assertEquals(LocalTime.parse("03:00"), registro.getDuracao());
		
	}

}
