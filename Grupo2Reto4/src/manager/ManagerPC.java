package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Caja;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Pokemon;
import utils.DBConexion;

public class ManagerPC implements ManagerInterface<MiPc> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	Metodos m = new Metodos();
	ManagerJugador mj = new ManagerJugador();

	@Override
	public ArrayList<MiPc> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<MiPc> pcs = new ArrayList<MiPc>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				registro2 = comando
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {

					int idbox = registro.getInt(2);

					Caja c = m.conseguirCajas(idbox);
					cajas.add(c);
				}

				MiPc pc = new MiPc(cajas, id);
				pcs.add(pc);
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (pcs.size() == 0)
			throw new NotFoundException("No hay pcs.");

		return pcs;
	}

	@Override
	public void insert(MiPc pc) throws SQLException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Jugador> jugadores = mj.selectAll();
		Jugador j = null;
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getPc() == pc) {
				j = jugadores.get(i);
			}
		}

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_MIPC + "(user_login) values ('" + j.getLogin() + "');");

		} finally {
			comando.close();
			conexion.close();
		}

	}

	@Override
	public void update(MiPc t_old, MiPc t_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			ArrayList<Caja> cajasO = t_old.getCajas();
			ArrayList<Caja> cajasN = t_new.getCajas();

			for (int i = 0; i < cajasO.size(); i++) {

				ArrayList<Pokemon> pN = cajasN.get(i).getPokemon();

				for (int j = 0; j < pN.size(); j++) {

					if (pN.get(j) != null) {

						comando.executeUpdate("update " + DBConexion.T_CAJAS_POKEMON + " set poke_id" + j + " ="
								+ pN.get(j).getId() + " where pc_id=" + t_old.getId_pc() + " and pc_box_id="
								+ cajasO.get(i).getId_caja() + ";");

					}

				}
			}

		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void delete(MiPc pc) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_MIPC + " where pc_id ='" + pc.getId_pc() + "';");

		} finally {
			comando.close();
			conexion.close();
		}
	}

}
