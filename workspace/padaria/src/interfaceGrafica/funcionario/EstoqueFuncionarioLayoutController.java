package interfaceGrafica.funcionario;

import java.util.ArrayList;

import classesBasicas.*;
import negocio.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EstoqueFuncionarioLayoutController {
	
	private SistemaPadaria sistema;

	@FXML 
	private TableView<Produto> tblProduto;
	@FXML 
	private TableColumn<Produto, String> colNome;
	@FXML 
	private TableColumn<Produto, Double> colPreco;
	@FXML 
	private TableColumn<Produto, Double> colQtd;

	@FXML
	private Button btnVoltar;

	@FXML 
	private Label lblNome;
	@FXML 
	private Label lblDescricao;
	@FXML 
	private Label lblValidade;
	@FXML 
	private Label lblQuantidade;
	@FXML 
	private Label lblPreco;

	@FXML 
	private TextField ttfBuscarProduto;

	@FXML 
	private Button btnLimpar;
	@FXML 
	private Button btnBuscar;
	
	
	
	@FXML
	public void initialize() {
		this.sistema = SistemaPadaria.getInstancia();
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		colNome.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colPreco.setCellValueFactory( new PropertyValueFactory<>("preco"));
		colQtd.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
		
		tblProduto.setOnMouseClicked( new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				Produto produto = tblProduto.getSelectionModel().getSelectedItem();
				
				lblNome.setText( "Nome: " + produto.getNome() );
				lblDescricao.setText( "Descrição: " + produto.getDescricao() );
				// TODO ajustar a formatação da validade
				lblValidade.setText( "Validade: " + produto.getValidade().getCalendarType() );
				lblQuantidade.setText( "Quantidade: " + produto.getQuantidade() );
				lblPreco.setText( "Preço: " + produto.getPreco() );
			}
			
		} );
		
		
		
		
		
	}


	@FXML
	public void onActionVoltar() {
		MainFuncionarioTest.setCenaAnterior();
	}


	@FXML
	public void onActionLimpar() {
		ttfBuscarProduto.clear();
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
	}


	@FXML
	public void onActionBuscar() {
		ArrayList<Produto> encontrados = this.sistema.buscarProdutoOcorrencia( ttfBuscarProduto.getText() );
		tblProduto.setItems( FXCollections.observableArrayList(encontrados) );
	}
	
	
	
	
	

}
