package app.disney.exceptions.control;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class ErrorMenssage {

	String exception;
	String menssage;
	String path;
	String traceError;

	public ErrorMenssage(Exception exception, String path) {
		super();
		this.exception = exception.getClass().getName();
		this.menssage = exception.getMessage();
		this.path = path;
		this.traceError = exception.getStackTrace().toString();
		
	}




	
	
}
