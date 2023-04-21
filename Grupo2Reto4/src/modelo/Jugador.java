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
