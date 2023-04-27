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
import manager.ManagerTipos;
import modelo.Caja;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;
import utils.DBConexion;

public class Metodos {

	ManagerTipos mt = new ManagerTipos();
	ManagerMovimientos mm = new ManagerMovimientos();
	ManagerPokemon mp = new ManagerPokemon();
	ManagerCajas mc = new ManagerCajas();
	ManagerPC mpc = new ManagerPC();

	// Comprueba que el string enviado es vacio
	public boolean esVacio(String contenido) {
		return contenido.equals("");
	}

	public Pokemon[] selecEquipo(Connection conexion, String login, String tEquipos)
			throws SQLException, NotFoundException, Exception {
		Pokemon[] equipo = new Pokemon[6];

		try {

			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM " + tEquipos + " where user_login=" + login + ";");

			while (registro.next() == true) {

				for (int i = 0; i < 7; i++) {
					if (String.valueOf(registro.getInt("poke_id")) != null) {
						int poke_id = registro.getInt("poke_id");
						equipo[i] = selecPokemon(conexion, poke_id);
					}
					equipo[i] = null;
				}

			}

		} finally {

			conexion.close();
		}
		return equipo;
	}

	private Pokemon selecPokemon(Connection conexion, int poke_id) throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub

		try {

			Statement comando = conexion.createStatement();
			ResultSet registro = comando
					.executeQuery("SELECT * FROM " + DBConexion.T_POKEMON + " where poke_id=" + poke_id + ";");

			while (registro.next() == true) {

			}

		} finally {
			conexion.close();
		}

		return null;
	}

	public MiPc selecMiPC(Connection conexion, String login, String tMipc)
			throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Caja> cajas = new ArrayList<Caja>();

		try {
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM " + tMipc + " where user_login=" + login + ";");

			while (registro.next() == true) {

			}

		} finally {
			conexion.close();
		}

		MiPc mipc = new MiPc(cajas, );

		return mipc;
	}

	public Tipo conseguirTipo(Integer id) throws NotFoundException, SQLException, Exception {
		Tipo t = null;
		if (id != null) {
			ArrayList<Tipo> tipos = mt.selectAll();
			t = tipos.get(id - 1);
		}

		return t;

	}

	public Movimiento conseguirMovimiento(int idM) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Movimiento> m = mm.selectAll();
		return m.get(idM-2);
	}

	public Pokemon conseguirPokemon(int idpokemon) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
		ArrayList<Pokemon> pokemons = mp.selectAll();
		return pokemons.get(idpokemon-1);
	}

	public Caja conseguirCajas(int idbox) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Caja> cajas = mc.selectAll();
		return cajas.get(idbox-1);
	}

	public MiPc conseguirPc(int id) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ArrayList<MiPc> pcs = mpc.selectAll();
		return pcs.get(id-1);
	}

	

}
