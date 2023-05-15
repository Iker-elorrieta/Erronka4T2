package modelo;

import java.util.Objects;

public class Region {
	
	private int id;
	private String nombre;
	
	public Region(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
