package negocio;

import repositorio.*;

import java.util.ArrayList;

import classesBasicas.*;
import exceptions.NegocioException;
import exceptions.SistemaException;

public class CadastroCliente {
	
	// atributos
	private RepositorioCliente clientes;
	
	
	// construtores
	public CadastroCliente() {
		this.clientes = new RepositorioCliente();
	}
	
	
	// metodo get
	public Cliente[] listar() {
		return (Cliente[])this.clientes.listar();
	}
	
	public Repositorio getRepositorio() {
		return this.clientes;
	}
	
	public boolean atualizar(Cliente antigo, Cliente novo) {
		this.clientes.atualizar(antigo, novo);
		return true;
	}
	
	/*
	 * este metodo cadastra um novo cliente no repositorio de clientes
	 * 	faz todas as verificacoes de negocio
	 * 
	 * @ parametro nome        --- nome        do cliente
	 * @ parametro logradouro  --- logradouro  do cliente
	 * @ parametro numero      --- numero      do cliente
	 * @ parametro complemento --- complemento do cliente
	 * @ parametro cidade      --- cidade      do cliente
	 * @ parametro estado      --- estado      do cliente
	 */
	public boolean cadastrar( String nome, String logradouro, String numero
							, String complemento, String cidade, String estado) throws NegocioException, SistemaException {
		
		if(  nome == null    || logradouro == null || numero == null
		  || complemento == null || cidade == null || estado == null ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro na opcao do metodo cadastrar, classe CadastroCliente");
		}
		
		
		Cliente aux = this.clientes.buscar(nome);
		
		if( aux != null ) {
			throw new NegocioException("Cliente " + nome + " já existe !", this);
		}
		
		Endereco endereco = new Endereco( logradouro, numero, complemento
										, cidade, estado );
		
		
		int id = 0;
		Cliente novo;
		
		while( true ) {
			
			novo = new Cliente(nome, id, endereco, 0, 0);
			
			if( this.clientes.buscar(novo) == null ) {
				
				break;
				
			} else {
				
				id++;
				
			}
		}
		
		
		this.clientes.adicionar(novo);
		return true;
	}

	
	/*
	 * este metodo remove um cliente do repositorio se o mesmo existir
	 * 
	 * @ parametro id   --- remover cliente por id
	 * @ parametro nome --- remover cliente por nome
	 */
	public boolean remover(int id) throws NegocioException {
		
		Cliente aux = new Cliente(null, id, null, 0, 0);
		
		
		Cliente remover = (Cliente)this.clientes.buscar(aux);
		
		
		if( remover == null ) {
			throw new NegocioException("Cliente não encontrado", this);
		}
		
		
		this.clientes.remover(remover);
		return true;
	}
	public boolean remover(String nome) throws NegocioException {
		
		Cliente remover = this.clientes.buscar(nome);
		
		
		if( remover == null ) {
			throw new NegocioException("Cliente não encontrado", this);
		}
		
		
		this.clientes.remover(remover);
		return true;
	}
	
	
	/*
	 * este metodo retorna um cliente existente
	 * 
	 * @ parametro nome --- efetuar busca pelo nome
	 * @ parametro id   --- efetuar busca pelo id
	 */
	public Cliente buscar(String nome) {
		return this.clientes.buscar(nome);
	}
	public Cliente buscar(int id) {
		Cliente procurado = new Cliente(null, id, null, 0, 0);
		
		return (Cliente)this.clientes.buscar(procurado);
	}

	/**
	 * Buscar ocorrencia de string nos nomes dos clientes
	 * @return uma ArrayList contendo os clientes encontrados
	 */
	public ArrayList<Cliente> buscarOcorrencia(String ocorrencia) {
		
		ArrayList<Cliente> encontrados = new ArrayList<Cliente>();
		ArrayList<Cliente> clientes    = this.todos();
		
		
		for( int k = 0; k < clientes.size(); k++ ) {
			
			Cliente aux = clientes.get(k);
			
			if( aux.getNome().toUpperCase().contains(ocorrencia.toUpperCase()) ) {
				encontrados.add(aux);
			}
		}
		
		return encontrados;
	}
	
	/*
	 * este metodo retorna a quantidade total de clientes
	 * 
	 * @ retorna total --- numero total de clientes cadastrados
	 */
	public int total() {
		return this.clientes.tamanho();
	}
	
	
	/*
	 * este metodo retorna uma array contendo todos os clientes
	 * 	cadastrados
	 * 
	 * @ retorna lista[] --- todos os clientes cadastrados
	 */
	public ArrayList<Cliente> todos() {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		for( Object obj : this.clientes.listar() ) {
			clientes.add( (Cliente)obj );
		}
		
		return clientes;
	}
	
	
	
	/*
	 * este metodo modifica um cliente cadastrado
	 * 
	 * 
	 * Parametros obrigatorios
	 * 
	 * 
	 * @ parametro id    --- id do cliente a ser modificado
	 * @ parametro campo --- campo de modificacao:
	 * 						Opcoes:
	 * 							0] mudar o nome
	 * 							1] mudar o logradouro
	 * 							2] mudar o numero
	 * 							3] mudar o complemento
	 *                          4] mudar a cidade
	 * 							5] mudar o estado
	 * 
	 * 
	 * @ parametro valor --- novo valor a ser posto no campo
	 */
	public boolean modificar(int id, int campo, String valor) throws NegocioException, SistemaException {
		
		if( valor == null ) {
			return false;
		}
		
		Cliente antigo = this.buscar(id);
		
		if( antigo == null ) {
			throw new NegocioException("Cliente não encontrado", this);
		}
		
		if( campo > 5 || campo < 0) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro no campo do metodo modificar, classe CadastroCliente");
		}
		
		
		
		String nomeAntigo        = antigo.getNome();
		String logradouroAntigo  = antigo.getEndereco().getLogradouro();
		String numeroAntigo      = antigo.getEndereco().getNumero();
		String complementoAntigo = antigo.getEndereco().getComplemento();
		String cidadeAntigo      = antigo.getEndereco().getCidade();
		String estadoAntigo      = antigo.getEndereco().getEstado();
		
		double qtdVendas  = antigo.getQtdVendas();
		double valorVendas = antigo.getValorVendas();
		
		
		
		
		switch( campo ) {
		
			case 0: { // nome
				
				Endereco endereco = new Endereco( logradouroAntigo, numeroAntigo
												, complementoAntigo, cidadeAntigo
												, estadoAntigo                    );
				
				Cliente atualizado = new Cliente( valor, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
			
			case 1: { // logradouro
				
				Endereco endereco = new Endereco( valor, numeroAntigo
												, complementoAntigo, cidadeAntigo
												, estadoAntigo                    );
				
				Cliente atualizado = new Cliente( nomeAntigo, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
			
			case 2: { // numero
				
				Endereco endereco = new Endereco( logradouroAntigo, valor
												, complementoAntigo, cidadeAntigo
												, estadoAntigo                    );
				
				Cliente atualizado = new Cliente( nomeAntigo, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
			
			case 3: { // complemento
				
				Endereco endereco = new Endereco( logradouroAntigo, numeroAntigo
												, valor, cidadeAntigo
												, estadoAntigo                    );
				
				Cliente atualizado = new Cliente( nomeAntigo, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
			
			case 4: { // cidade
				
				Endereco endereco = new Endereco( logradouroAntigo, numeroAntigo
												, complementoAntigo, valor
												, estadoAntigo                    );
				
				Cliente atualizado = new Cliente( nomeAntigo, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
			
			case 5: { // estado
				
				Endereco endereco = new Endereco( logradouroAntigo, numeroAntigo
												, complementoAntigo, cidadeAntigo
												, valor                           );
				
				Cliente atualizado = new Cliente( nomeAntigo, id, endereco
												, qtdVendas, valorVendas);
				
				
				this.clientes.atualizar(antigo, atualizado);
				return true;
			}
		}
		
		
		return true;
	}
	public boolean modificar(int id, String nome, String logradouro
			, String numero, String complemento, String cidade
			, String estado) throws NegocioException, SistemaException {
		
		this.modificar(id, 0, nome);
		this.modificar(id, 1, logradouro);
		this.modificar(id, 2, numero);
		this.modificar(id, 3, complemento);
		this.modificar(id, 4, cidade);
		this.modificar(id, 5, estado);
		
		return true;
	}
	
}
