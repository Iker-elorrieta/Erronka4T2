package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.Estilo;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class JugadorTest {

	@Test
	void testJugador() {
		Tipo[] tipos = new Tipo[2];
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		tipos[0] = planta;
		tipos[1] = veneno;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		Movimiento move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50, Estilo.Phys);
		moveset.add(move1);
		Movimiento move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40, Estilo.Phys);
		moveset.add(move2);
		Movimiento move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20, Estilo.Special);
		moveset.add(move3);
		Movimiento move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90, Estilo.Special);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);

		ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2);

		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		pokemon.add(pokemon1);
		pokemon.add(pokemon2);
		Pokemon[] equipo = {null, pokemon1, pokemon2, null, null, null};
		Pokemon[] equipo2 = {null, null, null, null, null, null};
		Caja caja = new Caja(1, pokemon);

		ArrayList<Caja> cajas = new ArrayList<Caja>();
		cajas.add(caja);

		MiPc pc1 = new MiPc(cajas);
		MiPc pc2 = new MiPc(cajas);
		
		Jugador player = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player2 = new Jugador("Unai", "Akos", "12345", equipo, pc1);
		Jugador player3 = player;
		assertTrue(player.Validar());
		assertEquals(player.getLogin(),"Akos");
		assertEquals(player.getPass(),"12345");
		assertEquals(player.getEquipo().toString(),equipo.toString());
		assertEquals(player.getNombre(),"Unai");
		assertEquals(player.getPc().toString(), pc2.toString());
		assertEquals(player.toString(), player2.toString());
		assertEquals(player.hashCode(), player2.hashCode());
		
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
		player2 = new Jugador("Unai", "Akos", "12345", equipo2, null);
		assertFalse(player2.Validar());
	}

}
