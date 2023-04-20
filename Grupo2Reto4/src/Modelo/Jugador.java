package Modelo;

public class Jugador extends Usuario{

	private Pokemon[] equipo;
	
	public void setEquipo(Pokemon[] equipo) {
		this.equipo = equipo;
	}

	private MiPc pc;
	
	public Jugador(String usuario, String login, String pass, Pokemon[] equipo) {
		super(usuario, login, pass);
		this.equipo=equipo;
	}
	
	public Jugador() {
		
	}

	public Pokemon[] getEquipo() {
		return equipo;
	}

	@Override
	public boolean Verificar() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
