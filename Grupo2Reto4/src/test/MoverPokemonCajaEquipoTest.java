package test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import controlador.MetodosVista;
import excepciones.ArrayListLlenoException;
import manager.ManagerCajas;
import manager.ManagerJugador;
import manager.ManagerPokemon;
import modelo.Caja;
import modelo.Jugador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;

class MoverPokemonCajaEquipoTest {

	Region reg = new Region(1, "Kanto");
	MetodosVista m = new MetodosVista();
	ManagerJugador mj = new ManagerJugador();
	ManagerCajas mc = new ManagerCajas();

	ManagerPokemon mp = new ManagerPokemon();

	@Test
	void test() throws Exception {

		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo[] tipos = { planta, veneno };

		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();

		Pokemon p = new Pokemon(1, "Bulbasaur", tipos, 45, 49, 49, 45, 65, 65, moveset, reg);
		Pokemon p2 = new Pokemon(2, "Ivysaur", tipos, 45, 49, 49, 45, 65, 65, moveset, reg);
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(p);
		Jugador j = new Jugador("prueba1", "prueba3", "123", equipo, null, false);

		mj.insert(j);

		ArrayList<Jugador> jugadores = mj.selectAll();
		for (Jugador jugador : jugadores) {
			if (jugador.getLogin().equals("prueba3"))
				j = jugador;
		}

		Caja c = j.getPc().getCajas().get(0);
		Caja c_old = j.getPc().getCajas().get(0);
		c.getPokemon().add(p2);
		
		mc.update(c_old, c);
		jugadores = mj.selectAll();
		for (Jugador jugador : jugadores) {
			if (jugador.getLogin().equals("prueba3"))
				j = jugador;
		}

		assertEquals(j.getEquipo().size(), 1);
		c = j.getPc().getCajas().get(0);
		try {
			m.moverPokemonCajaEquipo(j, p2, c);
		} catch (ArrayListLlenoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(j.getEquipo().size(), 2);
		assertEquals(j.getPc().getCajas().get(0).getPokemon().size(), 0);

		mj.delete(j);

	}

}
