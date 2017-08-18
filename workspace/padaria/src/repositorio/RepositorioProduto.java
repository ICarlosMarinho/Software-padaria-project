package repositorio;

import java.io.FileInputStream;
import java.util.ArrayList;

import classesBasicas.Produto;

public class RepositorioProduto extends Repositorio {
	
	// construtores
	public RepositorioProduto() {
		super();
	}
	public RepositorioProduto(int tamanhoInicial) {
		super( tamanhoInicial );
	}
	
	
	private int retornarPosicao(String nome) {
		if( nome == null ) {
			return -1;
		}
		
		Produto aux;
		
		for(int a = 0; a < super.repositorio.size(); a++) {
			
			aux = (Produto)super.repositorio.get(a);
			if( nome.equals(aux.getNome()) ) {
				return a;
			}
		
		}
		
		return -1;
	}
	
	
	public Produto buscar(String nome) {
		
		int posicao = retornarPosicao(nome);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return (Produto)super.repositorio.get(posicao);
	}
	
	
}