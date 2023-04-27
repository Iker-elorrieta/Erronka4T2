package excepciones;

public class NotFoundException extends GenericDataBaseExceptionAbstract{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException (String message){
		super(message);
	}
	
}
