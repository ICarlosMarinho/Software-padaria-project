package interfaceGrafica.funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import repositorio.*;

import java.util.ArrayList;

import classesBasicas.*;
import exceptions.NegocioException;
import negocio.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EstoqueFuncionarioLayoutController {

	private SistemaPadaria sistema;
	
	@FXML
    public void initialize() {
		
		sistema = SistemaPadaria.getInstancia();
		
		try {
			sistema.cadastrarProduto("leite em pó", "camponesa", 13, 9, 2018, 4, 4.5);
		} catch (NegocioException ne) {
			System.out.println( ne.getMessage() );
			System.exit(1);
		}
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		for( Produto produto : sistema.listaProduto() )  {
			produtos.add(produto);
		}
		
		
//		ObservableList<Produto> dados = FXCollections.observableArrayList( produtos );
        
//        tblProduto.setItems( dados );
        
//        colNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome") );
	}
    
    @FXML
    private TableView<Produto> tblProduto;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    @FXML
    private TableColumn<Produto, Integer> colQtd;
    
    
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
    public void onActionVoltar() {
            MainFuncionarioTest.setCenaAnterior();
    }

}
