package repositorio;

import classesBasicas.Cliente;

public class RepositorioCliente extends Repositorio {
	
	
	// construtores
	public RepositorioCliente() {
		super();
	}
	public RepositorioCliente(int tamanhoInicial) {
		super(tamanhoInicial);
	}
	
	
	public Cliente buscar(String nome) {
		int posicao = this.retornarPosicao(nome);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return (Cliente)super.repositorio.get(posicao);
	}
	
	
	private int retornarPosicao(String nome) {
		if( nome == null ) {
			return -1;
		}
		
		Cliente aux;
		
		for(int a = 0; a < super.repositorio.size(); a++) {
			
			aux = (Cliente)super.repositorio.get(a);
			if( nome.equals(aux.getNome()) ) {
				return a;
			}
			
		}
		
		return -1;
	}
	
}