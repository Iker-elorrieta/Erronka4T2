package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Pokemon;
import utils.DBConexion;

public class ManagerJugador implements ManagerInterface<Jugador> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	ResultSet registro3;
	Metodos m = new Metodos();

	@Override
	public ArrayList<Jugador> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		// Selecciona todos los jugadores de la base de datos
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next() == true) {

				String nombre = registro.getString("user_name");
				String login = registro.getString("user_login");
				String passw = registro.getString("user_pass");
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				registro2 = comando
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login =" + login + ";");

				while (registro2.next() == true) {
					Pokemon p1 = m.conseguirPokemon(registro2.getInt("poke_id1"));
					Pokemon p2 = m.conseguirPokemon(registro2.getInt("poke_id2"));
					Pokemon p3 = m.conseguirPokemon(registro2.getInt("poke_id3"));
					Pokemon p4 = m.conseguirPokemon(registro2.getInt("poke_id4"));
					Pokemon p5 = m.conseguirPokemon(registro2.getInt("poke_id5"));
					Pokemon p6 = m.conseguirPokemon(registro2.getInt("poke_id6"));

					equipo.add(p1);
					equipo.add(p2);
					equipo.add(p3);
					equipo.add(p4);
					equipo.add(p5);
					equipo.add(p6);

				}

				registro3 = comando
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login =" + login + ";");

				MiPc pc = null;
				while (registro3.next() == true) {

					pc = m.conseguirPc(registro3.getInt("pc_id"));
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc);
				jugadores.add(user);
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (jugadores.size() == 0)
			throw new NotFoundException("No hay jugadores.");

		return jugadores;
	}

	@Override
	public void insert(Jugador user) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_USERS + " values ('" + user.getLogin() + "', '"
					+ user.getNombre() + "', '" + user.getPass() + "', " + user.getEquipo().size() + ");");
			comando.executeUpdate("Insert into " + DBConexion.T_EQUIPOS + " values ('" + user.getLogin() + "', '"
					+ user.getEquipo().get(0) + "', '" + user.getEquipo().get(1) + "', '" + user.getEquipo().get(2)
					+ "', '" + user.getEquipo().get(3) + "', '" + user.getEquipo().get(4) + "', '"
					+ user.getEquipo().get(5) + "');");

		} finally {
			
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void update(Jugador user_old, Jugador user_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("update " + DBConexion.T_USERS + " set user_login='" + user_new.getLogin()
					+ "', user_name='" + user_new.getNombre() + "', user_pass='" + user_new.getPass()
					+ " where user_login='" + user_old.getLogin() + "';");

			Integer p2 = null;
			Integer p3 = null;
			Integer p4 = null;
			Integer p5 = null;
			Integer p6 = null;
			if (user_new.getEquipo().get(1) != null)
				p2 = user_new.getEquipo().get(1).getId();
			if (user_new.getEquipo().get(2) != null)
				p3 = user_new.getEquipo().get(2).getId();
			if (user_new.getEquipo().get(3) != null)
				p4 = user_new.getEquipo().get(3).getId();
			if (user_new.getEquipo().get(4) != null)
				p5 = user_new.getEquipo().get(4).getId();
			if (user_new.getEquipo().get(5) != null)
				p6 = user_new.getEquipo().get(5).getId();

			comando.executeUpdate("update " + DBConexion.T_EQUIPOS + " set user_login='" + user_new.getLogin()
					+ "', poke_id1=" + user_new.getEquipo().get(0).getId() + ", poke_id2=" + p2 + ", poke_id3=" + p3
					+ ", poke_id4=" + p4 + ", poke_id5=" + p5 + ", poke_id6=" + p6 + " where user_login='"
					+ user_old.getLogin() + "';");

		} finally {
			
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void delete(Jugador user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_USERS + " where user_login ='" + user.getLogin() + "';");

		} finally {
			comando.close();
			conexion.close();
		}
	}

}
