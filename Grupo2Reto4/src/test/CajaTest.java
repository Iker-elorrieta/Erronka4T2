package test;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.Generacion;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class CajaTest {
	Tipo[] tipos = new Tipo[2];
	Tipo planta = new Tipo(3,"Planta");
	Tipo veneno = new Tipo(12,"Veneno");
	Tipo normal = new Tipo(1,"Normal");
	Movimiento move1 = new Movimiento(1,"Latigo cepa",15,100,planta, 50);
	Movimiento move2 = new Movimiento(2,"Placaje",40,100,normal, 40);
	Movimiento move3 = new Movimiento(3,"Absorber",20,100,planta, 20);
	Movimiento move4 = new Movimiento(4,"Bomba Lodo",15,100,veneno, 90);
	ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
	ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
	ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
	Caja caja = new Caja(1, pokemon);
	Caja caja2 = new Caja(2, pokemon);
	Caja caja3 = new Caja(2, pokemon);
	
	@Test
	void testCajaConstructor() {
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
		Pokemon pokemon2 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, Generacion.Kanto);
		
		pokemon.add(pokemon1);
		pokemon.add(pokemon2);
		Caja caja = new Caja(1, pokemon);
		assertEquals(caja.getPokemon().get(0), pokemon2);
		assertEquals(caja.getId_caja(), 1);
		
	}
	
	
	@Test
	void testCajaEquals() {
		caja.equals(caja2);
		caja.equals(null);
		caja.equals(caja3);
		caja.equals(pokemon);
		caja2 = new Caja(2, pokemon);
		caja.equals(caja2);
		caja2 = new Caja(1, null);
		caja.equals(caja2);
		caja2=caja;
		caja.equals(caja2);
	}
	
	@Test
	void testCajaToString() {
		assertEquals(caja.toString(),caja.toString());
	}

	@Test
	void testCajaHashCode() {
		assertEquals(caja.hashCode(),caja.hashCode());
	}
}
