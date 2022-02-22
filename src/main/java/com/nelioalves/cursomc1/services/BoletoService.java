package com.nelioalves.cursomc1.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nelioalves.cursomc1.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	// Isso é só pra trocar a data de vencimento do boleto... Numa chamada real teríamos que criar um web service que gera um boleto
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH,7); // Aqui é gerado um boleto com data de vencimento de 7 dias para pagamento
		pagto.setDataVencimento(cal.getTime());
		
	}

}
