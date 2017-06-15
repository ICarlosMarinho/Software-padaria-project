package repositorio;

import classesBasicas.Funcionario;

public class RepositorioFuncionario 
{	
    //**Atributos
    
    private Funcionario[] funcionarios;
    private int totalFuncionarios;
	
    //**Metodos construtores
   
    public RepositorioFuncionario(int tamVetor)
    {
        this.funcionarios = new Funcionario[tamVetor];
        this.totalFuncionarios = 0;
    }
    
    //**Metodos get
    
    public Funcionario[] getFuncionarios()
    {
        return this.funcionarios;
    }
    
    public int getTotalFuncionarios()
    {
        return this.totalFuncionarios;
    }
    
    public void inserirFuncionario(Funcionario novoFun)
    {        
        this.funcionarios[this.totalFuncionarios] = novoFun;
        
        this.totalFuncionarios++;
    }
    
    public void removerFuncionario(int posicaoFun)
    {   
        for (int i = posicaoFun; i < this.totalFuncionarios;)
        {
            this.funcionarios[i] = this.funcionarios[i + 1];
            
            i++;
        }
        
        this.totalFuncionarios--;   
    }
    
    public boolean substituirFuncionario(Funcionario editFun, int posicaoFun)
    {
        if (editFun == null)
        {
            return false;
        }
        
        this.funcionarios[posicaoFun] = editFun;
        
        return true;
    }
    
    public Funcionario obterFuncionario(int posFun)
    {   
        return this.funcionarios[posFun];
    }
}
