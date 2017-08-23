package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import exceptions.NegocioException;
import exceptions.SistemaException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocio.SistemaPadaria;
import repositorio.Repositorio;

public class MainPadaria extends Application {

    public static Stage palco;
    public static Stack<Scene> cenaAnterior;
    public static Stack<String> tituloAnterior;
    public static Funcionario logado;

    public static Funcionario getLogado() {
        return MainPadaria.logado;
    }

    public static void setLogado(Funcionario logado) {
        MainPadaria.logado = logado;
    }

    public static void setCenaAtual(Scene scene) {
        MainPadaria.cenaAnterior.push(MainPadaria.palco.getScene());
        MainPadaria.palco.setScene(scene);
    }

    public static void setTituloAtualPalco(String title) {
        if (title == null) {
            throw new RuntimeException("Variable is null");
        }

        MainPadaria.tituloAnterior.push(MainPadaria.palco.getTitle());
        MainPadaria.palco.setTitle(title);
    }

    public static void setCenaAnterior() {
        MainPadaria.palco.setScene(MainPadaria.cenaAnterior.pop());
        MainPadaria.palco.setTitle(MainPadaria.tituloAnterior.pop());
    }

    public static void main(String[] args) throws SistemaException, NegocioException {

        SistemaPadaria sistema = SistemaPadaria.getInstancia();
/// caso para teste
//        sistema.cadastrarProduto("Leite", "Camponesa", 9, 2, 2020, 40, 4.5);;;;;
//        sistema.cadastrarProduto("Leite em pó", "Camponesa", 9, 2, 2020, 40, 4.5);
//        sistema.cadastrarProduto("Pão Francês", "Encomendado pelo fornecedor de Joana Bezerra", 9, 2, 2019, 200, 5.5);
//
//        sistema.cadastrarCliente("Maria", "Vasco da Gama", "1856", "A", "Recife", "PE");
//        sistema.cadastrarCliente("José", "Vasco da Gama", "1856", "A", "Recife", "PE");
//        sistema.cadastrarCliente("Arimatéia", "Vasco da Gama", "1856", "A", "Recife", "PE");
//
//        Endereco end = new Endereco("-", "-", "-", "-", "-");
//        Funcionario teste1 = new Funcionario("carlos", "Gerente", "admin", "12345", end, 0, 0, 1100);
//        teste1.setId(sistema.atribuirIdFuncionario());
//        sistema.cadastrarFuncionario(teste1);
//
//        Funcionario teste2 = new Funcionario("Jonas", "Caixa", "abcde", "54321", end, 0, 0, 1200);
//        teste2.setId(sistema.atribuirIdFuncionario());
//        sistema.cadastrarFuncionario(teste2);

        try {
            FileInputStream funcionarios = new FileInputStream("ArquivoFuncionarios.bin");
            FileInputStream estoque = new FileInputStream("ArquivoProdutos.bin");
            FileInputStream clientes = new FileInputStream("ArquivoClientes.bin");
            FileInputStream vendas = new FileInputStream("ArquivoVendas.bin");

            sistema.getRepositorioFuncionario().lerDoArquivo(funcionarios);
            sistema.getRepositorioProduto().lerDoArquivo(estoque);
            sistema.getRepositorioCliente().lerDoArquivo(clientes);
            sistema.getRepositorioVenda().lerDoArquivo(vendas);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Quantidade funcionario = " + sistema.getRepositorioFuncionario().tamanho());
        System.out.println("Quantidade produto = " + sistema.totalProduto());
        System.out.println("Quantidade cliente = " + sistema.totalCliente());

        launch(args);

        try {
            FileOutputStream funcionarios = new FileOutputStream("ArquivoFuncionarios.bin");
            FileOutputStream estoque = new FileOutputStream("ArquivoProdutos.bin");
            FileOutputStream clientes = new FileOutputStream("ArquivoClientes.bin");
            FileOutputStream vendas = new FileOutputStream("ArquivoVendas.bin");

            sistema.getRepositorioFuncionario().gravarNoArquivo(funcionarios);
            sistema.getRepositorioProduto().gravarNoArquivo(estoque);
            sistema.getRepositorioCliente().gravarNoArquivo(clientes);
            sistema.getRepositorioVenda().gravarNoArquivo(vendas);

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainPadaria.tituloAnterior = new Stack<>();
        MainPadaria.cenaAnterior = new Stack<>();
        MainPadaria.palco = primaryStage;

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("TelaLoginLayout.fxml"));
        } catch (IOException ioe) {
            System.out.println("Problema para carregar arquivo TelaMenuPrincipalFuncionarioLayout");
            System.exit(1);
        }

        Scene scene = new Scene(root, 600, 400);

        MainPadaria.palco.setScene(scene);
        MainPadaria.palco.setTitle("Login");
        MainPadaria.palco.show();

    }

}
