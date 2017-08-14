package interfaceGrafica.funcionario;

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
			root = FXMLLoader.load( getClass().getResource("VendaFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo VendaFuncionarioLayout");
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
			root = FXMLLoader.load( getClass().getResource("ClienteFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo ClienteFuncionarioLayout");
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
			root = FXMLLoader.load( getClass().getResource("EstoqueFuncionarioLayout.fxml") );			
		} catch ( IOException ioe ) {
			System.out.println("Problema para carregar arquivo EstoqueFuncionarioLayout");
			System.exit(1);
		}
		
		
		Scene scene = new Scene(root, 600, 400);
		
		MainFuncionarioTest.setCenaAtual(scene);
		MainFuncionarioTest.setTituloAtualPalco("Estoque | Funcionario: " + "Jonas");
	}
	

}
