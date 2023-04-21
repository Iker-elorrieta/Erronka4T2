package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Movimiento;
import modelo.Tipo;

class TestMovimiento {

	@Test
	void testMovimiento() {
		Movimiento mov1 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		Tipo planta = new Tipo(3,"Planta");
		mov1.setId(1);
		mov1.setNombre("Latigo cepa");
		mov1.setPrecision(100);
		mov1.setPuntosPoder(40);
		mov1.setTipo(planta);
		
		assertEquals(mov1.getId(),1);
		assertEquals(mov1.getNombre(),"Latigo cepa");
		assertEquals(mov1.getPrecision(),100);
		assertEquals(mov1.getPuntosPoder(),40);
		assertEquals(mov1.getTipo(),planta);
	}

}
