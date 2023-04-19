package Modelo;

public class Movimiento {

	private int id;
	private String nombre;
	private int puntosPoder;
	private int precision;
	private Tipo tipo;
	private int potencia;
	private Estilos estilo;

	public Movimiento(int id, String nombre, int puntosPoder, int precision, Tipo tipo, int potencia, Estilos estilo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosPoder = puntosPoder;
		this.precision = precision;
		this.tipo = tipo;
		this.potencia = potencia;
		this.estilo=estilo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntosPoder() {
		return puntosPoder;
	}

	public double getPrecision() {
		return precision;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public int getPotencia() {
		return potencia;
	}

	public Estilos getEstilo() {
		return estilo;
	}

}
