package modelo;

public class Jugador {

	private String usuario;
	private String login;
	private String pass;
	private Pokemon pokemon;
	
	public Jugador(String usuario, String login, String pass, Pokemon pokemon) {
		super();
		this.setUsuario(usuario);
		this.setLogin(login);
		this.setPass(pass);
		this.setPokemon(pokemon);
	}
	
	public Jugador() {
		
	}
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
}
