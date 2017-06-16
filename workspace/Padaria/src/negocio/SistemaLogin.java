package negocio;
import classesBasicas.Funcionario;

public class SistemaLogin 
{
    SistemaPadaria sistema = SistemaPadaria.getInstancia();
    Funcionario auxFun;
    
    public String InicializarLogin(String login, String senha)
    {
        this.auxFun = new Funcionario(sistema.buscarFuncionario(login));
        
        if(login == null || senha == null || this.auxFun == null)
        {
            return null;
        }
        
        else if(this.auxFun.getSenha().equals(senha) == false)
        {
            return null;
        }
        
        return this.auxFun.getCargo();
    }
}
