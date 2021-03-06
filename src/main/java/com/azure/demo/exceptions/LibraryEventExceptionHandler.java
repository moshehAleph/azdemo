package com.azure.demo.exceptions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




@ControllerAdvice
public class LibraryEventExceptionHandler {
	
	private Logger LOGGER = LoggerFactory.getLogger(LibraryEventExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleRequestBody(MethodArgumentNotValidException ex) {
		
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		String errorMessage = errorList.stream()
			.map(fieldError -> fieldError.getField()+" - "+fieldError.getDefaultMessage())
			.sorted()
			.collect(Collectors.joining(", ")).trim();
		
		LOGGER.info("Error message: {}",errorMessage);
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
	


}
