package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.NotFoundException;
import modelo.Generacion;
import modelo.Profesor;
import utils.DBConexion;

public class ManagerProfesor implements ManagerInterface<Profesor>{
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	@Override
	public ArrayList<Profesor> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_PROFS + ";");

			while (registro.next() == true) {
				
				String login = registro.getString(0);
				String nombre = registro.getString(1);
				String pass = registro.getString(2);
				Generacion g = Generacion.valueOf(registro.getString(3));
				
				Profesor p = new Profesor(login, nombre, pass, g);
				
				profesores.add(p);
			}

		} finally {
			registro.close();
			comando.close();
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

			comando.executeUpdate("Insert into "+DBConexion.T_PROFS+" values ('" +p.getLogin()+ "', '"+p.getNombre()+"','"+p.getPass()+"','"+p.getGen()+"');");


		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}
		
		
	}

	@Override
	public void update(Profesor p_old, Profesor p_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Profesor p) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from "+DBConexion.T_PROFS+" where prof_login ='"+p.getLogin()+"';");
		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}
	}
	

}
