package app.disney.common.exceptions;

public class BadRequestException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "PETICION MAL FORMADA (400)";

	public BadRequestException(String details) {
		super(DESCRIPTION + "." + details);
	}

}
