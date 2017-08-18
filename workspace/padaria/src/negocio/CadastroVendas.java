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
	
	public Repositorio getRepositorio() {
		return this.vendas;
	}
	
	
	/*
	 * este metodo retorna todo o historico em uma array
	 * 
	 * @ retorna historico[] --- historico de vendas
	 */
	public ArrayList<Venda> lista() {
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		
		for( Object obj : this.vendas.listar() ) {
			vendas.add( (Venda)obj );
		}
		
		return vendas;
	}
	
	// TODO parei aqui implementacao das funcionalidades de exception
	
	/*
	 * este metodo adiciona uma nova venda no historico das vendas
	 * 
	 * 
	 * @ parametro vendido    --- produto que foi vendido
	 * @ parametro vendedor   --- o funcionario que vendeu o produto
	 * @ parametro comprador  --- se o cliente foi cadastrado 	->	 opcional
	 */
	public boolean adicionar( ArrayList<DadoVenda> vendido, Funcionario vendedor ) throws SistemaException {
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
//		if( vendido == null || vendedor == null ) {
//			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
//					"algum dos parametros do metodo adicionar estao nulls, classe CadastroVendas");
//		}
		
		Calendar data = Calendar.getInstance();
		
		Venda venda = new Venda( data, vendido, null, vendedor );
		
		this.vendas.adicionar( venda );
		return true;
	}
	public boolean adicionar( ArrayList<DadoVenda> vendido, Funcionario vendedor, Cliente comprador ) throws SistemaException {
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
//		if( vendido == null || vendedor == null || comprador == null ) {
//			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
//					"algum dos parametros do metodo adicionar estao nulls, classe CadastroVendas");
//		}
		
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
	public boolean vender( ArrayList<DadoVenda> vendido, int idCliente, int idFuncionario )
			 throws NegocioException, SistemaException {
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		Funcionario vendedor = sistema.buscarFuncionario( idFuncionario );
		Cliente comprador    = sistema.buscarCliente( idCliente );
		
		if( vendedor == null ) {
			// TODO implementar isto
			System.out.println("Vendedor não encontrado");
		}
		
		if( vendido == null || comprador == null ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro no metodo vender, classe CadastroVenda");
		}
		
		double precoTotal = 0;
		double quantidade = 0;
		
		for( int k = 0; k < vendido.size(); k++ ) {
			
			if( vendido.get(k).getProduto().getQuantidade() < vendido.get(k).getQuantidade() ) {
				throw new NegocioException("Quantidade excede atual estoque", this);
			}
			
			precoTotal += vendido.get(k).getProduto().getPreco() * vendido.get(k).getQuantidade();
			quantidade += vendido.get(k).getQuantidade();
		}
		
		if(quantidade <= 0) {
			throw new NegocioException("Quantidade para vender é 0", this);
		}
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
//		vendedor.setValorVendas( vendedor.getValorVendas() + precoTotal );
		
		double precoFinal = precoTotal - comprador.getCredito();
		
		// computar credito para proxima compra
		double novoCredito;
		if( precoFinal >= 0 ) {
			novoCredito = precoFinal * CadastroVendas.porcentual;
			
		} else {
			
			novoCredito = 0;
			precoFinal  = 0;
			
		}
		
		if( novoCredito < CadastroVendas.max ) {
			comprador.setCredito(novoCredito);
		} else {
			comprador.setCredito( CadastroVendas.max );
		}
		
		
		for( int k = 0; k < vendido.size(); k++ ) {
			
			Produto produto = vendido.get(k).getProduto();
			double quant    = vendido.get(k).getQuantidade();
			
			if( quant == produto.getQuantidade() ) { // remover produto
				
				sistema.removerProduto(produto.getId());
				
				comprador.setQtdVendas( comprador.getQtdVendas() + quant );
				comprador.setValorVendas( comprador.getValorVendas() + precoFinal );
			} else {
				
				// diminuir a quantidade no estoque
				sistema.modificarProduto( produto.getId(), 3, produto.getQuantidade() - quant );
			}
		}
		
		comprador.setQtdVendas(   comprador.getQtdVendas()  +  quantidade );
		comprador.setValorVendas( comprador.getValorVendas() + precoFinal );

		// adicionar venda
		this.adicionar( vendido, vendedor, comprador );
		
		// alterar cliente
		Cliente antigo = sistema.buscarCliente(idCliente);
		sistema.atualizarCliente( antigo, comprador );
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
		// alterar funcionario
//		sistema.alterarInfoFuncionario(sistema.buscarFuncionario(vendedor.getId()), vendedor);
		
		
		return true;
	}
	public boolean vender( ArrayList<DadoVenda> vendido, int idFuncionario )
			throws NegocioException, SistemaException { // sem clientes cadastrados
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		
		Funcionario vendedor = sistema.buscarFuncionario( idFuncionario );
		
		if( vendedor == null ) {
			// TODO implementar isto
			System.out.println("Vendedor não encontrado");
		}
		
		if( vendido == null ) {
			throw new SistemaException("Ocorreu algum erro no sistema, contate o administrador!", 
					"Erro no metodo vender, classe CadastroVenda");
		}
		
		
		
		
		double precoTotal = 0;
		double quantidade = 0;
		
		for( int k = 0; k < vendido.size(); k++ ) {
			
			if( vendido.get(k).getProduto().getQuantidade() < vendido.get(k).getQuantidade() ) {
				throw new NegocioException("Quantidade inválida: " + vendido.get(k).getNome(), this);
			}
			
			precoTotal += vendido.get(k).getProduto().getPreco() * vendido.get(k).getQuantidade();
			quantidade += vendido.get(k).getQuantidade();
		}
		
		if(quantidade <= 0) {
			throw new NegocioException("Quantidade para vender é 0", this);
		}
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
//		vendedor.setValorVendas( vendedor.getValorVendas() + precoTotal );
		
		for( int k = 0; k < vendido.size(); k++ ) {
			
			Produto produto = vendido.get(k).getProduto();
			double quant    = vendido.get(k).getQuantidade();
			
			if( quant == produto.getQuantidade() ) { // remover produto
				sistema.removerProduto(produto.getId());
			} else {
				
				// diminuir a quantidade no estoque
				sistema.modificarProduto( produto.getId(), 3, produto.getQuantidade() - quant );
			}
		}
		

		// adicionar venda
		this.adicionar( vendido, vendedor);
		
//		TODO descomentar isto depois que o id do funcionario estiver pronto
		// alterar funcionario
//		sistema.alterarInfoFuncionario(sistema.buscarFuncionario(vendedor.getId()), vendedor);
		
		return true;
	}
	

}
