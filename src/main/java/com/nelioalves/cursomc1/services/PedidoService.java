package com.nelioalves.cursomc1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.Pedido;
import com.nelioalves.cursomc1.repositories.PedidoRepository;
import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired //injeção de dempedências
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	
	}
	

}
