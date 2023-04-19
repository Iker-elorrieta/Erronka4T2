package Modelo;


public abstract class Usuario {

	protected String usuario;
	protected String login;
	protected String pass;
	
	public Usuario(String usuario, String login, String pass) {
		this.usuario=usuario;
		this.login=login;
		this.pass=pass;
	}
	
	public Usuario() {
		
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
}
