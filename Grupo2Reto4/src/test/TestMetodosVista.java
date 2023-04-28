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
import modelo.Generacion;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class TestMetodosVista {
	MetodosVista mv = new MetodosVista();
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
	String nombre = "bulbasaur";
	Pokemon pokemon1 = new Pokemon(1, nombre, tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
	Caja caja = new Caja(1, listaPokemon);
	ArrayList<Caja> cajas = new ArrayList<Caja>();
	MiPc pcTest = new MiPc(cajas, 1);
	ManagerPC pc = new ManagerPC();
	ArrayList<MiPc> listadoPC = null;
	ManagerJugador playerMN = new ManagerJugador();
	ManagerCajas cajaMN = new ManagerCajas();
	
	@Test
	void testAnyadirPokemon() throws NotFoundException, ArrayListLlenoException, SQLException, Exception {
		listadoPC= 	pc.selectAll();
		MiPc pcGet = listadoPC.get(0);
		//--> 
		moveset.add(move1);
		moveset.add(move2);
		moveset.add(move3);
		moveset.add(move4);
		listaPokemon.add(pokemon1);
		//-->	
		mv.anyadirPokemon(pokemon1.getId(), pcGet);
		
		ArrayList<MiPc>listadoPC2 = pc.selectAll();
		MiPc pcGet2 = listadoPC.get(0);
		assertEquals(pcGet2.getCajas().get(0).getPokemon().get(0).getNombre_pokemon(),nombre);
	}
	
	@Test
	void testIntercambiarEquipoToCaja() throws NotFoundException, SQLException, Exception {
		
		int posEquipo = 0;
		int posCaja = 0;
		cajas.add(caja);
		Jugador player = playerMN.selectAll().get(0);
		ArrayList<Pokemon> equipoOld = player.getEquipo();
		Pokemon pokeOld = equipoOld.get(posEquipo);
		Caja caja = playerMN.selectAll().get(0).getPc().getCajas().get(0);
		ArrayList<Pokemon> cajaOld = caja.getPokemon();
		Pokemon pokeOldCaja = cajaOld.get(posCaja);
		//-->
		mv.intercambiarFromEquipoToCaja(posEquipo, posCaja, player, caja);
		//<-- Utilizaremos siempre la posicion 0
		player = playerMN.selectAll().get(0);
		ArrayList<Pokemon> equipoNew = player.getEquipo();
		ArrayList<Caja> cajasNew = playerMN.selectAll().get(0).getPc().getCajas();
		caja = cajasNew.get(0);
		
		assertEquals(pokeOldCaja.getNombre_pokemon(),"bulbasaur");
		assertEquals(equipoNew.get(equipoNew.size()-1),"bulbasaur");
		//WIP
	}

}
