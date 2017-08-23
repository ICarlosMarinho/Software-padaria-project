package interfaceGrafica;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.function.Consumer;

import com.sun.javafx.scene.control.behavior.OptionalBoolean;

import classesBasicas.*;
import exceptions.NegocioException;
import exceptions.SistemaException;
import negocio.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaEstoqueFuncionarioLayoutController {

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
	private Button btnCadastrar;
	@FXML 
	private Button btnConfirmarCadastrar;
	@FXML 
	private Button btnCancelarCadastrar;
	@FXML 
	private Button btnAtualizar;
	@FXML
	private Button btnExcluir;

	@FXML 
	private Label lblNome;
	@FXML 
	private Label lblDescricao;
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
	private TextField ttfNome;
	@FXML 
	private TextField ttfDescricao;
	@FXML
	private TextField ttfQuantidade;
	@FXML 
	private TextField ttfPreco;
	@FXML 
	private TextField ttfAno;
	@FXML 
	private TextField ttfMes;
	@FXML 
	private TextField ttfDia;


	
	
	
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
				
				ttfNome.setText( produto.getNome() + "" );
				ttfDescricao.setText( produto.getDescricao() + "" );
				ttfQuantidade.setText( produto.getQuantidade() + "");
				ttfPreco.setText( produto.getPreco() + "" );
				ttfDia.setText( produto.getValidade().get(Calendar.DAY_OF_MONTH) + "" );
				ttfMes.setText( produto.getValidade().get(Calendar.MONTH ) + "");
				ttfAno.setText( produto.getValidade().get(Calendar.YEAR ) + "");
				
			}
			
		} );
		
		
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnBuscar.disableProperty().bind( ttfBuscarProduto.textProperty().isEmpty() );
		
		
		// botao atualizar so ficara ativado se todos os campos forem preenchidos
		btnAtualizar.disableProperty().bind( ttfNome.textProperty().isEmpty().or(
				ttfDescricao.textProperty().isEmpty().or(
				ttfQuantidade.textProperty().isEmpty().or(
				ttfPreco.textProperty().isEmpty().or(
				ttfDia.textProperty().isEmpty().or(
				ttfMes.textProperty().isEmpty().or(
				ttfAno.textProperty().isEmpty())))))) );
		
		
		// botao confirmar so ficara ativado se todos os campos forem preenchidos
		btnConfirmarCadastrar.disableProperty().bind( ttfNome.textProperty().isEmpty().or(
				ttfDescricao.textProperty().isEmpty().or(
				ttfQuantidade.textProperty().isEmpty().or(
				ttfPreco.textProperty().isEmpty().or(
				ttfDia.textProperty().isEmpty().or(
				ttfMes.textProperty().isEmpty().or(
				ttfAno.textProperty().isEmpty())))))) );
		
		// botao cancelar e confirmar ficarao inicialmente invisiveis
		btnConfirmarCadastrar.setVisible(false);
		btnCancelarCadastrar.setVisible(false);
		
		if( MainPadaria.getLogado().getCargo().equalsIgnoreCase("Caixa") ) {
			
			ttfNome.setDisable(true);
			ttfDescricao.setDisable(true);
			ttfDia.setDisable(true);
			ttfMes.setDisable(true);
			ttfAno.setDisable(true);
			ttfPreco.setDisable(true);
			ttfQuantidade.setDisable(true);

			
			btnCadastrar.setVisible(false);
			btnExcluir.setVisible(false);
			btnAtualizar.setVisible(false);
		}
		
		
	}


	@FXML
	public void onActionVoltar() {
		MainPadaria.setCenaAnterior();
	}


	@FXML
	public void onActionLimpar() {
		ttfBuscarProduto.clear();
		tblProduto.setItems(null);
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
		
		ttfNome.clear();
		ttfDescricao.clear();
		ttfDia.clear();
		ttfMes.clear();
		ttfAno.clear();
		ttfPreco.clear();
		ttfQuantidade.clear();
	}



	@FXML
	public void onActionBuscar() {
		ArrayList<Produto> encontrados = this.sistema.buscarProdutoOcorrencia( ttfBuscarProduto.getText() );
		tblProduto.setItems( FXCollections.observableArrayList(encontrados) );
	}


	@FXML
	public void onActionCadastrar() {
		ttfNome.clear();
		ttfDescricao.clear();
		ttfDia.clear();
		ttfMes.clear();
		ttfAno.clear();
		ttfPreco.clear();
		ttfQuantidade.clear();
		
		btnConfirmarCadastrar.setVisible(true);
		btnCancelarCadastrar.setVisible(true);
		
		btnAtualizar.setVisible(false);
		btnExcluir.setVisible(false);
	}


	@FXML
	public void onActionConfirmarCadastrar() {
		
		try {
			
			if(this.sistema.cadastrarProduto( ttfNome.getText(), ttfDescricao.getText()
					, ttfDia.getText(), ttfMes.getText(), ttfAno.getText()
					, ttfQuantidade.getText(), ttfPreco.getText() )) {
				Alert info = new Alert(Alert.AlertType.INFORMATION);
				info.setTitle("INFO");
				info.setHeaderText("Estoque");
				info.setContentText( "Produto cadastrado com sucesso !" );
				info.showAndWait();
			}
			
			
		} catch (NegocioException ne) {
			Alert erro = new Alert(Alert.AlertType.ERROR);
			erro.setTitle("ERRO");
			erro.setHeaderText("Estoque");
			erro.setContentText( ne.getMessage() );
			erro.showAndWait();
		}
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
	}


	@FXML
	public void onActionCancelarCadastrar() {
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		
		ttfNome.clear();
		ttfDescricao.clear();
		ttfDia.clear();
		ttfMes.clear();
		ttfAno.clear();
		ttfPreco.clear();
		ttfQuantidade.clear();
		
		btnConfirmarCadastrar.setVisible(false);
		btnCancelarCadastrar.setVisible(false);
		
		btnAtualizar.setVisible(true);
		btnExcluir.setVisible(true);
	}


	@FXML
	public void onActionAtualizar() {
		Produto atualizar = tblProduto.getSelectionModel().getSelectedItem();
		Alert dialogo;
		
		try {
			if ( this.sistema.modificarProduto(atualizar.getId(), ttfNome.getText(), ttfDescricao.getText()
					, ttfDia.getText(), ttfMes.getText(), ttfAno.getText()
					, ttfQuantidade.getText(), ttfPreco.getText() ) ) {
				
				dialogo = new Alert(Alert.AlertType.INFORMATION);
				dialogo.setTitle("INFO");
				dialogo.setHeaderText("Estoque");
				dialogo.setContentText("Produto atualizado com sucesso !");
				dialogo.showAndWait();
				
			}
			
		} catch ( NegocioException ne ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Estoque");
			dialogo.setContentText(ne.getMessage());
			dialogo.showAndWait();
		} catch ( SistemaException se ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Estoque");
			dialogo.setContentText(se.getMessage());
			dialogo.showAndWait();
		}
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
	}


	@FXML
	public void onActionExcluir() {
		Produto excluir = tblProduto.getSelectionModel().getSelectedItem();
		
		Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType sim  = new ButtonType("Sim");
		ButtonType nao  = new ButtonType("Não");
		dialogo.getButtonTypes().setAll(sim, nao);
		dialogo.setTitle("REMOÇÃO");
		dialogo.setHeaderText("Você realmente deseja excluir");
		dialogo.setContentText(excluir.getNome());
		Optional<ButtonType> resposta = dialogo.showAndWait();
		
		if( resposta.get() == nao ) {
			return;
		}
		
		try {
			if( this.sistema.removerProduto( excluir.getId() ) ) {
				dialogo = new Alert(Alert.AlertType.INFORMATION);
				dialogo.setTitle("INFO");
				dialogo.setHeaderText("Estoque");
				dialogo.setContentText("Produto excluído com sucesso !");
				dialogo.showAndWait();
			}
			
		} catch (NegocioException ne) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Estoque");
			dialogo.setContentText( ne.getMessage() );
			dialogo.showAndWait();
		}
		
		tblProduto.setItems( FXCollections.observableArrayList(this.sistema.listaProduto()) );
		tblProduto.refresh();
		ttfNome.clear();
		ttfDescricao.clear();
		ttfDia.clear();
		ttfMes.clear();
		ttfAno.clear();
		ttfPreco.clear();
		ttfQuantidade.clear();
	}
	
	
	
	
	


}
