package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Jugador extends Usuario {

	private ArrayList<Pokemon> equipo;
	private MiPc pc;

	public Jugador(String usuario, String login, String pass, ArrayList<Pokemon> equipo, MiPc pc) {
		super(usuario, login, pass);
		this.equipo = equipo;
		this.pc = pc;
	}

	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	public MiPc getPc() {
		return pc;
	}

	@Override
	public boolean Validar() {
		boolean encontrado = false;
		if (equipo != null) {
			int contador = 0;
			while (contador < equipo.size() && encontrado == false) {
				if (equipo.get(contador) != null)
					encontrado = true;
				contador++;
			}
		}
		return encontrado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(equipo, pc);
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
		return Objects.equals(equipo, other.equipo) && Objects.equals(pc, other.pc);
	}

	@Override
	public String toString() {
		return "Jugador [equipo=" + equipo + ", pc=" + pc + "]";
	}

	

}
