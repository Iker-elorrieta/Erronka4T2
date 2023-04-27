package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.NotFoundException;
import manager.ManagerCajas;
import manager.ManagerMovimientos;
import manager.ManagerPC;
import manager.ManagerPokemon;
import manager.ManagerTipos;
import modelo.Caja;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;

public class Metodos {

	
	
	
	
	

	// Comprueba que el string enviado es vacio
	public boolean esVacio(String contenido) {
		return contenido.equals("");
	}

	public Tipo conseguirTipo(Integer id) throws NotFoundException, SQLException, Exception {
		ManagerTipos mt = new ManagerTipos();
		Tipo t = null;
		if (id != null) {
			ArrayList<Tipo> tipos = mt.selectAll();
			t = tipos.get(id - 1);
		}

		return t;

	}

	public Movimiento conseguirMovimiento(int idM) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerMovimientos mm = new ManagerMovimientos();
		ArrayList<Movimiento> m = mm.selectAll();
		return m.get(idM-2);
	}

	public Pokemon conseguirPokemon(int idpokemon) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerPokemon mp = new ManagerPokemon();
		ArrayList<Pokemon> pokemons = mp.selectAll();
		return pokemons.get(idpokemon-1);
	}

	public Caja conseguirCajas(int idbox) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerCajas mc = new ManagerCajas();
		ArrayList<Caja> cajas = mc.selectAll();
		return cajas.get(idbox-1);
	}

	public MiPc conseguirPc(int id) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerPC mpc = new ManagerPC();
		ArrayList<MiPc> pcs = mpc.selectAll();
		return pcs.get(id-1);
	}

	

}
