package com.nelioalves.cursomc1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.Categoria;
import com.nelioalves.cursomc1.repositories.CategoriaRepository;
import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //injeção de dempedências
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	
	}
	

}
