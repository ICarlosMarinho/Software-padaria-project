package repositorios;

import basico.Produto;

import java.util.ArrayList;

public class RepositorioProduto {
	
	// atributos
	private ArrayList<Produto> estoque;
	
	
	// construtores
	public RepositorioProduto() {
		this.estoque = new ArrayList<Produto>();
	}
	public RepositorioProduto(int tamanhoInicial) {
		this.estoque = new ArrayList<Produto>(tamanhoInicial);
	}
	public RepositorioProduto(ArrayList<Produto> aux) {
		this.estoque = aux;
		aux = null;
	}
	
	
	/*
	 * este metodo e exclusivo da classe procura por uma ocorrencia
	 * 	no estoque
	 * 
	 * @ retorna posicao --- se o produto existir no estoque
	 * @ retorna -1      --- se o produto nao existir
	 */
	private int retornarPosicao(Produto procurado) {
		if( procurado == null ) {
			return -1;
		}
		
		Produto aux;
		
		for(int a = 0; a < this.estoque.size(); a++) {
			
			aux = this.estoque.get(a);
			if( aux.getId() == procurado.getId() ) {
				return a;
			}
		
		}
		
		return -1;
	}
	
	
	/*
	 * este metodo adiciona um produto no estoque
	 * 
	 * @ parametro novo --- novo produto para ser adicionado
	 */
	public boolean adicionar(Produto novo) {
		if( novo == null ) {
			return false;
		}
		
		if( retornarPosicao(novo) != -1 ) { // o produto nao pode existir no estoque
			System.out.println("O produto já existe");
			return false;
		}
		
		this.estoque.add(novo);
		return true;	
	}
	
	
	/*
	 * este metodo remove um produto no estoque se o mesmo existir
	 * 
	 * @ parametro procurado --- produto que se deseja remover
	 */
	public boolean remover(Produto procurado) {
		
		int posicao = this.retornarPosicao(procurado); 
		
		if( posicao == -1 ) {
			return false;
		}
		
		this.estoque.remove(posicao);
		return true;
	}
	
	
	/*
	 * este metodo procura um produto no estoque e o devolve se existir
	 * 
	 * @ parametro procurado --- produto que e procurado
	 */
	public Produto buscar(Produto procurado) {
		
		int posicao = this.retornarPosicao(procurado);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return this.estoque.get(posicao);
	}
	
	
	//TODO funcao atualizar() tamanho()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
