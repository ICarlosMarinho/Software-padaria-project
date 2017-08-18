package interfaceGrafica;

import java.util.ArrayList;

import classesBasicas.*;
import exceptions.NegocioException;
import exceptions.SistemaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.SistemaPadaria;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class TelaVendaFuncionarioLayoutController {
	
	private SistemaPadaria sistema;
	private ArrayList<DadoVenda> adicionados;
	
	
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
	private Button btnRemover;
	
	@FXML
	private TextField ttfQuantidade;
	
	@FXML 
	private TableView<DadoVenda> tblVenda;
	
	@FXML
	private TableColumn<DadoVenda, String> colNomeVenda;
	@FXML
	private TableColumn<DadoVenda, Double> colQtdVenda;
	
	@FXML
	private TextField ttfClienteCadastrado;
	
	
	@FXML
	public void initialize() {
		this.sistema = SistemaPadaria.getInstancia();
		this.adicionados = new ArrayList<DadoVenda>();
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		colNomeProduto.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colPrecoProduto.setCellValueFactory( new PropertyValueFactory<>("preco"));
		colQtdProduto.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
		
		colNomeVenda.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colQtdVenda.setCellValueFactory( new PropertyValueFactory<>("quantidade"));
		
		
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnBuscar.disableProperty().bind( ttfBuscarProduto.textProperty().isEmpty() );
		
		// botao adicionar so ficara ativado se o campo de quantidade tiver algo
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
	public void onActionConfirmarVenda() {
		Alert dialogo;
		
		try {
		
			if( ttfClienteCadastrado.getText().isEmpty() ) {
				this.sistema.efetuarVenda(adicionados, 0);
			} else {
				
				Cliente comprador = this.sistema.buscarCliente(ttfClienteCadastrado.getText());
				if(comprador == null ) {
					throw new NegocioException("Não existe cliente: " + ttfClienteCadastrado.getText(), this);
				}
				this.sistema.efetuarVenda(adicionados, 0, comprador.getId());
			}
			
			dialogo = new Alert(Alert.AlertType.INFORMATION);
			dialogo.setTitle("INFO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText("Venda efetuada com sucesso!");
			dialogo.showAndWait();
		
		} catch (NegocioException ne) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText(ne.getMessage());
			dialogo.showAndWait();
		} catch (SistemaException se) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText(se.getMessage());
			System.out.println(se.getMensagemParaDesenvolvedor());
			dialogo.showAndWait();
		}
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
		tblVenda.setItems(null);
		adicionados.clear();
		ttfClienteCadastrado.clear();
		ttfQuantidade.clear();
	}



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

			adicionados.add( new DadoVenda( adicionar.getNome(), quantidade, adicionar ) );
			
			tblVenda.setItems( FXCollections.observableArrayList(adicionados) );
			tblVenda.refresh();
			ttfQuantidade.clear();
			
		} catch ( NumberFormatException nfe ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText("Dado inválido");
			dialogo.showAndWait();
		}
		
		
	}



	@FXML
	public void onActionRemover() {
		DadoVenda remover = tblVenda.getSelectionModel().getSelectedItem();
		Alert dialogo;
		
		if( remover == null ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Venda");
			dialogo.setContentText("É necessário escolher um produto");
			dialogo.showAndWait();
		}
		
		adicionados.remove(remover);
		
		tblVenda.setItems(null);
		tblVenda.setItems( FXCollections.observableArrayList(adicionados) );
		tblVenda.refresh();
	}


}
