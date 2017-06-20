package interfaceConsole;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import java.util.Scanner;
import negocio.SistemaPadaria;

public class SubmenusFuncionario 
{
    SistemaPadaria sistema = SistemaPadaria.getInstancia();
    private Scanner input;
    private Funcionario auxFun;
    private Endereco auxEnd;
    private int auxOpc;
    
    public SubmenusFuncionario()
    {
        input = new Scanner(System.in);
        auxOpc = 0;
    }
    
    public void menuCadastrarFuncionaio()
    {
        this.auxFun = new Funcionario();
        this.auxEnd = new Endereco();

        System.out.print("Insira o nome do funcionario: ");
        this.auxFun.setNome(input.nextLine());

        System.out.print("Insira o cargo(Gerente, caixa, estoquista ou padeiro): ");
        this.auxFun.setCargo(input.nextLine());

        this.auxFun.setId(sistema.atribuirIdFuncionario(this.auxFun));
        

        System.out.print("\nID: " + this.auxFun.getId());

        if (auxFun.getCargo().equalsIgnoreCase("Gerente") || auxFun.getCargo().equalsIgnoreCase("Caixa"))
        {
            System.out.print("\nInsira o login(4-10 caracteres): ");
            this.auxFun.setLogin(input.nextLine());

            System.out.print("Insira a senha(4-10 caracteres): ");
            this.auxFun.setSenha(input.nextLine());
        }

        System.out.print("Insira o salario: R$");
        this.auxFun.setSalario(Double.parseDouble(input.nextLine()));

        this.auxFun.setValorVendas(0);

        //**PARTE DE CADASTRO DO ENDEREÇO DO FUNCIONARIO QUE ESTA SENDO CADASTRADO

        System.out.print("\n\n\t**Endereco**\n\n");

        System.out.print("Insira o logradouro: ");
        this.auxEnd.setLogradouro(input.nextLine());

        System.out.print("Insira o numero: ");
        this.auxEnd.setNumero(input.nextLine());

        System.out.print("Insira o complemento: ");              
        this.auxEnd.setComplemento(input.nextLine());

        System.out.print("Insira a cidade: ");
        this.auxEnd.setCidade(input.nextLine());

        System.out.print("Insira o Estado: ");
        this.auxEnd.setEstado(input.nextLine());

        this.auxFun.setEndereco(auxEnd);//**ATRIBUIR O ENDEREÇO QUE FOI PREENCHIDO AO FUNCIONARIO CADASTRADO

        if(sistema.cadastrarFuncionario(auxFun) == true)//**CADASTRO EFETIVADO NO VETOR DE FUNCIONARIOS 
        {
            System.out.println("\n\nFuncionario cadastrado com sucesso!");
        }
        else
        {
            System.err.println("\n\nErro no cadastro do funcionario.");
        }
    }
    
    public void menuEditarFuncionario()
    {
        int auxId;

        System.out.print("Insira o ID: ");
        auxId = Integer.parseInt(input.nextLine());

        if(sistema.buscarFuncionario(auxId) == null)
        {
           System.err.print("\n\nEsse funcionario nao existe!\n\n");

           return;
        }

        this.auxFun = new Funcionario(sistema.buscarFuncionario(auxId));

        System.out.print("1.Editar nome\n");
        System.out.print("2.Editar cargo\n");
        System.out.print("3.Editar senha\n");
        System.out.print("4.Editar endereco\n");
        System.out.print("5.Editar salario\n");

        this.auxOpc = Integer.parseInt(input.nextLine());

        switch(auxOpc)
        {
            case 1:
            {
                System.out.print("\n\nInsira o novo nome: ");
                this.auxFun.setNome(input.nextLine());


            }
            break;

            case 2:
            {
                System.out.print("\n\nInsira o novo cargo(Gerente, caixa, estoquista ou padeiro): ");
                this.auxFun.setCargo(input.nextLine());
            }
            break;

            case 3:
            {
                System.out.print("\nInsira a nova senha(4-10 caracteres): ");
                this.auxFun.setSenha(input.nextLine());
            }
            break;

            case 4:
            {
                System.out.print("\nInsira o novo salario: ");
                this.auxFun.setSalario(Double.parseDouble(input.nextLine()));
            }   
        }

        if (sistema.alterarInfoFuncionario(auxFun) == true)
        {
            System.out.println("\n\nFuncionario atualizado!");
        }
        else
        {
            System.err.println("\n\nErro na atualizaçao das informações.");
        }
    }
    
    public void menuBuscarFuncionario()
    {
        int auxId;

        System.out.print("Insira o ID: ");
        auxId = Integer.parseInt(input.nextLine());

        if(sistema.buscarFuncionario(auxId) == null)
        {
           System.err.print("\n\nEsse funcionario nao existe!\n\n");

           return;
        }

        System.out.print(sistema.buscarFuncionario(auxId).toString() + "\n\n");
    }
    
    public void menuExcluirFuncionario()
    {
        int auxId;

        System.out.print("Insira o ID: ");

        auxId = Integer.parseInt(input.nextLine());

        if(sistema.buscarFuncionario(auxId) == null)
        {
           System.err.print("\n\nEsse funcionario nao existe!\n\n");

           return;
        }

        if(sistema.excluirFuncionario(sistema.retornarPosicaoFuncionario(auxId)) == true)
        {
            System.out.print("\nFuncionario excuido com sucesso!\n\n");
        }
    }
}
