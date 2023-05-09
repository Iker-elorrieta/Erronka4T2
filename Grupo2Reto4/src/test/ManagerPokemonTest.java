package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import controlador.Metodos;
import excepciones.NotFoundException;
import manager.ManagerMovimientos;
import manager.ManagerPokemon;
import manager.ManagerTipos;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import utils.DBConexion;

class ManagerPokemonTest {
	ManagerPokemon mp = new ManagerPokemon();
	ManagerMovimientos mm = new ManagerMovimientos();
	ManagerTipos mt = new ManagerTipos();
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	
	Metodos m = new Metodos();
	
	Region r = new Region(1,"Kanto");
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Tipo[] tipos = { planta, veneno };
	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	Movimiento move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);
	

	Pokemon pokemon = new Pokemon(722, "prueba", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
	
	Pokemon pokemon1 = new Pokemon(722, "prueba", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
	Pokemon pokemon2 = new Pokemon(722, "prueba2", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Pokemon> pokemon = mp.selectAll();
		assertEquals(pokemon.get(0).getNombre_pokemon(), "Bulbasaur");
	}
	
	@Test
	void testSelectPokemon() throws NotFoundException, SQLException, Exception {
		Pokemon pokemon = mp.selectPokemon(493);
		assertEquals(pokemon.getNombre_pokemon(), "Arceus");
	}
	
	@Test
	void testInsert() throws SQLException, Exception {
		
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_POKEMON + " where poke_id =" + pokemon.getId() + ";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		mp.insert(pokemon);
		
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

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
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);
				
				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1-1);
				Tipo t2 = null;

				if(tipo2 != 0)
					t2 = tipos.get(tipo2-1);

				tiposs[0] = t1;
				tiposs[1] = t2;
				
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

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(pokemons.get(pokemons.size()-1).getNombre_pokemon(), "prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);


		
		mp.update(pokemon1, pokemon2);
		
ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

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
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);
				
				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1-1);
				Tipo t2 = null;

				if(tipo2 != 0)
					t2 = tipos.get(tipo2-1);

				tiposs[0] = t1;
				tiposs[1] = t2;
				
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

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(pokemons.get(pokemons.size()-1).getNombre_pokemon(), "prueba2");
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_POKEMON + " where poke_id =" + pokemon2.getId() + ";");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
	}
	
	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_POKEMON + " values (" + pokemon.getId() + ", '"
					+ pokemon.getNombre_pokemon() + "'," + pokemon.getTipo()[0].getId() + "," + pokemon.getTipo()[1].getId()
					+ ",'Poner descripción'," + pokemon.getHp() + "," + pokemon.getAtt() + "," + pokemon.getDef() + "," + pokemon.getVel() + ","
					+ pokemon.getSatt() + "," + pokemon.getSdef() + ",'" + pokemon.getReg().getId() + "',"
					+ pokemon.getMovimientos().get(0).getId() + "," + pokemon.getMovimientos().get(1).getId() + ","
					+ pokemon.getMovimientos().get(2).getId() + "," + pokemon.getMovimientos().get(3).getId() + ");");

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		
		mp.delete(pokemon);
		
ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

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
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3)-1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);
				
				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1-1);
				Tipo t2 = null;

				if(tipo2 != 0)
					t2 = tipos.get(tipo2-1);

				tiposs[0] = t1;
				tiposs[1] = t2;
				
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

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		} finally {
			if (conexion != null)
				conexion.close();
		}
		
		assertEquals(pokemons.size(), 721);
		
		
	}
	
	

	

}
