package modelo;

public class Jugador extends Usuario{

	private Pokemon pokemon;
	
	public Jugador(String usuario, String login, String pass) {
		super(usuario, login, pass);
	}
	
	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
}
