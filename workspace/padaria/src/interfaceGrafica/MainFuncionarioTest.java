package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import java.io.IOException;
import java.util.Stack;

import exceptions.NegocioException;
import exceptions.SistemaException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.SistemaPadaria;

public class MainFuncionarioTest extends Application {

    public static Stage palco;
    public static Stack<Scene> cenaAnterior;
    public static Stack<String> tituloAnterior;
    public static Funcionario logado;

    public static void setCenaAtual(Scene scene) {
        MainFuncionarioTest.cenaAnterior.push(MainFuncionarioTest.palco.getScene());
        MainFuncionarioTest.palco.setScene(scene);
    }

    public static void setTituloAtualPalco(String title) {
        if (title == null) {
            throw new RuntimeException("Variable is null");
        }

        MainFuncionarioTest.tituloAnterior.push(MainFuncionarioTest.palco.getTitle());
        MainFuncionarioTest.palco.setTitle(title);
    }

    public static void setCenaAnterior() {
        MainFuncionarioTest.palco.setScene(MainFuncionarioTest.cenaAnterior.pop());
        MainFuncionarioTest.palco.setTitle(MainFuncionarioTest.tituloAnterior.pop());
    }

    public static void main(String[] args) throws SistemaException, NegocioException {

        SistemaPadaria sistema = SistemaPadaria.getInstancia();

        /// caso para teste
        sistema.cadastrarProduto("Leite", "Camponesa", 9, 2, 2020, 40, 4.5);
        sistema.cadastrarProduto("Leite em pó", "Camponesa", 9, 2, 2020, 40, 4.5);
        sistema.cadastrarProduto("Pão Francês", "Encomendado pelo fornecedor de Joana Bezerra", 9, 2, 2019, 200, 5.5);

        sistema.cadastrarCliente("Maria", "Vasco da Gama", "1856", "A", "Recife", "PE");
        sistema.cadastrarCliente("José", "Vasco da Gama", "1856", "A", "Recife", "PE");
        sistema.cadastrarCliente("Arimatéia", "Vasco da Gama", "1856", "A", "Recife", "PE");

        Endereco end = new Endereco("-", "-", "-", "-", "-");
        Funcionario teste1 = new Funcionario("carlos", "Gerente", "admin", "12345", end, 0, 0, 1100);
        teste1.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste1);

        Funcionario teste2 = new Funcionario("Jonas", "Caixa", "abcde", "54321", end, 0, 0, 1200);
        teste2.setId(sistema.atribuirIdFuncionario());
        sistema.cadastrarFuncionario(teste2);

        System.out.println("Quantidade produto = " + sistema.totalProduto());
        System.out.println("Quantidade cliente = " + sistema.totalCliente());

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainFuncionarioTest.tituloAnterior = new Stack<>();
        MainFuncionarioTest.cenaAnterior = new Stack<>();
        MainFuncionarioTest.palco = primaryStage;

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("TelaLoginLayout.fxml"));
        } catch (IOException ioe) {
            System.out.println("Problema para carregar arquivo TelaMenuPrincipalFuncionarioLayout");
            System.exit(1);
        }

        Scene scene = new Scene(root, 600, 400);

        MainFuncionarioTest.palco.setScene(scene);
        MainFuncionarioTest.palco.setTitle("Login");
        MainFuncionarioTest.palco.show();

    }

}
