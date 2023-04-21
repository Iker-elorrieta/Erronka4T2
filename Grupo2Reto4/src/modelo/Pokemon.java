package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Pokemon {
	
	private int id;
	private String nombre_pokemon;
	private Tipo[] tipo;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(tipo);
		result = prime * result + Objects.hash(att, def, hp, id, movimientos, nombre_pokemon, satt, sdef, vel);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return att == other.att && def == other.def && hp == other.hp && id == other.id
				&& Objects.equals(movimientos, other.movimientos)
				&& Objects.equals(nombre_pokemon, other.nombre_pokemon) && satt == other.satt && sdef == other.sdef
				&& Arrays.equals(tipo, other.tipo) && vel == other.vel;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre_pokemon=" + nombre_pokemon + ", tipo=" + Arrays.toString(tipo) + ", hp="
				+ hp + ", att=" + att + ", def=" + def + ", satt=" + satt + ", sdef=" + sdef + ", vel=" + vel
				+ ", movimientos=" + movimientos + "]";
	}

	private int hp;
	private int att;
	private int def;
	private int satt;
	private int sdef;
	private int vel;
	private ArrayList<Movimiento> movimientos;
	
	public int getId() {
		return id;
	}

	public String getNombre_pokemon() {
		return nombre_pokemon;
	}

	public Tipo[] getTipo() {
		return tipo;
	}

	public int getHp() {
		return hp;
	}

	public int getAtt() {
		return att;
	}

	public int getDef() {
		return def;
	}

	public int getSatt() {
		return satt;
	}

	public int getSdef() {
		return sdef;
	}

	public int getVel() {
		return vel;
	}

	public Pokemon(int id, String nombre_pokemon, Tipo[] tipo, int hp, int att, int def, int satt, int sdef, int vel, ArrayList<Movimiento> moveset) {
		super();
		this.id = id;
		this.nombre_pokemon = nombre_pokemon;
		this.tipo = tipo;
		this.hp = hp;
		this.att = att;
		this.def = def;
		this.satt = satt;
		this.sdef = sdef;
		this.vel = vel;
		this.movimientos = moveset;
	}

	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	
	
}
