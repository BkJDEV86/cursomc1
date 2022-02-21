package com.nelioalves.cursomc1.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc1.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	
	@org.springframework.transaction.annotation.Transactional(readOnly=true)// A operação de leitura deixa o sistema mais rápido.
	Cliente findByEmail(String email);// Método para encontrar email
	

}
