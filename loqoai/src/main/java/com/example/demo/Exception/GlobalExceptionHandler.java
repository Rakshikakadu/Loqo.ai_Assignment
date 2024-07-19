package com.example.demo.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	/**
	 * @param productNotFoundException : it handles all types of productException which is checked exception.
	 * @param req : general webRequest metadata.
	 * @return : responseEntity of customized error details with http status of error code 400.
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorDetails> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(productNotFoundException.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @param methodArgumentNotValidException : it handles all types of methodArgumentNotValidException which is to be thrown when an invalid argument passed.
	 * @param req : general webRequest metadata.
	 * @return : responseEntity of customized error details with http status of error code 400.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException methodArgumentNotValidException, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param noHandlerFoundException : it handles all types of noHandlerFoundException which is to be thrown when dispatcherServlet can't find a handler for a request.
	 * @param req : general webRequest metadata.
	 * @return : responseEntity of customized error details with http status of error code 404.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(
			NoHandlerFoundException noHandlerFoundException, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(noHandlerFoundException.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception : it handles all types of exception.
	 * @param req : general webRequest metadata.
	 * @return : responseEntity of customized error details with http status of error code 400.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception exception, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(exception.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}


}
