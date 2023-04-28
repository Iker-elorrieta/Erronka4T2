package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Generacion;
import modelo.Movimiento;
import modelo.Pokemon;
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

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(0);
				String name = registro.getString(1);
				Integer tipo1 = registro.getInt(2);
				Integer tipo2 = registro.getInt(3);

				Tipo tipos[] = new Tipo[2];
				Tipo t1 = m.conseguirTipo(tipo1);
				Tipo t2 = m.conseguirTipo(tipo2);

				tipos[0] = t1;
				tipos[1] = t2;

				int hp = registro.getInt(5);
				int atk = registro.getInt(6);
				int def = registro.getInt(7);
				int vel = registro.getInt(8);
				int spAtk = registro.getInt(9);
				int spDef = registro.getInt(10);

				Generacion g = Generacion.valueOf(registro.getString(11));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = m.conseguirMovimiento(registro.getInt(12));
				Movimiento m2 = m.conseguirMovimiento(registro.getInt(13));
				Movimiento m3 = m.conseguirMovimiento(registro.getInt(14));
				Movimiento m4 = m.conseguirMovimiento(registro.getInt(15));

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tipos, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, g);

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
					+ p.getSatt() + "," + p.getSdef() + ",'" + p.getGeneracion() + "',"
					+ p.getMovimientos().get(0).getId() + "," + p.getMovimientos().get(1).getId() + ","
					+ p.getMovimientos().get(2).getId() + "," + p.getMovimientos().get(3).getId() + ");");

		} finally {
			registro.close();
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
					+ ", spDef=" + p_new.getSdef() + ", poke_gen='" + p_new.getGeneracion() + "', poke_mov1="
					+ p_new.getMovimientos().get(0).getId() + "', poke_mov2=" + p_new.getMovimientos().get(1).getId()
					+ "', poke_mov3=" + p_new.getMovimientos().get(2).getId() + "', poke_mov4="
					+ p_new.getMovimientos().get(3).getId() + " where poke_id=" + p_old.getId()+";");

		} finally {
			registro.close();
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
			registro.close();
			comando.close();
			conexion.close();
		}

	}

}
