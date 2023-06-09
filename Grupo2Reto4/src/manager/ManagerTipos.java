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

/**
 * @author UNAI-PC
 *
 */
public class ManagerTipos implements ManagerInterface<Tipo> {

	/**
	 * Constante para iniciar conexion contra el servidor o localhost
	 */
	Connection conexion;
	/**
	 * Constante
	 */
	Statement comando;
	/**
	 * Constante para recoger los datos del comando previo
	 */
	ResultSet registro;

	@Override
	public ArrayList<Tipo> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + " order by type_id;");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}

		if (tipos.size() == 0)
			throw new NotFoundException("No hay tipos.");

		return tipos;
	}

	@Override
	public void insert(Tipo t) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate(
					"Insert into " + DBConexion.T_TIPOS + " values (" + t.getId() + ", '" + t.getNombre_tipo() + "');");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void update(Tipo t_old, Tipo t_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("Update " + DBConexion.T_TIPOS + " set type_id="+t_new.getId()+", type_name='"+t_new.getNombre_tipo()+"' where type_id="+t_old.getId()+";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void delete(Tipo t) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_TIPOS + " where type_id=" + t.getId() + ";");
		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}

}
