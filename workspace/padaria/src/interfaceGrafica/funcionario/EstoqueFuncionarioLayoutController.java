package interfaceGrafica.funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repositorio.*;
import classesBasicas.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EstoqueFuncionarioLayoutController {

    public EstoqueFuncionarioLayoutController(RepositorioProduto produtos) {
        
        ObservableList<Produto> dados = FXCollections.observableArrayList( (Produto[])produtos.listar() );
        
        tblProduto.setItems( dados );
        
        colNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome") );
        
        
        
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
