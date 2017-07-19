package repositorio;

import classesBasicas.Produto;

import java.util.ArrayList;

public class RepositorioProduto implements IRepositorioProduto {
	
	// atributos
	private ArrayList<Produto> produtos;
	
	
	// construtores
	public RepositorioProduto() {
		this.produtos = new ArrayList<Produto>();
	}
	public RepositorioProduto(int tamanhoInicial) {
		this.produtos = new ArrayList<Produto>(tamanhoInicial);
	}
	public RepositorioProduto(ArrayList<Produto> aux) {
		this.produtos = aux;
	}
	
	
	// metodo get
	public Produto[] getProdutos() {
		
		int tamanho = produtos.size();
		
		Produto[] lista = new Produto[tamanho];
		
		
		for( int k = 0; k < tamanho; k++ ) {
			lista[k] = produtos.get(k);
		}
		
		return lista;
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
		
		for(int a = 0; a < this.produtos.size(); a++) {
			
			aux = this.produtos.get(a);
			if( aux.getId() == procurado.getId() ) {
				return a;
			}
		
		}
		
		return -1;
	}
	private int retornarPosicao(String nome) {
		if( nome == null ) {
			return -1;
		}
		
		Produto aux;
		
		for(int a = 0; a < this.produtos.size(); a++) {
			
			aux = this.produtos.get(a);
			if( nome.equals(aux.getNome()) ) {
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
		
		this.produtos.add(novo);
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
		
		this.produtos.remove(posicao);
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
		
		return this.produtos.get(posicao);
	}
	public Produto buscar(String nome) {
		
		int posicao = retornarPosicao(nome);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return this.produtos.get(posicao);
	}
	
	/*
	 * este metodo atualiza um produto no estoque
	 * 
	 * @ parametro antigo --- produto antigo que deve ser atualizado
	 * @ parametro novo   --- produto atualizado
	 */
	public boolean atualizar(Produto antigo, Produto novo) {
		if( novo == null ) {
			return false;
		}
		
		int posicao = this.retornarPosicao(antigo);
		
		if( posicao == -1 ) {
			return false;
		}
		
		this.produtos.set(posicao, novo);
		return true;
	}
	
	
	/*
	 * este metodo retorna o tamanho de produtos no estoque
	 * 	
	 * Obs.: O metodo retorna o numero de produtos DIFERENTES no estoque
	 */
	public int tamanho() {
		return this.produtos.size();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
