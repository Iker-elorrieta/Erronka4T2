package Modelo;

public class Jugador extends Usuario{

	private Pokemon pokemon;
	
	public Jugador(String usuario, String login, String pass, Pokemon pokemon) {
		super(usuario, login, pass);
		this.pokemon=pokemon;
	}
	
	public Jugador() {
		
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
}
