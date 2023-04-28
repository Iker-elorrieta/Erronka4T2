package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerProfesor;
import modelo.Generacion;
import modelo.Profesor;

class ManagerProfesorTest {
	ManagerProfesor mt = new ManagerProfesor();
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mt.selectAll();
		assertEquals(profesores.get(0).getGen(), Generacion.Kanto);
	}

	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mt.selectAll();
		mt.delete(profesores.get(0));
		assertFalse(profesores.get(0).getGen().equals(Generacion.Kanto));
		Profesor prof = new Profesor("oak","Profesor Oak","KantoKanto", Generacion.Kanto);
		mt.insert(prof);
		profesores = mt.selectAll();
		assertEquals(profesores.get(profesores.size()-1).getGen(), Generacion.Kanto);
	}

	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mt.selectAll();
		Profesor prof2 = new Profesor("ooak","Profesor Oak","KantoKanto", Generacion.Kanto);
		mt.update(profesores.get(0), prof2);
		profesores = mt.selectAll();
		assertEquals(profesores.get(0).getLogin(), "ooak");
		Profesor prof1 = new Profesor("oak","Profesor Oak","KantoKanto", Generacion.Kanto);
		mt.update(profesores.get(0), prof1);
	}

	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mt.selectAll();
		mt.delete(profesores.get(0));
		assertFalse(profesores.get(0).getGen().equals(Generacion.Kanto));
		Profesor prof = new Profesor("oak","Profesor Oak","KantoKanto", Generacion.Kanto);
		mt.insert(prof);
		
	}

}
