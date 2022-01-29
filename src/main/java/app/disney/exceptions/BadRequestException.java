package app.disney.exceptions;

public class BadRequestException extends RuntimeException {
	
	
	private static final String DESCRIPTION = "PETICION MAL FORMADA (400)";

	public BadRequestException(String details) {
		super(DESCRIPTION + "." + details);
	}

}
