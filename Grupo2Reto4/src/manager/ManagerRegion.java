package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.NotFoundException;
import modelo.Region;
import utils.DBConexion;

/**
 * @author UNAI-PC
 *
 */
public class ManagerRegion implements ManagerInterface<Region>{
	
	/**
	 * Constante para iniciar conexion contra el servidor o localhost
	 */
	Connection conexion;
	/**
	 *  Constante para lanzar comandos
	 */
	Statement comando;
	/**
	 * Constante para recoger los datos del comando previo
	 */
	ResultSet registro;

	@Override
	public ArrayList<Region> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Region> regiones = new ArrayList<Region>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_REGION + " order by reg_id;");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Region r = new Region(id, nombre);
				regiones.add(r);
			}

		} finally {
			if (conexion != null)
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
					"Insert into " + DBConexion.T_REGION + " values (" + r.getId() + ", '" + r.getNombre() + "', '"+r.getNombre()+"');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void update(Region r_old, Region r_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("Update " + DBConexion.T_REGION + " set reg_id="+r_new.getId()+", reg_name='"+r_new.getNombre()+"' where reg_id="+r_old.getId()+";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void delete(Region r) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_REGION + " where reg_id=" + r.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}

}
