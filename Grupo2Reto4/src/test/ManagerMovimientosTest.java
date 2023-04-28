package test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerMovimientos;
import modelo.Movimiento;

class ManagerMovimientosTest {
	ManagerMovimientos mm = new ManagerMovimientos();
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Movimiento> mosv = mm.selectAll();
		assertEquals(mosv.get(0).getId(), 1);
	}

	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Movimiento mov1 = new Movimiento(0, "Placaje", 0, 0, null, 0);
		mm.insert(mov1);
		ArrayList<Movimiento> mosv = mm.selectAll();
		assertEquals(mosv.get(mosv.size()-1).getNombre(), "Placaje");
	}

	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		ArrayList<Movimiento> mosv = mm.selectAll();
		Movimiento mov1 = new Movimiento(1, "Placaje", 0, 0, null, 0);
		mm.update(mosv.get(0), mov1);
		assertEquals(mosv.get(0).getId(), 1);
	}

	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		ArrayList<Movimiento> mosv = mm.selectAll();
		Movimiento mov = mosv.get(0);
		String nombre = mov.getNombre();
		mm.delete(mov);
		mosv = mm.selectAll();
		assertEquals(mosv.get(0).getNombre(), nombre);
	}

}
