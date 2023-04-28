package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.Generacion;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class JugadorTest {
	Tipo[] tipos = new Tipo[2];
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Movimiento move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50);
	Movimiento move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40);
	Movimiento move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20);
	Movimiento move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90);
	ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	ArrayList<Caja> cajas = new ArrayList<Caja>();

	@Test
	void testJugadorConstructor() {

		tipos[0] = planta;
		tipos[1] = veneno;

		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);

		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);

		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);

		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon1);
		equipo.add(pokemon2);
		
		Caja caja = new Caja(1, equipo);

		cajas.add(caja);

		MiPc pc1 = new MiPc(cajas, 1);
		
		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		assertEquals(player.getNombre(), "Unai");
		assertEquals(player.getLogin(), "Akos");
		assertEquals(player.getPass(), "12345");
		assertEquals(player.getEquipo().toString(), equipo.toString());
		assertEquals(player.getPc().toString(), pc1.toString());
		assertEquals(player.getNombre(), "Unai");
	}

	@Test
	void testEqualsJugador() {
		tipos[0] = planta;
		tipos[1] = veneno;
		
		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);
		
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		
		equipo.add(pokemon1);
		equipo.add(pokemon2);
		
		ArrayList<Pokemon> equipo2 = new ArrayList<Pokemon>();
		
		equipo2.add(pokemon1);
		equipo2.add(pokemon2);
		
		Caja caja = new Caja(1, equipo);
		cajas.add(caja);
		MiPc pc1 = new MiPc(cajas, 1);

		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player2 = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player3 = player;

		player.equals(null);
		player.equals(player3);
		player.equals(player2);
		player.equals(planta);
		player2 = new Jugador("Unai", "Akos", "12345", equipo, null);
		player.equals(player2);
		player2 = new Jugador("Unai", "Akos", "12345", null, pc1);
		player.equals(player2);

		player2 = new Jugador("Unai", "Akos", "12345", null, null);
		assertFalse(player2.Validar());
		player2 = new Jugador("Unai", "Akos", "12345", null, null);
		assertFalse(player2.Validar());

	}

	@Test
	void testToStringJugador() {
		tipos[0] = planta;
		tipos[1] = veneno;
		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon1);
		equipo.add(pokemon2);
		
		
		Caja caja = new Caja(1, equipo);
		cajas.add(caja);
		MiPc pc1 = new MiPc(cajas, 1);
		MiPc pc2 = new MiPc(cajas, 1);
		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player2 = new Jugador("Unai", "Akos", "12345", equipo, pc2);
		
		assertEquals(player.toString(), player2.toString());
	}

	@Test
	void testHashCodeJugador() {
		tipos[0] = planta;
		tipos[1] = veneno;
		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon1);
		equipo.add(pokemon2);
		
		
		Caja caja = new Caja(1, equipo);
		cajas.add(caja);
		MiPc pc1 = new MiPc(cajas, 1);
		MiPc pc2 = new MiPc(cajas, 1);
		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player2 = new Jugador("Unai", "Akos", "12345", equipo, pc2);

		assertEquals(player.hashCode(), player2.hashCode());
	}

	@Test
	void testValidarJugador() {
		tipos[0] = planta;
		tipos[1] = veneno;
		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon1);
		equipo.add(pokemon2);
		Caja caja = new Caja(1, equipo);
		cajas.add(caja);
		MiPc pc1 = new MiPc(cajas, 1);
		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		assertTrue(player.Validar());
	}
}
