package test;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import controlador.Metodos;
import manager.ManagerCajas;
import manager.ManagerJugador;
import manager.ManagerPC;
import manager.ManagerPokemon;
import modelo.Caja;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import utils.DBConexion;

class ManagerJugadorTest {

	ManagerJugador mj = new ManagerJugador();
	ManagerPokemon mp = new ManagerPokemon();
	ManagerPC mpc = new ManagerPC();
	Metodos m = new Metodos();
	ManagerCajas mc = new ManagerCajas();

	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	ResultSet registro3;

	Region r = new Region(1, "Kanto");
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Tipo[] tipos = { planta, veneno };

	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	Movimiento move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);

	Pokemon pokemon = new Pokemon(1, "Bulbasaur", tipos, 45, 49, 49, 45, 65, 65, moveset, r);

	ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

	Jugador j = new Jugador("Igor", "aaaaa", "123", equipo, null, false);

	@Test
	void testSelectAll() {

		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		equipo.add(pokemon);

		// insertamos el usuario
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_USERS + "(user_login, user_name, user_pass) values ('"
					+ j.getLogin() + "', '" + j.getNombre() + "', '" + j.getPass() + "');");

			comando.executeUpdate("call cargarEquipo (" + j.getEquipo().get(0).getId() + ", '" + j.getLogin() + "');");

			// recogemos los usuarios
			ArrayList<Jugador> js = mj.selectAll();
			assertEquals(js.get(0).getLogin(), "aaaaa");

			// eliminamos al usuario

			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_USERS + " where user_login ='" + j.getLogin() + "';");

			if (conexion != null)
				conexion.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

	}

	@Test
	void testInsert() {
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		equipo.add(pokemon);
		// eliminamos al usuario
		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);

			comando = conexion.createStatement();
			comando.executeUpdate("delete from " + DBConexion.T_USERS + " where user_login ='" + j.getLogin() + "';");

		
		// insertamos al usuario
		mj.insert(j);

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		// recogemos los tipos
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		
		// recogemos los movimientos
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3) - 1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		
		// recogemos los pokemons
	
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);

				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1 - 1);
				Tipo t2 = null;

				if (tipo2 != 0)
					t2 = tipos.get(tipo2 - 1);

				tiposs[0] = t1;
				tiposs[1] = t2;

				// String descripcion = registro.getString(5);
				int hp = registro.getInt(6);
				int atk = registro.getInt(7);
				int def = registro.getInt(8);
				int vel = registro.getInt(9);
				int spAtk = registro.getInt(10);
				int spDef = registro.getInt(11);

				Region r = m.conseguirRegion(registro.getInt(12));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = movimientos.get(registro.getInt(13) - 1);
				Movimiento m2 = movimientos.get(registro.getInt(14) - 1);
				Movimiento m3 = movimientos.get(registro.getInt(15) - 1);
				Movimiento m4 = movimientos.get(registro.getInt(16) - 1);

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		// recogemos las cajas

		ArrayList<Caja> cajasPC = new ArrayList<Caja>();
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Pokemon> pokemonCAJA = new ArrayList<Pokemon>();
				Statement comando2 = conexion.createStatement();
				registro2 = comando2.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro.getInt(3);

					Pokemon p = pokemons.get(idpokemon - 1);

					pokemonCAJA.add(p);
				}

				Caja c = new Caja(id, pokemonCAJA);

				cajasPC.add(c);

			}

		// recogemos los pcs

		ArrayList<MiPc> pcs = new ArrayList<MiPc>();// mpc.selectAll();
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {
					int idbox = registro2.getInt(1);
					Caja caja = null;
					int i = 0;
					do {
						if (cajasPC.get(i).getId_caja() == idbox) {
							caja = cajasPC.get(i);
							cajasPC.remove(caja);
							cajas.add(caja);
						}
						i++;
					} while (caja == null && i < cajasPC.size());

				}

				MiPc pc = new MiPc(cajas, id);
				pcs.add(pc);
			}

		// recogemos los usuarios
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next()) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String passw = registro.getString(3);

				boolean ban = false;
				if (registro.getInt(5) == 1)
					ban = true;
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ='" + login + "';");

				while (registro2.next()) {
					Pokemon p1 = pokemons.get(registro2.getInt("poke_id1") - 1);
					equipo.add(p1);

					Pokemon p2 = null;
					if ((Integer) registro2.getInt("poke_id2") != 0) {
						p2 = pokemons.get(registro2.getInt("poke_id2") - 1);
						equipo.add(p2);
					}

					Pokemon p3 = null;
					if ((Integer) registro2.getInt("poke_id3") != 0) {
						p3 = pokemons.get(registro2.getInt("poke_id3") - 1);
						equipo.add(p3);
					}

					Pokemon p4 = null;
					if ((Integer) registro2.getInt("poke_id4") != 0) {
						p4 = pokemons.get(registro2.getInt("poke_id4") - 1);
						equipo.add(p4);
					}

					Pokemon p5 = null;
					if ((Integer) registro2.getInt("poke_id5") != 0) {
						p5 = pokemons.get(registro2.getInt("poke_id5") - 1);
						equipo.add(p5);
					}

					Pokemon p6 = null;
					if ((Integer) registro2.getInt("poke_id6") != 0) {
						p6 = pokemons.get(registro2.getInt("poke_id6") - 1);
						equipo.add(p6);
					}

				}

				Statement comando3 = conexion.createStatement();
				registro3 = comando3
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ='" + login + "';");

				MiPc pc = null;
				while (registro3.next() == true) {
					for (MiPc mipc : pcs) {
						if (mipc.getId_pc() == registro3.getInt("pc_id"))
							pc = mipc;
					}
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc, ban);
				jugadores.add(user);
			}

		

		assertEquals(jugadores.get(0).getLogin(), "aaaaa");
		
		
		if (conexion != null)
			conexion.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	void testUpdate(){
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		equipo.add(pokemon);

		
		// recogemos los tipos
		try {
			ArrayList<Jugador> jugadores = mj.selectAll();
			for (Jugador jugador : jugadores) {
				if (jugador.getLogin().equals("aaaaa"))
					j = jugador;
			}

			Jugador j2 = new Jugador("Igor", "aaaaaa", "123", equipo, j.getPc(), false);

			mj.update(j, j2);

			jugadores = new ArrayList<Jugador>();

			ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

			ArrayList<Tipo> tipos = new ArrayList<Tipo>();
			
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		
		// recogemos los movimientos
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3) - 1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		
		// recogemos los pokemons
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);

				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1 - 1);
				Tipo t2 = null;

				if (tipo2 != 0)
					t2 = tipos.get(tipo2 - 1);

				tiposs[0] = t1;
				tiposs[1] = t2;

				// String descripcion = registro.getString(5);
				int hp = registro.getInt(6);
				int atk = registro.getInt(7);
				int def = registro.getInt(8);
				int vel = registro.getInt(9);
				int spAtk = registro.getInt(10);
				int spDef = registro.getInt(11);

				Region r = m.conseguirRegion(registro.getInt(12));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = movimientos.get(registro.getInt(13) - 1);
				Movimiento m2 = movimientos.get(registro.getInt(14) - 1);
				Movimiento m3 = movimientos.get(registro.getInt(15) - 1);
				Movimiento m4 = movimientos.get(registro.getInt(16) - 1);

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		

		// recogemos las cajas

		ArrayList<Caja> cajasPC = new ArrayList<Caja>();
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Pokemon> pokemonCAJA = new ArrayList<Pokemon>();
				Statement comando2 = conexion.createStatement();
				registro2 = comando2.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro.getInt(3);

					Pokemon p = pokemons.get(idpokemon - 1);

					pokemonCAJA.add(p);
				}

				Caja c = new Caja(id, pokemonCAJA);

				cajasPC.add(c);

			}

		
		// recogemos los pcs

		ArrayList<MiPc> pcs = new ArrayList<MiPc>();// mpc.selectAll();

			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {
					int idbox = registro2.getInt(1);
					Caja caja = null;
					int i = 0;
					do {
						if (cajasPC.get(i).getId_caja() == idbox) {
							caja = cajasPC.get(i);
							cajasPC.remove(caja);
							cajas.add(caja);
						}
						i++;
					} while (caja == null && i < cajasPC.size());

				}

				MiPc pc = new MiPc(cajas, id);
				pcs.add(pc);
			}

		// recogemos los usuarios
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next()) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String passw = registro.getString(3);

				boolean ban = false;
				if (registro.getInt(5) == 1)
					ban = true;
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ='" + login + "';");

				while (registro2.next()) {
					Pokemon p1 = pokemons.get(registro2.getInt("poke_id1") - 1);
					equipo.add(p1);

					Pokemon p2 = null;
					if ((Integer) registro2.getInt("poke_id2") != 0) {
						p2 = pokemons.get(registro2.getInt("poke_id2") - 1);
						equipo.add(p2);
					}

					Pokemon p3 = null;
					if ((Integer) registro2.getInt("poke_id3") != 0) {
						p3 = pokemons.get(registro2.getInt("poke_id3") - 1);
						equipo.add(p3);
					}

					Pokemon p4 = null;
					if ((Integer) registro2.getInt("poke_id4") != 0) {
						p4 = pokemons.get(registro2.getInt("poke_id4") - 1);
						equipo.add(p4);
					}

					Pokemon p5 = null;
					if ((Integer) registro2.getInt("poke_id5") != 0) {
						p5 = pokemons.get(registro2.getInt("poke_id5") - 1);
						equipo.add(p5);
					}

					Pokemon p6 = null;
					if ((Integer) registro2.getInt("poke_id6") != 0) {
						p6 = pokemons.get(registro2.getInt("poke_id6") - 1);
						equipo.add(p6);
					}

				}

				Statement comando3 = conexion.createStatement();
				registro3 = comando3
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ='" + login + "';");

				MiPc pc = null;
				while (registro3.next() == true) {
					for (MiPc mipc : pcs) {
						if (mipc.getId_pc() == registro3.getInt("pc_id"))
							pc = mipc;
					}
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc, ban);
				jugadores.add(user);
			}

		assertEquals(jugadores.get(0).getLogin(), "aaaaaa");


			comando = conexion.createStatement();

			comando.executeUpdate("delete from " + DBConexion.T_USERS + " where user_login ='" + j2.getLogin() + "';");

			if (conexion != null)
				conexion.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

	}

	@Test
	void testDelete(){
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		equipo.add(pokemon);

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_USERS + "(user_login, user_name, user_pass) values ('"
					+ j.getLogin() + "', '" + j.getNombre() + "', '" + j.getPass() + "');");

			comando.executeUpdate("call cargarEquipo (" + j.getEquipo().get(0).getId() + ", '" + j.getLogin() + "');");

		

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
		// recogemos los tipos
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos.add(t);
			}

		
		// recogemos los movimientos
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();

			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos.get(registro.getInt(3) - 1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos.add(m);

			}

		
		// recogemos los pokemons
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);

				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos.get(tipo1 - 1);
				Tipo t2 = null;

				if (tipo2 != 0)
					t2 = tipos.get(tipo2 - 1);

				tiposs[0] = t1;
				tiposs[1] = t2;

				// String descripcion = registro.getString(5);
				int hp = registro.getInt(6);
				int atk = registro.getInt(7);
				int def = registro.getInt(8);
				int vel = registro.getInt(9);
				int spAtk = registro.getInt(10);
				int spDef = registro.getInt(11);

				Region r = m.conseguirRegion(registro.getInt(12));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = movimientos.get(registro.getInt(13) - 1);
				Movimiento m2 = movimientos.get(registro.getInt(14) - 1);
				Movimiento m3 = movimientos.get(registro.getInt(15) - 1);
				Movimiento m4 = movimientos.get(registro.getInt(16) - 1);

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons.add(p);
			}

		

		// recogemos las cajas

		ArrayList<Caja> cajasPC = new ArrayList<Caja>();
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Pokemon> pokemonCAJA = new ArrayList<Pokemon>();
				Statement comando2 = conexion.createStatement();
				registro2 = comando2.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro.getInt(3);

					Pokemon p = pokemons.get(idpokemon - 1);

					pokemonCAJA.add(p);
				}

				Caja c = new Caja(id, pokemonCAJA);

				cajasPC.add(c);

			}

		
		// recogemos los pcs

		ArrayList<MiPc> pcs = new ArrayList<MiPc>();

		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {
					int idbox = registro2.getInt(1);
					Caja caja = null;
					int i = 0;
					do {
						if (cajasPC.get(i).getId_caja() == idbox) {
							caja = cajasPC.get(i);
							cajasPC.remove(caja);
							cajas.add(caja);
						}
						i++;
					} while (caja == null && i < cajasPC.size());

				}

				MiPc pc = new MiPc(cajas, id);
				pcs.add(pc);
			}

		

		// recogemos los usuarios
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next()) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String passw = registro.getString(3);

				boolean ban = false;
				if (registro.getInt(5) == 1)
					ban = true;
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ='" + login + "';");

				while (registro2.next()) {
					Pokemon p1 = pokemons.get(registro2.getInt("poke_id1") - 1);
					equipo.add(p1);

					Pokemon p2 = null;
					if ((Integer) registro2.getInt("poke_id2") != 0) {
						p2 = pokemons.get(registro2.getInt("poke_id2") - 1);
						equipo.add(p2);
					}

					Pokemon p3 = null;
					if ((Integer) registro2.getInt("poke_id3") != 0) {
						p3 = pokemons.get(registro2.getInt("poke_id3") - 1);
						equipo.add(p3);
					}

					Pokemon p4 = null;
					if ((Integer) registro2.getInt("poke_id4") != 0) {
						p4 = pokemons.get(registro2.getInt("poke_id4") - 1);
						equipo.add(p4);
					}

					Pokemon p5 = null;
					if ((Integer) registro2.getInt("poke_id5") != 0) {
						p5 = pokemons.get(registro2.getInt("poke_id5") - 1);
						equipo.add(p5);
					}

					Pokemon p6 = null;
					if ((Integer) registro2.getInt("poke_id6") != 0) {
						p6 = pokemons.get(registro2.getInt("poke_id6") - 1);
						equipo.add(p6);
					}

				}

				Statement comando3 = conexion.createStatement();
				registro3 = comando3
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ='" + login + "';");

				MiPc pc = null;
				while (registro3.next() == true) {
					for (MiPc mipc : pcs) {
						if (mipc.getId_pc() == registro3.getInt("pc_id"))
							pc = mipc;
					}
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc, ban);
				jugadores.add(user);
			}

		

		mj.delete(j);

		ArrayList<Jugador> jugadores2 = new ArrayList<Jugador>();

		ArrayList<Pokemon> pokemons2 = new ArrayList<Pokemon>();

		ArrayList<Tipo> tipos2 = new ArrayList<Tipo>();
		// recogemos los tipos
	
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_TIPOS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);

				Tipo t = new Tipo(id, nombre);
				tipos2.add(t);
			}

		
		// recogemos los movimientos
		ArrayList<Movimiento> movimientos2 = new ArrayList<Movimiento>();

		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MOVS + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String nombre = registro.getString(2);
				Tipo t = tipos2.get(registro.getInt(3) - 1);
				int potencia = registro.getInt(4);
				int pp = registro.getInt(5);
				double precision = registro.getInt(6);

				Movimiento m = new Movimiento(id, nombre, pp, precision, t, potencia);
				movimientos2.add(m);

			}

		
		// recogemos los pokemons
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + ";");

			while (registro.next() == true) {
				int id = registro.getInt(1);
				String name = registro.getString(2);
				Integer tipo1 = registro.getInt(3);
				Integer tipo2 = registro.getInt(4);

				Tipo tiposs[] = new Tipo[2];
				Tipo t1 = tipos2.get(tipo1 - 1);
				Tipo t2 = null;

				if (tipo2 != 0)
					t2 = tipos2.get(tipo2 - 1);

				tiposs[0] = t1;
				tiposs[1] = t2;

				// String descripcion = registro.getString(5);
				int hp = registro.getInt(6);
				int atk = registro.getInt(7);
				int def = registro.getInt(8);
				int vel = registro.getInt(9);
				int spAtk = registro.getInt(10);
				int spDef = registro.getInt(11);

				Region r = m.conseguirRegion(registro.getInt(12));

				ArrayList<Movimiento> movimientos_pokemon = new ArrayList<Movimiento>();

				Movimiento m1 = movimientos2.get(registro.getInt(13) - 1);
				Movimiento m2 = movimientos2.get(registro.getInt(14) - 1);
				Movimiento m3 = movimientos2.get(registro.getInt(15) - 1);
				Movimiento m4 = movimientos2.get(registro.getInt(16) - 1);

				movimientos_pokemon.add(m1);
				movimientos_pokemon.add(m2);
				movimientos_pokemon.add(m3);
				movimientos_pokemon.add(m4);

				Pokemon p = new Pokemon(id, name, tiposs, hp, atk, def, spAtk, spDef, vel, movimientos_pokemon, r);

				pokemons2.add(p);
			}

		

		// recogemos las cajas

		ArrayList<Caja> cajasPC2 = new ArrayList<Caja>();
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Pokemon> pokemonCAJA = new ArrayList<Pokemon>();
				Statement comando2 = conexion.createStatement();
				registro2 = comando2.executeQuery(
						"SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = " + id + ";");

				while (registro2.next() == true) {

					int idpokemon = registro.getInt(3);

					Pokemon p = pokemons2.get(idpokemon - 1);

					pokemonCAJA.add(p);
				}

				Caja c = new Caja(id, pokemonCAJA);

				cajasPC2.add(c);

			}

		
		// recogemos los pcs

		ArrayList<MiPc> pcs2 = new ArrayList<MiPc>();

		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + ";");

			while (registro.next() == true) {

				int id = registro.getInt(1);

				ArrayList<Caja> cajas = new ArrayList<Caja>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT pc_box_id FROM " + DBConexion.T_CAJAS + " where pc_id = " + id + ";");

				while (registro2.next() == true) {
					int idbox = registro2.getInt(1);
					Caja caja = null;
					int i = 0;
					do {
						if (cajasPC2.get(i).getId_caja() == idbox) {
							caja = cajasPC2.get(i);
							cajasPC2.remove(caja);
							cajas.add(caja);
						}
						i++;
					} while (caja == null && i < cajasPC.size());

				}

				MiPc pc = new MiPc(cajas, id);
				pcs2.add(pc);
			}


		// recogemos los usuarios
		
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_USERS + ";");

			while (registro.next()) {

				String login = registro.getString(1);
				String nombre = registro.getString(2);
				String passw = registro.getString(3);

				boolean ban = false;
				if (registro.getInt(5) == 1)
					ban = true;
				ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

				Statement comando2 = conexion.createStatement();
				registro2 = comando2
						.executeQuery("SELECT * FROM " + DBConexion.T_EQUIPOS + " where user_login ='" + login + "';");

				while (registro2.next()) {
					Pokemon p1 = pokemons2.get(registro2.getInt("poke_id1") - 1);
					equipo.add(p1);

					Pokemon p2 = null;
					if ((Integer) registro2.getInt("poke_id2") != 0) {
						p2 = pokemons2.get(registro2.getInt("poke_id2") - 1);
						equipo.add(p2);
					}

					Pokemon p3 = null;
					if ((Integer) registro2.getInt("poke_id3") != 0) {
						p3 = pokemons2.get(registro2.getInt("poke_id3") - 1);
						equipo.add(p3);
					}

					Pokemon p4 = null;
					if ((Integer) registro2.getInt("poke_id4") != 0) {
						p4 = pokemons2.get(registro2.getInt("poke_id4") - 1);
						equipo.add(p4);
					}

					Pokemon p5 = null;
					if ((Integer) registro2.getInt("poke_id5") != 0) {
						p5 = pokemons2.get(registro2.getInt("poke_id5") - 1);
						equipo.add(p5);
					}

					Pokemon p6 = null;
					if ((Integer) registro2.getInt("poke_id6") != 0) {
						p6 = pokemons2.get(registro2.getInt("poke_id6") - 1);
						equipo.add(p6);
					}

				}

				Statement comando3 = conexion.createStatement();
				registro3 = comando3
						.executeQuery("SELECT * FROM " + DBConexion.T_MIPC + " where user_login ='" + login + "';");

				MiPc pc = null;
				while (registro3.next() == true) {
					for (MiPc mipc : pcs2) {
						if (mipc.getId_pc() == registro3.getInt("pc_id"))
							pc = mipc;
					}
				}

				Jugador user = new Jugador(nombre, login, passw, equipo, pc, ban);
				jugadores2.add(user);
			}
			assertEquals(jugadores.size(), jugadores2.size() + 1);
			
			if (conexion != null)
				conexion.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

	}

}
