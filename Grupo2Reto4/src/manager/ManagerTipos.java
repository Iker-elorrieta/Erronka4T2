package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.NotFoundException;
import modelo.Tipo;
import utils.DBConexion;

public class ManagerTipos implements ManagerInterface<Tipo> {
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	@Override
	public ArrayList<Tipo> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(0);
				String nombre = registro.getString(1);
				
				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (tipos.size() == 0)
			throw new NotFoundException("No hay tipos.");

		return tipos;
	}

	@Override
	public void insert(Tipo t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tipo t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tipo t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
