package modelo;


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

}
