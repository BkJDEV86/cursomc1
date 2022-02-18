package com.nelioalves.cursomc1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.Categoria;
import com.nelioalves.cursomc1.dto.CategoriaDTO;
import com.nelioalves.cursomc1.repositories.CategoriaRepository;
import com.nelioalves.cursomc1.services.exceptions.DataIntegrityException;
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
		Categoria newObj = find(obj.getId());// Aqui é chamado um construtor
		updateData(newObj, obj);
		//obj.setId(null);
		
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
		       throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		
	   }
	
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);  
		return repo.findAll(pageRequest);
	  
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
	return new Categoria(objDto.getId(),objDto.getNome());
	
}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
	
	}
	
}