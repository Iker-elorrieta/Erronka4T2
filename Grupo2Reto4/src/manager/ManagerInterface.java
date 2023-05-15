package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import excepciones.NotFoundException;



/**
 * @author UNAI-PC
 *
 * @param <T>
 */
public interface ManagerInterface<T>{
	
	
/**
 * @return devuelve toda la lista de contenido del interfaz seleccionado desde la base de datos
	 * @throws NotFoundException error para verificar si algo no se encuentra
	 * @throws SQLException error para validar los errores de conexion a SQL
	 * @throws Exception conjunto de errores varios
 */
public ArrayList<T> selectAll () throws SQLException, NotFoundException, Exception;
	
	/**
	 * Inserts para la base de datos
	 * 
	 * @param t objeto 
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void insert (T t) throws SQLException, Exception;

	/**
	 * Actualizamos los parametros deseados en la base de datos sobre el interfaz escogido 
	 * 
	 * @param t objeto 1 
	 * @param t2 objeto 2
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void update (T t, T t2) throws SQLException, Exception;
	
	/**
	 * bBorramos los parametros seleccionados o la fila que queramos de la base de datos
	 * 
	 * @param t
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void delete (T t) throws SQLException, Exception;
	
	
	
}
