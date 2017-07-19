package repositorio;

import classesBasicas.*;

public interface IRepositorioFuncionario {
	
	public Funcionario[] getFuncionarios();
	
	public int getTotalFuncionarios();
	
	public void inserirFuncionario( Funcionario novoFun );
	
	public void removerFuncionario( int posicaoFun );
	
	public boolean substituirFuncionario( Funcionario editFun, int posicaoFun );
	
	public Funcionario obterFuncionario( int posFun );
	
	

}
