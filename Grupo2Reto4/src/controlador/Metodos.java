package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import excepciones.NotFoundException;
import manager.ManagerCajas;
import manager.ManagerMovimientos;
import manager.ManagerPC;
import manager.ManagerPokemon;
import manager.ManagerRegion;
import manager.ManagerTipos;
import modelo.Caja;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Region;
import modelo.Tipo;
import modelo.Usuario;

public class Metodos {

	Connection conexion;
	Statement comando;
	ResultSet registro;

	// Comprueba que el string enviado es vacio
	public boolean esVacio(String contenido) {
		return contenido.equals("");
	}

	public Tipo conseguirTipo(Integer id) throws NotFoundException, SQLException, Exception {
		ManagerTipos mt = new ManagerTipos();
		Tipo t = null;
		if (id != 0) {
			ArrayList<Tipo> tipos = mt.selectAll();
			t = tipos.get(id - 1);
		}

		return t;

	}

	public Movimiento conseguirMovimiento(int idM) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerMovimientos mm = new ManagerMovimientos();
		ArrayList<Movimiento> m = mm.selectAll();
		return m.get(idM - 2);
	}

	public Pokemon conseguirPokemon(int idpokemon) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerPokemon mp = new ManagerPokemon();
		ArrayList<Pokemon> pokemons = mp.selectAll();
		return pokemons.get(idpokemon - 1);
	}

	public Caja conseguirCajas(int idbox) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerCajas mc = new ManagerCajas();
		ArrayList<Caja> cajas = mc.selectAll();
		return cajas.get(idbox - 1);
	}

	public MiPc conseguirPc(int id) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerPC mpc = new ManagerPC();
		ArrayList<MiPc> pcs = mpc.selectAll();
		return pcs.get(id - 1);
	}

	public Usuario encontrarUsuario(ArrayList<Usuario> users, String login, String passw) {
		// TODO Auto-generated method stub
		Usuario usuario = null;

		for (Usuario user : users) {
			if (user.getLogin().equals(login)) {
				if (user.getPass().equals(passw)) {
					usuario = user;
				}
			}
		}

		return usuario;
	}
	
	public Region conseguirRegion(int id) throws NotFoundException, SQLException, Exception{
		ManagerRegion mr = new ManagerRegion();
		ArrayList<Region> res = mr.selectAll();
		return res.get(id-1);
	}

}
