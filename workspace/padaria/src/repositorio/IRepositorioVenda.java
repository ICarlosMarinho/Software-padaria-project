package repositorio;

import classesBasicas.Venda;

public interface IRepositorioVenda {
	
	public boolean adicionar( Venda venda );
	
	public Venda[] todos();
	
	public boolean remover( Venda venda );
	
	public boolean atualizar( Venda antigo, Venda novo );
	
	public Venda buscar( Venda procurado );
}
