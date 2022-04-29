package app.disney.common.exceptions;

public class MethodNotAllowedException extends BadRequestException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DESCRIPTION = "CONTROLADOR NO ENCONTRADO (405)";

	public MethodNotAllowedException(String details) {
		super(DESCRIPTION + "." + details);
	}

}
