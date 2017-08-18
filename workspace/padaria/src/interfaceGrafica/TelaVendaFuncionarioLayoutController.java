package interfaceGrafica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

import classesBasicas.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import negocio.SistemaPadaria;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class TelaVendaFuncionarioLayoutController {
	
	private SistemaPadaria sistema;
	private Stack<Produto> adicionados;
	private Stack<Double> quantidades;
	
	
	@FXML
	private TextField ttfBuscarProduto;
	
	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnLimpar;

	@FXML
	private TableView<Produto> tblProduto;

	@FXML
	private TableColumn<Produto, String> colNomeProduto;
	@FXML
	private TableColumn<Produto, Double> colPrecoProduto;
	@FXML
	private TableColumn<Produto, Double> colQtdProduto;

	@FXML
	private Button btnConfirmarVenda;
	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnAdicionar;

	@FXML
	private TextField ttfQuantidade;

	@FXML
	private TableView<Produto> tblNomeVenda;
	@FXML
	private TableView<Double> tblQtdVenda;
	
	@FXML
	private TableColumn<Produto, String> colNomeVenda;
	@FXML
	private TableColumn<Double, Double> colQtdVenda;
	
	@FXML
	private TextField ttfClienteCadastrado;

	
	
	@FXML
	public void initialize() {
		this.sistema = SistemaPadaria.getInstancia();
		this.adicionados = new Stack<Produto>();
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		colNomeProduto.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colPrecoProduto.setCellValueFactory( new PropertyValueFactory<>("preco"));
		colQtdProduto.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
		
		colNomeVenda.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colQtdVenda.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
		
		
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnBuscar.disableProperty().bind( ttfBuscarProduto.textProperty().isEmpty() );
		
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnAdicionar.disableProperty().bind( ttfQuantidade.textProperty().isEmpty() );		
		
		
		
		// TODO ajustar opcoes conforme 
	
	}



	@FXML
	public void onActionBuscar() {
		ArrayList<Produto> encontrados = this.sistema.buscarProdutoOcorrencia( ttfBuscarProduto.getText() );
		tblProduto.setItems( FXCollections.observableArrayList(encontrados) );
	}



	@FXML
	public void onActionLimpar() {
		ttfBuscarProduto.clear();
		tblProduto.setItems(null);
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
	}



	@FXML
	public void onActionConfirmarVenda() {}



	@FXML
	public void onActionVoltar() {
		MainFuncionarioTest.setCenaAnterior();
	}



	@FXML
	public void onActionAdicionar() {
		
		Produto adicionar = tblProduto.getSelectionModel().getSelectedItem();
		Alert dialogo;
		double quantidade;
		
		if( adicionar == null ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText("É necessário escolher um produto do estoque");
			dialogo.showAndWait();
		}
		
		try {
			quantidade = Double.parseDouble( ttfQuantidade.getText() );
			
			adicionados.push( adicionar );
			quantidades.push( new Double(quantidade) );
			
//			tblVenda.setItems( FXCollections.observableArrayList( adicionados ) );
			
		} catch ( NumberFormatException nfe ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText("Dado inválido");
			dialogo.showAndWait();
		}
		
		
		
		
	}


}
