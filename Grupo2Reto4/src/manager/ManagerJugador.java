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

public class ManagerJugador implements ManagerInterface {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos metodos = new Metodos();

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
				Pokemon[] equipo = metodos.selecEquipo(conexion, login, DBConexion.T_EQUIPOS);
				MiPc miPC = metodos.selecMiPC(conexion, login, DBConexion.T_MIPC);
				Usuario user = new Jugador(nombre, login, passw, equipo, miPC);
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
