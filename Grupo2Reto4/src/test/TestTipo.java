package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class TestTipo {
	
	Tipo planta1 = new Tipo(3, "Planta");
	Tipo planta2 = new Tipo(3, "Planta");
	
	Movimiento move1;
	Movimiento move2;
	Movimiento move3;
	Movimiento move4;
	
	Tipo planta = new Tipo(3, "Planta");
	Tipo veneno = new Tipo(12, "Veneno");
	Tipo normal = new Tipo(1, "Normal");
	
	
	@Test
	void testTipoConstructor() {

		
		assertEquals(planta1.getId(), 3);
		assertEquals(planta1.getNombre_tipo(), "Planta");

		Tipo[] tipos = new Tipo[2];
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
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
		Pokemon pokemon = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);

		planta1.equals(pokemon);

	}
	
	@Test
	void testTipoToString() {
		assertEquals(planta1.toString(), planta2.toString());
	}
	
	@Test
	void testTipoEquals() {
		planta1.equals(planta2);
		Tipo planta3 = null;
		planta1.equals(planta3);
		planta3 = planta1;
		planta1.equals(planta3);
		planta3 = new Tipo(2, "Planta");
		planta1.equals(planta3);
		planta3 = new Tipo(3, "Agua");
		planta1.equals(planta3);
		planta3 = new Tipo(2, "Agua");
		planta1.equals(planta3);
	}
	
	@Test
	void testTipoHashCode() {
		assertEquals(planta1.hashCode(), planta2.hashCode());
		
	}

}
