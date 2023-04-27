package excepciones;

/**
 * Defines a generic Exception 
 */
public abstract class GenericDataBaseExceptionAbstract extends Exception {

	private static final long serialVersionUID = -6924666277760461817L;

	public GenericDataBaseExceptionAbstract(String message) {
		super(message);
	}
	
    public GenericDataBaseExceptionAbstract(Throwable cause) {
        super(cause);
    }
}
