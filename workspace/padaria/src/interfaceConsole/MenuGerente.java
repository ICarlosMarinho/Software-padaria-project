package interfaceConsole;

import java.util.Scanner;

public class MenuGerente 
{
    private int auxOpc;
    private Scanner input;
    private SubmenusFuncionario subFun;
    private SubmenusCliente subCli;
    
    public MenuGerente() 
    {
        this.subFun = new SubmenusFuncionario();
        this.subCli = new SubmenusCliente();
        this.input = new Scanner(System.in);
        this.auxOpc = 0;
    }
 
    public void inicializarMenu()
    {
    	boolean continuar = true;
    	
    	
    	do {
	    	
	        System.out.print("***MENU GERENTE***\n\n\n");//**MENU PRINCIPAL
	        
	        System.out.println("1.Funcionarios\n"
	                + "2.Produtos\n"
	                + "3.Clientes\n\n"
	                + "4.Sair\n");
	        
	       this.auxOpc = Integer.parseInt(input.nextLine());
	       
	       switch(this.auxOpc)
	       {
	           case 1://**SUBMENU QUE REALIZA OPERAÇOES COM FUNCIONARIOS
	           {
	               do
	               {
	                    System.out.print("***FUNCIONARIOS***\n\n\n");
	               
	                    System.out.println("1.Cadastar funcionario\n"
	                            + "2.Editar funcionario\n"
	                            + "3.Buscar funcionario\n"
	                            + "4.Remover funcionario\n\n"
	                            + "5.Sair\n");
	
	                    this.auxOpc = Integer.parseInt(input.nextLine());
	
	                    switch(this.auxOpc)
	                    {
	                        case 1://**CADASTRAR UM FUNCIONARIO
	                        {
	                            this.subFun.menuCadastrarFuncionaio();
	                        }
	                        break;
	
	                        case 2://**EDITAR FUNCIONARIO
	                        {
	                            this.subFun.menuEditarFuncionario();
	                        }
	                        break;
	
	                        case 3://**BUSCAR FUNCIONARIO USANDO O ID COMO ARGUMENTO
	                        {
	                            this.subFun.menuBuscarFuncionario();
	                        }
	                        break;
	
	                        case 4://EXCLUIR FUNCIONARIO USANDO ID COMO ARGUMENTO
	                        {
	                            this.subFun.menuExcluirFuncionario();
	                        }
	                        break;
	                    }
	                    
	               }
	               while(this.auxOpc != 5);
	               break;
	           }
	
	            case 2:
	            {
	                do
	                {
	                    System.out.print("***PRODUTOS***\n\n\n");
	               
	                    System.out.println("1.Cadastar produto\n"
	                            + "2.Editar produto\n"
	                            + "3.Buscar produto\n"
	                            + "4.Remover produto\n"
	                            + "5.Listar produtos\n"
	                            + "6.Sair\n");
	
	                    this.auxOpc = Integer.parseInt(input.nextLine());
	                    
	                    switch(auxOpc)
	                    {
	                        case 1:
	                        {
	                            //Cadastrar produto
	                        }
	                        break;
	                        
	                        case 2:
	                        {
	                            //Editar
	                        }
	                        break;
	                        
	                        case 3:
	                        {
	                            //Buscar
	                        }
	                        break;
	                        
	                        case 4:
	                        {
	                            //Remover
	                        }
	                        break;
	                        
	                        case 5:
	                        {
	                            //Listar
	                        }
	                        break;
	                    }
	                }
	                while(this.auxOpc != 6);
	            }
	            break;
	            
	            case 3:
	            {
	                do
	                {
	                    System.out.print("***CLIENTES***\n\n\n");
	               
	                    System.out.println("1.Cadastar cliente\n"
	                            + "2.Editar cliente\n"
	                            + "3.Buscar cliente\n"
	                            + "4.Remover cliente\n"
	                            + "5.Listar clientes\n"
	                            + "6.Sair\n");
	
	                    this.auxOpc = Integer.parseInt(input.nextLine());
	                    
	                    switch(auxOpc)
	                    {
	                        case 1:
	                        {
	                            this.subCli.menuCadastrarCliente();
	                        }
	                        break;
	                        
	                        case 2:
	                        {
	                            this.subCli.menuEditarCliente();
	                        }
	                        break;
	                        
	                        case 3:
	                        {
	                            this.subCli.menuBuscarCliente();
	                        }
	                        break;
	                        
	                        case 4:
	                        {
	                            this.subCli.menuRemoverCliente();
	                        }
	                        break;
	                        
	                        case 5:
	                        {
	                            this.subCli.menuListarClientes();
	                        }
	                        break;
	                    }
	                    
	                }
	                while(this.auxOpc != 6);
	            }
	            break;
	            
	            case 4: {
	            	continuar = false;
	            }
	       }
	       
	    } while ( continuar );
    	
    }  
}
