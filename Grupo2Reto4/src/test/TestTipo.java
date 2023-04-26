package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

class TestTipo {

	@Test
	void testTipo() {
		Tipo planta1 = new Tipo(3, "Planta");
		Tipo planta2 = new Tipo(3, "Planta");

		assertEquals(planta1.getId(), 3);
		assertEquals(planta1.getNombre_tipo(), "Planta");
		assertEquals(planta1.toString(), planta2.toString());
		assertEquals(planta1.hashCode(), planta2.hashCode());

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

		Tipo[] tipos = new Tipo[2];
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo normal = new Tipo(1, "Normal");
		tipos[0] = planta;
		tipos[1] = veneno;
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		Movimiento move1 = new Movimiento(1, "Latigo cepa", 15, 100, planta, 50);
		moveset.add(move1);
		Movimiento move2 = new Movimiento(2, "Placaje", 40, 100, normal, 40);
		moveset.add(move2);
		Movimiento move3 = new Movimiento(3, "Absorber", 20, 100, planta, 20);
		moveset.add(move3);
		Movimiento move4 = new Movimiento(4, "Bomba Lodo", 15, 100, veneno, 90);
		moveset.add(move4);
		Pokemon pokemon = new Pokemon(1, "bulbasaur", tipos, 20, 5, 11, 11, 9, 6, moveset);

		planta1.equals(pokemon);

	}

}
