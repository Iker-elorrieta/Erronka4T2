package modelo;

import java.util.Objects;

abstract class Usuario implements Verificable{
	
	protected String nombre;
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
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", login=" + login + ", pass=" + pass + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, nombre, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(pass, other.pass);
	}

	
}
