package modelo;

import java.util.ArrayList;
import java.util.Objects;

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

	@Override
	public String toString() {
		return "Caja [id_caja=" + id_caja + ", pokemon=" + pokemon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_caja, pokemon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return id_caja == other.id_caja && Objects.equals(pokemon, other.pokemon);
	}
	
}
