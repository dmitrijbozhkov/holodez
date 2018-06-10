package org.nure.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nure.exceptions.AppException;
import org.nure.exceptions.BadRequestException;
import org.nure.exceptions.ExistsException;
import org.nure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@ControllerAdvice
public class ExceptionResolverController extends ResponseEntityExceptionHandler {

	private ResponseEntity<?> constructError(RuntimeException ex, HttpStatus status) {
		HashMap<String, String> messageDetails = new HashMap<String, String>();
		messageDetails.put("error", ex.getMessage());
		return makeJSONError(messageDetails, status);
	}
	
	private ResponseEntity<Map<String, String>> makeJSONError(Map<String, String> body, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Map<String, String>>(body, headers, status);
	}
	
	@ExceptionHandler(value = { AppException.class })
	protected ResponseEntity<Map<String, String>> handleAppError(RuntimeException ex, WebRequest request) {
		HashMap<String, String> messageDetails = new HashMap<String, String>();
		messageDetails.put("error", ex.getMessage());
		messageDetails.put("trace", ex.getStackTrace().toString());
		return makeJSONError(messageDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<?> handleNotFoundError(RuntimeException ex, WebRequest request) {
		return constructError(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { BadRequestException.class,
								ExistsException.class,
								JsonParseException.class,
								JsonMappingException.class,
								IOException.class })
	protected ResponseEntity<?> handleBadRequestError(RuntimeException ex, WebRequest request) {
		return constructError(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { SignatureException.class,
								MalformedJwtException.class,
								ExpiredJwtException.class,
								UnsupportedJwtException.class,
								IllegalArgumentException.class})
	protected ResponseEntity<?> jwtValidationError(RuntimeException ex, WebRequest request) {
		return constructError(ex, HttpStatus.UNAUTHORIZED);
	}
}
