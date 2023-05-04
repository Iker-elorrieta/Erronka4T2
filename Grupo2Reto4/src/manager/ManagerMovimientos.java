package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Movimiento;
import modelo.Tipo;
import utils.DBConexion;

public class ManagerMovimientos implements ManagerInterface<Movimiento> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos m = new Metodos();

	@Override
	public ArrayList<Movimiento> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				int tipo = registro.getInt(3);
				Tipo t = m.conseguirTipo(tipo);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (movimientos.size() == 0)
			throw new NotFoundException("No hay movimientos.");

		return movimientos;
	}

	@Override
	public void insert(Movimiento m) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_MOVS + " values (" + m.getId() + ",'" + m.getNombre()
					+ "'," + m.getTipo().getId() + "," + m.getPotencia() + "," + m.getPuntosPoder() + ","
					+ m.getPrecision() + ");");

		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void update(Movimiento m_old, Movimiento m_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		comando.executeUpdate("update  " + DBConexion.T_MOVS + " set move_name='" + m_new.getNombre() + "', mov_type="
				+ m_new.getTipo().getId() + ", potency=" + m_new.getPotencia() + ", pp=" + m_new.getPuntosPoder()
				+ ", accuracy=" + m_new.getPrecision() + " where mov_id=" + m_old.getId() + ";");
	}

	@Override
	public void delete(Movimiento m) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_MOVS + " where mov_id =" + m.getId() + ";");

		} finally {
			comando.close();
			conexion.close();
		}

	}

}
