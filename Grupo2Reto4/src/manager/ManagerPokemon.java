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
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import utils.DBConexion;

public class ManagerPokemon implements ManagerInterface<Pokemon> {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos m = new Metodos();

	@Override
	public ArrayList<Pokemon> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		ManagerMovimientos mm = new ManagerMovimientos();
		ArrayList<Movimiento> movimientos = mm.selectAll();
		ArrayList<Tipo> tiposA = new ArrayList<Tipo>();
		ManagerTipos mt = new ManagerTipos();
		tiposA=mt.selectAll();
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);
				
				Tipo tipos[] = new Tipo[2];
				Tipo t1 = tiposA.get(tipo1-1);
				Tipo t2 = null;

				if(tipo2 != null)
					t2 = m.conseguirTipo(tipo2);

				tipos[0] = t1;
				tipos[1] = t2;
				
				//String descripcion = registro.getString(5);
				int hp = registro.getInt(6);
				int atk = registro.getInt(7);
				int def = registro.getInt(8);
				int vel = registro.getInt(9);
				int spAtk = registro.getInt(10);
				int spDef = registro.getInt(11);

				Region r = m.conseguirRegion(registro.getInt(12));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = movimientos.get(registro.getInt(13)-1);
				Movimiento m2 = movimientos.get(registro.getInt(14)-1);
				Movimiento m3 = movimientos.get(registro.getInt(15)-1);
				Movimiento m4 = movimientos.get(registro.getInt(16)-1);

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tipos, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (pokemons.size() == 0)
			throw new NotFoundException("No hay pokemons.");

		return pokemons;
	}

	@Override
	public void insert(Pokemon p) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_POKEMON + " values (" + p.getId() + ", '"
					+ p.getNombre_pokemon() + "'," + p.getTipo()[0].getId() + "," + p.getTipo()[1].getId()
					+ ",'Poner descripción'," + p.getHp() + "," + p.getAtt() + "," + p.getDef() + "," + p.getVel() + ","
					+ p.getSatt() + "," + p.getSdef() + ",'" + p.getReg().getId() + "',"
					+ p.getMovimientos().get(0).getId() + "," + p.getMovimientos().get(1).getId() + ","
					+ p.getMovimientos().get(2).getId() + "," + p.getMovimientos().get(3).getId() + ");");

		} finally {
			comando.close();
			conexion.close();
		}

	}

	@Override
	public void update(Pokemon p_old, Pokemon p_new) throws SQLException, Exception {
		// TODO Auto-generated method stub
		try {
			String descripcion = "Descripción genérica";
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			Integer tipo2 = null;
			if (p_new.getTipo()[1] != null)
				tipo2 = p_new.getTipo()[1].getId();

			comando.executeUpdate("update " + DBConexion.T_POKEMON + " set poke_id=" + p_new.getId() + ", poke_name='"
					+ p_new.getNombre_pokemon() + "', poke_type1=" + p_new.getTipo()[0].getId() + ", poke_type2="
					+ tipo2 + ", descripcion='" + descripcion + "', hp=" + p_new.getHp() + ", atk=" + p_new.getAtt()
					+ ", " + ", def=" + p_new.getDef() + ", vel=" + p_new.getVel() + ", spAtk=" + p_new.getSatt()
					+ ", spDef=" + p_new.getSdef() + ", poke_gen='" + p_new.getReg().getId() + "', poke_mov1="
					+ p_new.getMovimientos().get(0).getId() + "', poke_mov2=" + p_new.getMovimientos().get(1).getId()
					+ "', poke_mov3=" + p_new.getMovimientos().get(2).getId() + "', poke_mov4="
					+ p_new.getMovimientos().get(3).getId() + " where poke_id=" + p_old.getId()+";");

		} finally {
			comando.close();
			conexion.close();
		}
	}

	@Override
	public void delete(Pokemon p) throws SQLException, Exception {
		// TODO Auto-generated method stub

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_POKEMON + " where poke_id =" + p.getId() + ";");

		} finally {
			comando.close();
			conexion.close();
		}

	}

}
