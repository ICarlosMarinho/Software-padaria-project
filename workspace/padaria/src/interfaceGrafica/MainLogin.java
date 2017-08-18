/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package interfaceGrafica;

import java.util.Stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainLogin extends Application {

	private static Stage stage;
	private static Stack<Scene> cenaAnterior;
	private static Stack<String> tituloAnterior;
	
	public static void setCena(Scene scene) {
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

    @Override
    public void start(Stage primaryStage) throws Exception {

    	stage = primaryStage;
    	cenaAnterior = new Stack<Scene>();
    	
        Pane root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("TelaLoginLayout.fxml"));

        } catch (Exception exc) {

            System.out.println("Erro ao ler fxml");
            exc.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root, 600, 400);

        stage.setScene(scene);
        stage.setTitle("Padarex");
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
*/