package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.MetodosVista;
import excepciones.ArrayListLlenoException;
import excepciones.NotFoundException;
import manager.ManagerCajas;
import manager.ManagerJugador;
import manager.ManagerPC;
import modelo.Caja;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;

class TestMetodosVista {
	MetodosVista mv = new MetodosVista();
	Region reg = new Region(1,"Kanto");
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Tipo bicho = new Tipo(7,"Bicho");
	Tipo[] tipos = { planta, veneno };
	Movimiento move1 = new Movimiento(15, "cut", 30, 95, normal, 50);
	Movimiento move2 = new Movimiento(20, "bind", 20, 85, normal, 15);
	Movimiento move3 = new Movimiento(300, "mud-sport", 0, 100, normal, 0);
	Movimiento move4 = new Movimiento(450, "bug-bite", 20, 100, bicho, 60);
	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	ArrayList<Pokemon> listaPokemon = new ArrayList<Pokemon>();
	String nombre = "Bulbasaur";
	Pokemon pokemon1 = new Pokemon(1, nombre, tipos, 20, 5, 11, 11, 9, 6, moveset, reg);
	
	ArrayList<Caja> cajas = new ArrayList<Caja>();
	MiPc pcTest = new MiPc(cajas, 1);
	ManagerPC pc = new ManagerPC();
	ArrayList<MiPc> listadoPC = null;
	ManagerJugador playerMN = new ManagerJugador();
	ManagerCajas cajaMN = new ManagerCajas();
	
	@Test
	void testAnyadirPokemon() throws NotFoundException, ArrayListLlenoException, SQLException, Exception {
		
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon1);
		Jugador jugadorNEW = new Jugador("PruebaAnyadirPKMNnombre", "PruebaAnyadirPKMNlogin", "123", equipo, null, false);
		playerMN.insert(jugadorNEW);
		
		ArrayList<Jugador> jugadores=playerMN.selectAll();
		for(Jugador jugador : jugadores) {
			if(jugador.getLogin().equals("PruebaAnyadirPKMNlogin"))
				jugadorNEW=jugador;
		}
		
		MiPc pcGet = jugadorNEW.getPc();
		
		Pokemon pokemon2 = new Pokemon(493, "G.O.D.", null, 1,1,1,1,1,1, null, null);	
		
		mv.anyadirPokemon(pokemon2.getId(), pcGet, pcGet.getCajas().get(0));
		
		jugadores=playerMN.selectAll();
		for(Jugador jugador : jugadores) {
			if(jugador.getLogin().equals("PruebaAnyadirPKMNlogin"))
				jugadorNEW=jugador;
		}
		
		assertEquals(jugadorNEW.getPc().getCajas().get(0).getPokemon().get(0).getNombre_pokemon(), "Arceus");
	}
	
	@Test
	void testIntercambiarEquipoToCaja() throws NotFoundException, SQLException, Exception {
		Jugador jugadorNEW =null;
		ArrayList<Jugador> jugadores=playerMN.selectAll();
		for(Jugador jugador : jugadores) {
			if(jugador.getLogin().equals("PruebaAnyadirPKMNlogin"))
				jugadorNEW=jugador;
		}
		int posEquipo=0;
		int posCaja=0;
		

		jugadorNEW=mv.intercambiarFromEquipoToCaja(posEquipo, posCaja, jugadorNEW, jugadorNEW.getPc().getCajas().get(0));
		
		ArrayList<Pokemon> equipoNew = jugadorNEW.getEquipo();
		
		assertEquals(jugadorNEW.getPc().getCajas().get(0).getPokemon().get(0).getNombre_pokemon(),"Bulbasaur");
		assertEquals(equipoNew.get(0).getNombre_pokemon(),"Arceus");
		
		playerMN.delete(jugadorNEW);
	}

}
