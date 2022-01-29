package app.disney.exceptions;

public class ErrorMenssage {

	String exception;
	String menssage;
	String path;

	public ErrorMenssage(Exception exception, String path) {
		super();
		this.exception = exception.getClass().getName();
		this.menssage = exception.getMessage();
		this.path = path;
	}

	public String getException() {
		return exception;
	}

	public String getMenssage() {
		return menssage;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ErrorMenssage [exception=" + exception + ", menssage=" + menssage + ", path=" + path + "]";
	}

	
	
}
