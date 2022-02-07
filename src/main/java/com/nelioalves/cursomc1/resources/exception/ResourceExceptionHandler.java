package com.nelioalves.cursomc1.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@ControllerAdvice // Gerenciador de erros. // classe auxiliar que vai receptar as exceções tendo a assinatura abaixo
public class ResourceExceptionHandler {
	
	//Aqui abaixo é recebido um construtor
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		// Aqui abaixo temos o value porque o http vai em forma de inteiro
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
