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
import modelo.Usuario;
import utils.DBConexion;

public class ManagerJugador implements ManagerInterface<Usuario> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	ResultSet registro3;
	Metodos m = new Metodos();

	@Override
	public ArrayList<Usuario> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		// Selecciona todos los jugadores de la base de datos
		ArrayList<Usuario> jugadores = new ArrayList<Usuario>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next() == true) {

				String nombre = registro.getString("user_name");
				String login = registro.getString("user_login");
				String passw = registro.getString("user_pass");
				Pokemon[] equipo = new Pokemon[6];
				
				registro2 = comando.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ="+login+";");
				
				while (registro2.next() == true) {
					Pokemon p1 = m.conseguirPokemon(registro2.getInt("poke_id1"));
					Pokemon p2 = m.conseguirPokemon(registro2.getInt("poke_id2"));
					Pokemon p3 = m.conseguirPokemon(registro2.getInt("poke_id3"));
					Pokemon p4 = m.conseguirPokemon(registro2.getInt("poke_id4"));
					Pokemon p5 = m.conseguirPokemon(registro2.getInt("poke_id5"));
					Pokemon p6 = m.conseguirPokemon(registro2.getInt("poke_id6"));
					
					equipo[0] = p1;
					equipo[1] = p2;
					equipo[2] = p3;
					equipo[3] = p4;
					equipo[4] = p5;
					equipo[5] = p6;
				
				}
				
				registro3 = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ="+login+";");
				
				MiPc pc = null;
				while (registro3.next() == true) {
					
					pc = m.conseguirPc(registro3.getInt("pc_id"));
				}
				
				
				
				
				
				Usuario user = new Jugador(nombre, login, passw, equipo, pc);
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
	public void insert(Usuario user) throws SQLException, Exception {
		// TODO Auto-generated method stub	
		
		conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
		comando = conexion.createStatement();
		registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");
	}

	@Override
	public void update(Usuario user) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Usuario user) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
