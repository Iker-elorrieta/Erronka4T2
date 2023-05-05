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
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		ManagerPokemon mp = new ManagerPokemon();
		pokemon = mp.selectAll();
		ManagerPC mpc = new ManagerPC();
		ArrayList<MiPc> pcs = mpc.selectAll();
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next()) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String passw = registro.getString(3);
				boolean ban = registro.getBoolean(5);
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ='" + login + "';");

				while (registro2.next()) {
					Pokemon p1 = pokemon.get(registro2.getInt("poke_id1") - 1);

					Pokemon p2 = null;
					if ((Integer) registro2.getInt("poke_id2") != 0)
						p2 = pokemon.get(registro2.getInt("poke_id2") - 1);

					Pokemon p3 = null;
					if ((Integer) registro2.getInt("poke_id3") != 0)
						p3 = pokemon.get(registro2.getInt("poke_id3") - 1);

					Pokemon p4 = null;
					if ((Integer) registro2.getInt("poke_id4") != 0)
						p4 = pokemon.get(registro2.getInt("poke_id4") - 1);

					Pokemon p5 = null;
					if ((Integer) registro2.getInt("poke_id5") != 0)
						p5 = pokemon.get(registro2.getInt("poke_id5") - 1);

					Pokemon p6 = null;
					if ((Integer) registro2.getInt("poke_id6") != 0)
						p6 = pokemon.get(registro2.getInt("poke_id6") - 1);

					equipo.add(p1);
					equipo.add(p2);
					equipo.add(p3);
					equipo.add(p4);
					equipo.add(p5);
					equipo.add(p6);

				}

				Statement comando3 = conexion.createStatement();
				registro3 = comando3
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ='" + login + "';");

				MiPc pc = null;
				while (registro3.next() == true) {
					for (MiPc mipc : pcs) {
						if (mipc.getId_pc() == registro3.getInt("pc_id"))
							pc = mipc;
					}
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc, ban);
				jugadores.add(user);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}

		return jugadores;
	}

	@Override
	public void insert(Jugador user) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_USERS + "(user_login, user_name, user_pass) values ('"
					+ user.getLogin() + "', '" + user.getNombre() + "', '" + user.getPass() + "');");

			comando.executeUpdate(
					"call cargarEquipo (" + user.getEquipo().get(0).getId() + ", '" + user.getLogin() + "');");

		} finally {
			if (conexion != null)
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
					+ "', user_name='" + user_new.getNombre() + "', user_pass='" + user_new.getPass() + "', baneado=" + user_new.isBan()
					+ " where user_login='" + user_old.getLogin() + "';");

			comando.executeUpdate("update " + DBConexion.T_EQUIPOS + " set user_login = '" + user_new.getLogin()
					+ "' where user_login='" + user_old.getLogin() + "';");

			for (int i = 0; i < user_new.getEquipo().size(); i++) {

				comando.executeUpdate("update " + DBConexion.T_EQUIPOS + " set poke_id" + (i + 1) + " = "
						+ user_new.getEquipo().get(i).getId() + " where user_login = '" + user_new.getLogin() + "';");
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void delete(Jugador user) throws SQLException, Exception, NotFoundException {
		// TODO Auto-generated method stub
		if (user == null)
			throw new NotFoundException("El contenedor jugador esta vacio.");

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);

			comando = conexion.createStatement();

			comando.executeUpdate(
					"delete from " + DBConexion.T_USERS + " where user_login ='" + user.getLogin() + "';");

		} finally {
			if (comando == null)
				throw new SQLException("No se ha podido establecer conexion con la base de datos.");
			else if (conexion != null)
				conexion.close();
		}

	}

}
