package interfaceConsole;

import java.util.Scanner;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import negocio.SistemaPadaria;

public class InicializarSistema {

    public static void main(String[] args) {
        MenuCaixa caixa = new MenuCaixa();
        MenuGerente gerente = new MenuGerente();
        Scanner input = new Scanner(System.in);
        SistemaPadaria sistema = SistemaPadaria.getInstancia();
        String auxLogin;
        String auxSenha;

        Endereco end = new Endereco("-", "-", "-", "-", "-");

        Funcionario teste1 = new Funcionario("carlos", "gerente", "admin", "12345", end, 0, 0, 0);
        teste1.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste1);

        Funcionario teste2 = new Funcionario("carlos", "caixa", "funcionario", "54321", end, 0, 0, 0);
        teste2.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste2);

        System.out.print("Insira o login: ");
        auxLogin = input.nextLine();

        System.out.print("Insira a senha: ");
        auxSenha = input.nextLine();

        if (auxLogin.equalsIgnoreCase("admin") == true) {
            gerente.inicializarMenu();
        } else {
            System.out.println("\n\nFalha no login.");
        }

        input.close();
    }
}
