package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import excepciones.NotFoundException;
import manager.ManagerRegion;
import modelo.Region;
import utils.DBConexion;


class ManagerRegionTest {
	
	ManagerRegion mr = new ManagerRegion();
	
	Connection conexion;
	Statement comando;
	ResultSet registro;

	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Region> regiones = mr.selectAll();
		assertEquals(regiones.get(0).getId(), 1);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Region region = new Region(7, "Prueba");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + region.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mr.insert(region);
		
		ArrayList<Region> regiones = new ArrayList<Region>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_REGION + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Region r = new Region(id, nombre);
				regiones.add(r);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(regiones.get(region.getId()-2).getNombre(), "Prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Region region1 = new Region(7, "Prueba");
		Region region2 = new Region(8, "Prueba");
		mr.update(region1, region2);
		
		ArrayList<Region> regiones =mr.selectAll();
		assertEquals(regiones.get(region1.getId()-1).getId(), 8);
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + region2.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}
	
	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		Region region = new Region(7, "Prueba");
		mr.insert(region);
		ArrayList<Region> regiones = mr.selectAll();
		assertEquals(regiones.size(), 7);
		mr.delete(region);
		regiones = mr.selectAll();
		assertEquals(regiones.size(), 6);
	}

}
