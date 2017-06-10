package repositorio;

import classesBasicas.Funcionario;

import java.util.ArrayList;

public class RepositorioFuncionario {
	
	// atributos
	private ArrayList<Funcionario> funcionarios;
	
	
	// construtores
	public RepositorioFuncionario() {
		this.funcionarios = new ArrayList<Funcionario>();
	}
		// apenas cria um repositorio vazio de funcionarios com tamanhoInicial especificado
	public RepositorioFuncionario(int tamanhoInicial) {
		this.funcionarios = new ArrayList<Funcionario>(tamanhoInicial);
	}
		// inicializa o repositorio de funcionarios com os funcionarios da ArrayList aux
	public RepositorioFuncionario(ArrayList<Funcionario> aux) {
		this.funcionarios = aux;
	}
	
	
	/* 
	 * metodo adicionar funcionario no repositorio
	 * 
	 * @ parametro novoFuncionario - novo funcionario a ser adicionado
	 */
	public boolean adicionar(Funcionario f) {
		if(f == null) {
			return false;
		}
		
		this.funcionarios.add(f);
		
		return true;
	}
	
	
	/*
	 * metodo para remover funcionario
	 */
	public boolean remover(Funcionario f) {
		
		int posicao = this.retornarPosicao(f);
		
		if(posicao == -1) {
			return false;
		}
		// remocao
		funcionarios.remove(posicao);
		return true;
	}
	
	
	/*
	 * metodo para atualizar funcionario no repositorio
	 * 
	 * @ parametro antigo     --- funcionario que precisa ser atualizado
	 * @ parametro atualizado --- funcionario para ser atualizado
	 */
	public boolean atualizar(Funcionario antigo, Funcionario atualizado) {
		int posicao = this.retornarPosicao(antigo);
		
		if( posicao == -1 || atualizado == null) {
			return false;
		}
		
		this.funcionarios.set(posicao, atualizado);
		
		return true;
	}
	
	
	/*
	 * metodo para buscar funcionario no repositorio
	 * 
	 * @ retorna o Funcionario --- se ele existir no repositorio
	 * @ retorna null          --- se ele nao existir
	 */
	public Funcionario buscar(Funcionario procurado) {
		int posicao = this.retornarPosicao(procurado);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return this.funcionarios.get(posicao);
	}
	public Funcionario buscar(String nome) { // buscar funcionario pelo nome
		int posicao = this.retornarPosicao(nome);
		
		if( posicao == -1 ) {
			return null;
		}
		
		return this.funcionarios.get(posicao);
	}
	
	/*
	 * metodo exclusivo da classe
	 * 
	 * @ retorna a posicao do funcionario na lista de funcionarios
	 * @ retorna -1 se o funcionario nao foi encontrado
	 */
	public int retornarPosicao(Funcionario procurado) {
		if( procurado == null ) {
			return -1;
		}
		
		Funcionario auxiliar;
		
		for(int a = 0; a < this.funcionarios.size(); a++) {
			
			auxiliar = this.funcionarios.get(a);
			if( procurado.getId() == auxiliar.getId() ) {
				return a;
			}
		}
		
		return -1;
	}
	public int retornarPosicao(String nome) { // retornar posicao pelo nome
		if( nome == null ) {
			return -1;
		}
		
		Funcionario auxiliar;
		
		for(int a = 0; a < this.funcionarios.size(); a++) {
			
			auxiliar = this.funcionarios.get(a);
			if( nome.equals(auxiliar.getNome()) ) {
				return a;
			}
		}
		
		return -1;
	}
	
	
	
	
	
}
