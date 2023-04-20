package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Generacion;
import modelo.Profesor;

class TestProfesor {

	@Test
	void testProfesor() {
		Profesor profesor = new Profesor(null, null, "Profesor Oak", null);
		profesor.setLogin("Oak");
		profesor.setPass("Kanto");
		profesor.setGen(Generacion.Kanto);
		assertEquals(profesor.getLogin(),"Oak");
		assertEquals(profesor.getPass(), "Kanto");
		assertEquals(profesor.getNombre(),"Profesor Oak");
		assertEquals(profesor.getGen(),Generacion.Kanto);
	}

}
