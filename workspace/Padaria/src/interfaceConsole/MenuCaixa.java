package interfaceConsole;

import java.util.Calendar;
import java.util.Scanner;

import negocio.SistemaPadaria;
import classesBasicas.*;


public class MenuCaixa {
	
	// atributos
	SistemaPadaria sistema;
	Scanner console;
	
	
	// construtores
	public MenuCaixa() {
		this.sistema = SistemaPadaria.getInstancia();
		console = new Scanner(System.in);
	}
	
	
	// opcoes do metodo inicializarMenu
	public int opcaoMenuInicializarMenu() {
		
		System.out.print("***MENU FUNCIONÁRIO***\n\n\n");
		
		System.out.println("1.Adicionar Produto\n"
                + "2.Remover Produto\n"
                + "3.Ver Estoque\n"
                + "4.Buscar Produto\n"
                + "5.Editar Produto\n"
                + "6.Vender Produto\n"
                + "7.Cliente\n\n"
                + "8.Sair\n");
		
		int opcao = 0;
		
		do {
			
			System.out.print(">>> ");
			opcao = this.console.nextInt();
			this.console.nextLine();
			
			if( opcao >= 1 && opcao <= 8 ) {
				break;
			}
			
			System.out.println("Opcão inválida");
			
		} while( true );
		
		return opcao;
	}
	
	
	
	// opcoes da rotina editar produto
	public int opcaoEditarProduto() {
		
		System.out.println("1.Nome\n"
                + "2.Descrição\n"
                + "3.Validade\n"
                + "4.Quantidade\n"
                + "5.Preço\n\n"
                + "6.Sair\n");
		
		
		int opcao = 0;
		
		do {
			
			System.out.print(">>> ");
			opcao = this.console.nextInt();
			this.console.nextLine();
			
			if( opcao >= 1 && opcao <= 6 ) {
				break;
			}
			
			System.out.println("Opcão inválida");
			
		} while( true );
		
		return opcao;
	}
	
	
	/*
	 * menu principal do funcionario
	 */
	public void inicializarMenu() {
		
		boolean continuar = true;
		
		do {
			
			int opcao = this.opcaoMenuInicializarMenu();
			
			switch( opcao ) {
			case 1: { // adicionar produto
				
				System.out.print("Insira o nome do produto: ");
				String nome = this.console.nextLine();
				
				System.out.print("Insira a descrição do produto: ");
				String descricao = this.console.nextLine();
				
				
				
				System.out.println("\nValidade: ");
				
				System.out.print("Insira o dia: ");
				int dia = this.console.nextInt();
				this.console.nextLine();
				
				System.out.print("Insira o mes: ");
				int mes = this.console.nextInt();
				this.console.nextLine();
				
				System.out.print("Insira o ano: ");
				int ano = this.console.nextInt();
				this.console.nextLine();
				
				
				
				System.out.print("\nInsira a quantidade: ");
				double quantidade = this.console.nextDouble();
				this.console.nextLine();
				
				System.out.print("Insira o preço do produto: ");
				double preco = this.console.nextDouble();
				this.console.nextLine();
				
				
				if ( this.sistema.cadastrarProduto(nome, descricao
											      , dia, mes, ano
											      , quantidade, preco) ) {
					
					System.out.println("Produto cadastrado com sucesso !");
					
				} else {
					
					System.out.println("Cadastro não realizado");
					
				}
				
				break;
			}
			
			case 2: { // remover produto
				
				System.out.print("Insira o id do produto: ");
				int id = this.console.nextInt();
				this.console.nextLine();
				
				
				
				if( this.sistema.removerProduto(id) ) {
					
					System.out.println("Produto removido com sucesso !");
					
				} else {
					
					System.out.println("Remoção não realizada");
				}
				
				break;
			}
			
			case 3: { // ver estoque
				
				System.out.println("----------------------------");
				System.out.println("\tESTOQUE");
				System.out.println("----------------------------");
				
				
				Produto[] estoque = this.sistema.listaProduto();
				
				if( estoque.length == 0 ) {
					
					System.out.println("Estoque está vazio");
					break;
				}
				
				
				int dia, mes, ano;
				
				for( int k = 0; k < estoque.length; k++ ) {
					
					
					dia = estoque[k].getValidade().get(Calendar.DAY_OF_MONTH);
					mes = estoque[k].getValidade().get(Calendar.MONTH);
					ano = estoque[k].getValidade().get(Calendar.YEAR);
					
					
					System.out.print( "Nome: " + estoque[k].getNome() );
					
					if( !this.sistema.validadeProduto(dia, mes, ano) ) {
						System.out.println("*");
					} else {
						System.out.println();
					}
					
					System.out.println( "Id: " + estoque[k].getId() );
					System.out.println( "Preço: R$" + estoque[k].getPreco() );
					
					System.out.println("----------------------------------------");
					
				}
				
				break;
			}
			
			case 4: { // buscar produto
				
				System.out.print("Insira o id do produto: ");
				int id = this.console.nextInt();
				this.console.nextLine();
				
				
				Produto procurado = this.sistema.buscarProduto(id);
				
				
				if( procurado == null ) {
					
					System.out.println("Produto não encontrado");
					break;
					
				}
				
				
				int dia, mes, ano;
				
				dia = procurado.getValidade().get(Calendar.DAY_OF_MONTH);
				mes = procurado.getValidade().get(Calendar.MONTH);
				ano = procurado.getValidade().get(Calendar.YEAR);
				
				
				
				System.out.println( "Nome: " + procurado.getNome() );
				System.out.println( "Descrição: " + procurado.getDescricao() );
				System.out.println( "Id: " + procurado.getId() );
				System.out.println( "Preço: R$" + procurado.getPreco() );
				
				if( !this.sistema.validadeProduto(dia, mes, ano) ) {
					System.out.println("Validade: VENCIDO");
				} else {
					System.out.printf(  "Validade: %02d/%02d/%d%n", dia, mes+1, ano);
				}
				
				System.out.println( "Quantidade: " + procurado.getQuantidade() );
				
				break;
			}
			
			case 5: { // editar produto
				
				System.out.print("Insira o id do produto: ");
				int id = this.console.nextInt();
				this.console.nextLine();
				
				
				Produto editar = this.sistema.buscarProduto(id);
				
				if( editar == null ) {
					System.out.println("Produto não encontrado");
				}
				
				System.out.println("O que editar: \n");
				
				int oQue = this.opcaoEditarProduto();
				
				
				switch( oQue ) {
				case 1: { // nome
					
					System.out.print("Insira o novo nome: ");
					String novoNome = this.console.nextLine();
					
					
					if( this.sistema.modificarProduto(id, 0, novoNome) ) {
						
						System.out.println("Nome modificado com sucesso !");
						
					} else { 
						
						System.out.println("Nome não modificado");
						
					}
					
					break;
				}
				
				case 2: { // descrição
					
					System.out.print("Insira a nova descrição: ");
					String novaDescricao = this.console.nextLine();
					
					if( this.sistema.modificarProduto(id, 1, novaDescricao) ) {
						
						System.out.println("Descrição modificada com sucesso !");
						
					} else { 
						
						System.out.println("Descrição não modificada");
						
					}
					
					break;
				}
				
				case 3: { // validade
					
					System.out.println("Insira a nova validade:");
					System.out.print("dia: ");
					int dia = this.console.nextInt();
					this.console.hasNextLine();
					System.out.print("mes: ");
					int mes = this.console.nextInt();
					this.console.hasNextLine();
					System.out.print("ano: ");
					int ano = this.console.nextInt();
					this.console.hasNextLine();
					
					
					if( !this.sistema.validadeProduto(dia, --mes, ano) ) {
						System.out.println("Validade inválida");
						break;
					}
					
					if( this.sistema.modificarProduto(id, 2, dia, mes, ano) ) {
						
						System.out.println("Validade modificada com sucesso !");
						
					} else { 
						
						System.out.println("Validade não modificada");
						
					}
					
					break;
				}
				
				case 4: { // quantidade
					
					System.out.print("Insira a nova quantidade: ");
					int quantidade = this.console.nextInt();
					this.console.nextLine();
					
					
					if( quantidade <= 0 ) {
						System.out.println("Quantidade inválida");
						break;
					}
					
					if( this.sistema.modificarProduto(id, 3, quantidade) ) {
						
						System.out.println("Quantidade modificada com sucesso !");
						
					} else { 
						
						System.out.println("Quantidade não modificada");
						
					}
					
					break;
				}
				
				case 5: { // preco
					
					System.out.print("Insira o novo preço: ");
					double preco = this.console.nextDouble();
					this.console.nextLine();
					
					
					if( preco <= 0 ) {
						System.out.println("Preço inválido");
						break;
					}
					
					if( this.sistema.modificarProduto(id, 4, preco) ) {
						
						System.out.println("Preço modificado com sucesso !");
						
					} else { 
						
						System.out.println("Preço não modificado");
						
					}
					
					
					break;
				}
				// fim editar produto
				}
				
				break;
			}
			
			case 6: { // vender produto
				
				System.out.print("Insira o id do produto: ");
				int idProduto = this.console.nextInt();
				this.console.nextLine();
				
				
				Produto vender = this.sistema.buscarProduto(idProduto);
				
				if( vender == null ) {
					System.out.println("Produto não existe");
					break;
				}
				
				System.out.print("Insira a quantidade: ");
				int quantidade = this.console.nextInt();
				this.console.nextLine();
				
				
				System.out.println("Cliente cadastrado ou não (s/n): ");
				String resposta = this.console.nextLine();
				
				if( !resposta.equals("s") ) {
					
					System.out.println("Venda será efetuada para cliente não cadastado");
				
					if( this.sistema.venderProduto(idProduto, quantidade) ) {
	
						System.out.println("Venda efetuada com sucesso !");
						
					} else {
						
						System.out.println("Venda não concluída");
						
					}
					
				} else { // resposta foi s
					
					System.out.println("Insira o id do cliente: ");
					int idCliente = this.console.nextInt();
					this.console.nextLine();
					
					
					if( this.sistema.buscarCliente(idCliente) == null ) {
						System.out.println("Cliente não encontrado");
						break;
					}
					
					
					if( this.sistema.venderProduto(idProduto, idCliente
												  , quantidade)        ) {
						
						System.out.println("Venda efetuada com sucesso !");
						
					} else {
						
						System.out.println("Venda não concluída");
						
					}
					
				}
				// fim venda
				break;
			}
			
			case 7: { // cliente
				
				// TODO colocar aqui chamada para classe com os menus do cliente
				System.out.println("Ainda para implementar");
				break;
			}
			
			
			case 8: { // sair
				
				System.out.println("Saindo");
				continuar = false;
				break;
			}
			}
		
			this.console.nextLine();
			
		} while ( continuar );
	
		
	}
	
	

}
