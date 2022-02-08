package com.nelioalves.cursomc1.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
		
	}
	
	// Aqui abaixo só é lançado o get pois como não vai mudar a enumeraçao dos já existentes não há a necessidade dos sets.
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
		
	}
	
	// Abaixo uma operação que retorna um tipo cliente convertido para enum porém static pois não será instanciada
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		// se o código varrido x for igual ao cod ( que eu estou procurando ele vai retotnar x)
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}



	


