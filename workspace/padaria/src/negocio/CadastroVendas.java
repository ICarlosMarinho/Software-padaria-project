package negocio;

import java.util.*;

import repositorio.*;
import classesBasicas.*;
import exceptions.*;

public class CadastroVendas {
	
	// EM CONSTRUCAO
	
	private RepositorioVendas vendas;
	private static double max = 10.0;
	private static double porcentual = 0.1;
	
	
	// construtor
	public CadastroVendas() {
		this.vendas = new RepositorioVendas();
	}
	
	
	/*
	 * este metodo retorna todo o historico em uma array
	 * 
	 * @ retorna historico[] --- historico de vendas
	 */
	public Venda[] lista() {
		
		return this.vendas.todos();
		
	}
	
	// TODO parei aqui implementacao das funcionarlidades de exception
	
	/*
	 * este metodo adiciona uma nova venda no historico das vendas
	 * 
	 * 
	 * @ parametro vendido    --- produto que foi vendido
	 * @ parametro vendedor   --- o funcionario que vendeu o produto
	 * @ parametro comprador  --- se o cliente foi cadastrado 	->	 opcional
	 */
	public boolean adicionar( Produto vendido, Funcionario vendedor ) throws SistemaException {
		
		if( vendido == null || vendedor == null ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"algum dos parametros do metodo adicionar estao nulls, classe CadastroVendas");
		}
		
		Calendar data = Calendar.getInstance();
		
		Venda venda = new Venda( data, vendido, null, vendedor );
		
		this.vendas.adicionar( venda );
		return true;
	}
	public boolean adicionar( Produto vendido, Funcionario vendedor, Cliente comprador ) {
		
		if( vendido == null || vendedor == null || comprador == null ) {
			return false;
		}
		
		Calendar data = Calendar.getInstance();
		
		Venda venda = new Venda( data, vendido, comprador, vendedor );
		
		this.vendas.adicionar( venda );
		return true;
	}
	
	
	/*
	 * este metodo vende um produto
	 * 
	 * @ parametro idProduto   --- id do produto do estoque a ser vendido
	 * @ parametro idCliente   --- id do cliente se o mesmo esta cadastrado -> opcional
	 * @ parametro quantidade  --- quantidade do produto a ser vendido
	 * 
	 * @ retorna true          --- se a operacao for bem sucedida
	 */
	public boolean vender( int idProduto, int idCliente, int idFuncionario, double quantidade )
			 throws NegocioException, SistemaException {
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		if( quantidade <= 0 ) {
			return false;
		}
		
		
		Produto produto      = sistema.buscarProduto( idProduto );
		Funcionario vendedor = sistema.buscarFuncionario( idFuncionario );
		Cliente comprador    = sistema.buscarCliente( idCliente );
		
		if( produto == null || comprador == null || vendedor == null ) {
			return false;
		}
		
		
		
		
		double precoProduto = produto.getPreco();
		
		vendedor.setValorVendas( vendedor.getValorVendas() + (quantidade * precoProduto) );
		
		double precoFinal = ( quantidade*precoProduto ) - comprador.getCredito();
		
		// computar credito para proxima compra
		double novoCredito;
		if( precoFinal >= 0 ) {
			
			novoCredito = precoFinal * CadastroVendas.porcentual;
			
		} else {
			
			novoCredito = 0;
			precoFinal  = 0;
			
		}
		
		if( quantidade == produto.getQuantidade() ) { // remover produto
			
			sistema.removerProduto(idProduto);
			
			if( novoCredito < CadastroVendas.max ) {
				
				comprador.setCredito(novoCredito);
				
			} else {
				
				comprador.setCredito( CadastroVendas.max );
			}
			
			comprador.setQtdVendas( comprador.getQtdVendas() + quantidade     );
			comprador.setValorVendas( comprador.getValorVendas() + precoFinal );
			
			
		} else {
			
			
			// diminuir a quantidade no estoque
			sistema.modificarProduto( idProduto, 3, produto.getQuantidade() - quantidade );
			
			
			if( novoCredito < CadastroVendas.max ) {
				
				comprador.setCredito(novoCredito);
			
			} else {
				
				comprador.setCredito( CadastroVendas.max );
				
			}

			comprador.setQtdVendas(   comprador.getQtdVendas()  +  quantidade );
			comprador.setValorVendas( comprador.getValorVendas() + precoFinal );
			
		}
		

		// adicionar venda
		this.adicionar( produto, vendedor, comprador );
		
		// alterar cliente
		Cliente antigo = sistema.buscarCliente(idCliente);
		sistema.atualizarCliente( antigo, comprador );
		
		// alterar funcionario
		sistema.alterarInfoFuncionario(sistema.buscarFuncionario(vendedor.getId()), vendedor);
		
		
		return true;
	}
	public boolean vender( int idProduto, int idFuncionario, double quantidade )
			throws NegocioException, SistemaException { // sem clientes cadastrados
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		Produto produto      = sistema.buscarProduto( idProduto );
		Funcionario vendedor = sistema.buscarFuncionario( idFuncionario );

		if( quantidade <= 0 || produto == null || vendedor == null ) {
			return false;
		}
		
		
		if( quantidade == produto.getQuantidade() ) { // remover produto
			
			sistema.removerProduto( idProduto );
			
		} else { // diminuir a quantidade no estoque
			
			sistema.modificarProduto( idProduto, 3, (produto.getQuantidade() - quantidade) );
			
		}
		
		// adicionar venda
		this.adicionar( produto, vendedor );
		
		// alterar funcionario
		sistema.alterarInfoFuncionario(sistema.buscarFuncionario(vendedor.getId()), vendedor);
		
		return true;
	}
	

}
