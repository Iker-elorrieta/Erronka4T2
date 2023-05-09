package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Profesor;
import modelo.Region;
import utils.DBConexion;

public class ManagerProfesor implements ManagerInterface<Profesor> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos m = new Metodos();

	@Override
	public ArrayList<Profesor> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub

		ArrayList<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_PROFS + " order by prof_gen;");

			while (registro.next() == true) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String pass = registro.getString(3);
				Region r = m.conseguirRegion(registro.getInt(4));

				Profesor p = new Profesor(nombre, login, pass, r);

				profesores.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}

		if (profesores.size() == 0)
			throw new NotFoundException("No hay profesores.");

		return profesores;

	}

	@Override
	public void insert(Profesor p) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_PROFS + " values ('" + p.getLogin() + "', '"
					+ p.getNombre() + "','" + p.getPass() + "','" + p.getReg().getId() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}

	}

	@Override
	public void update(Profesor p_old, Profesor p_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("update " + DBConexion.T_PROFS + " set prof_login='" + p_new.getLogin()
					+ "', prof_name='" + p_new.getNombre() + "', prof_pass='" + p_new.getPass() + "', prof_gen='"
					+ p_new.getReg().getId() + "' where prof_login='" + p_old.getLogin() + "';");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void delete(Profesor p) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_PROFS + " where prof_login ='" + p.getLogin() + "';");
		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

}
