package interfaceGrafica.funcionario;

import java.util.ArrayList;
import java.util.Optional;

import classesBasicas.*;
import exceptions.NegocioException;
import exceptions.SistemaException;
import negocio.*;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private Button btnExcluir;




	
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
		
		
		// TODO ajustar opcoes conforme cliente
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
		
		ttfNome.clear();
		ttfLogradouro.clear();
		ttfNumero.clear();
		ttfComplemento.clear();
		ttfCidade.clear();
		ttfEstado.clear();
		ttfQtdVendas.clear();
		ttfValorVendas.clear();
		ttfCredito.clear();
	}




	@FXML
	public void onActionCadastrar() {
		// TODO fazer btnConfirmarCadastrar e btnCancelarCadastrar visiveis
	}




	@FXML
	public void onActionConfirmarCadastrar() {
		
		try {
			
			if( this.sistema.cadastrarCliente( ttfNome.getText(), ttfLogradouro.getText()
					, ttfNumero.getText(), ttfComplemento.getText(), ttfCidade.getText()
					, ttfEstado.getText() ) ) {
				
				Alert info = new Alert(Alert.AlertType.INFORMATION);
				info.setTitle("INFO");
				info.setHeaderText("Cliente");
				info.setContentText( "Cliente cadastrado com sucesso !" );
				info.showAndWait();
			}
			
			
		} catch (NegocioException ne) {
			Alert erro = new Alert(Alert.AlertType.ERROR);
			erro.setTitle("ERRO");
			erro.setHeaderText("Cliente");
			erro.setContentText( ne.getMessage() );
			erro.showAndWait();
		} catch (SistemaException se) {
			Alert erro = new Alert(Alert.AlertType.ERROR);
			erro.setTitle("ERRO");
			erro.setHeaderText("Cliente");
			erro.setContentText( se.getMessage() );
			erro.showAndWait();
		}
		
		tblCliente.setItems( FXCollections.observableArrayList(this.sistema.listaCliente()) );
		tblCliente.refresh();
	}




	@FXML
	public void onActionCancelarCadastrar() {
		tblCliente.setItems( FXCollections.observableArrayList(this.sistema.listaCliente()) );
		
		ttfNome.clear();
		ttfLogradouro.clear();
		ttfNumero.clear();
		ttfComplemento.clear();
		ttfCidade.clear();
		ttfEstado.clear();
		
		// // TODO fazer btnConfirmarCadastrar e btnCancelarCadastrar invisiveis
	}




	@FXML
	public void onActionAtualizar() {
		Cliente atualizar = tblCliente.getSelectionModel().getSelectedItem();
		Alert dialogo;
		
		try {
			if ( this.sistema.modificarCliente(atualizar.getId(), ttfNome.getText(), ttfLogradouro.getText()
					, ttfNumero.getText(), ttfComplemento.getText(), ttfCidade.getText()
					, ttfEstado.getText() ) ) {
				
				dialogo = new Alert(Alert.AlertType.INFORMATION);
				dialogo.setTitle("INFO");
				dialogo.setHeaderText("Cliente");
				dialogo.setContentText("Cliente atualizado com sucesso !");
				dialogo.showAndWait();
				
			}
			
		} catch ( NegocioException ne ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Cliente");
			dialogo.setContentText(ne.getMessage());
			dialogo.showAndWait();
		} catch ( SistemaException se ) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Cliente");
			dialogo.setContentText(se.getMessage());
			dialogo.showAndWait();
		}
		
		tblCliente.setItems( FXCollections.observableArrayList(this.sistema.listaCliente()) );
		tblCliente.refresh();
	}




	@FXML
	public void onActionVoltar() {
		MainFuncionarioTest.setCenaAnterior();
	}




	@FXML
	public void onActionExcluir() {
		Cliente excluir = tblCliente.getSelectionModel().getSelectedItem();
		
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
			if( this.sistema.removerCliente( excluir.getId() ) ) {
				dialogo = new Alert(Alert.AlertType.INFORMATION);
				dialogo.setTitle("INFO");
				dialogo.setHeaderText("Cliente");
				dialogo.setContentText("Cliente excluído com sucesso !");
				dialogo.showAndWait();
			}
			
		} catch (NegocioException ne) {
			dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERRO");
			dialogo.setHeaderText("Cliente");
			dialogo.setContentText( ne.getMessage() );
			dialogo.showAndWait();
		}
		
		tblCliente.setItems( FXCollections.observableArrayList(this.sistema.listaCliente()) );
		tblCliente.refresh();
		ttfNome.clear();
		ttfLogradouro.clear();
		ttfNumero.clear();
		ttfComplemento.clear();
		ttfCidade.clear();
		ttfEstado.clear();
		ttfQtdVendas.clear();
		ttfValorVendas.clear();
		ttfCredito.clear();
	}






	
	
	
	
	
	
	
	

}
