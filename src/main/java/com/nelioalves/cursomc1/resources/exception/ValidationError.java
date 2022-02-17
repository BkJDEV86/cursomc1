package com.nelioalves.cursomc1.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}


	public List<FieldMessage> getErrors() {
		return errors;
	}


	public void addError(String fieldName, String messagem) { // Aqui não se quer retornar uma lista e sim um elemento por vez.
		errors.add(new FieldMessage(fieldName, messagem));
	}

	
	

}
