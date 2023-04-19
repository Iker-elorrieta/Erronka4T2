package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Estilos;
import Modelo.Movimiento;
import Modelo.Tipo;

class TestMovimiento {

	@Test
	void testMovimiento() {
		Movimiento mov1 = new Movimiento(1, "Latigo Cepa", 40, 100, Tipo.planta, 40, Estilos.Phys);
		assertEquals(mov1.getId(), 1);
		assertEquals(mov1.getNombre(), "Latigo cepa");
		assertEquals(mov1.getPrecision(), 100);
		assertEquals(mov1.getPuntosPoder(), 40);
		assertEquals(mov1.getTipo(), Tipo.planta);
		assertEquals(mov1.getPotencia(), 40);
		assertEquals(mov1.getEstilo(), Estilos.Phys);
	}

}
