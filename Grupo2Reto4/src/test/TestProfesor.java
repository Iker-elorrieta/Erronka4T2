package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Generacion;
import modelo.Profesor;

class TestProfesor {

	@Test
	void testProfesor() {
		Profesor profesor = new Profesor("Profesor Oak", "Oak", "Kanto", Generacion.Kanto);
		assertTrue(profesor.Verificar(profesor));
		assertEquals(profesor.getLogin(),"Oak");
		assertEquals(profesor.getPass(), "Kanto");
		assertEquals(profesor.getNombre(),"Profesor Oak");
		assertEquals(profesor.getGen(),Generacion.Kanto);
	}

}
