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
      // Aqui é feita a operação de inserção do CRUD
	public Categoria insert(Categoria obj) {
		obj.setId(null); // Aqui para garantir que seja uma nova inserção e o objeto seja nulo. Do contrário seria uma atualização.
		return repo.save(obj);
	}
	
	// A diferença entre o método acima e do debaixo é que se ele não for nula vai ser uma atuzalição.
	public Categoria update(Categoria obj) {
		find(obj.getId());// Aqui é chamado o objeto, caso ele não existe é lançado uma exceção
		//obj.setId(null);
		return repo.save(obj);
		
	}
	
	

}
