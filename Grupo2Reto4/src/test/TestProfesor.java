package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Movimiento;
import modelo.Profesor;
import modelo.Region;

class TestProfesor {
	
	Region reg = new Region(1,"Kanto");
	Region reg2 = new Region(2,"Johto");
	Profesor profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", null);
	Profesor profesor2 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
	Movimiento mov1 = new Movimiento(0, "Placaje", 0, 0, null, 0);
	@Test
	void testProfesorConstructor() {
		
		assertFalse(profesor1.Validar());
		profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
		assertTrue(profesor1.Validar());
		assertEquals(profesor1.getLogin(),"Oak");
		assertEquals(profesor1.getPass(), "Kanto");
		assertEquals(profesor1.getNombre(),"Profesor Oak");
		assertEquals(profesor1.getReg(),reg);
		
	}
	
	@Test
	void testProfesorToString() {
		Profesor profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
		Profesor profesor2 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
		assertEquals(profesor1.toString(), profesor2.toString());
	}
	
	@Test
	void testProfesorEquals() {
		profesor1.equals(profesor2);
		profesor2=profesor1;
		profesor1.equals(profesor2);
		Profesor profesor2 = new Profesor("Profesor Oak", "Oak", "Kanto", null);
		profesor1.equals(profesor2);
		Profesor profesor3 = null;
		profesor1.equals(profesor3);
		profesor3 = new Profesor("Profesor Elm", "Elm", "Jhoto", reg2);
		profesor1.equals(profesor3);

		profesor1.equals(mov1);
	}
	
	@Test
	void testProfesorHashCode() {
		Profesor profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
		Profesor profesor2 = new Profesor("Profesor Oak", "Oak", "Kanto", reg);
		assertEquals(profesor1.hashCode(), profesor2.hashCode());
	}
	
}
