package negocio;

import repositorio.RepositorioFuncionario;
import classesBasicas.*;
import java.util.Random;

public class OperacoesFuncionario 
{
    private static final int TAM_VETOR = 100; //**Tamanho do vetor de funcionarios
    
    private RepositorioFuncionario repo;
    private Random aleatorio = new Random();
    
    public OperacoesFuncionario()
    {
        repo = new RepositorioFuncionario(TAM_VETOR);
    }
    
    public boolean cadastrar(Funcionario novoFun)
    {
        if (novoFun == null)
        {
            return false;
        }
        else if(this.repo.getTotalFuncionarios() >= TAM_VETOR)
        {
            return false;
        }
        
        for (int i = 0; i < this.repo.getTotalFuncionarios(); i++)
        {
            if (novoFun.equals(this.repo.obterFuncionario(i)) == true)
            {
                return false;
            }
        }
        
        this.repo.inserirFuncionario(novoFun);
        
        return true;
    }
    
    public boolean excluir(int posicaoFun)
    {
        if (posicaoFun < 0 || posicaoFun >= this.repo.getTotalFuncionarios())
        {
            return false;
        }
        
        this.repo.removerFuncionario(posicaoFun);
        
        return true;
    }
    
    public Funcionario buscar(int auxId)
    {
        int i;
        
        if (auxId < 0)
        {
            return null;
        }
        
        for(i = 0; i < this.repo.getTotalFuncionarios() && this.repo.obterFuncionario(i).getId()!= auxId ;)
        {
            i++;
        }
        
        if (i == this.repo.getTotalFuncionarios())
        {
            return null;
        }
        
        return this.repo.obterFuncionario(i);
    }
    
    public int retornarPosicao(int auxId)
    {
        int i;
        
        if (auxId < 0)
        {
            return -1;
        }
        
        for (i = 0; i < this.repo.getTotalFuncionarios() && this.repo.getFuncionarios()[i].getId() != auxId;)
        {
            i++;
        }
        
        if (i == this.repo.getTotalFuncionarios())
        {
            return -1;
        }
        
        return i;
    }
    
    public int atribuirId(String auxCargo)//**Talvez mude de classe.
    {
        int auxId = -1;
        
        do
        {
            if (auxCargo.equalsIgnoreCase("Gerente") == true)
            {
                auxId = 10000 + aleatorio.nextInt(1000);
            }
            else if(auxCargo.equalsIgnoreCase("Caixa") == true)
            {
                auxId = 11000 + aleatorio.nextInt(1000);
            }
            else if(auxCargo.equalsIgnoreCase("Estoquista") == true)
            {
                auxId = 12000 + aleatorio.nextInt(1000);
            }
            else if(auxCargo.equalsIgnoreCase("Padeiro") == true)
            {
                auxId = 13000 + aleatorio.nextInt(1000);
            }

            for (int i = 0; i < this.repo.getTotalFuncionarios(); i++)
            {
                if(this.repo.obterFuncionario(i).getId() == auxId)
                {
                    auxId = -1;
                }
            }
        }
        while(auxId == -1);
        
        return auxId;
    }
}
