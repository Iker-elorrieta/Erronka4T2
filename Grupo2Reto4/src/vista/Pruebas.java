package vista;

import controlador.Metodos;
import controlador.MetodosVista;
import modelo.Jugador;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetodosVista m = new MetodosVista();
		
		Jugador j = new Jugador("aaa", "as", null, null, null);
		
		m.guardarLogin(j);
	}

}
