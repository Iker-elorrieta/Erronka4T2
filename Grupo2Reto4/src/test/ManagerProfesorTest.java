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
import manager.ManagerRegion;
import modelo.Profesor;
import modelo.Region;
import utils.DBConexion;

class ManagerProfesorTest {
	ManagerRegion mr = new ManagerRegion();
	ManagerProfesor mp = new ManagerProfesor();
	Metodos m = new Metodos();
	Connection conexion;
	Statement comando;
	ResultSet registro;

	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Profesor> profesores = mp.selectAll();
		assertEquals(profesores.get(0).getReg().getId(), 1);
	}

	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Region r = new Region(6, "Prueba");
		Profesor prof = new Profesor("Profesor Prueba", "prueba", "PruebaPrueba", r);

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_PROFS + " where prof_login ='" + prof.getLogin() + "';");
		} finally {
			if (conexion != null)
				conexion.close();
		}

		
		mr.insert(r);
		mp.insert(prof);

		ArrayList<Profesor> profesores = mp.selectAll();

		assertEquals(profesores.get(profesores.size() - 1).getLogin(), "prueba");

	}

	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Region r = new Region(6, "Prueba");
		Profesor prof1 = new Profesor("Profesor Prueba", "prueba", "PruebaPrueba", r);
		Profesor prof2 = new Profesor("Profesor Prueba2", "prueba", "PruebaPrueba", r);

		mp.update(prof1, prof2);

		ArrayList<Profesor> profesores = mp.selectAll();

		assertEquals(profesores.get(profesores.size() - 1).getNombre(), "Profesor Prueba2");

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_PROFS + " where prof_login ='" + prof2.getLogin() + "';");
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
		Region r = new Region(6, "Prueba");
		Profesor prof = new Profesor("Profesor Prueba", "prueba", "PruebaPrueba", r);

		mr.insert(r);
		mp.insert(prof);

		ArrayList<Profesor> profesores = mp.selectAll();

		assertEquals(profesores.size(), 6);

		mp.delete(prof);

		profesores = mp.selectAll();

		assertEquals(profesores.size(), 5);

		mr.delete(r);

	}

}
