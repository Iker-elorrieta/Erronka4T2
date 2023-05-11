package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import excepciones.ArrayListLlenoException;
import excepciones.NotFoundException;

import modelo.Caja;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Pokemon;
import modelo.Usuario;
import utils.DBConexion;

public class MetodosVista {

	Connection conexion;
	Statement comando;
	ResultSet registro;

	public void guardarLogin(Usuario user) {
		Date fecha = new Date();

		DateFormat dateFormat = new SimpleDateFormat("dd");
		DateFormat dateFormatMes = new SimpleDateFormat("MM");
		DateFormat dateFormatA = new SimpleDateFormat("yyyy");

		String dia = dateFormat.format(fecha.getTime());
		String mes = dateFormatMes.format(fecha.getTime());
		String anyo = dateFormatA.format(fecha.getTime());

		String fechaS = dia + "/" + mes + "/" + anyo;

		DateFormat min = new SimpleDateFormat("mm");
		DateFormat hora = new SimpleDateFormat("hh");

		String mins = min.format(fecha.getTime());
		String horas = hora.format(fecha.getTime());

		String horaS = horas + ":" + mins;

		String cadena = "";
		try {
			FileReader fic = new FileReader("LoginHistorial/historial.txt");
			BufferedReader buf = new BufferedReader(fic);
			String linea;
			while ((linea = buf.readLine()) != null)
				cadena += linea + "\r\n";

			buf.close();
			fic.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			FileWriter fic = new FileWriter("LoginHistorial/historial.txt");

			fic.write(cadena + "El usuario " + user.getNombre() + " con login " + user.getLogin()
					+ " ha iniciado sesion el " + fechaS + " a la(s) " + horaS);

			fic.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void moverPokemonCajaEquipo(Jugador j, Pokemon p, Caja c) throws ArrayListLlenoException, SQLException {
		int tamanyo = j.getEquipo().size();
		if (tamanyo < 6) {
			c.getPokemon().remove(p);
			j.getEquipo().add(p);

			try {
				conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
				comando = conexion.createStatement();

				comando.executeUpdate("update " + DBConexion.T_EQUIPOS + " set poke_id" + (tamanyo + 1) + " = "
						+ p.getId() + " where user_login = '" + j.getLogin() + "';");

				comando.executeUpdate(
						"delete from " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id =" + c.getId_caja() + ";");

			} finally {

				comando.close();
				conexion.close();
			}

		} else {
			throw new ArrayListLlenoException("Equipo lleno");
		}

	}

	public void anyadirPokemon(int opcion, MiPc pc, Caja caja)
			throws NotFoundException, SQLException, Exception, ArrayListLlenoException {
		// Se manda la caja seleccionada

		if (caja.getPokemon().size() < 30) {

			int pc_id = pc.getId_pc();
			int pc_box_id = caja.getId_caja();
			int poke_id = opcion;
			try {
				conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
				comando = conexion.createStatement();
				comando.executeUpdate("Insert into " + DBConexion.T_CAJAS_POKEMON + " (pc_id, pc_box_id, poke_id) values ("
						+ pc_id + ", " + pc_box_id + ", " + poke_id + ");");
			} finally {
				conexion.close();
			}
		} else {
			throw new ArrayListLlenoException("CAJA LLENA.");
		}
	}

	public void liberarPokemon(int opcion, MiPc pc, Caja caja) throws NotFoundException, SQLException, Exception {
		// Se manda la caja seleccionada

		if (opcion != 0) {

			try {
				conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
				comando = conexion.createStatement();
				comando.executeUpdate("delete from " + DBConexion.T_CAJAS_POKEMON + " where pc_id=" + pc.getId_pc()
						+ " and box_id=" + caja.getId_caja() + " and poke_id=" + opcion + ";");
			} finally {
				conexion.close();
			}
		} else {
			throw new NotFoundException("No hay pokemon en esa celda.");
		}
	}

	public Jugador intercambiarFromEquipoToCaja(int pokeEquipo, int pokeCaja, Jugador player, Caja caja) {
		ArrayList<Pokemon> cajaElegida = caja.getPokemon();
		ArrayList<Pokemon> equipo = player.getEquipo();
		// sacamos el pokemon y su posicion de los 2 arrayList
		Pokemon poke1 = cajaElegida.get(pokeCaja);
		int posicion1 = cajaElegida.indexOf(poke1);
		Pokemon poke2 = equipo.get(pokeEquipo);
		int posicion2 = equipo.indexOf(poke2);
		// borramos los pokemon de dichas posiciones
		cajaElegida.remove(posicion1);
		equipo.remove(posicion2);
		// añadimos los pokemons
		equipo.add(poke1);
		cajaElegida.add(poke2);
		caja= new Caja(caja.getId_caja(), cajaElegida);
		
		player.getPc().getCajas().set(pokeCaja, caja);
		player.getEquipo().set(pokeEquipo, poke1);
		
		return player;
	}

}
