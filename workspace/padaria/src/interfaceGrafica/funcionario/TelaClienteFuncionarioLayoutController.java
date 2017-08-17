package interfaceGrafica.funcionario;

import java.util.ArrayList;

import classesBasicas.*;
import negocio.*;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class TelaClienteFuncionarioLayoutController {
	
	private SistemaPadaria sistema;

	@FXML 
	private TextField ttfBuscarProduto;
	
	@FXML 
	private Button btnBuscar;
	@FXML 
	private Button btnLimpar;

	@FXML
	private TableView<Cliente> tblCliente;

	@FXML
	private TableColumn<Cliente, String> colNome;
	@FXML
	private TableColumn<Cliente, Double> colCredito;

	@FXML
	private TextField ttfNome;
	@FXML
	private TextField ttfLogradouro;
	@FXML
	private TextField ttfNumero;
	@FXML
	private TextField ttfComplemento;
	@FXML
	private TextField ttfCidade;
	@FXML
	private TextField ttfEstado;
	@FXML
	private TextField ttfCredito;
	@FXML
	private TextField ttfQtdVendas;
	@FXML
	private TextField ttfValorVendas;

	
	@FXML
	private Button btnVoltar;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnCancelarCadastrar;
	@FXML
	private Button btnConfirmarCadastrar;




	
	@FXML
	public void initialize() {
		this.sistema = SistemaPadaria.getInstancia();
		
		tblCliente.setItems( FXCollections.observableArrayList(sistema.listaCliente()) );
		colNome.setCellValueFactory( new PropertyValueFactory<>("nome") );
		colCredito.setCellValueFactory( new PropertyValueFactory<>("credito") );
		
		tblCliente.setOnMouseClicked( new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				Cliente cliente = tblCliente.getSelectionModel().getSelectedItem();
				
				ttfNome.setText( cliente.getNome() );
				ttfLogradouro.setText( cliente.getEndereco().getLogradouro() );
				ttfNumero.setText( cliente.getEndereco().getNumero() );
				ttfComplemento.setText( cliente.getEndereco().getComplemento() );
				ttfCidade.setText( cliente.getEndereco().getCidade() );
				ttfEstado.setText( cliente.getEndereco().getEstado() );
				ttfCredito.setText( cliente.getCredito() + "" );
				ttfQtdVendas.setText( cliente.getQtdVendas() + "" );
				ttfValorVendas.setText( cliente.getValorVendas() + "" );
				
			}
		} );
		
		
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnLimpar.disableProperty().bind( ttfBuscarProduto.textProperty().isEmpty() );
		// botao limpar so ficara ativado se o campo de busca tiver algo
		btnBuscar.disableProperty().bind( ttfBuscarProduto.textProperty().isEmpty() );
		
		
		// botao confirmar so ficara ativado se todos os campos forem preenchidos
		btnConfirmarCadastrar.disableProperty().bind( ttfNome.textProperty().isEmpty().or(
				ttfLogradouro.textProperty().isEmpty().or(
				ttfNumero.textProperty().isEmpty().or(
				ttfComplemento.textProperty().isEmpty().or(
				ttfEstado.textProperty().isEmpty().or(
				ttfCidade.textProperty().isEmpty()))))) );
		
		// botao atualizar so ficara ativado se todos os campos forem preenchidos
		btnAtualizar.disableProperty().bind( ttfNome.textProperty().isEmpty().or(
				ttfLogradouro.textProperty().isEmpty().or(
				ttfNumero.textProperty().isEmpty().or(
				ttfComplemento.textProperty().isEmpty().or(
				ttfEstado.textProperty().isEmpty().or(
				ttfCidade.textProperty().isEmpty()))))) );
		
		
	}




	@FXML
	public void onActionBuscar() {
		ArrayList<Cliente> encontrados = this.sistema.buscarClienteOcorrencia( ttfBuscarProduto.getText() );
		tblCliente.setItems( FXCollections.observableArrayList(encontrados) );
	}


	
	@FXML
	public void onActionLimpar() {
		ttfBuscarProduto.clear();
		tblCliente.setItems( FXCollections.observableArrayList(this.sistema.listaCliente()) );
	}




	@FXML
	public void onActionCadastrar() {}




	@FXML
	public void onActionConfirmarCadastrar() {}




	@FXML
	public void onActionCancelarCadastrar() {}




	@FXML
	public void onActionAtualizar() {}




	@FXML public void onActionVoltar() {
		MainFuncionarioTest.setCenaAnterior();
	}






	
	
	
	
	
	
	
	

}
