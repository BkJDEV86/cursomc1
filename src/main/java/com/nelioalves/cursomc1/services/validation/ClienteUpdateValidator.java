package com.nelioalves.cursomc1.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.nelioalves.cursomc1.domain.Cliente;
import com.nelioalves.cursomc1.dto.ClienteDTO;
import com.nelioalves.cursomc1.repositories.ClienteRepository;
import com.nelioalves.cursomc1.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;// Isto vai permitir chamar o parâmetro da URI no postman.
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		Map<String, String> map =(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id")); //aqui é feito uma conversão de um map para um Integer.
		
		List<FieldMessage> list = new ArrayList<>();

		
		//Aqui abaixo vai ser testado que e verificar se o email atualizado é igual a um emai existente em um outro cliente existente
		// no banco de dados
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux !=null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
			
		}
			
		
		// Esse campo abaixo é responsável por percorrer o meu erro e transportando os meus erros personalizados para a lista do Framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
