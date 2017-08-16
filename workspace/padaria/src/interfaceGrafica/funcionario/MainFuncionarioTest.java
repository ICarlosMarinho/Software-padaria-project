package interfaceGrafica.funcionario;

import java.io.IOException;
import java.util.Stack;

import exceptions.NegocioException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.SistemaPadaria;

public class MainFuncionarioTest extends Application {

	public static Stage palco;
	public static Stack<Scene> cenaAnterior;
	public static Stack<String> tituloAnterior;
	
	public static void setCenaAtual(Scene scene) {
		MainFuncionarioTest.cenaAnterior.push( MainFuncionarioTest.palco.getScene() );
		MainFuncionarioTest.palco.setScene(scene);
	}
	
	public static void setTituloAtualPalco(String title) {
		if(title == null) {
			throw new RuntimeException("Variable is null");
		}
		
		MainFuncionarioTest.tituloAnterior.push( MainFuncionarioTest.palco.getTitle() );
		MainFuncionarioTest.palco.setTitle(title);
	}
	
	public static void setCenaAnterior() {
		MainFuncionarioTest.palco.setScene( MainFuncionarioTest.cenaAnterior.pop() );
		MainFuncionarioTest.palco.setTitle( MainFuncionarioTest.tituloAnterior.pop() );
	}
	
	
	public static void main(String[] args) {
		
		SistemaPadaria sistema = SistemaPadaria.getInstancia();
		
		try { /// caso para teste
			sistema.cadastrarProduto("Leite", "Camponesa", 9, 2, 2020, 40, 4.5);
			sistema.cadastrarProduto("Leite em pó", "Camponesa", 9, 2, 2020, 40, 4.5);
			sistema.cadastrarProduto("Pão Francês", "Encomendado pelo fornecedor de Joana Bezerra", 9, 2, 2019, 200, 5.5);
		} catch (NegocioException ne) {
			System.out.println(ne.getMessage());
		}
		
		System.out.println("Quantidade = " + sistema.totalProduto());
		
		launch(args);
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainFuncionarioTest.tituloAnterior = new Stack<>();
		MainFuncionarioTest.cenaAnterior = new Stack<>();
		MainFuncionarioTest.palco = primaryStage;
		
		
		Parent root = null;
		
		try {
			root = FXMLLoader.load( getClass().getResource("PrincipalFuncionarioLayout.fxml") );
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo PrincipalFuncionarioLayout");
			System.exit(1);
		}
		
		Scene scene = new Scene(root, 600, 400);
		
		
		MainFuncionarioTest.palco.setScene(scene);
		MainFuncionarioTest.palco.setTitle("TESTE DO FUNCIONARIO");
		MainFuncionarioTest.palco.show();
		
	}

}
