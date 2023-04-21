package modelo;

public class Jugador extends Usuario{

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
	public void Verificar() {
		// TODO Auto-generated method stub
		
	}

	
}
