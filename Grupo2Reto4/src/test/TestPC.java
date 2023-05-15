package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Caja;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;

class TestPC {
	
	Region reg = new Region(1,"Kanto");
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	Movimiento move1;
	Movimiento move2;
	Movimiento move3;
	Movimiento move4;
	ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
	ArrayList<Caja> cajas = new ArrayList<Caja>();
	MiPc pc1 = new MiPc(cajas, 1);
	MiPc pc2 = new MiPc(cajas, 1);
	MiPc pc3 = null;
	

	@Test
	void TestPCConstructor() {
		Tipo[] tipos = new Tipo[2];

		tipos[0] = planta;
		tipos[1] = veneno;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50);
		moveset.add(move1);
		move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40);
		moveset.add(move2);
		move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20);
		moveset.add(move3);
		move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90);
		moveset.add(move4);
		Pokemon pokemon1 = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset, reg);

		ArrayList<Movimiento> moveset2 = new ArrayList<Movimiento>();
		moveset2.add(move1);
		moveset2.add(move2);
		moveset2.add(move3);
		moveset2.add(move4);
		Pokemon pokemon2 = new Pokemon(1, "Ivysaur", tipos, 20, 5, 11, 11, 9, 6, moveset2, reg);

		pokemon.add(pokemon1);
		pokemon.add(pokemon2);

		Caja caja = new Caja(1, pokemon);

		cajas.add(caja);

		assertEquals(pc1.getCajas().get(0).getPokemon().get(1).getNombre_pokemon(), "Ivysaur");
		assertEquals(pc1.getId_pc(), 1);
	}

	@Test
	void TestPCToString() {
		assertEquals(pc1.toString(), pc2.toString());
	}

	@Test
	void TestPCEquals() {
		pc1.equals(pc3);
		pc1.equals(pc2);
		pc3 = pc1;
		pc1.equals(planta);
		pc1.equals(pc3);
		pc2 = new MiPc(cajas, 2);
		pc1.equals(pc2);
		pc2 = new MiPc(null, 2);
		pc1.equals(pc2);
	}

	@Test
	void TestPCHashCode() {
		assertEquals(pc1.hashCode(), pc2.hashCode());
	}

}
