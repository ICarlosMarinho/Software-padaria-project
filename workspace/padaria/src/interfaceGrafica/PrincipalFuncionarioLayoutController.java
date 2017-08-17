package interfaceGrafica;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PrincipalFuncionarioLayoutController {

	@FXML
	public void onActionLogout() {
		System.out.println("onActionLogout method activated");
		// TODO implementar metodo do login
	}

	@FXML
	public void onActionVender() {
		
		Parent root = null;
		
		try {
			root = FXMLLoader.load( getClass().getResource("TelaVendaFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo TelaVendaFuncionarioLayout");
			System.exit(1);
		}
		
		
		
		Scene scene = new Scene(root, 600, 400);
		
		MainFuncionarioTest.setCenaAtual(scene);
		MainFuncionarioTest.setTituloAtualPalco("Venda | Funcionario: " + "Jonas");
	}

	@FXML
	public void onActionCliente() {
		
		Parent root = null;
		
		try {
			root = FXMLLoader.load( getClass().getResource("TelaClienteFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo ClienteFuncionarioLayout");
			ioe.printStackTrace();
			System.out.println("Problema para carregar arquivo TelaClienteFuncionarioLayout");
			ioe.printStackTrace();
			System.exit(1);
		}
		
		Scene scene = new Scene(root, 600, 400);
		
		MainFuncionarioTest.setCenaAtual(scene);
		MainFuncionarioTest.setTituloAtualPalco("Cliente | Funcionario: " + "Jonas");
	}

	@FXML
	public void onActionEstoque() {
		
		Parent root = null;
		
		try {
			root = FXMLLoader.load( getClass().getResource("TelaEstoqueFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo EstoqueFuncionarioLayout");
			System.out.println("Problema para carregar arquivo TelaEstoqueFuncionarioLayout");
			ioe.printStackTrace();
			System.exit(1);
		}
		
		
		Scene scene = new Scene(root, 600, 400);
		
		MainFuncionarioTest.setCenaAtual(scene);
		MainFuncionarioTest.setTituloAtualPalco("Estoque | Funcionario: " + "Jonas");
	}
	

}
