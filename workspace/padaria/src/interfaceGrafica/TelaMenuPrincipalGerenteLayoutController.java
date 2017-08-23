/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
        MainPadaria.logado = null;
        MainPadaria.setCenaAnterior();
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

        MainPadaria.setTituloAtualPalco("Funcionários | Funcionário: " + MainPadaria.logado.getNome());
        MainPadaria.setCenaAtual(scene);
    }

    public void fecharPrograma() {
        System.exit(0);
    }

    public void abrirMenuClientes() {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("TelaClienteFuncionarioLayout.fxml"));
        } catch (IOException ioe) {
            System.out.println("Problema para carregar arquivo ClienteFuncionarioLayout");
            ioe.printStackTrace();
            System.out.println("Problema para carregar arquivo TelaClienteFuncionarioLayout");
            ioe.printStackTrace();
            System.exit(1);
        }

        Scene scene = new Scene(root, 600, 400);

        MainPadaria.setCenaAtual(scene);
        MainPadaria.setTituloAtualPalco("Clientes | Funcionario: " + MainPadaria.logado.getNome());
    }

    public void abrirMenuProdutos() {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("TelaEstoqueFuncionarioLayout.fxml"));
        } catch (IOException ioe) {
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
