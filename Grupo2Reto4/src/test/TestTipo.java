package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Tipo;

class TestTipo {

	@Test
	void testTipo() {
		Tipo planta = new Tipo(3,"Planta");
		assertEquals(planta.getId(),3);
		assertEquals(planta.getNombre_tipo(),"Planta");
	}

}
