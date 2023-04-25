package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import excepciones.NotFoundException;
import modelo.Usuario;



public interface ManagerInterface{
	
	
public ArrayList<Usuario> selectAll () throws SQLException, NotFoundException, Exception;
	
	/**
	 * Insert a new T on Data Base
	 * 
	 * @param t
	 */
	public void insert (Usuario user) throws SQLException, Exception;

	/**
	 * Updates a T on Data Base 
	 * 
	 * @param t
	 */
	public void update (Usuario user) throws SQLException, Exception;
	
	/**
	 * Delete a T on Data Base 
	 * 
	 * @param t
	 */
	public void delete (Usuario user) throws SQLException, Exception;
	
	
	
}
