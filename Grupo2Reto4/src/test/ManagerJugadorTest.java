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
import manager.ManagerJugador;
import modelo.Jugador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import utils.DBConexion;

class ManagerJugadorTest {
	
	ManagerJugador mj = new ManagerJugador();
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	Region r = new Region(1,"Kanto");
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Tipo[] tipos = { planta, veneno };
	
	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	Movimiento move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);
	

	Pokemon pokemon = new Pokemon(1, "Bulbasaur", tipos, 45, 49, 49, 45, 65, 65, moveset, r);
	
	
	ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
	
	
	Jugador j = new Jugador("Igor","aaaaa","123", equipo, null, false);
	
	@Test
	void testSelectAll() throws SQLException, Exception {
		
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		
		equipo.add(pokemon);
		
		equipo.add(pokemon);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_USERS + "(user_login, user_name, user_pass) values ('"
					+ j.getLogin() + "', '" + j.getNombre() + "', '" + j.getPass() + "');");

			comando.executeUpdate(
					"call cargarEquipo (" + j.getEquipo().get(0).getId() + ", '" + j.getLogin() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		ArrayList<Jugador> js = mj.selectAll();
		assertEquals(js.get(0).getLogin(), "aaaaa");
		
		if (j == null)
			throw new NotFoundException("El contenedor jugador esta vacio.");

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);

			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_USERS + " where user_login ='" + j.getLogin() + "';");

		} finally {
			if (comando == null)
				throw new SQLException("No se ha podido establecer conexion con la base de datos.");
			else if (conexion != null)
				conexion.close();
		}
		
		
	}
	
	@Test
	void testInsert() throws SQLException, Exception {
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		
		equipo.add(pokemon);
		
		equipo.add(pokemon);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);

			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_USERS + " where user_login ='" + j.getLogin() + "';");

		} finally {
			if (comando == null)
				throw new SQLException("No se ha podido establecer conexion con la base de datos.");
			else if (conexion != null)
				conexion.close();
		}
		
		mj.insert(j);
		
		
	
	}

	

}
