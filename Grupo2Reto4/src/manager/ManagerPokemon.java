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
import modelo.Tipo;
import utils.DBConexion;

public class ManagerPokemon implements ManagerInterface<Pokemon>{
	
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
				
				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();
				
				Movimiento m1 = m.conseguirMovimiento(registro.getInt(11));
				Movimiento m2 = m.conseguirMovimiento(registro.getInt(12));
				Movimiento m3 = m.conseguirMovimiento(registro.getInt(13));
				Movimiento m4 = m.conseguirMovimiento(registro.getInt(1));
				
				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);
				
				Pokemon p = new Pokemon(id, name, tipos, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon);
				
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
	public void insert(Pokemon t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Pokemon t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Pokemon t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
	
}
