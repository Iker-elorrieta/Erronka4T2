package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Jugador extends Usuario {

	private ArrayList<Pokemon> equipo;
	private MiPc pc;
	private boolean ban;

	public Jugador(String usuario, String login, String pass, ArrayList<Pokemon> equipo, MiPc pc, boolean ban) {
		super(usuario, login, pass);
		this.equipo = equipo;
		this.pc = pc;
		this.ban = ban;
	}

	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	public MiPc getPc() {
		return pc;
	}

	public boolean isBan() {
		return ban;
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
		result = prime * result + Objects.hash(ban, equipo, pc);
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
		return ban == other.ban && Objects.equals(equipo, other.equipo) && Objects.equals(pc, other.pc);
	}

	@Override
	public String toString() {
		return "Jugador [equipo=" + equipo + ", pc=" + pc + ", ban=" + ban + "]";
	}

	
	

}
