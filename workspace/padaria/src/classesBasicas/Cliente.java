package classesBasicas;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	private String nome;
	private int id;
	private Endereco endereco;
	private double qtdVendas;
	private double valorVendas;
	private double credito;
	
	
	// construtores
	public Cliente() {}
	public Cliente(String nome, int id, Endereco endereco
				  , double qtdVendas, double valorVendas) {
		this.nome = nome;
		this.id = id;
		this.endereco = endereco;
		this.qtdVendas = qtdVendas;
		this.valorVendas = valorVendas;
		this.credito = 0;
	}
	
	
	// metodos set
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public void setQtdVendas(double qtdVendas) {
		this.qtdVendas = qtdVendas;
	}
	public void setValorVendas(double valorVendas) {
		this.valorVendas = valorVendas;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	
	// metodos get
	public String getNome() {
		return this.nome;
	}
	public int getId() {
		return this.id;
	}
	public Endereco getEndereco() {
		return this.endereco;
	}
	public double getQtdVendas() {
		return this.qtdVendas;
	}
	public double getValorVendas() {
		return this.valorVendas;
	}
	public double getCredito() {
		return this.credito;
	}
	
	
	// metodo toString
	public String toString() {
		return this.nome + " --- " + this.id;
	}
	
	
	// metodo equals
	public boolean equals(Object c) {
		if( c instanceof Cliente ) {

			if( this.id == ((Cliente)c).id) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
}