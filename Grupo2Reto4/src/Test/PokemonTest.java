package Test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Modelo.Pokemon;
import Modelo.Tipo;

class PokemonTest {

	@Test
	void testPokemon() {
		Tipo[] tipos = {Tipo.planta, Tipo.veneno};
		Pokemon pokemon = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6);

		assertEquals(pokemon.getId(), 1);
		assertEquals(pokemon.getNombre_pokemon(), "bulbasaur");
		assertEquals(pokemon.getTipo()[0], Tipo.planta);
		assertEquals(pokemon.getAtt(), 5);
		assertEquals(pokemon.getDef(), 11);
		assertEquals(pokemon.getSatt(), 11);
		assertEquals(pokemon.getSdef(), 9);
		assertEquals(pokemon.getHp(), 20);
		assertEquals(pokemon.getVel(), 6);
		
		
	}

}
