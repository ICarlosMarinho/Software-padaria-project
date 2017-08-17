package negocio;

import java.util.*;

import repositorio.*;
import classesBasicas.*;
import exceptions.*;


public class CadastroProduto {
	
	// atributos
	private RepositorioProduto estoque;
	
	
	// construtor
	public CadastroProduto() {
		this.estoque = new RepositorioProduto();
	}
	
	
	// metodo get
	public RepositorioProduto getProdutos() {
		return this.estoque;
	}

	/*
	 *  verifica a validade de uma data
	 * 
	 * @ parametro dia  --- dia em questao
	 * @ parametro mes  --- mes em questao
	 * @ parametro ano  --- ano em questao
	 */
	public boolean validadeOK(int dia, int mes, int ano) {
		
		Calendar atual = Calendar.getInstance();
		
		if( ano < atual.get(Calendar.YEAR) ) {
			return false;
		}
		
		if(    mes < atual.getMinimum(Calendar.MONTH)
		    || mes > atual.getMaximum(Calendar.MONTH) ) {
			
			return false;
		}
		
		
		if( ano == atual.get(Calendar.YEAR) ) {
			
			if( mes < atual.get(Calendar.MONTH) ) {
					return false;
			}
			
			
			
			Calendar validade = Calendar.getInstance();
			
			validade.set(Calendar.YEAR, ano);
			validade.set(Calendar.MONTH, mes);
			
			
			if(    dia < validade.getActualMinimum(Calendar.DAY_OF_MONTH)
			    || dia > validade.getActualMaximum(Calendar.DAY_OF_MONTH) ) {
				
				return false;
			}
			
			
			
			if( mes == atual.get(Calendar.MONTH) ) {
				
				if( dia <= atual.get(Calendar.DAY_OF_MONTH) ) {
					return false;
				}
			}
			
			
		} else { // ano > maior que ano atual
			
			
			Calendar validade = Calendar.getInstance();
			
			validade.set(Calendar.YEAR, ano);
			validade.set(Calendar.MONTH, mes);
			
			
			
			if(    dia < validade.getActualMinimum(Calendar.DAY_OF_MONTH)
			    || dia > validade.getActualMaximum(Calendar.DAY_OF_MONTH) ) {
				
				return false;
			}
			
			
		}
		
		return true;
	}
	
	
	/*
	 * 
	 * este metodo cadastra um novo produto no estoque
	 * 
	 * @ parametro nome       --- nome       do novo produto
	 * @ parametro descricao  --- descricao  do novo produto
	 * @ parametro validade   --- validade   do novo produto
	 * @ parametro quantidade --- quantidade do novo produto
	 * @ parametro preco      --- preco      do novo produto
	 * 
	 * @ retorna true  se o produto foi adicionado
	 * 
	 * throw NegocioException se houver algum problema,
	 * 	informacao sera passada apenas na string.
	 */
	public boolean cadastrar(String nome, String descricao
						    , int dia, int mes, int ano
						    , double quantidade, double preco) throws NegocioException {
		
		if( nome == null ) {
			throw new NegocioException( "Nome inválido", this );
		}
		if( descricao == null ) {
			throw new NegocioException( "Descrição inválida", this );
		}
		if( quantidade <= 0 ) {
			throw new NegocioException( "Quantidade inválida", this );
		}
		if( preco <= 0 ) {
			throw new NegocioException( "Preço inválido", this );
		}
		
		
		
		mes--;
		if( !validadeOK(dia, mes, ano) ) {
			throw new NegocioException( "Data inválida", this );
		}
		if( this.estoque.buscar(nome) != null ) {
			throw new NegocioException( "Produto já existe", this );
		}
		
		
		
		Calendar validade = Calendar.getInstance();
		
		validade.set(Calendar.DAY_OF_MONTH, dia);
		validade.set(Calendar.MONTH, mes);
		validade.set(Calendar.YEAR, ano);
		
		
		
		int id = 0;
		Produto auxiliar;
		
		for( id = 0; id < estoque.tamanho(); id++ ) {
			
			auxiliar = new Produto(null, null, id, null, 0, 0);
			if( estoque.buscar(auxiliar) == null ) {
				break;
			}
				
		}
		
		
		auxiliar = new Produto(nome, descricao, id, validade, quantidade, preco);
		
		this.estoque.adicionar(auxiliar);
		return true;
	}
	public boolean cadastrar(String nome, String descricao
		    , String dia, String mes, String ano
		    , String quantidade, String preco) throws NegocioException {
		
		int diaInt, mesInt, anoInt;
		double quantidadeDou, precoDou;
		
		try {
			diaInt = Integer.parseInt(dia);
			mesInt = Integer.parseInt(mes);
			anoInt = Integer.parseInt(ano);
			quantidadeDou = Double.parseDouble(quantidade);
			precoDou = Double.parseDouble(preco);
		} catch( NumberFormatException  nfe ) {
			throw new NegocioException("Dados inválidos", this);
		}
		
		if(this.cadastrar(nome, descricao, diaInt, mesInt, anoInt, quantidadeDou, precoDou) ) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * throwable classe
	 * 
	 * este metodo remove um produto do estoque
	 * 	se o mesmo existir
	 * 
	 * @ parametro id  ---  id do produto a ser removido
	 * 
	 * throw NegocioException se houver algum problema,
	 * 	informacao sera passada apenas na string.
	 * 
	 */
	public boolean remover(int id) throws NegocioException {
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		
		if( this.estoque.buscar( auxiliar ) == null ) {
			throw new NegocioException( "Produto Inexistente", this );
		}
		
		if( this.estoque.remover(auxiliar) ) {
			return true;
		}
		
		return false;
	}
	
	

	
	/*
	 * 
	 * este metodo modifica um produto existente no estoque
	 * 
	 * @ parametro id    --- id do produto que será modificado
	 * @ parametro opcao --- parametro que informa a funcao o que modificar
	 * 						
	 * 						Opcoes:
	 * 								0 --- modificar o nome
	 * 								1 --- modificar a descricao
	 * 								2 --- modificar a validade
	 *  							3 --- modificar a quantidade no estoque
	 * 								4 --- modificar o preco
	 * 
	 * @ parametro valor --- novo valor que sera sobrescrito
	 */
	public boolean modificar(int id, int opcao, String valor ) 
			throws SistemaException, NegocioException {
		
		if(opcao != 0 && opcao != 1 ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro na opcao do metodo modificar, classe CadastroProduto");
		}
		
		if( valor == null ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro no valor do metodo modificar, classe CadastroProduto, string null");
		}
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			throw new NegocioException( "Produto inexistente", this );
		}
		
		Produto atualizado = null;
		
		if( opcao == 0 ) { // mudar nome
			
			atualizado = new Produto(valor, antigo.getDescricao(),  antigo.getId() 
									, antigo.getValidade(), antigo.getQuantidade()
									, antigo.getPreco()                          );
			
		} else {           // mudar descricao
			
			atualizado = new Produto( antigo.getNome(), valor,  antigo.getId() 
					                , antigo.getValidade(), antigo.getQuantidade()
				                  	, antigo.getPreco()                          );
		}
		
		
		
		
		if( this.estoque.atualizar(antigo, atualizado) ) {
			return true;
		}
		
		return false;
	}
	public boolean modificar(int id, int opcao, int dia, int mes, int ano ) 
			throws SistemaException, NegocioException {
		
		if(opcao != 2 ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro na opcao do metodo modificar, classe CadastroProduto");
		}
		
		if( !validadeOK(dia, mes, ano) ) {
			throw new NegocioException( "Data inválida", this);
		}
		
		Calendar validade = Calendar.getInstance();
		
		validade.set(Calendar.DAY_OF_MONTH, dia);
		validade.set(Calendar.MONTH, mes);
		validade.set(Calendar.YEAR, ano);
		
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			throw new NegocioException( "Produto inexistente", this);
		}
		
		Produto atualizado = new Produto( antigo.getNome()      ,  antigo.getDescricao()
				                        , antigo.getId()        ,  validade
				                        , antigo.getQuantidade(),  antigo.getPreco()   );
		
		
		
		
		if( this.estoque.atualizar(antigo, atualizado) ) {
			return true;
		}
		
		
			return false;
	}
	public boolean modificar(int id, int opcao, double valor    ) 
			throws SistemaException, NegocioException {
		
		if( opcao != 3 && opcao != 4 ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro na opcao do metodo modificar, classe CadastroProduto");
		}
		
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			throw new NegocioException( "Produto inexistente", this );
		}
		
		Produto atualizado;
		
		if( opcao == 3 ) {
			
			atualizado = new Produto( antigo.getNome()      ,  antigo.getDescricao()
					                        , antigo.getId()        ,   antigo.getValidade()
					                        , valor                 ,  antigo.getPreco()   );
			
		} else {
			
			atualizado = new Produto( antigo.getNome()      ,  antigo.getDescricao()
						                    , antigo.getId()        ,   antigo.getValidade()
						                    , antigo.getQuantidade(),  valor               );
			
		}
		
		
		
		if( this.estoque.atualizar(antigo, atualizado) ) {
			return true;
		}
		
		
		return false;
	}
	public boolean modificar(int id, String nome, String descricao
			, String dia, String mes, String ano
			, String quantidade, String preco) throws NegocioException, SistemaException {
		
		
		if( nome == null || descricao == null
				|| dia == null || mes == null || ano == null
				|| quantidade == null || preco == null ) {
			throw new NegocioException("Dados inválidos", this);
		}
		
		int diaInt, mesInt, anoInt;
		double quantidadeDou, precoDou;
		
		try {
			diaInt = Integer.parseInt(dia);
			mesInt = Integer.parseInt(mes);
			anoInt = Integer.parseInt(ano);
			quantidadeDou = Double.parseDouble(quantidade);
			precoDou = Double.parseDouble(preco);
		} catch( NumberFormatException  nfe ) {
			throw new NegocioException("Dados inválidos", this);
		}
		
		boolean cNome = false;
		boolean cDescricao = false;
		boolean cDia = false;
		boolean cMes = false;
		boolean cAno = false;
		boolean cQuantidade = false;
		boolean cPreco = false;
		
		Produto antigo = this.buscar(id);
		if( antigo == null ) {
			throw new NegocioException("Produto não encontrado", this);
		}
		
		if( !antigo.getNome().equals(nome) ) cNome = true;
		if( !antigo.getDescricao().equals(descricao) ) cDescricao = true;
		if( antigo.getValidade().get(Calendar.DAY_OF_MONTH) != diaInt ) cDia = true;
		if( antigo.getValidade().get(Calendar.MONTH) != mesInt ) cMes = true;
		if( antigo.getValidade().get(Calendar.YEAR) != anoInt ) cAno = true;
		if( antigo.getQuantidade() != quantidadeDou ) cQuantidade = true;
		if( antigo.getPreco() != precoDou ) cPreco = true;
		
		
		if( cNome ) { this.modificar(id, 0, nome); }
		if( cDescricao ) { this.modificar(id, 1, descricao); }
		if( cDia || cMes || cAno ) { this.modificar(id, 2, diaInt, mesInt, anoInt); }
		if( cQuantidade ) { this.modificar(id, 3, quantidadeDou); }
		if( cPreco ) { this.modificar(id, 4, precoDou); }
		
		return true;
	}
	
	/*
	 * este metodo retorna um produto do estoque se o mesmo existir
	 * 
	 * @ parametro id --- id do produto procurado
	 * @ parametro nome --- nome do produto procurado (a procura sera
	 * 													a partir do nome)
	 */
	public Produto buscar(int id) {
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		
		return (Produto)this.estoque.buscar(auxiliar);
	}
	public Produto buscar(String nome) {
		
		Produto auxiliar = this.estoque.buscar(nome);
		
		return auxiliar;
	}
	
	/**
	 * Buscar ocorrencia de string nos nomes dos produtos
	 * @return uma ArrayList contendo os produtos encontrados
	 */
	public ArrayList<Produto> buscarOcorrencia(String ocorrencia) {
		
		ArrayList<Produto> encontrados = new ArrayList<Produto>();
		ArrayList<Produto> produtos    = this.todos();
		
		
		for( int k = 0; k < produtos.size(); k++ ) {
			
			Produto aux = produtos.get(k);
			
			if( aux.getNome().contains(ocorrencia) ) {
				encontrados.add(aux);
			}
		}
		
		return encontrados;
	}
	
	/*
	 * este metodo retorna uma array contendo todo o estoque de produtos
	 * 
	 * @ retorna Produtos[] --- array contendo todo o estoque
	 */
	public ArrayList<Produto> todos() {
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		for( Object obj : this.estoque.listar() ) {
			produtos.add( (Produto)obj );
		}
		
		return produtos;
	}
	
	
	/*
	 * este metodo retorna um inteiro contendo a quantidade de produtos
	 * 
	 *   Obs.: a quantidade de produtos se refere a quantidade de produtos
	 *   	   diferentes.
	 *   
	 * @ retorna tamanho --- tamanho do estoque
	 */
	public int total() {
		
		return this.estoque.tamanho();
		
	}
	
	
	
}
