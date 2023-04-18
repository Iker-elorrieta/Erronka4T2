package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Movimiento;
import Modelo.Tipo;

class TestMovimiento {

	@Test
	void testMovimiento() {
		Movimiento mov1 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		mov1.setId(1);
		mov1.setNombre("Latigo cepa");
		mov1.setPrecision(100);
		mov1.setPuntosPoder(40);
		mov1.setTipo(Tipo.planta);
		assertEquals(mov1.getId(),1);
		assertEquals(mov1.getNombre(),"Latigo cepa");
		assertEquals(mov1.getPrecision(),100);
		assertEquals(mov1.getPuntosPoder(),40);
		assertEquals(mov1.getTipo(),Tipo.planta);
	}

}
