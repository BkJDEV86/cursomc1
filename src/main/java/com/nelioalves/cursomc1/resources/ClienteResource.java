package com.nelioalves.cursomc1.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc1.domain.Cliente;
import com.nelioalves.cursomc1.dto.ClienteDTO;
import com.nelioalves.cursomc1.services.ClienteService;

@RestController
@RequestMapping(value="clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET) // significa que o endpoint é /categorias/id que for digitado...
	// O ResponseEntity é pra dizer que dizer que armazena uma resposta http para um serviço Rest...
	public 	ResponseEntity<?> find(@PathVariable Integer id) { // Aqui é pra dixer que o id da url vai para o id da variável...
		
		Cliente obj = service.find(id);
		
		
		
		
		
		return ResponseEntity.ok().body(obj); // Aqui é pra dizer que ocorreu tudo bem com o objeto.
		
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)// Usamos essa anotação igual a da classe Response acima pois
	//quando colocarmos a uri no postman precisamos chamar a url com o id
	// Aqui abaixo é uma mistura chamando o corpo da requisição e o inteiro
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);// converter para objeto...
		obj.setId(id);
		obj=service.update(obj);
		return ResponseEntity.noContent().build();// Aqui não tem que retornar nenhum contéudo por isso é noContent e o código no
		// postman é 204
		
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE) 
	public 	ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
			
	}
	
	@RequestMapping( method=RequestMethod.GET) // aqui não vai ter value pois irá retonar todas as categorias
	public 	ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		//Aqui acima estamos convertendo uma lista para outra lista, transformando uma stream de objetos para uma outra lista.
		
		return ResponseEntity.ok().body(listDto);
	
	
	
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) // aqui não vai ter value pois irá retonar todas as categorias
	public 	ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue="0") Integer page,
		    @RequestParam(value = "linesPerPage", defaultValue="24")Integer linesPerPage,
		    @RequestParam(value = "orderBy", defaultValue="nome") String orderBy,
		    @RequestParam(value = "direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		//Aqui acima estamos convertendo uma lista para outra lista, transformando uma stream de objetos para uma outra lista.
		
		return ResponseEntity.ok().body(listDto);
	
	
	
	}
	
	
	
	
	

}
