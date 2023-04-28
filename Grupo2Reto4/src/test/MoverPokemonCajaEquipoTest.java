package test;



import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import excepciones.ArrayListLlenoException;
import manager.ManagerCajas;
import manager.ManagerJugador;
import manager.ManagerPC;
import modelo.Caja;
import modelo.Generacion;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;


class MoverPokemonCajaEquipoTest {
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	Metodos m = new Metodos();
	ManagerJugador mj = new ManagerJugador();
	ManagerPC mpc = new ManagerPC();
	ManagerCajas mc = new ManagerCajas();
	@Test
	void test() throws Exception {
		
		Tipo planta = new Tipo(3, "Planta");
		Tipo veneno = new Tipo(12, "Veneno");
		Tipo[] tipos = { planta, veneno };
		
		ArrayList<Movimiento> moveset = new ArrayList<Movimiento>();
		
		
		
		Pokemon p = new Pokemon(1111, "bulbasaur", tipos,45,49,49,45,65,65,moveset, Generacion.Kanto);
		
		ArrayList<Pokemon> plista = new ArrayList<Pokemon>();
		
		
		plista.add(p);
		Caja c = new Caja(1,plista);
		
		ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
		
		ArrayList<Caja> cajas = new ArrayList<Caja>();
		
		cajas.add(c);
		MiPc pc = new MiPc(cajas,1);
		
		Jugador j = new Jugador("prueba1", "prueba", "123", equipo, pc);
		
		mj.insert(j);
		
		mpc.insert(pc);
		
		mc.insert(c);
		
		try {
			m.moverPokemonCajaEquipo(j, p, c);
			
			
		} catch (ArrayListLlenoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(j.getEquipo().get(0),p);
		assertEquals(j.getPc().getCajas().get(0).getPokemon().get(0),null);
		
		mc.delete(c);
		mpc.delete(pc);
		mj.delete(j);
		
	}

}
