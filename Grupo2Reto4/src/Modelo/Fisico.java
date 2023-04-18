package Modelo;


public class Fisico extends Movimiento{
	
	private int potencia;
	
	
	public Fisico(int id, String nombre, int puntosPoder, double precision, Tipo tipo, int potencia) {
		super(id, nombre, puntosPoder, precision, tipo);
		// TODO Auto-generated constructor stub
		this.potencia = potencia;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	@Override
	public String toString() {
		return "Fisico [potencia=" + potencia + ", id=" + id + ", nombre=" + nombre + ", puntosPoder=" + puntosPoder
				+ ", precision=" + precision + ", tipo=" + tipo + "]";
	}

	
	
	
	
	
}
