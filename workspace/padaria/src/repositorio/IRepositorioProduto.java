package repositorio;

import classesBasicas.Produto;

public interface IRepositorioProduto {
	
	public boolean adicionar( Produto novo );
	
	public boolean remover(Produto procurado);
	
	public Produto buscar(Produto procurado);
	public Produto buscar(String nome);
	
	public boolean atualizar(Produto antigo, Produto novo);
	
	public int tamanho();
}
