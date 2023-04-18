package Modelo;


public class Movimiento {
	
	protected int id;
	protected String nombre;
	protected int puntosPoder;
	protected double precision;
	protected Tipo tipo;
	protected int potencia;
	
	public Movimiento(int id, String nombre, int puntosPoder, double precision, Tipo tipo, int potencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosPoder = puntosPoder;
		this.precision = precision;
		this.tipo = tipo;
		this.potencia = potencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosPoder() {
		return puntosPoder;
	}

	public void setPuntosPoder(int puntosPoder) {
		this.puntosPoder = puntosPoder;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	
	

}
