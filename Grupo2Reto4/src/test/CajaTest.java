package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class CajaTest {

	@Test
	void testCaja() {
		
		Tipo[] tipos = {Tipo.planta, Tipo.veneno};
		ArrayList<Movimiento> moveset1 = new ArrayList<Movimiento>();
		Movimiento move1 = new Movimiento(1,"Latigo cepa",15,100,Tipo.planta, 50);
		moveset1.add(move1);
		Movimiento move2 = new Movimiento(2,"Placaje",40,100,Tipo.normal, 40);
		moveset1.add(move2);
		Movimiento move3 = new Movimiento(3,"Absorber",20,100,Tipo.planta, 20);
		moveset1.add(move3);
		Movimiento move4 = new Movimiento(4,"Bomba Lodo",15,100,Tipo.veneno, 90);
		moveset1.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset1);
		
		ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset2);
		
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		pokemon.add(pokemon1);
		pokemon.add(pokemon2);
		
		Caja caja = new Caja(1, pokemon);
		assertEquals(caja.getId_caja(),1);
		assertEquals(caja.getPokemon().get(1),pokemon2);
		
	}

}
