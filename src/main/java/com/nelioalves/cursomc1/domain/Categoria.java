package com.nelioalves.cursomc1.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// Aqui é para que os onjetos possam ser convertidos em bytes...
@Entity
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // Aqui diz que a classe possui a versão padrão 1
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@JsonManagedReference // Referência gerenciada pelo json do que queremos os objetos associados.
	@ManyToMany(mappedBy="categorias") // Porque aqui o mapeamento foi feito no atributo categorias...
	private List<Produto> produtos = new ArrayList<>();
	
	public Categoria() {
			
	}
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	//Aqui acima é para os objetos serem comparados por valor e não por ponteiros...
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
	
	

}
