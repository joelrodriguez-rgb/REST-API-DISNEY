package app.disney.exceptions;

public class ExistingNameException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "NOMBRE YA EXISTENTE (409)";
	
	
	public ExistingNameException(String details) {
		super(DESCRIPTION + details );
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
