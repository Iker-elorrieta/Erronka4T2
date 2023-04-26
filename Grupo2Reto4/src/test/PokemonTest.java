package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class PokemonTest {

	@Test
	void testPokemon() {
		Tipo[] tipos = new Tipo[2];
		Tipo planta = new Tipo(3,"Planta");
		Tipo veneno = new Tipo(12,"Veneno");
		Tipo normal = new Tipo(1,"Normal");
		tipos[0] = planta;
		tipos[1] = veneno;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		Movimiento move1 = new Movimiento(1,"Latigo cepa",15,100,planta, 50);
		moveset.add(move1);
		Movimiento move2 = new Movimiento(2,"Placaje",40,100,normal, 40);
		moveset.add(move2);
		Movimiento move3 = new Movimiento(3,"Absorber",20,100,planta, 20);
		moveset.add(move3);
		Movimiento move4 = new Movimiento(4,"Bomba Lodo",15,100,veneno, 90);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);
		Pokemon pokemon2 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);
		Pokemon pokemon3 = null;

		assertEquals(pokemon1.getId(), 1);
		assertEquals(pokemon1.getNombre_pokemon(), "bulbasaur");
		assertEquals(pokemon1.getTipo()[0], planta);
		assertEquals(pokemon1.getAtt(), 5);
		assertEquals(pokemon1.getDef(), 11);
		assertEquals(pokemon1.getSatt(), 11);
		assertEquals(pokemon1.getSdef(), 9);
		assertEquals(pokemon1.getHp(), 20);
		assertEquals(pokemon1.getVel(), 6);	
		assertEquals(pokemon1.getMovimientos().get(0).getNombre(),"Latigo cepa");
		assertEquals(pokemon1.toString(), pokemon2.toString());	
		assertEquals(pokemon1.hashCode(), pokemon2.hashCode());	
		
		pokemon1.equals(pokemon3);
		pokemon1.equals(pokemon2);
		pokemon1.equals(planta);
		pokemon2 = new Pokemon(2, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);
		pokemon3 = pokemon1;
		pokemon1.equals(pokemon2);
		pokemon1.equals(pokemon3);
		
	}

}
