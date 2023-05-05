package test;

import static org.junit.Assert.assertEquals;


import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerRegion;
import modelo.Region;


class ManagerRegionTest {
	
	ManagerRegion mr = new ManagerRegion();

	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Region> regiones = mr.selectAll();
		assertEquals(regiones.get(0).getId(), 1);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Region region = new Region(7, "Prueba");
		mr.delete(region);
		mr.insert(region);
		ArrayList<Region> regiones =mr.selectAll();
		assertEquals(regiones.get(region.getId()-1).getNombre(), "Prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Region region1 = new Region(7, "Prueba");
		Region region2 = new Region(8, "Prueba");
		mr.update(region1, region2);
		
		ArrayList<Region> regiones =mr.selectAll();
		assertEquals(regiones.get(region1.getId()-1).getNombre(), "Prueba");
		mr.delete(region2);
	}

}
