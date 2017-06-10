package classesBasicas;

public class Cliente {
	
	private String nome;
	private int id;
	private Endereco endereco;
	private int qtdVendas;
	private double valorVendas;
	
	
	// construtores
	public Cliente() {}
	public Cliente(String nome, int id, Endereco endereco, int qtdVendas, double valorVendas) {
		this.nome = nome;
		this.id = id;
		this.endereco = endereco;
		this.qtdVendas = qtdVendas;
		this.valorVendas = valorVendas;
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
	public void setQtdVendas(int qtdVendas) {
		this.qtdVendas = qtdVendas;
	}
	public void setValorVendas(double valorVendas) {
		this.valorVendas = valorVendas;
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
	public int getQtdVendas() {
		return this.qtdVendas;
	}
	public double getValorVendas() {
		return this.valorVendas;
	}
	
	
	// metodo toString
	public String toString() {
		return this.nome + " --- " + this.id;
	}
	
	
	// metodo equals
	public boolean equals(Cliente c) {
		if(c == null) {
			return false;
		}
		
		if(c.id == this.id) {
			return true;
		}
		
		return false;
	}
	
	
}
