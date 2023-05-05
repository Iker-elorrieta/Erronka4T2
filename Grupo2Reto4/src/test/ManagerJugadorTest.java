package test;



import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.ManagerJugador;
import modelo.Jugador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;

class ManagerJugadorTest {
	
	ManagerJugador mj = new ManagerJugador();
	@Test
	void testSelectAll() throws SQLException, Exception {
		
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

		Pokemon pokemon = new Pokemon(1, "Bulbasaur", tipos, 45, 49, 49, 45, 65, 65, moveset, r);
		
		
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		equipo.add(pokemon);
		
		Jugador j = new Jugador("Igor","aaaaa","123", equipo, null, false);
		
		mj.insert(j);
		
		//ArrayList<Jugador> js = mj.selectAll();
		
		
		
		
	}

	

}
