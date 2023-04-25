package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.NotFoundException;
import modelo.Caja;
import modelo.MiPc;
import modelo.Pokemon;
import utils.DBConexion;

public class Metodos {

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

		MiPc mipc = new MiPc(cajas);

		return mipc;
	}

}
