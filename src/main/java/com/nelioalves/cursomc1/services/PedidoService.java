package com.nelioalves.cursomc1.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc1.domain.ItemPedido;
import com.nelioalves.cursomc1.domain.PagamentoComBoleto;
import com.nelioalves.cursomc1.domain.Pedido;
import com.nelioalves.cursomc1.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc1.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc1.repositories.PagamentoRepository;
import com.nelioalves.cursomc1.repositories.PedidoRepository;
import com.nelioalves.cursomc1.repositories.ProdutoRepository;
import com.nelioalves.cursomc1.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired //injeção de denpedências
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstance(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {// se o meu pagamento for um pagamento com boleto
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstance());// Aqui é preenchido a data de vencimento do pagamento
		}
		
		obj = repo.save(obj); // salvei meus objetos no banco
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	
	
	

}
