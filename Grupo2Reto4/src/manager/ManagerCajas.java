package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Metodos;
import excepciones.NotFoundException;
import modelo.Caja;
import modelo.Pokemon;
import utils.DBConexion;

public class ManagerCajas implements ManagerInterface<Caja>{
	
	Connection conexion;
	Statement comando;
	ResultSet registro;
	ResultSet registro2;
	Metodos m = new Metodos();
	
	@Override
	public ArrayList<Caja> selectAll() throws SQLException, NotFoundException, Exception {
		// TODO Auto-generated method stub
		ArrayList<Caja> cajas = new ArrayList<Caja>();

		try {
			conexion = DriverManager.getConnection(DBConexion.URL, DBConexion.USER, DBConexion.PASSW);
			comando = conexion.createStatement();
			registro = comando.executeQuery("SELECT * FROM " + DBConexion.T_CAJAS + ";");
			 

			while (registro.next() == true) {
				
				int id = registro.getInt(0);
				
				ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
				
				registro2 = comando.executeQuery("SELECT poke_id FROM " + DBConexion.T_CAJAS_POKEMON + " where pc_box_id = "+id+";");
				
				while (registro2.next() == true) {
					
					int idpokemon = registro.getInt(2);
					
					Pokemon p = m.conseguirPokemon(idpokemon);
					
					pokemons.add(p);
				}
				
				Caja c = new Caja(id, pokemons);
				
				cajas.add(c);
						
			}

		} finally {
			registro.close();
			comando.close();
			conexion.close();
		}

		if (cajas.size() == 0)
			throw new NotFoundException("No hay cajas.");

		return cajas;
	}

	@Override
	public void insert(Caja t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Caja t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Caja t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
