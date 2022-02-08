package com.nelioalves.cursomc1.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc1.domain.Pedido;
import com.nelioalves.cursomc1.services.PedidoService;

@RestController
@RequestMapping(value="pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET) // significa que o endpoint é /categorias/id que for digitado...
	// O ResponseEntity é pra dizer que dizer que armazena uma resposta http para um serviço Rest...
	public 	ResponseEntity<?> find(@PathVariable Integer id) { // Aqui é pra dixer que o id da url vai para o id da variável...
		
		Pedido obj = service.find(id);
		
		
		
		
		
		return ResponseEntity.ok().body(obj); // Aqui é pra dizer que ocorreu tudo bem com o objeto.
		
	}
	
	
	

}
