package modelo;

import java.util.ArrayList;

public class Caja {

	private int id_caja;
	private ArrayList<Pokemon> pokemon;
	
	public Caja(int id_caja, ArrayList<Pokemon> pokemon) {
		this.setId_caja(id_caja);
		this.setPokemon(pokemon);
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
	
	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}
	
}
