package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import controlador.MetodosVista;
import manager.ManagerCajas;
import manager.ManagerJugador;
import manager.ManagerPC;
import manager.ManagerPokemon;
import modelo.Jugador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import modelo.Usuario;
import utils.DBConexion;

class MoverPokemonCajaEquipoTest {

	Connection conexion;
	Statement comando;
	ResultSet registro;
	Region reg = new Region(1, "Kanto");
	MetodosVista m = new MetodosVista();
	Metodos me = new Metodos();
	ManagerJugador mj = new ManagerJugador();
	ManagerPC mpc = new ManagerPC();
	ManagerCajas mc = new ManagerCajas();

	ManagerPokemon mp = new ManagerPokemon();

	@Test
	void test() throws Exception {

		Region r = new Region(1, "Kanto");
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		Tipo[] tipos = { planta, veneno };

		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		Movimiento move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);

		Pokemon pokemon = new Pokemon(1, "Bulbasaur", tipos, 45, 49, 49, 45, 65, 65, moveset, r);

		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

		Jugador j = new Jugador("Pruebaman", "Pruebaman", "123", equipo, null, false);
		
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		equipo.add(pokemon);
		
		mj.delete(j);
		mj.insert(j);

		ArrayList<Usuario> jugadores = new ArrayList<Usuario>();

		jugadores.addAll(mj.selectAll());

		Jugador u = (Jugador) me.encontrarUsuario(jugadores, j.getLogin(), j.getPass());

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();

			comando.executeUpdate("Insert into " + DBConexion.T_CAJAS_POKEMON + " values (" + u.getPc().getId_pc()
					+ ", " + u.getPc().getCajas().get(0).getId_caja() + ", " + pokemon.getId() + ");");

		} finally {
			if (conexion != null)
				conexion.close();
		}

		m.moverPokemonCajaEquipo(u, pokemon, u.getPc().getCajas().get(0));

		assertEquals(u.getEquipo().get(1).getId(), pokemon.getId());

		mj.delete(j);

	}

}
