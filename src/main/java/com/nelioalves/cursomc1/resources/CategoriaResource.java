package com.nelioalves.cursomc1.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc1.domain.Categoria;
import com.nelioalves.cursomc1.dto.CategoriaDTO;
import com.nelioalves.cursomc1.services.CategoriaService;

@RestController
@RequestMapping(value="categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET) // significa que o endpoint é /categorias/id que for digitado...
	// O ResponseEntity é pra dizer que dizer que armazena uma resposta http para um serviço Rest...
	public 	ResponseEntity<?> find(@PathVariable Integer id) { // Aqui é pra dixer que o id da url vai para o id da variável...
		
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj); // Aqui é pra dizer que ocorreu tudo bem com o objeto.
		
	}
	
	//Aqui é feito uma classe para inserir obj/Uma das classes do crud
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){// Aqui é para o objeto categoria ser construído fazendo o obj
		// json ser convertido para o objeto java automaticamente.
		
		obj = service.insert(obj);
		// A URI (Uniform Resource Identifier, ou Identificador Uniforme de Recursos) é uma string 
		//(sequência de caracteres) que se refere a um recurso. A mais comum é a URL, que identifica o recurso localizando-o na Web.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		// o código no postman é 201
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)// Usamos essa anotação igual a da classe Response acima pois
	//quando colocarmos a uri no postman precisamos chamar a url com o id
	// Aqui abaixo é uma mistura chamando o corpo da requisição e o inteiro
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
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
	public 	ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		//Aqui acima estamos convertendo uma lista para outra lista, transformando uma stream de objetos para uma outra lista.
		
		return ResponseEntity.ok().body(listDto);
	
	
	
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) // aqui não vai ter value pois irá retonar todas as categorias
	public 	ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue="0") Integer page,
		    @RequestParam(value = "linesPerPage", defaultValue="24")Integer linesPerPage,
		    @RequestParam(value = "orderBy", defaultValue="nome") String orderBy,
		    @RequestParam(value = "direction", defaultValue="ASC") String direction) {
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		//Aqui acima estamos convertendo uma lista para outra lista, transformando uma stream de objetos para uma outra lista.
		
		return ResponseEntity.ok().body(listDto);
	
	
	
	}
	
	
	
	
	
	}
