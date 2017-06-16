package interfaceConsole;

import java.util.Scanner;
import negocio.SistemaPadaria;

public class InicializarSistema 
{
    public static void main(String[] args) 
    {
        MenuCaixa caixa = new MenuCaixa();
        MenuGerente gerente = new MenuGerente();
        Scanner input = new Scanner(System.in);
        SistemaPadaria sistema = SistemaPadaria.getInstancia();
        String auxLogin;
        String auxSenha;
        
        System.out.print("Insira o login: ");
        auxLogin = input.nextLine();
        
        System.out.print("Insira a senha: ");
        auxSenha = input.nextLine();
        
        if(sistema.inicializarSistemaLogin(auxLogin, auxSenha).equalsIgnoreCase("Gerente"))
        {
            gerente.inicializarMenu();
        }
        else if((sistema.inicializarSistemaLogin(auxLogin, auxSenha)).equalsIgnoreCase("Caixa"))
        {
            caixa.inicializarMenu();
        }
        else
        {
            System.out.println("\n\nFalha no login.");
        }
        
    }
}
