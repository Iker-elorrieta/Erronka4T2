package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import excepciones.NotFoundException;
import manager.ManagerProfesor;
import modelo.Profesor;
import modelo.Region;
import utils.DBConexion;

class ManagerProfesorTest {
	
	ManagerProfesor mp = new ManagerProfesor();
	Metodos m = new Metodos();
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mp.selectAll();
		assertEquals(profesores.get(0).getReg().getId(), 3);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Region r = new Region(7,"Prueba");
		Profesor prof = new Profesor("Profesor Prueba","prueba","PruebaPrueba", r);
		
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_PROFS + " where prof_login ='" + prof.getLogin() + "';");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + r.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"Insert into " + DBConexion.T_REGION + " values (" + r.getId() + ", '" + r.getNombre() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mp.insert(prof);
		
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_PROFS + ";");

			while (registro.next() == true) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String pass = registro.getString(3);
				Region reg = m.conseguirRegion(registro.getInt(4));

				Profesor p = new Profesor(nombre, login, pass, reg);

				profesores.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(profesores.get(profesores.size()-2).getLogin(), "prueba");
		
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Region r = new Region(7,"Prueba");
		Profesor prof1 = new Profesor("Profesor Prueba","prueba","PruebaPrueba", r);
		Profesor prof2 = new Profesor("Profesor Prueba2","prueba","PruebaPrueba", r);
		
		mp.update(prof1, prof2);
		
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_PROFS + ";");

			while (registro.next() == true) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String pass = registro.getString(3);
				Region reg = m.conseguirRegion(registro.getInt(4));

				Profesor p = new Profesor(nombre, login, pass, reg);

				profesores.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(profesores.get(profesores.size()-2).getNombre(), "Profesor Prueba2");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_PROFS + " where prof_login ='" + prof2.getLogin() + "';");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + r.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}
	
	
	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		Region r = new Region(7,"Prueba");
		Profesor prof = new Profesor("Profesor Prueba","prueba","PruebaPrueba", r);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"Insert into " + DBConexion.T_REGION + " values (" + r.getId() + ", '" + r.getNombre() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_PROFS + " values ('" + prof.getLogin() + "', '"
					+ prof.getNombre() + "','" + prof.getPass() + "','" + prof.getReg().getId() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}

		mp.delete(prof);
		
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_PROFS + ";");

			while (registro.next() == true) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String pass = registro.getString(3);
				Region reg = m.conseguirRegion(registro.getInt(4));

				Profesor p = new Profesor(nombre, login, pass, reg);

				profesores.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		
		assertEquals(profesores.size(), 5);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + r.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}
	
	
	
	
	

	

}
