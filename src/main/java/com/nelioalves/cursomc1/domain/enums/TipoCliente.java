package com.nelioalves.cursomc1.domain.enums;


// abaixo é melhor construírmos um id e um nome pois se algum desenvolvedor mudar algo e fosse inicializado automaticamente as iden
//tificações iriam mudar automaticamente o que constituiria um erro. Se fosse feito sem numeração e somente por nome ocuparia muita 
// memória o que não é bom
public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");

	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		// se o código varrido x for igual ao cod ( que eu estou procurando ele vai retotnar x)
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}











