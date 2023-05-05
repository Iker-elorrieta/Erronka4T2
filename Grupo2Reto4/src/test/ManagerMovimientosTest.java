package test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerMovimientos;
import modelo.Movimiento;
import modelo.Tipo;

class ManagerMovimientosTest {
	ManagerMovimientos mm = new ManagerMovimientos();
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Movimiento> movs = mm.selectAll();
		assertEquals(movs.get(0).getId(), 3);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Tipo t = new Tipo(10,"Fuego");
		Movimiento mov1 = new Movimiento(1, "Placaje2", 1, 0,t , 0);
		mm.delete(mov1);
		mm.insert(mov1);
		ArrayList<Movimiento> mosv = mm.selectAll();
		assertEquals(mosv.get(mov1.getId()-1).getNombre(), "Placaje2");
		
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Tipo t = new Tipo(10,"Fuego");
		Movimiento mov1 = new Movimiento(1, "Placaje2", 1, 0,t , 0);
		Movimiento mov2 = new Movimiento(1, "Placaje3", 1, 0,t , 0);
		mm.update(mov1, mov2);
		ArrayList<Movimiento> movs = mm.selectAll();
		assertEquals(movs.get(mov1.getId()-1).getNombre(), "Placaje3");
		mm.delete(mov2);
	}

	
}
