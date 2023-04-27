package modelo;

import java.util.Objects;

public class Movimiento {

	private int id;
	private String nombre;
	private int puntosPoder;
	private double precision;
	private Tipo tipo;
	private int potencia;

	public Movimiento(int id, String nombre, int puntosPoder, double precision, Tipo tipo, int potencia) {
		this.id = id;
		this.nombre = nombre;
		this.puntosPoder = puntosPoder;
		this.precision = precision;
		this.tipo = tipo;
		this.potencia = potencia;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, potencia, precision, puntosPoder, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", nombre=" + nombre + ", puntosPoder=" + puntosPoder + ", precision="
				+ precision + ", tipo=" + tipo + ", potencia=" + potencia + "]";
	}

}
