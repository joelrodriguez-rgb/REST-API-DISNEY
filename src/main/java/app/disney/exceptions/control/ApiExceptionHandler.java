package app.disney.exceptions.control;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.disney.exceptions.BadRequestException;
import app.disney.exceptions.NotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

	// 400
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class, NumberFormatException.class })
	@ResponseBody()
	public ErrorMenssage badRequest(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

	// 401
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ BadRequestException.class, NumberFormatException.class })
	@ResponseBody()
	public ErrorMenssage unauthorizedException(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

	// 403
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ BadRequestException.class, NumberFormatException.class })
	@ResponseBody()
	public ErrorMenssage forbiddenException(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

	// 404
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class, NoSuchElementException.class })
	@ResponseBody()
	public ErrorMenssage notFound(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

	// 405
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ BadRequestException.class, NumberFormatException.class })
	@ResponseBody()
	public ErrorMenssage methodNotAllowdException(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

	// 431
	@ResponseStatus(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE)
	@ExceptionHandler({ BadRequestException.class, NumberFormatException.class })
	@ResponseBody()
	public ErrorMenssage headerTooLargeException(Exception exception, HttpServletRequest request) {

		return new ErrorMenssage(exception, request.getRequestURI());

	}

}
