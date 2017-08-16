/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FuncionarioMenuLayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void botaoCadastrarFuncionario() {

        Pane root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("MenuCadastrarFuncionario.fxml"));

        } catch (Exception exc) {

            System.out.println("Erro ao ler fxml");
            exc.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root, 600, 400);

        MainLogin.setCena(scene);
    }
}
