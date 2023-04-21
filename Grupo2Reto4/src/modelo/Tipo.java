package modelo;


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

	public int getId() {
		return id;
	}
}
