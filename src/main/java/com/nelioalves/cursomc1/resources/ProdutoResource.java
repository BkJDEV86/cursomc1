package com.nelioalves.cursomc1.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc1.domain.Produto;
import com.nelioalves.cursomc1.dto.ProdutoDTO;
import com.nelioalves.cursomc1.resources.utils.URL;
import com.nelioalves.cursomc1.services.ProdutoService;

@RestController
@RequestMapping(value="produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET) // significa que o endpoint é /categorias/id que for digitado...
	// O ResponseEntity é pra dizer que dizer que armazena uma resposta http para um serviço Rest...
	public 	ResponseEntity<?> find(@PathVariable Integer id) { // Aqui é pra dixer que o id da url vai para o id da variável...
		
		Produto obj = service.find(id);
		
		
		
		
		
		return ResponseEntity.ok().body(obj); // Aqui é pra dizer que ocorreu tudo bem com o objeto.
		
	}
	
	@RequestMapping(method=RequestMethod.GET) // aqui não vai ter value pois irá retonar todas as categorias
	public 	ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue="") String nome,
			@RequestParam(value = "categorias", defaultValue="") String categorias,
			@RequestParam(value = "page", defaultValue="0") Integer page,
		    @RequestParam(value = "linesPerPage", defaultValue="24")Integer linesPerPage,
		    @RequestParam(value = "orderBy", defaultValue="nome") String orderBy,
		    @RequestParam(value = "direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		//Aqui acima estamos convertendo uma lista para outra lista, transformando uma stream de objetos para uma outra lista.
		
		return ResponseEntity.ok().body(listDto);
	
	
	
	}
}
