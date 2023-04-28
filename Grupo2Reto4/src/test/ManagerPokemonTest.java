package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import excepciones.NotFoundException;
import manager.ManagerPokemon;
import modelo.Generacion;
import modelo.Movimiento;
import modelo.Pokemon;
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
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		Tipo[] tipos = { planta, veneno };
		Movimiento move1;
		Movimiento move2;
		Movimiento move3;
		Movimiento move4;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50);
		moveset.add(move1);
		move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40);
		moveset.add(move2);
		move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20);
		moveset.add(move3);
		move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(650, "bolbasour", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		mp.insert(pokemon1);
		ArrayList<Pokemon> pokemon = mp.selectAll();
		assertEquals(pokemon.get(649).getNombre_pokemon(), "Bolbasour");
	}

	@Test
	void testUpdate() throws NotFoundException, SQLException, Exception {
		ArrayList<Pokemon> pokemon = mp.selectAll();
		Pokemon bulbasaur = pokemon.get(0);
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		Tipo[] tipos = { planta, veneno };
		Movimiento move1;
		Movimiento move2;
		Movimiento move3;
		Movimiento move4;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50);
		moveset.add(move1);
		move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40);
		moveset.add(move2);
		move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20);
		moveset.add(move3);
		move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bolbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, Generacion.Kanto);
		mp.update(pokemon.get(0), pokemon1);
		pokemon = mp.selectAll();
		assertEquals(pokemon.get(0).getNombre_pokemon(), "Bolbasaur");
		mp.update(pokemon.get(0), bulbasaur);
		
	}

	@Test
	void testDelete() throws NotFoundException, SQLException, Exception {
		ArrayList<Pokemon> pokemon = mp.selectAll();
		Pokemon bulbasaur = pokemon.get(0);
		mp.delete(pokemon.get(0));
		pokemon = mp.selectAll();
		assertEquals(pokemon.indexOf(bulbasaur), -1);
	}

}
