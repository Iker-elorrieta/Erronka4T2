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
import manager.ManagerTipos;
import modelo.Tipo;
import utils.DBConexion;

class ManagerTiposTest {
	ManagerTipos mt = new ManagerTipos();
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Tipo> tipos = mt.selectAll();
		assertEquals(tipos.get(0).getId(), 1);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Tipo tipo = new Tipo(19, "Prueba");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_TIPOS + " where type_id=" + tipo.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mt.insert(tipo);
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(tipos.get(tipo.getId()-1).getNombre_tipo(), "Prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Tipo tipo1 = new Tipo(19, "Prueba");
		Tipo tipo2 = new Tipo(20, "Prueba");
		mt.update(tipo1, tipo2);
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		assertEquals(tipos.get(tipo1.getId()-1).getNombre_tipo(), "Prueba");


		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_TIPOS + " where type_id=" + tipo2.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
	}
	
	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		Tipo tipo = new Tipo(19, "Prueba");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"Insert into " + DBConexion.T_TIPOS + " values (" + tipo.getId() + ", '" + tipo.getNombre_tipo() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mt.delete(tipo);
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(tipos.size(), 18);
		
	}
	
	

	

}
