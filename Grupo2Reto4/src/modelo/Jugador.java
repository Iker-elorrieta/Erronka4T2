package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Jugador extends Usuario{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(equipo);
		result = prime * result + Objects.hash(pc);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Arrays.equals(equipo, other.equipo) && Objects.equals(pc, other.pc);
	}

	@Override
	public String toString() {
		return "Jugador [equipo=" + Arrays.toString(equipo) + ", pc=" + pc + "]";
	}

	private Pokemon[] equipo;
	private MiPc pc;
	
	public Jugador(String usuario, String login, String pass, Pokemon[] equipo, MiPc pc) {
		super(usuario, login, pass);
		this.equipo=equipo;
		this.pc=pc;
	}
	
	public Pokemon[] getEquipo() {
		return equipo;
	}


	public MiPc getPc() {
		return pc;
	}

	@Override
	public boolean Validar() {
		int contador = 0;
		boolean encontrado =false;
		while(contador<equipo.length && encontrado == false ) {
			if(equipo[contador]!=null)
				encontrado = true;
			contador++;
		}
		return encontrado;
	}

	
}
