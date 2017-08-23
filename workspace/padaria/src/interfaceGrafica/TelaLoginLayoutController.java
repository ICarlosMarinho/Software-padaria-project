package interfaceGrafica;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        this.tfLogin.clear();
        this.tfSenha.clear();
    }

    @FXML
    public void botaoLogin() {

        System.out.println(this.tfLogin.getText());

        MainPadaria.logado = sistema.buscarFuncionario(this.tfLogin.getText());

        if (MainPadaria.logado != null) {

            if (MainPadaria.logado.getSenha().equals(this.tfSenha.getText()) != false) {

                this.initialize();

                Parent root = null;

                try {

                    if (MainPadaria.logado.getCargo().equalsIgnoreCase("Gerente") == true) {

                        root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipalGerenteLayout.fxml"));

                    } else if (MainPadaria.logado.getCargo().equalsIgnoreCase("Caixa") == true) {

                        root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipalFuncionarioLayout.fxml"));
                    }

                } catch (Exception exc) {

                    System.out.println("Erro ao ler fxml");
                    exc.printStackTrace();
                    System.exit(0);
                }

                Scene scene = new Scene(root, 600, 400);

                MainPadaria.setTituloAtualPalco("Menu Principal | Funcionário: " + MainPadaria.logado.getNome());
                MainPadaria.setCenaAtual(scene);
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
