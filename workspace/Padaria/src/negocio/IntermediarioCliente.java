package negocio;

import repositorio.RepositorioCliente;
import classesBasicas.Cliente;
import classesBasicas.Endereco;

public class IntermediarioCliente {
	
	// atributos
	private RepositorioCliente clientes;
	
	
	// construtores
	public IntermediarioCliente() {
		this.clientes = new RepositorioCliente();
	}
	
	
	// metodo get
	public Cliente[] getClientes() {
		return this.clientes.getClientes();
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
							, String complemento, String cidade, String estado) {
		
		if(  nome == null    || logradouro == null || numero == null
		  || complemento == null || cidade == null || estado == null ) {
			return false;
		}
		
		
		Cliente aux = this.clientes.buscar(nome);
		
		if( aux != null ) {
			return false;
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
	public boolean remover(int id) {
		
		Cliente aux = new Cliente(null, id, null, 0, 0);
		
		
		Cliente remover = this.clientes.buscar(aux);
		
		
		if( remover == null ) {
			// cliente nao encontrado
			return false;
		}
		
		
		this.clientes.remover(remover);
		return true;
	}
	public boolean remover(String nome) {
		
		Cliente remover = this.clientes.buscar(nome);
		
		
		if( remover == null ) {
			// cliente nao encontrado
			return false;
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
		
		return this.clientes.buscar(procurado);
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
	public Cliente[] todos() {
		return this.clientes.getClientes();
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
	 *                                                      4] mudar a cidade
	 * 							5] mudar o estado
	 * 
	 * 
	 * @ parametro valor --- novo valor a ser posto no campo
	 */
	public boolean modificar(int id, int campo, String valor) {
		
		if( valor == null ) {
			return false;
		}
		
		Cliente antigo = this.buscar(id);
		
		if( antigo == null ) {
			// cliente nao encontrado
			return false;
		}
		
		if( campo > 5 || campo < 0) {
			// campo errado
			return false;
		}
		
		
		
		String nomeAntigo        = antigo.getNome();
		String logradouroAntigo  = antigo.getEndereco().getLogradouro();
		String numeroAntigo      = antigo.getEndereco().getNumero();
		String complementoAntigo = antigo.getEndereco().getComplemento();
		String cidadeAntigo      = antigo.getEndereco().getCidade();
		String estadoAntigo      = antigo.getEndereco().getEstado();
		
		int qtdVendas  = antigo.getQtdVendas();
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
	
	
}
