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
    
    public Funcionario verificarInformacoes(Funcionario auxFun)
    {   
        //**VERIFICAR FUNCIONARIO OU ENDEREÇO NULO
        if (auxFun == null || auxFun.getEndereco() == null)
        {
            return null;
        }
        
        //**VERIFICAR SE A STRING REFERENTE AO NOME É NULA
        else if(auxFun.getNome() == null)
        {
            return null;
        }
        
        //**VERIFICA SE O CARGO DO FUNCIONARIO É VALIDO 
        else if(auxFun.getCargo().equalsIgnoreCase("Gerente") == false 
                && auxFun.getCargo().equalsIgnoreCase("Caixa") == false
                && auxFun.getCargo().equalsIgnoreCase("Padeiro") == false
                && auxFun.getCargo().equalsIgnoreCase("Estoquista") == false)
        {
            return null;
        }
        
    
       /*
        *Verifica algumas informações sobre login e senha caso o funcionário seja gerente ou caixa,
        *pois somente esses cargos tem acesso ao sistema.
        */
        else if(auxFun.getCargo().equalsIgnoreCase("Gerente") || auxFun.getCargo().equalsIgnoreCase("Caixa"))
        {
            //**VERIFICA SE O LOGIN E A SENHA ESTÃO NULOS
            if(auxFun.getLogin() == null || auxFun.getSenha() == null)
            {
                return null;
            }
            
            //**VERIFICA O TAMANHO DO LOGIN E SENHA (A REGRA AQUI É ESTAR ENTRE 4~10 CARACTERES)
            else if( auxFun.getLogin().length() < 4 && auxFun.getLogin().length() > 10
                    || auxFun.getSenha().length() < 4 && auxFun.getSenha().length() > 10)
            {
                return null;
            }            
        }

        
        //**VERIFICA SE O SALARIO É MAIOR QUE ZERO
        else if(auxFun.getSalario() <= 0f)
        {
            return null;
        }
        
       //**VERIFICA SE ALGUMA INFORMAÇÃO DO ENDEREÇO ESTÁ NULA
       if(auxFun.getEndereco().getLogradouro() == null
          || auxFun.getEndereco().getCidade() == null
          || auxFun.getEndereco().getComplemento() == null
          || auxFun.getEndereco().getEstado() == null
          || auxFun.getEndereco().getNumero() == null)
       {
           return null;
       }
       
       return auxFun;
    }
    
    public boolean cadastrar(Funcionario novoFun)
    {
        //**VERIFICAR SE O VETOR DE FUNCIONARIOS ATINGIU O LIMITE
        if(this.repo.getTotalFuncionarios() >= TAM_VETOR)
        {
            return false;
        }
        
        else if(this.verificarInformacoes(novoFun) == null)
        {
            return false;
        }
        
        //**VERIFICA SE O LOGIN DO FUNCIONARIO A SER CADASTRADO JA EXISTE
        for (int i = 0; i < this.repo.getTotalFuncionarios(); i++)
        {
            if (novoFun.getLogin().equalsIgnoreCase(this.repo.obterFuncionario(i).getLogin()) == true)
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
    
    public boolean alterarInfo(Funcionario auxFun)
    {
        if (this.verificarInformacoes(auxFun) != null)
        {
            this.repo.getFuncionarios()[this.retornarPosicao(auxFun.getId())] = auxFun;
            
            return true;
        }
       
        return false;    
    }
    
    public Funcionario buscar(int auxId)//**BUSCAR FUNCIONARIO A PARTIR DO ID
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
    
    public Funcionario buscar(String auxLogin)//**BUSCAR FUNCIONARIO A PARTIR DO LOGIN
    {
        int i;
        
        if (auxLogin == null)
        {
            return null;
        }
        
        for(i = 0; i < this.repo.getTotalFuncionarios() && this.repo.obterFuncionario(i).getLogin().equalsIgnoreCase(auxLogin) != true;)
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
    
    public int atribuirId(Funcionario auxFun)
    {
        int auxId = -1;
        
        do
        {
            auxId = 10000 + aleatorio.nextInt(10000);
            
            for (int i = 0; i < this.repo.getTotalFuncionarios(); i++)
            {
                if(this.repo.obterFuncionario(i).getId() == auxFun.getId())
                {
                    auxId = -1;
                }
            }
        }
        while(auxId == -1);
        
        return auxId;
    }
}
