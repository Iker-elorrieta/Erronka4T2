package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Jugador;

class JugadorTest {

	@Test
	void testJugador() {
		Jugador player = new Jugador();
		player.setLogin("Akos");
		player.setPass("12345");
		player.setEquipo(null);
		player.setUsuario("Unai");
		assertEquals(player.getLogin(),"Akos");
		assertEquals(player.getPass(),"12345");
		assertEquals(player.getEquipo(),null);
		assertEquals(player.getUsuario(),"Unai");
		
		Jugador player2 = new Jugador("Unai", "Akos", "12345", null);
		assertEquals(player2.getLogin(),"Akos");
	}

}
