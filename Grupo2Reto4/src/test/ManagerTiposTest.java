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
		Tipo tipo = new Tipo(18, "Hada");

		try {
			mt.insert(tipo);
		}catch(Exception e) {
			System.out.println("No se puede insertar nuevos tipos en la tabla porque es de tipo enum.");
		}
	
	}

	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		Tipo tipo1 = new Tipo(18, "Jorge");
		Tipo tipo2 = new Tipo(18, "Hada");
		mt.update(tipo1, tipo2);

		ArrayList<Tipo> tipos = mt.selectAll();

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
		
		
		assertEquals(tipos.get(tipo1.getId() - 1).getNombre_tipo(), "Hada");

	}

	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		Tipo tipo = new Tipo(18, "Hada");
		try {
		mt.delete(tipo);
		}catch(Exception e) {
			System.out.println("No se puede borrar un tipo porque actua como FK en otras tablas");
		}
		
	}

}
