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
import modelo.MiPc;
import modelo.Pokemon;
import utils.DBConexion;

public class ManagerCajas implements ManagerInterface<Caja> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	Metodos m = new Metodos();
	ManagerPC mpc = new ManagerPC();

	@Override
	public ArrayList<Caja> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Caja> cajas = new ArrayList<Caja>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(0);

				ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

				registro2 = comando.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro.getInt(2);

					Pokemon p = m.conseguirPokemon(idpokemon);

					pokemons.add(p);
				}

				Caja c = new Caja(id, pokemons);

				cajas.add(c);

			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (cajas.size() == 0)
			throw new NotFoundException("No hay cajas.");

		return cajas;
	}

	@Override
	public void insert(Caja c) throws SQLException, Exception {
		// TODO Auto-generated method stub
		ArrayList<MiPc> pcs = mpc.selectAll();
		MiPc pc = null;
		for (int i = 0; i < pcs.size(); i++) {
			ArrayList<Caja> cajas = pcs.get(i).getCajas();
			for (int j = 0; j < cajas.size(); j++) {
				if (cajas.get(j) == c) {
					pc = pcs.get(i);
				}
			}
		}

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_CAJAS + "(pc_id, box_pokemon) values (" + pc.getId_pc()
					+ "," + c.getPokemon().size() + ");");

			if (c.getPokemon().size() > 0) {

				for (int i = 0; i < c.getPokemon().size(); i++) {
					comando.executeUpdate("Insert into " + DBConexion.T_CAJAS_POKEMON
							+ "(pc_id, pc_box_id, poke_id) values (" + pc.getId_pc() + "," + c.getId_caja() + ","
							+ c.getPokemon().get(i).getId() + ");");
				}
			}

		} finally {
			comando.close();
			conexion.close();
		}

	}

	@Override
	public void update(Caja c_old, Caja c_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			for (int i = 0;i < c_new.getPokemon().size();i++) {
				Integer poke_id=null;
				if(c_new.getPokemon().get(i) != null)
					poke_id=c_new.getPokemon().get(i).getId();
				comando.executeUpdate("update from "+DBConexion.T_CAJAS_POKEMON+" set poke_id="+poke_id+" where pc_box_id="+c_old.getId_caja()+";");
			}
		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void delete(Caja c) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from "+DBConexion.T_CAJAS+" where pc_box_id ="+c.getId_caja()+";");
			
			comando.executeUpdate("delete from "+DBConexion.T_CAJAS_POKEMON+" where pc_box_id ="+c.getId_caja()+";");

		} finally {
			comando.close();
			conexion.close();
		}
	}

}
