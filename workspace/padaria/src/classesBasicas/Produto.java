package classesBasicas;

import java.util.Calendar;


public class Produto {
	
	// atributos
	private String nome;
	private String descricao;
	private int id;
	private Calendar validade;
	private double quantidade;
	private double preco;
	
	// construtores
	public Produto() {}
	public Produto(String nome, String descricao, int id
				  , Calendar validade, double quantidade, double preco) {
		
		this.nome = nome;
		this.descricao = descricao;
		this.id = id;
		this.validade = validade;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	
	// metodos get
	public String getNome() {
		return this.nome;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public int getId() {
		return this.id;
	}
	public Calendar getValidade() {
		return this.validade;
	}
	public double getQuantidade() {
		return this.quantidade;
	}
	public double getPreco() {
		return this.preco;
	}

	
	// metodo set
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao ;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setValidade(Calendar validade) {
		this.validade = validade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	// metodo toString
	public String toString() {
		return String.format( "%-20s | R$%-20.2f | id --- %d%n", this.nome, this.preco, this.id);
	}

	
	// metodo equals
	public boolean equals(Produto p) {
		if( p == null ) {
			return false;
		}
		
		if( this.id == p.id) {
			return true;
		}
		
		return false;
	}
	
	
}