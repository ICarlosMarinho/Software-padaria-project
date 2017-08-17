/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class MainLogin extends Application {

    private static Stage stage;

    public static void setCena(Scene cena) {

        MainLogin.stage.setScene(cena);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("TelaLoginLayout.fxml"));

        } catch (Exception exc) {

            System.out.println("Erro ao ler fxml");
            exc.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root, 600, 400);

        stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("Padarex");
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
