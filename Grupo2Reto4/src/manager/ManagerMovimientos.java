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

public class ManagerMovimientos implements ManagerInterface<Movimiento>{
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos m = new Metodos();
	
	@Override
	public ArrayList<Movimiento > selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(0);
				String nombre = registro.getString(1);
				int tipo = registro.getInt(2);
				Tipo t = m.conseguirTipo(tipo);
				int potencia = registro.getInt(3);
				int pp = registro.getInt(4);
				double precision = registro.getInt(5);
				
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
	public void insert(Movimiento t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movimiento t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Movimiento t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
