package modelo;

import java.util.Objects;

public class Profesor extends Usuario{
	
	private Region reg;
	
	public Profesor(String usuario, String login, String pass, Region reg) {
		super(usuario,login,pass);
		this.reg = reg;
	}
	

	public Region getReg() {
		return reg;
	}

	@Override
	public boolean Validar() {
		return (reg!=null);
	}
	
	//Tiene que pillar de usuario
	@Override
	public String toString() {
		return "Profesor ["+super.toString()+" gen=" + reg + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(reg)+super.hashCode();
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
		return reg == other.reg;
	}
	
}
