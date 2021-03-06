package com.nelioalves.cursomc1.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc1.domain.Categoria;
import com.nelioalves.cursomc1.domain.Pedido;
import com.nelioalves.cursomc1.dto.CategoriaDTO;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){// Aqui é para o objeto categoria ser construído fazendo o obj
		// json ser convertido para o objeto java automaticamente.
		
		
		
		obj = service.insert(obj);
		// A URI (Uniform Resource Identifier, ou Identificador Uniforme de Recursos) é uma string 
		//(sequência de caracteres) que se refere a um recurso. A mais comum é a URL, que identifica o recurso localizando-o na Web.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		// o código no postman é 201
	}
	
	
	
	

}
