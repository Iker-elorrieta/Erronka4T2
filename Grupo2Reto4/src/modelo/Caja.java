package modelo;

import java.util.ArrayList;

public class Caja {

	private int id_caja;
	private ArrayList<Pokemon> pokemon;
	
	public Caja() {
		
	}

	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}

	public void setPokemon(ArrayList<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}

	public int getId_caja() {
		return id_caja;
	}
	
}
