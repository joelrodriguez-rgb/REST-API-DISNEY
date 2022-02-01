package app.disney.exceptions;

public class NotFoundException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "ELEMENTO NO ENCONTRADO (404)";

	public NotFoundException(String details) {
		super(DESCRIPTION + "." + details);
	}
	
	
	
}
