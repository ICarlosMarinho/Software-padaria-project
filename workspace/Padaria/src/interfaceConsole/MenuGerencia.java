package interfaceConsole;
import java.util.Scanner;
import classesBasicas.*;
import negocio.SistemaPadaria;

public class MenuGerencia 
{
        
    SistemaPadaria sistema;
    Scanner input;
    Funcionario auxFun;
    Endereco auxEnd;
    int auxOpc;
    
    
    public MenuGerencia() 
    {
    	this.sistema = SistemaPadaria.getInstancia();
        this.auxFun = new Funcionario();
        this.auxEnd = new Endereco();
        this.input = new Scanner(System.in);
        this.auxOpc = 0;
    }
 
    public void inicializarMenu()
    {
        System.out.print("***MENU GERENCIA***\n\n\n");//**MENU PRINCIPAL
        
        System.out.println("1.Funcionarios\n"
                + "2.Produtos\n"
                + "3.Fornecedores\n"
                + "4.Ajustar Porcentual de crédito\n"
                + "5.Valor máximo de crédito\n"
                + "6.Clientes\n\n"
                + "7.Sair\n");
        
       this.auxOpc = Integer.parseInt(input.nextLine());
       
       switch(this.auxOpc)
       {
           case 1://**SUBMENU QUE REALIZA OPERAÇOES COM FUNCIONARIOS
           {
               do
               {
                    System.out.println("***FUNCIONARIOS***\n\n\n");
               
                    System.out.println("1.Cadastar funcionario\n"
                            + "2.Editar funcionario\n"
                            + "3.Buscar funcionario\n"
                            + "4.Remover funcionario\n"
                            + "5.Ranking de vendas\n\n"
                            + "6.Sair\n");

                    this.auxOpc = Integer.parseInt(input.nextLine());

                    switch(this.auxOpc)
                    {
                        case 1://**CADASTRO DE UM FUNCIONARIO
                        {
                            System.out.print("Insira o nome do funcionario: ");
                            this.auxFun.setNome(input.nextLine());

                            System.out.print("Insira o cargo: ");
                            this.auxFun.setCargo(input.nextLine());
                           
                            do
                            {
                                this.auxFun.setId(sistema.atribuirIdFuncionario(this.auxFun.getCargo()));
                            }
                            while(sistema.buscarFuncionario(this.auxFun.getId()) != null);
                          
                            System.out.print("ID: " + this.auxFun.getId());

                            System.out.print("\nInsira o login: ");
                            this.auxFun.setLogin(input.nextLine());

                            System.out.print("Insira a senha: ");
                            this.auxFun.setSenha(input.nextLine());

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
                        }
                        break;

                        case 2:
                        {
                            //Editar
                        }
                        break;

                        case 3://**BUSCAR FUNCIONARIO USANDO O ID COMO ARGUMENTO
                        {
                            int auxId;

                            System.out.print("Insira o ID: ");
                            auxId = Integer.parseInt(input.nextLine());

                            if(sistema.buscarFuncionario(auxId) == null)
                            {
                               System.err.print("\n\nEsse funcionario nao existe!\n\n");
                           
                               break;
                            }

                            System.out.print(sistema.buscarFuncionario(auxId).toString() + "\n\n");
                        }
                        break;
                        
                        case 4: { // Ajustar porcentual de credito
                        	
                        	System.out.print("Insira o novo porcentual [0 - 100]%: ");
                        	double novoPorcentual = this.input.nextDouble();
                        	this.input.nextLine();
                        	
                        	if( novoPorcentual < 0 ) {
                        		System.out.println("Porcentual inválido\n");
                        		break;
                        	}
                        	
                        	novoPorcentual /= 100;
                        	
                        	this.sistema.setPorcentual(novoPorcentual);
                        	break;
                        }
                        
                        case 5: { // Ajustar maximo de credito
                        	
                        	System.out.print("Insira o novo maximo de credito: ");
                        	double novoMaximo = this.input.nextDouble();
                        	this.input.nextLine();
                        	
                        	if( novoMaximo < 0 ) {
                        		System.out.println("Máximo inválido\n");
                        		break;
                        	}
                        	
                        	this.sistema.setMax(novoMaximo);
                        	break;
                        	
                        }
                        

                        case 6://EXCLUIR FUNCIONARIO USANDO ID COMO ARGUMENTO
                        {
                            int auxId;

                            System.out.print("Insira o ID: ");

                            auxId = Integer.parseInt(input.nextLine());

                            if(sistema.buscarFuncionario(auxId) == null)
                            {
                               System.err.print("\n\nEsse funcionario nao existe!\n\n");

                               break;
                            }

                            if(sistema.excluirFuncionario(sistema.retornarPosicaoFuncionario(auxId)) == true)
                            {
                                System.out.print("\nFuncionario excuido com sucesso!\n\n");
                            }
                        }
                        break;

                        case 7: 
                        {
                            //Ranking
                        }
                        break;
                    }
               }
               while(this.auxOpc != 6);
               
           }
       }
    }
       
}
