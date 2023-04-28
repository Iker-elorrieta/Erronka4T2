package controlador;

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
import manager.ManagerCajas;
import manager.ManagerMovimientos;
import manager.ManagerPC;
import manager.ManagerPokemon;
import manager.ManagerTipos;
import modelo.Caja;
import modelo.Jugador;
import modelo.MiPc;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;
import modelo.Usuario;
import utils.DBConexion;

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
	
	
	public void guardarLogin (Usuario user) {
		Date fecha = new Date();
		
		DateFormat dateFormat = new SimpleDateFormat("dd");
		DateFormat dateFormatMes = new SimpleDateFormat("MM");
		DateFormat dateFormatA = new SimpleDateFormat("yyyy");
		
		String dia = dateFormat.format(fecha.getTime());
		String mes = dateFormatMes.format(fecha.getTime());
		String anyo = dateFormatA.format(fecha.getTime());
		
		String fechaS =dia+"/"+mes+"/"+anyo;
		
		DateFormat min = new SimpleDateFormat("mm");
		DateFormat hora = new SimpleDateFormat("hh");
		
		String mins = min.format(fecha.getTime());
		String horas = hora.format(fecha.getTime());
		
		String horaS = horas+":"+mins;
		
		
		try {
			FileWriter fic = new FileWriter("LoginHistorial/historial.txt");
			
			fic.write("El usuario "+user.getNombre()+" con usuario "+user.getLogin()+" ha iniciado sesion el "+fechaS+" a la(s) "+horaS);
			
			fic.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}
	
	
	public void moverPokemonCajaEquipo (Jugador j, Pokemon p, Caja c) throws ArrayListLlenoException, SQLException {
		int tamanyo = j.getEquipo().size();
		if(tamanyo < 6) {
			c.getPokemon().remove(p);	
			j.getEquipo().add(p);
			
			try {
				conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
				comando = conexion.createStatement();

				comando.executeUpdate("update " + DBConexion.T_EQUIPOS + " set poke_id" +(tamanyo+1)+" = "+p.getId()+" where user_login = '"+j.getLogin()+"';");

				comando.executeUpdate("delete from "+DBConexion.T_CAJAS+" where pc_box_id ="+c.getId_caja()+";");

			} finally {
				registro.close();
				comando.close();
				conexion.close();
			}
			
		}else {
			throw new ArrayListLlenoException("Equipo lleno");
		}
		
	}
	
	public void anyadirPokemon(int opcion, MiPc pc)
			throws NotFoundException, SQLException, Exception, ArrayListLlenoException {
		// Se manda la caja seleccionada
		ManagerPokemon man = new ManagerPokemon();
		ManagerCajas cajas = new ManagerCajas();
		ArrayList<Caja> listaCajas = pc.getCajas();
		ArrayList<Pokemon> lista = new ArrayList<Pokemon>();
		lista = man.selectAll();
		Pokemon pokeSeleccionado = lista.get(opcion - 1);
		Caja caja = null;

		for (int i = 0; i < listaCajas.size(); i++) {
			if (listaCajas.get(i).getPokemon().size() < 30) {
				caja = listaCajas.get(i);
			}
		}
		if (caja != null) {
			caja.getPokemon().add(pokeSeleccionado);
			int pc_id = pc.getId_pc();
			int pc_box_id = caja.getId_caja();
			int poke_id = pokeSeleccionado.getId();
			try {
				conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
				comando = conexion.createStatement();
				comando.executeUpdate("Insert into" + DBConexion.T_CAJAS_POKEMON + "(pc_id,pc_box_id,poke_id) values ("
						+ pc_id + "," + pc_box_id + "," + poke_id + ");");
			} finally {
				registro.close();
				comando.close();
				conexion.close();
			}
		} else {
			throw new ArrayListLlenoException("PC LLENO");
		}
	}


	public void intercambiarFromEquipoToCaja(int pokeEquipo, int pokeCaja, Jugador player, Caja caja) {
		ArrayList<Pokemon> cajaElegida = caja.getPokemon();
		ArrayList<Pokemon> equipo = player.getEquipo();
		cajaElegida.get(pokeCaja);

	}
	

}
