package vista;

import java.sql.SQLException;
import java.util.ArrayList;

import excepciones.NotFoundException;
import manager.ManagerTipos;
import modelo.Tipo;

public class test {

	public static void main(String[] args) throws NotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		ManagerTipos tipos = new ManagerTipos();
		ArrayList<Tipo> tipo = new ArrayList<Tipo>();
		tipo = tipos.selectAll();
		System.out.println(tipo.get(2).getNombre_tipo());
	}

}
