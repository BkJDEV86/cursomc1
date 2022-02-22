package com.nelioalves.cursomc1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.Categoria;
import com.nelioalves.cursomc1.domain.Produto;
import com.nelioalves.cursomc1.repositories.CategoriaRepository;
import com.nelioalves.cursomc1.repositories.ProdutoRepository;
import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired //injeção de dempedências
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		 Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));

	
	}
	
	//Abaixo temos uma busca paginada....
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); // Vai buscar todas as categorias correspondentes ao id
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	

}
