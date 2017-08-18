/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import exceptions.NegocioException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import negocio.SistemaPadaria;

public class TelaLoginLayoutController {

    SistemaPadaria sistema = SistemaPadaria.getInstancia();

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblFalhaLogin;

    @FXML
    public void initialize() {

        btnLogin.disableProperty().bind(tfLogin.textProperty().isEmpty().or(tfSenha.textProperty().isEmpty()));

        lblFalhaLogin.setVisible(false);
    }

    @FXML
    public void botaoLogin() {

        System.out.println(this.tfLogin.getText());

        Funcionario auxFun = sistema.buscarFuncionario(this.tfLogin.getText());

        if (auxFun != null) {

            if (auxFun.getSenha().equals(this.tfSenha.getText()) != false) {

                lblFalhaLogin.setVisible(false);
                this.tfLogin.clear();
                this.tfSenha.clear();

                Parent root = null;

                try {

                    if (auxFun.getCargo().equalsIgnoreCase("Gerente") == true) {

                        root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipalGerenteLayout.fxml"));

                    } else if (auxFun.getCargo().equalsIgnoreCase("Caixa") == true) {

                        root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipalFuncionarioLayout.fxml"));
                    }

                } catch (Exception exc) {

                    System.out.println("Erro ao ler fxml");
                    exc.printStackTrace();
                    System.exit(0);
                }

                Scene scene = new Scene(root, 600, 400);

                MainFuncionarioTest.setTituloAtualPalco("Menu Principal");
                MainFuncionarioTest.setCenaAtual(scene);
            } else {

                System.out.println("Falha no login");

                lblFalhaLogin.setText("Senha digitada incorreta!");
                lblFalhaLogin.visibleProperty().setValue(true);
            }
        } else {

            System.out.println("Falha no login");

            lblFalhaLogin.setText("Usuario digitado invalido!");
            lblFalhaLogin.visibleProperty().setValue(true);
        }

    }
}
