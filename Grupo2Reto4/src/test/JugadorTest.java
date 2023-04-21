package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Jugador;

class JugadorTest {

	@Test
	void testJugador() {
		Jugador player = new Jugador("Unai", "Akos", "12345", null, null);
		assertFalse(player.Verificar(player));
		assertEquals(player.getLogin(),"Akos");
		assertEquals(player.getPass(),"12345");
		assertEquals(player.getEquipo(),null);
		assertEquals(player.getNombre(),"Unai");	
		assertEquals(player.getPc(),null);
	}

}
