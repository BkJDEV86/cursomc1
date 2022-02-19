package com.nelioalves.cursomc1.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.Cidade;
import com.nelioalves.cursomc1.domain.Cliente;
import com.nelioalves.cursomc1.domain.Endereco;
import com.nelioalves.cursomc1.domain.enums.TipoCliente;
import com.nelioalves.cursomc1.dto.ClienteDTO;
import com.nelioalves.cursomc1.dto.ClienteNewDTO;
import com.nelioalves.cursomc1.repositories.ClienteRepository;
import com.nelioalves.cursomc1.repositories.EnderecoRepository;
import com.nelioalves.cursomc1.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //injeção de dempedências
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	
	public Cliente find(Integer id) {
		 Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); // Aqui para garantir que seja uma nova inserção e o objeto seja nulo. Do contrário seria uma atualização.
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());// Aqui é chamado um construtor
		updateData(newObj, obj);
		//obj.setId(null);
		
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
		       throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		
	   }
	
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);  
		return repo.findAll(pageRequest);
	  
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
	return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {// Aqui abaixo convertemos o tipo inteiro para o tipo clientes
		Cliente cli =  new Cliente(null, objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo( )));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	
	}
}
