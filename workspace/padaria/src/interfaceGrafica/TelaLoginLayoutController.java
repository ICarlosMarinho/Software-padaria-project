/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import java.awt.Color;
import java.awt.Graphics;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import negocio.SistemaPadaria;

/**
 *
 * @author User
 */
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

        Endereco end = new Endereco("-", "-", "-", "-", "-");

        Funcionario teste1 = new Funcionario("carlos", "Gerente", "admin", "12345", end, 0, 0, 0);
        teste1.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste1);

        Funcionario teste2 = new Funcionario("Jonas", "Caixa", "abcde", "54321", end, 0, 0, 0);
        teste2.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste2);

        btnLogin.disableProperty().bind(tfLogin.textProperty().isEmpty().or(tfSenha.textProperty().isEmpty()));

        lblFalhaLogin.setVisible(false);
    }

    @FXML
    public void botaoLogin() {

        System.out.println(this.tfLogin.getText());

        Funcionario auxFun = sistema.buscarFuncionario(this.tfLogin.getText());

        if (auxFun != null) {

            if (auxFun.getSenha().equals(this.tfSenha.getText()) != false) {

                Pane root = null;

                try {

                    root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipalGerenteLayout.fxml"));

                } catch (Exception exc) {

                    System.out.println("Erro ao ler fxml");
                    exc.printStackTrace();
                    System.exit(0);
                }

                Scene scene = new Scene(root, 600, 400);

                MainLogin.setCena(scene);
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
