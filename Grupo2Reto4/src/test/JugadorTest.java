package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Jugador;

class JugadorTest {

	@Test
	void testJugador() {
		Jugador player = new Jugador("Unai", "Akos", "12345");
		
		player.setPokemon(null);
		
		assertEquals(player.getLogin(),"Akos");
		assertEquals(player.getPass(),"12345");
		assertEquals(player.getPokemon(),null);
		assertEquals(player.getNombre(),"Unai");
		
		Jugador player2 = new Jugador("Unai","Akos","12345");
		assertEquals(player2.getLogin(),"Akos");
	}

}
