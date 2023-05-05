package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import excepciones.NotFoundException;
import manager.ManagerPokemon;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;

class ManagerPokemonTest {
	ManagerPokemon mp = new ManagerPokemon();
	
	@Test
	void testSelectAll() throws NotFoundException, SQLException, Exception {
		ArrayList<Pokemon> pokemon = mp.selectAll();
		assertEquals(pokemon.get(0).getNombre_pokemon(), "Bulbasaur");
	}
	
	@Test
	void testInsert() throws SQLException, Exception {
		Region r = new Region(1,"Kanto");
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		Tipo[] tipos = { planta, veneno };
		Movimiento move1;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		Pokemon pokemon = new Pokemon(722, "prueba", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
		mp.delete(pokemon);
		mp.insert(pokemon);
		ArrayList<Pokemon> pokemons = mp.selectAll();
		assertEquals(pokemons.get(pokemons.size()-1).getNombre_pokemon(), "prueba");
	}
	
	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		
		Region r = new Region(1,"Kanto");
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		Tipo[] tipos = { planta, veneno };
		Movimiento move1;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		move1 = new Movimiento(33, "tackle", 35, 100, normal, 50);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);
		moveset.add(move1);

		Pokemon pokemon1 = new Pokemon(722, "prueba", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
		Pokemon pokemon2 = new Pokemon(722, "prueba2", tipos, 20, 5, 11, 11, 9, 6, moveset, r);
		
		mp.update(pokemon1, pokemon2);
		
		ArrayList<Pokemon> pokemons = mp.selectAll();
		assertEquals(pokemons.get(pokemons.size()-1).getNombre_pokemon(), "prueba2");
		
		mp.delete(pokemon2);
		
	}

	

}
