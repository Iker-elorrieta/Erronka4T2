package modelo;

import java.util.ArrayList;

public class Pokemon {
	
	private int id;
	private String nombre_pokemon;
	private Tipo[] tipo;
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