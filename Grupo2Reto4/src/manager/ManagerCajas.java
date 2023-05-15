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
import modelo.Pokemon;
import utils.DBConexion;

/**
 * @author in1dm3
 *
 */
public class ManagerCajas implements ManagerInterface<Caja> {

	/**
	 * 
	 */
	Connection conexion;
	/**
	 * 
	 */
	Statement comando;
	/**
	 * 
	 */
	Statement comando2;
	/**
	 * 
	 */
	ResultSet registro;
	/**
	 * 
	 */
	ResultSet registro2;
	/**
	 * 
	 */
	Metodos m = new Metodos();

	@Override
	public ArrayList<Caja> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Caja> cajas = new ArrayList<Caja>();
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

		ManagerPokemon mp = new ManagerPokemon();
		pokemon = mp.selectAll();
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Pokemon> pokemonCAJA = new ArrayList<Pokemon>();
				comando2 = conexion.createStatement();
				registro2 = comando2.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro2.getInt(1);

					Pokemon p = pokemon.get(idpokemon - 1);

					pokemonCAJA.add(p);
				}

				Caja c = new Caja(id, pokemonCAJA);

				cajas.add(c);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}

		return cajas;
	}

	@Override
	public void insert(Caja c) throws SQLException, Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Caja c_old, Caja c_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			registro = comando.executeQuery(
					"SELECT * FROM " + DBConexion.T_CAJAS + " where pc_box_id=" + c_old.getId_caja() + ";");
			int pcid=0;
			if(registro.next() == true)
				pcid = registro.getInt(2);

			comando.executeUpdate(
					"delete from " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id=" + c_old.getId_caja() + ";");

			for (int i = 0; i < c_new.getPokemon().size(); i++) {
				Integer poke_id = null;
				if (c_new.getPokemon().get(i) != null) {
					poke_id = c_new.getPokemon().get(i).getId();
					comando.executeUpdate("insert into " + DBConexion.T_CAJAS_POKEMON + " values(" + pcid
							+ ", " + c_new.getId_caja() + ", " + poke_id + ");");
				}
			}
		} finally {
			if (conexion != null)
				conexion.close();
		}
	}

	@Override
	public void delete(Caja c) throws SQLException, Exception {
		// TODO Auto-generated method stub
	}
}
