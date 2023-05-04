package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.NotFoundException;
import modelo.Region;
import modelo.Tipo;
import utils.DBConexion;

public class ManagerRegion implements ManagerInterface<Region>{
	
	Connection conexion;
	Statement comando;
	ResultSet registro;

	@Override
	public ArrayList<Region> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Region> regiones = new ArrayList<Region>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_REGION + ";");

			while (registro.next() == true) {
				int id = registro.getInt(0);
				String nombre = registro.getString(1);

				Region r = new Region(id, nombre);
				regiones.add(r);
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (regiones.size() == 0)
			throw new NotFoundException("No hay regiones.");

		return regiones;
	}

	@Override
	public void insert(Region r) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"Insert into " + DBConexion.T_REGION + " values (" + r.getId() + ", " + r.getNombre() + ");");

		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void update(Region r_old, Region r_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("Update " + DBConexion.T_REGION + " set type_id="+r_new.getId()+", type_name='"+r_new.getNombre()+"' where type_id="+r_old.getId()+";");

		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void delete(Region r) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where type_id=" + r.getId() + ";");
		} finally {
			comando.close();
			conexion.close();
		}
		
	}

}