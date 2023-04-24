package modelo;

import java.util.Objects;

public class Movimiento {

	protected int id;
	protected String nombre;
	protected int puntosPoder;
	protected double precision;
	protected Tipo tipo;
	protected int potencia;
	protected Estilo estilo;

	public Movimiento(int id, String nombre, int puntosPoder, double precision, Tipo tipo, int potencia,
			Estilo estilo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosPoder = puntosPoder;
		this.precision = precision;
		this.tipo = tipo;
		this.potencia = potencia;
		this.estilo = estilo;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
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
		return id == other.id && Objects.equals(nombre, other.nombre) && potencia == other.potencia
				&& Double.doubleToLongBits(precision) == Double.doubleToLongBits(other.precision)
				&& puntosPoder == other.puntosPoder && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", nombre=" + nombre + ", puntosPoder=" + puntosPoder + ", precision="
				+ precision + ", tipo=" + tipo + ", potencia=" + potencia + "]";
	}

}
