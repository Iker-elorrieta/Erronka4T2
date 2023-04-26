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
		mov1.setPotencia(50);
		
		assertEquals(mov1.getId(),1);
		assertEquals(mov1.getNombre(),"Latigo cepa");
		assertEquals(mov1.getPrecision(),100);
		assertEquals(mov1.getPuntosPoder(),40);
		assertEquals(mov1.getTipo(),planta);
		assertEquals(mov1.getPotencia(),50);
		
		Movimiento mov2 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		Movimiento mov3 = null;
		mov2.setId(1);
		mov2.setNombre("Latigo cepa");
		mov2.setPrecision(100);
		mov2.setPuntosPoder(40);
		mov2.setTipo(planta);
		mov2.setPotencia(50);
		
		assertEquals(mov1.toString(), mov2.toString());
		assertEquals(mov1.hashCode(), mov2.hashCode());
		
		mov1.equals(mov3);
		mov1.equals(mov2);
		mov1.equals(planta);
		mov2 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		mov3=mov1;
		mov1.equals(mov2);
		mov1.equals(mov3);
	}

}
