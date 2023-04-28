package vista;

import controlador.Metodos;
import modelo.Jugador;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Metodos m = new Metodos();
		
		Jugador j = new Jugador("aaa", "as", null, null, null);
		
		m.guardarLogin(j);
	}

}
