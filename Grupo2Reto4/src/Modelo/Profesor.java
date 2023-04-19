package Modelo;

public class Profesor extends Usuario{
	
	private Generacion gen;
	
	public Profesor(String usuario, String login, String pass, Generacion gen) {
		super(usuario,login,pass);
		this.gen=gen;
	}
	
	public Profesor () {
		
	}
	public Generacion getGen() {
		return gen;
	}

	public void setGen(Generacion gen) {
		this.gen = gen;
	}
}
