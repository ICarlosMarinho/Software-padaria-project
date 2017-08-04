package interfaceConsole;

import classesBasicas.*;
import java.util.Scanner;
import negocio.SistemaPadaria;

public class SubmenusCliente 
{
   SistemaPadaria sistema = SistemaPadaria.getInstancia();
   private Scanner input;
   private Cliente auxCli;
   private Endereco auxEnd;
   private int auxId;
   private Cliente[] auxLista;
   
   public SubmenusCliente()
   {
       this.input = new Scanner(System.in);
       this.auxLista = new Cliente[sistema.totalCliente()];
   }
   
   public void menuCadastrarCliente()
   {
       this.auxCli = new Cliente();
       this.auxEnd = new Endereco();
       
       System.out.print("Insira o nome do cliente: ");
       this.auxCli.setNome(this.input.nextLine());
       
       System.out.print("Insira o logradouro: ");
       this.auxEnd.setLogradouro(this.input.nextLine());
       
       System.out.print("Insira o numero: ");
       this.auxEnd.setNumero(this.input.nextLine());
       
       System.out.print("Insira o complemento: ");
       this.auxEnd.setComplemento(input.nextLine());
       
       System.out.print("Insira a cidade: ");
       this.auxEnd.setCidade(input.nextLine());
       
       System.out.print("Insira a Estado: ");
       this.auxEnd.setEstado(input.nextLine());
       
       if(sistema.cadastrarCliente(this.auxCli.getNome(),
                                   this.auxEnd.getLogradouro(),
                                   this.auxEnd.getNumero(),
                                   this.auxEnd.getComplemento(),
                                   this.auxEnd.getCidade(),
                                   this.auxEnd.getEstado()) == false)
       {
           System.err.println("\n\nErro no cadastro.");
       }
       else
       {
           System.out.println("\n\nCliente cadastrado com sucesso!");
       }
   }
   
   public void menuRemoverCliente()
   {
       System.out.print("Insira o ID do cliente: ");
       this.auxId = Integer.parseInt(input.nextLine());
       
       if(sistema.removerCliente(auxId) == true)
       {
           System.out.println("\n\nCliente removido com sucesso!");
       }
       else
       {
           System.err.println("\n\nErro na operação.");
       }
   }
   
   public void menuBuscarCliente()
   {
        System.out.print("Insira o ID do cliente: ");
        this.auxId = Integer.parseInt(input.nextLine());
        
        if(sistema.buscarCliente(auxId) != null)
        {
            System.out.println(sistema.buscarCliente(auxId).toString());
        }
        else 
        {
            System.out.println("Cliente não encontrado.");
        }
   }
   
   public void menuListarClientes()
   {
       this.auxLista = sistema.listaCliente();
       
       for(int i = 0; i < this.auxLista.length; i++)
       {
           System.out.println(this.auxLista[i].toString());
       }
   }
   
   public void menuEditarCliente()
   {
       //**Falta desenvolver.
   }
}
