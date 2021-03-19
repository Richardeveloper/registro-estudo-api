package com.richardeveloper.services.exceptions;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.richardeveloper.resources.errors.InsertValidationError;
import com.richardeveloper.resources.errors.StandardError;
import com.richardeveloper.resources.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
		String err = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError(Instant.now(),
												status.value(),
												err,
												exception.getMessage(),
												request.getRequestURI());
		
		return new ResponseEntity<StandardError>(error, status);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<InsertValidationError>> saveValidation(MethodArgumentNotValidException exception){
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		List<InsertValidationError> errors = new ArrayList<InsertValidationError>();
		
		fieldErrors.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errors.add(new InsertValidationError(error.getField(), mensagem));
		});
		
		return new ResponseEntity<List<InsertValidationError>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<StandardError> resourceNotFound(EmptyResultDataAccessException exception, HttpServletRequest request){
		String err = "Resource Is Not Exist";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError(Instant.now(),
				status.value(),
				err,
				exception.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<StandardError>(error, status);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> resourceNotFound(HttpMessageNotReadableException exception, HttpServletRequest request){
		String err = "Resource is Not Valid";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError(Instant.now(),
				status.value(),
				err,
				exception.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<StandardError>(error, status);
	}

	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<StandardError> resourceNotFound(DateTimeException exception, HttpServletRequest request){
		String err = "Time or date format invalid";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError(Instant.now(),
				status.value(),
				err,
				exception.getMessage(),
				request.getRequestURI());
		
		return new ResponseEntity<StandardError>(error, status);
	}
	
}
