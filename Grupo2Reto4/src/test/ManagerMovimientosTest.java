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
import manager.ManagerMovimientos;
import manager.ManagerTipos;
import modelo.Movimiento;
import modelo.Tipo;
import utils.DBConexion;

class ManagerMovimientosTest {
	ManagerMovimientos mm = new ManagerMovimientos();
	ManagerTipos mt = new ManagerTipos();
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Movimiento> movs = mm.selectAll();
		assertEquals(movs.get(0).getId(), 3);
	}
	
	@Test
	void testInsert() throws NotFoundException, SQLException, Exception {
		Tipo t = new Tipo(10,"Fuego");
		Movimiento mov1 = new Movimiento(1, "Placaje2", 1, 0,t , 0);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_MOVS + " where mov_id =" + mov1.getId() + ";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		
		mm.insert(mov1);
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo tipot = new Tipo(id, nombre);
				tipos.add(tipot);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo tipo = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, tipo, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(movimientos.get(mov1.getId()-1).getNombre(), "Placaje2");
		
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Tipo t = new Tipo(10,"Fuego");
		Movimiento mov1 = new Movimiento(1, "Placaje2", 1, 0,t , 0);
		Movimiento mov2 = new Movimiento(1, "Placaje3", 1, 0,t , 0);
		mm.update(mov1, mov2);
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo tipot = new Tipo(id, nombre);
				tipos.add(tipot);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo tipo = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, tipo, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(movimientos.get(mov1.getId()-1).getNombre(), "Placaje3");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_MOVS + " where mov_id =" + mov2.getId() + ";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}
	
	
	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		Tipo t = new Tipo(10,"Fuego");
		Movimiento mov1 = new Movimiento(1, "Placaje2", 1, 0,t , 0);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_MOVS + " values (" + mov1.getId() + ",'" + mov1.getNombre()
					+ "'," + mov1.getTipo().getId() + "," + mov1.getPotencia() + "," + mov1.getPuntosPoder() + ","
					+ mov1.getPrecision() + ");");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mm.delete(mov1);
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo tipot = new Tipo(id, nombre);
				tipos.add(tipot);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo tipo = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, tipo, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(movimientos.size(), 619);
		
		
	}

	
}
