package repositorio;

import basico.Cliente;

import java.util.ArrayList;

public class RepositorioCliente {
	
	// atributos
	private ArrayList<Cliente> clientes;
 	
	
	// construtores
	public RepositorioCliente() {
		this.clientes = new ArrayList<Cliente>();
	}
	public RepositorioCliente(int tamanhoInicial) {
		this.clientes = new ArrayList<Cliente>(tamanhoInicial);
	}
	public RepositorioCliente(ArrayList<Cliente> aux) {
		this.clientes = aux;
	}
	
	
	/*
	 * metodo para adicionar um novo Cliente ao repositorio
	 * 
	 * @ parametro novo --- novo cliente a ser adicionado
	 */
	public boolean adicionar(Cliente novo) {
		if(novo == null) {
			return false;
		}
		
		clientes.add(novo);
		return true;
	}
	
	
	/*
	 * metodo para remover o cliente, o cliente sera removido
	 * 	se realmente existir no repositorio
	 * 
	 * @ parametro procurado --- cliente a ser removido
	 */
	public boolean remover(Cliente procurado) {
		int posicao = this.retornarPosicao(procurado);
		
		if( posicao == -1 ) {
			return false;
		}
		
		this.clientes.remove(posicao);
		return true;
	}
	
	
	/*
	 * metodo para atualizar o cliente no repositorio
	 * 	se o cliente a ser atualizado existir no repositorio
	 * 
	 * @ parametro antigo --- cliente antigo a ser atualizado
	 * @ parametro novo   --- cliente novo para ser atualizado
	 */
	public boolean atualizar(Cliente antigo, Cliente novo) {
		int posicao = this.retornarPosicao(antigo);
		
		if( posicao == -1 || novo == null ) {
			return false;
		}
		
		this.clientes.set(posicao, novo);
		return true;
	}
	
	
	/*
	 * metodo que procura um cliente e o devolve se ele existir
	 * 	no repositorio
	 * 
	 * @ parametro procurado --- cliente procurado no repositorio
	 */
	public Cliente buscar(Cliente procurado) {
		int posicao = this.retornarPosicao(procurado);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return this.clientes.get(posicao);
	}
	
	
	/*
	 * metodo exclusivo da classe Cliente que devolve a posicao
	 * 	do cliente no repositorio se o mesmo existir
	 * 
	 * @ parametro procurado --- cliente procurado no repositorio
	 * 
	 * @ retorna a posicao --- se o cliente for encontrado
	 * @ retorna -1        --- se o cliente não for encontrado
	 */
	private int retornarPosicao(Cliente procurado) {
		if( procurado == null ) {
			return -1;
		}
		
		Cliente aux;
		
		for(int a = 0; a < this.clientes.size(); a++) {
			
			aux = this.clientes.get(a);
			if( procurado.getId() == aux.getId() ) {
				return a;
			}
			
		}
		
		return -1;
	}
	
	
	
	
}
