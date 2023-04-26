package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Generacion;
import modelo.Movimiento;
import modelo.Profesor;
import modelo.Tipo;

class TestProfesor {
	Profesor profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", null);
	Profesor profesor2 = new Profesor("Profesor Oak", "Oak", "Kanto", Generacion.Kanto);
	@Test
	void testProfesorConstructor() {
		
		assertFalse(profesor1.Validar());
		profesor1 = new Profesor("Profesor Oak", "Oak", "Kanto", Generacion.Kanto);
		assertTrue(profesor1.Validar());
		assertEquals(profesor1.getLogin(),"Oak");
		assertEquals(profesor1.getPass(), "Kanto");
		assertEquals(profesor1.getNombre(),"Profesor Oak");
		assertEquals(profesor1.getGen(),Generacion.Kanto);
		
	}
	
	@Test
	void testProfesorToString() {
		assertEquals(profesor1.toString(), profesor2.toString());
	}
	
	@Test
	void testProfesorEquals() {
		profesor1.equals(profesor2);
		profesor2=profesor1;
		profesor1.equals(profesor2);
		Profesor profesor3 = null;
		profesor1.equals(profesor3);
		profesor3 = new Profesor("Profesor Elm", "Elm", "Jhoto", Generacion.Jhoto);
		profesor1.equals(profesor3);
		
		Movimiento mov1 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		Tipo planta = new Tipo(3,"Planta");
		mov1.setId(1);
		mov1.setNombre("Latigo cepa");
		mov1.setPrecision(100);
		mov1.setPuntosPoder(40);
		mov1.setTipo(planta);
		
		profesor1.equals(mov1);
	}
	
	@Test
	void testProfesorHashCode() {
		assertEquals(profesor1.hashCode(), profesor2.hashCode());
	}
	
}
