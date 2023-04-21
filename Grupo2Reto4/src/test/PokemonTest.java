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
		Pokemon pokemon = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);

		assertEquals(pokemon.getId(), 1);
		assertEquals(pokemon.getNombre_pokemon(), "bulbasaur");
		assertEquals(pokemon.getTipo()[0], planta);
		assertEquals(pokemon.getAtt(), 5);
		assertEquals(pokemon.getDef(), 11);
		assertEquals(pokemon.getSatt(), 11);
		assertEquals(pokemon.getSdef(), 9);
		assertEquals(pokemon.getHp(), 20);
		assertEquals(pokemon.getVel(), 6);	
		assertEquals(pokemon.getMovimientos().get(0).getNombre(),"Latigo cepa");
	}

}
