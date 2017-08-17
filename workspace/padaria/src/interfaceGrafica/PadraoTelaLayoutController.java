package interfaceGrafica;

import javafx.fxml.FXML;

/**
 * a ideia e que todos os controllers herdem desta classe visto
 * 	que os botoes do menubar sao sempre os mesmos em todas as telas
 */

public class PadraoTelaLayoutController {
	
	@FXML
	public void onActionSairDeSessao() {
		System.out.println("Botão sair de sessão pressionado");
	}
	@FXML
	public void onActionReiniciar () {
		System.out.println("Botão reiniciar pressionado");}
	@FXML 
	public void onActionFechar() {
		System.out.println("Botão fechar pressionado");}
	

}
