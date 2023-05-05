package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerProfesor;
import manager.ManagerRegion;
import modelo.Profesor;
import modelo.Region;

class ManagerProfesorTest {
	
	ManagerProfesor mp = new ManagerProfesor();
	ManagerRegion mr = new ManagerRegion();
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mp.selectAll();
		assertEquals(profesores.get(0).getReg().getId(), 3);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Region r = new Region(7,"Prueba");
		Profesor prof = new Profesor("Profesor Prueba","prueba","PruebaPrueba", r);
		
		mp.delete(prof);
		mr.delete(r);
			
		mr.insert(r);
		mp.insert(prof);
		
		ArrayList<Profesor> profesores = mp.selectAll();
		assertEquals(profesores.get(profesores.size()-2).getLogin(), "prueba");
		
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Region r = new Region(7,"Prueba");
		Profesor prof1 = new Profesor("Profesor Prueba","prueba","PruebaPrueba", r);
		Profesor prof2 = new Profesor("Profesor Prueba2","prueba","PruebaPrueba", r);
		
		mp.update(prof1, prof2);
		
		ArrayList<Profesor> profesores = mp.selectAll();
		assertEquals(profesores.get(profesores.size()-2).getNombre(), "Profesor Prueba2");
		mp.delete(prof2);
	}
	
	
	
	
	
	

	

}
