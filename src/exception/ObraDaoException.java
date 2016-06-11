package exception;

public class ObraDaoException extends GenericException{

	private static final long serialVersionUID = -5116807674076248444L;
	
	public ObraDaoException(){
		super();
	}
	
	public ObraDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ObraDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObraDaoException(String message) {
		super(message);
	}

	public ObraDaoException(Throwable cause) {
		super(cause);
	}

}
