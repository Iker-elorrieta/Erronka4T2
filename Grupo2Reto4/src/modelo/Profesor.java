package modelo;

import java.util.Objects;

public class Profesor extends Usuario{
	
	private Generacion gen;
	
	public Profesor(String usuario, String login, String pass, Generacion gen) {
		super(usuario,login,pass);
		this.setGen(gen);
	}
	
	public Generacion getGen() {
		return gen;
	}

	public void setGen(Generacion gen) {
		this.gen = gen;
	}

	@Override
	public boolean Validar() {
		return (gen!=null);
	}
	
	//Tiene que pillar de usuario
	@Override
	public String toString() {
		return "Profesor ["+super.toString()+" gen=" + gen + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(gen)+super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return gen == other.gen;
	}
	
}
