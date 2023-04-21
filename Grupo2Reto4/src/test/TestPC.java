package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class TestPC {

	@Test
	void test() {
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
		
		ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2);
		
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		pokemon.add(pokemon1);
		pokemon.add(pokemon2);
		
		Caja caja = new Caja(1, pokemon);
		
		ArrayList<Caja> cajas =new ArrayList<Caja>();
		cajas.add(caja);
		
		MiPc pc = new MiPc(cajas);
		
		assertEquals(pc.getCajas().get(0).getPokemon().get(1).getNombre_pokemon(), "Ivysaur");
		
	}

}
