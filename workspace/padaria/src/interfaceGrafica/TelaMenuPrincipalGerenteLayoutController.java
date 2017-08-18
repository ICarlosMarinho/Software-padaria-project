/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TelaMenuPrincipalGerenteLayoutController implements Initializable {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnMenuFuncionarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void botaoSair() {

        Pane root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("../gerente/FuncionarioMenuLayout.fxml"));

        } catch (Exception exc) {

            System.out.println("Erro ao ler fxml");
            exc.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root, 600, 400);

        MainLogin.setCena(scene);
    }

    public void abrirMenuFuncionarios() {

        Pane root = null;

        try {

            root = FXMLLoader.load(getClass().getResource("TelaMenuFuncionariosLayout.fxml"));

        } catch (Exception exc) {

            System.out.println("Erro ao ler fxml");
            exc.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(root, 720, 400);

        MainLogin.setCena(scene);
    }

    public void fecharPrograma() {
        System.exit(0);
    }
}
