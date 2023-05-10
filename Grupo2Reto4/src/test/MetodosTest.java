package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerProfesor;
import modelo.Pokemon;
import modelo.Profesor;
import modelo.Region;
import modelo.Tipo;
import modelo.Usuario;

class MetodosTest {
	Metodos metodos = new Metodos();
	ManagerJugador mj = new ManagerJugador();
	ManagerProfesor mp = new ManagerProfesor();
	@Test
	void testEsVacio() {
		assertTrue(metodos.esVacio(""));
	}

	@Test
	void testConseguirTipo() throws NotFoundException, SQLException, Exception {
		Tipo tipo = metodos.conseguirTipo(18);
		assertEquals(tipo.getNombre_tipo(), "Hada");
	}

	@Test
	void testConseguirPokemon() throws NotFoundException, SQLException, Exception {
		Pokemon pokemon = metodos.conseguirPokemon(493);
		assertEquals(pokemon.getNombre_pokemon(), "Arceus");
	}

	@Test
	void testEncontrarUsuario() throws NotFoundException, SQLException, Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.addAll(mp.selectAll());
		Profesor profOak=(Profesor) metodos.encontrarUsuario(usuarios, "oak", "KantoKanto");
		assertEquals(profOak.getReg().getNombre(), "Kanto");
	}

	@Test
	void testConseguirRegion() throws NotFoundException, SQLException, Exception {
		Region region = metodos.conseguirRegion(1);
		assertEquals(region.getNombre(), "Kanto");
	}

	@Test
	void testExisteUsuario() throws NotFoundException, SQLException, Exception {
		assertTrue(metodos.existeUsuario("clemen"));
	}

}
