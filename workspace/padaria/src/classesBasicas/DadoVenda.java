package classesBasicas;

public class DadoVenda {
	
	private String nome;
	private double quantidade;
	private Produto produto;
	
	
	
	public DadoVenda(String nome, double quantidade, Produto produto) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
	
}
