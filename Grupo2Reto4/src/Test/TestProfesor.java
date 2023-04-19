package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Generacion;
import Modelo.Profesor;

class TestProfesor {

	@Test
	void testProfesor() {
		Profesor profesor = new Profesor();
		profesor.setLogin("Oak");
		profesor.setPass("Kanto");
		profesor.setUsuario("Profesor Oak");
		profesor.setGen(Generacion.Kanto);
		assertEquals(profesor.getLogin(),"Oak");
		assertEquals(profesor.getPass(), "Kanto");
		assertEquals(profesor.getUsuario(),"Profesor Oak");
		assertEquals(profesor.getGen(),Generacion.Kanto);
	}

}
