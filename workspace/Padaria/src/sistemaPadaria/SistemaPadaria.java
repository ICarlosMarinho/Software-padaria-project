package sistemaPadaria;
import negocio.*;
import classesBasicas.*;

public class SistemaPadaria 
{
   private static SistemaPadaria system;
   private OperacoesFuncionario opFuncionarios = new OperacoesFuncionario();;
   
   private SistemaPadaria()
   {
        
   }
   
   public static SistemaPadaria getInstancia()
   {
        if(system == null)
        {
            system = new SistemaPadaria();
        }
        
        return system;
   }
   
   //**METODOS DE ACESSO A CLASSE OPERACOESFUNCIONARIO
   
   public boolean cadastrarFuncionario(Funcionario auxFun)
   {
       return this.opFuncionarios.cadastrar(auxFun);
   }
   
   public boolean excluirFuncionario(int posicaoFun)
   {
       return this.opFuncionarios.excluir(posicaoFun);
   }
   
   public Funcionario buscarFuncionario(int auxId)
   {
       return this.opFuncionarios.buscar(auxId);
   }
   
   public int retornarPosicaoFuncionario(int auxId)
   {
       return this.opFuncionarios.retornarPosicao(auxId);
   }
   
   public int atribuirIdFuncionario(String auxCargo)
   {
       return this.opFuncionarios.atribuirId(auxCargo);
   }
   
   //**METODOS DE ACESSO A CLASSE OPERACOESPRODUTO
   
   //**METODOS DE ACESSO A CLASSE OPERACOESCLIENTE
}
