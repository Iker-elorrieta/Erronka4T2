package modelo;

abstract class Usuario {
	
	protected String nombre;
	public String getNombre() {
		return nombre;
	}

	protected String login;
	protected String pass;
	
	public Usuario(String nombre, String login, String pass) {
		super();
		this.setNombre(nombre);
		this.setLogin(login);
		this.setPass(pass);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
