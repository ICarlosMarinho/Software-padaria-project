package interfaceGrafica;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TelaMenuPrincipalFuncionarioLayoutController {

	@FXML
	public void onActionLogout() {
		MainPadaria.logado = null;
		MainPadaria.setCenaAnterior();
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
		
		MainPadaria.setCenaAtual(scene);
		MainPadaria.setTituloAtualPalco("Venda | Funcionário: " + MainPadaria.logado.getNome());
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
		
		MainPadaria.setCenaAtual(scene);
		MainPadaria.setTituloAtualPalco("Cliente | Funcionário: " + MainPadaria.logado.getNome());
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
		
		MainPadaria.setCenaAtual(scene);
		MainPadaria.setTituloAtualPalco("Estoque | Funcionário: " + MainPadaria.logado.getNome());
	}
	

}
