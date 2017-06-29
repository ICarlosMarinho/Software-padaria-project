package negocio;

import repositorio.*;

import java.util.Calendar;

import classesBasicas.*;

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
	
	
	/*
	 * este metodo adiciona uma nova venda no historico das vendas
	 * 
	 * 
	 * @ parametro vendido    --- produto que foi vendido
	 * @ parametro vendedor   --- o funcionario que vendeu o produto
	 * @ parametro comprador  --- se o cliente foi cadastrado 	->	 opcional
	 */
	public boolean adicionar( Produto vendido, Funcionario vendedor ) {
		
		if( vendido == null || vendedor == null ) {
			return false;
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
	
	
	
	

}
