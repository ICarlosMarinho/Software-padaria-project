package repositorio;

import java.util.*; // ArrayList

import classesBasicas.*;

public class RepositorioVendas extends Repositorio {
	
	private ArrayList<Venda> vendas;
	
	
	// construtor
	public RepositorioVendas( ) {
		this.vendas = new ArrayList<Venda>();
	}
	public RepositorioVendas( ArrayList<Venda> vendas ) {
		this.vendas = vendas;
	}

	
	
	/*
	 * este metodo retorna a posicao de uma venda nas vendas
	 * 
	 * @ retorna -1 --- se a venda nao foi encontrada
	 */
	public int retornarPosicao( Venda procurado ) {
		
		Venda aux = null;
		
		for( int k = 0; k < this.vendas.size(); k++ ) {
			
			
			aux = this.vendas.get(k);
			
			if( aux.equals( procurado ) ) {
				
				return k;
				
			}
			
		}
		
		return -1;
	}
	
	
	/*
	 * este metodo adiciona uma nova venda no hist�rico das vendas
	 * 
	 * @ parametro venda --- nova venda para adicionar
	 */
	public boolean adicionar( Venda venda ) { // sem cliente cadastrado
		
		if( venda == null ) {
			return false;
		}
		
		this.vendas.add( venda );
		return true;
	}
	
	
	
	/*
	 * este metodo remove uma venda das vendas se a mesma existir
	 * 
	 * @ parametro venda --- venda que se deseja remover
	 */
	public boolean remover( Venda venda ) {
		
		int posicao = retornarPosicao( venda );
		
		if( posicao == -1 ) {
			return false;
		}
		
		
		this.vendas.remove(posicao);
		return true;
	}
	
	
	
	/*
	 * este metodo atualiza uma venda existente no estoque por outra
	 * 
	 * @ parametro antigo --- venda antiga que sera atualizada
	 * @ parametro novo   --- nova venda atualizada
	 */
	public boolean atualizar( Venda antigo, Venda novo ) {
		
		int posicao = this.retornarPosicao( antigo );
		
		if( posicao == -1 || novo == null ) {
			return false;
		}
		
		this.vendas.set(posicao, novo);
		return true;
	}
	
	
	
	/*
	 * este metodo procura uma venda e retorna ela se a mesma existir
	 * 
	 * @ parametro procurado --- venda procurada
	 */
	public Venda buscar( Venda procurado ) {
		
		int posicao = this.retornarPosicao(procurado);
		
		if( posicao == -1 ) {
			
			return null;
			
		}
		
		return this.vendas.get(posicao);
	}

	
	
	
	

}
