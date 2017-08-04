package repositorio;

import classesBasicas.*;

public interface IRepositorioCliente {
	
	public boolean adicionar( Cliente novo );
	
	public boolean remover( Cliente procurado );
	
	public boolean atualizar( Cliente antigo, Cliente novo );
	
	public Cliente buscar( Cliente procurado );
	public Cliente buscar( String nome );

	public Cliente[] listar();
	
	public int tamanho();
	

}
