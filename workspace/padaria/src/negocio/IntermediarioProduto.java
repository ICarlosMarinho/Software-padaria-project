package negocio;

import java.util.Calendar;

import repositorio.RepositorioProduto;
import classesBasicas.Produto;
import classesBasicas.Cliente;

public class IntermediarioProduto {
	
	// constantes
	private static double max;
	private static double porcentual;
	
	// atributos
	private RepositorioProduto estoque;
	
	
	// construtor
	public IntermediarioProduto() {
		this.estoque = new RepositorioProduto();
		max = 10;
		porcentual = 0.1;
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
	 * este metodo cadastra um novo produto no estoque
	 * 
	 * @ parametro nome       --- nome       do novo produto
	 * @ parametro descricao  --- descricao  do novo produto
	 * @ parametro validade   --- validade   do novo produto
	 * @ parametro quantidade --- quantidade do novo produto
	 * @ parametro preco      --- preco      do novo produto
	 * 
	 * @ retorna true  se o produto foi adicionado
	 * @ retorna false se nao
	 */
	public boolean cadastrar(String nome, String descricao
						    , int dia, int mes, int ano
						    , double quantidade, double preco) {
		
		if( nome == null || descricao == null || quantidade <= 0 || preco <= 0 ) {
			return false;
		}
		mes--;
		if( !validadeOK(dia, mes, ano) ) {
			System.out.println("validade inválida"); // remover
			return false;
		}
		if( this.estoque.buscar(nome) != null ) {
			System.out.println("Ja existe"); // remover
			return false;
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
	
	
	/*
	 * este metodo remove um produto do estoque
	 * 	se o mesmo existir
	 * 
	 * @ parametro id  ---  id do produto a ser removido
	 */
	public boolean remover(int id) {
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		
		if( this.estoque.remover(auxiliar) ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	
	/*
	 * este metodo vende um produto
	 * 
	 * @ parametro idProduto   --- id do produto do estoque a ser vendido
	 * @ parametro idCliente   --- id do cliente se o mesmo esta cadastrado
	 * @ parametro quantidade  --- quantidade do produto a ser vendido
	 * 
	 * @ retorna true          --- se a operacao for bem sucedida
	 */
	public boolean vender( int idProduto, int idCliente, double quantidade ) {
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		if( quantidade <= 0 ) {
			return false;
		}
		
		
		Produto paraVender = this.buscar(idProduto);
		
		if( paraVender == null ) {
			return false;
		}
		
		
		Cliente paraComprar = sistema.buscarCliente(idCliente);
		
		if( paraComprar == null ) {
			return false;
		}
		
		
		
		double precoProduto = paraVender.getPreco();
		
		if( quantidade == paraVender.getQuantidade() ) { // remover produto
			
			
			this.estoque.remover(paraVender);
			
			double precoFinal = ( quantidade*precoProduto ) - paraComprar.getCredito();
			
			
			// computar credito para proxima compra
			double novoCredito;
			if( precoFinal >= 0 ) {
				
				novoCredito = precoFinal * IntermediarioProduto.porcentual;
				
			} else {
				
				novoCredito = 0;
				
			}
			
			
			if( novoCredito < IntermediarioProduto.max ) {
				
				paraComprar.setCredito(novoCredito);
			
			} else {
				
				paraComprar.setCredito( IntermediarioProduto.max );
				
			}
			
			paraComprar.setQtdVendas( paraComprar.getQtdVendas() + quantidade     );
			paraComprar.setValorVendas( paraComprar.getValorVendas() + precoFinal );
			
			
		} else {
			
			
			// diminuir a quantidade no estoque
			this.modificar( idProduto, 3, paraVender.getQuantidade() - quantidade );
			
			double precoFinal = ( quantidade*precoProduto ) - paraComprar.getCredito();
			
			// computar credito para proxima compra
			double novoCredito;
			if( precoFinal >= 0 ) {
				
				novoCredito = precoFinal * IntermediarioProduto.porcentual;
				
			} else {
				
				novoCredito = 0;
				
			}
			
			
			if( novoCredito < IntermediarioProduto.max ) {
				
				paraComprar.setCredito(novoCredito);
			
			} else {
				
				paraComprar.setCredito( IntermediarioProduto.max );
				
			}

			paraComprar.setQtdVendas( paraComprar.getQtdVendas() + quantidade     );
			paraComprar.setValorVendas( paraComprar.getValorVendas() + precoFinal );
			
		}
		
		Cliente antigo = sistema.buscarCliente(idCliente);
		
		sistema.atualizarCliente( antigo, paraComprar );
		
		return true;
	}
	public boolean vender( int idProduto, double quantidade ) { // sem clientes cadastrados
		
		if( quantidade <= 0 ) {
			return false;
		}
		
		
		Produto paraVender = this.buscar(idProduto);
		
		if( paraVender == null ) {
			return false;
		}
		
		
		
		if( quantidade == paraVender.getQuantidade() ) { // remover produto
			
			this.estoque.remover(paraVender);
			
		} else { // diminuir a quantidade no estoque
			
			this.modificar( idProduto, 3, (paraVender.getQuantidade() - quantidade) );
			
		}
		
		
		
		return true;
	}



	
	/*
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
	public boolean modificar(int id, int opcao, String valor ) {
		
		if(opcao != 0 && opcao != 1 ) {
			System.out.println("Argumento na função modificar errado");
			return false;
		}
		
		if( valor == null ) {
			return false;
		}
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			return false;
		}
		
		Produto atualizado = null;
		
		if( opcao == 0 ) { // mudar nome
			
			atualizado = new Produto(valor, antigo.getDescricao(),  antigo.getId() 
									, antigo.getValidade(), antigo.getQuantidade()
									, antigo.getPreco()                          );
			
		} else {           // mudar descricao
			
			atualizado = new Produto(antigo.getNome(), valor,  antigo.getId() 
					, antigo.getValidade(), antigo.getQuantidade()
					, antigo.getPreco()                          );
		}
		
		
		
		if( this.estoque.atualizar(antigo, atualizado) ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
		
	}
	public boolean modificar(int id, int opcao, int dia, int mes, int ano ) {
		
		if( opcao != 2 ) {
			System.out.println("Argumento na função modificar errado");
			return false;
		}
		
		if( !validadeOK(dia, mes, ano) ) {
			//System.out.println("Nova validade inválida");
			return false;
		}
		
		Calendar validade = Calendar.getInstance();
		
		validade.set(Calendar.DAY_OF_MONTH, dia);
		validade.set(Calendar.MONTH, mes);
		validade.set(Calendar.YEAR, ano);
		
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			//System.out.println("Produto não encontrado");
			return false;
		}
		
		Produto atualizado = new Produto( antigo.getNome()      ,  antigo.getDescricao()
				                        , antigo.getId()        ,  validade
				                        , antigo.getQuantidade(),  antigo.getPreco()   );
		
		
		
		
		if( this.estoque.atualizar(antigo, atualizado) ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
	
	}
	public boolean modificar(int id, int opcao, double valor    ) {
		
		if( opcao != 3 && opcao != 4 ) {
			System.out.println("Argumento na função modificar errado");
			return false;
		}
		
		
		Produto auxiliar = new Produto(null, null, id, null, 0, 0);
		Produto antigo = (Produto)this.estoque.buscar(auxiliar);
		
		if( antigo == null ) {
			//System.out.println("Produto não encontrado");
			return false;
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
			
		} else {
			
			
			return false;
			
		}
		
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
	
	
	/*
	 * este metodo retorna uma array contendo todo o estoque de produtos
	 * 
	 * @ retorna Produtos[] --- array contendo todo o estoque
	 */
	public Produto[] todos() {
		
		return (Produto[])this.estoque.listar();
		
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