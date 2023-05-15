package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import excepciones.NotFoundException;
import manager.ManagerJugador;
import manager.ManagerRegion;
import manager.ManagerTipos;
import modelo.Jugador;
import modelo.Region;
import modelo.Tipo;
import modelo.Usuario;

/**
 * @author UNAI-PC
 *
 */
public class Metodos {

	/**
	 * Constante para iniciar conexion contra el servidor o localhost
	 */
	Connection conexion;
	/**
	 * Constante para lanzar comandos
	 */
	Statement comando;
	/**
	 * Constante para recoger los datos del comando previo
	 */
	ResultSet registro;

	// Comprueba que el string enviado es vacio
	/**
	 * @param contenido se manda un String con el contenido a comparar
	 * @return se devuelve el resultado de la comparativa del String con un espacio en blanco 
	 */
	public boolean esVacio(String contenido) {
		return contenido.equals("");
	}

	/**
	 * @param id un integer para verificar si el tipo existe o no con ese id
	 * @return devuelve un Tipo completo en caso de que exista o un objeto de tipo Tipo vacio en caso de que no
	 * @throws NotFoundException error para verificar si algo no se encuentra
	 * @throws SQLException error para validar los errores de conexion a SQL
	 * @throws Exception conjunto de errores varios
	 */
	public Tipo conseguirTipo(Integer id) throws NotFoundException, SQLException, Exception {
		ManagerTipos mt = new ManagerTipos();
		Tipo t = null;
		if (id != 0) {
			ArrayList<Tipo> tipos = mt.selectAll();
			t = tipos.get(id - 1);
		}
		return t;
	}



	/**
	 * @param users listado de usuarios completo
	 * @param login String para verificar el usuario que buscamos
	 * @param passw String para verificar la pass del usuario que busscamos
	 * @return en caso de que ambos esten bien, devuelve un usuario con los datos de la base de datos, en caso de que no, devuelve un usuario null
	 */
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

	/**
	 * @param id int para buscar una region por su id 
	 * @return devuelve una posicion -1 de lo que haya obtenido con ese id en la lista completa de regiones
	 * @throws NotFoundException error para verificar si algo no se encuentra
	 * @throws SQLException error para validar los errores de conexion a SQL
	 * @throws Exception conjunto de errores varios
	 */
	public Region conseguirRegion(int id) throws NotFoundException, SQLException, Exception {
		ManagerRegion mr = new ManagerRegion();
		ArrayList<Region> res = mr.selectAll();
		return res.get(id - 1);
	}

	/**
	 * @param login String que contiene el nombre de usuario
	 * @return devuelve un boolean que solo se pone a true en caso de que el String previo exista en la base de datos
	 * @throws NotFoundException error para verificar si algo no se encuentra
	 * @throws SQLException error para validar los errores de conexion a SQL
	 * @throws Exception conjunto de errores varios
	 */
	public boolean existeUsuario(String login) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerJugador mu = new ManagerJugador();
		ArrayList<Jugador> jugadores = mu.selectAll();

		boolean existe = false;
		if (jugadores.size() != 0) {
			for (Jugador jugador : jugadores) {
				if (jugador.getLogin().equals(login))
					existe = true;
			}
		}
		return existe;
	}

}
