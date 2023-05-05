package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerTipos;
import modelo.Tipo;

class ManagerTiposTest {
	ManagerTipos mt = new ManagerTipos();
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Tipo> tipos = mt.selectAll();
		assertEquals(tipos.get(0).getId(), 1);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Tipo tipo = new Tipo(19, "Prueba");
		mt.delete(tipo);
		mt.insert(tipo);
		ArrayList<Tipo> tipos=mt.selectAll();
		assertEquals(tipos.get(tipo.getId()-1).getNombre_tipo(), "Prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Tipo tipo1 = new Tipo(19, "Prueba");
		Tipo tipo2 = new Tipo(20, "Prueba");
		mt.update(tipo1, tipo2);
		
		ArrayList<Tipo> tipos=mt.selectAll();
		assertEquals(tipos.get(tipo1.getId()-1).getNombre_tipo(), "Prueba");
		mt.delete(tipo2);
	}
	
	

	

}
