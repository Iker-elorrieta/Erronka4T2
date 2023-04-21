package modelo;

import java.util.Objects;

public class Tipo {
	
	private int id;
	private String nombre_tipo;
	
	public Tipo(int id, String nombre_tipo) {
		this.id=id;
		this.nombre_tipo=nombre_tipo;
	}

	public String getNombre_tipo() {
		return nombre_tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre_tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipo other = (Tipo) obj;
		return id == other.id && Objects.equals(nombre_tipo, other.nombre_tipo);
	}

	@Override
	public String toString() {
		return "Tipo [id=" + id + ", nombre_tipo=" + nombre_tipo + "]";
	}

	public int getId() {
		return id;
	}
}
