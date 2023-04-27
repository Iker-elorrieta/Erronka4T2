package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import excepciones.NotFoundException;



public interface ManagerInterface<T>{
	
	
public ArrayList<T> selectAll () throws SQLException, NotFoundException, Exception;
	
	/**
	 * Insert a new T on Data Base
	 * 
	 * @param t
	 */
	public void insert (T t) throws SQLException, Exception;

	/**
	 * Updates a T on Data Base 
	 * 
	 * @param t
	 * @param t2
	 */
	public void update (T t, T t2) throws SQLException, Exception;
	
	/**
	 * Delete a T on Data Base 
	 * 
	 * @param t
	 */
	public void delete (T t) throws SQLException, Exception;
	
	
	
}
