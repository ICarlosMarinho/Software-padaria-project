package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import exceptions.NegocioException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import negocio.SistemaPadaria;

public class TelaMenuFuncionariosLayoutController implements Initializable {

    SistemaPadaria sistema = SistemaPadaria.getInstancia();

    private Funcionario pressionado;

    private ObservableList<Funcionario> dadosTabela;

    @FXML
    private ChoiceBox cbCargo;

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfSenha;
    @FXML
    private TextField tfSalario;
    @FXML
    private TextField tfLogradouro;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfEstado;
    @FXML
    private TextField tfComplemento;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView tbvLista;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpar;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.cbCargo.getItems().addAll("Gerente", "Caixa", "Padeiro");

        this.tfUsuario.disableProperty().bind(this.cbCargo.valueProperty().isNotEqualTo("Gerente").and(this.cbCargo.valueProperty().isNotEqualTo("Caixa")));
        this.tfSenha.disableProperty().bind(this.cbCargo.valueProperty().isNotEqualTo("Gerente").and(this.cbCargo.valueProperty().isNotEqualTo("Caixa")));
        this.btnBuscar.disableProperty().bind(this.tfBuscar.textProperty().isEmpty());
        this.btnConfirmar.setVisible(false);
        this.btnCancelar.setVisible(false);
        this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));

        this.tbvLista.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                pressionado = (Funcionario) tbvLista.getSelectionModel().getSelectedItem();

                cbCargo.setValue(pressionado.getCargo());
                tfNome.setText(pressionado.getNome());

                if (pressionado.getCargo().equals("Gerente") == true | pressionado.getCargo().equals("Caixa") == true) {

                    tfUsuario.setText(pressionado.getLogin());
                    tfSenha.setText(pressionado.getSenha());
                }

                tfSalario.setText(String.valueOf(pressionado.getSalario()));
                tfLogradouro.setText(pressionado.getEndereco().getLogradouro());
                tfNumero.setText(pressionado.getEndereco().getNumero());
                tfCidade.setText(pressionado.getEndereco().getCidade());
                tfEstado.setText(pressionado.getEndereco().getEstado());
                tfComplemento.setText(pressionado.getEndereco().getComplemento());
            }
        });
    }

    public void carregarItensNaTabela(ObservableList<Funcionario> lista) {

        this.dadosTabela = lista;

        this.tbvLista.setItems(this.dadosTabela);
        colId.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
    }

    public void limparCamposDeTexto() {

        this.tfNome.clear();
        this.tfUsuario.clear();
        this.tfSenha.clear();
        this.tfSalario.clear();
        this.tfLogradouro.clear();
        this.tfNumero.clear();
        this.tfCidade.clear();
        this.tfEstado.clear();
        this.tfComplemento.clear();
    }

    public void confirmarCadastro() {

        Endereco auxEndereco = new Endereco(this.tfLogradouro.getText(), this.tfNumero.getText(), this.tfComplemento.getText(),
                this.tfCidade.getText(), this.tfEstado.getText());

        Funcionario auxFuncionario = new Funcionario();

        auxFuncionario.setEndereco(auxEndereco);
        auxFuncionario.setNome(this.tfNome.getText());

        if (this.tfUsuario.isDisabled() != true | this.tfSenha.isDisabled() != true) {

            auxFuncionario.setLogin(this.tfUsuario.getText());
            auxFuncionario.setSenha(this.tfSenha.getText());
        } else {

            auxFuncionario.setLogin(null);
            auxFuncionario.setSenha(null);
        }

        auxFuncionario.setCargo((String) this.cbCargo.getValue());

        if (this.tfSalario.getText().isEmpty() != true) {

            try {

                auxFuncionario.setSalario(Double.parseDouble(this.tfSalario.getText()));

            } catch (NumberFormatException num) {

                auxFuncionario.setSalario(-1);
            }
        }

        auxFuncionario.setId(sistema.atribuirIdFuncionario());

        try {

            sistema.cadastrarFuncionario(auxFuncionario);
            this.limparCamposDeTexto();
            this.limparCampoDeBusca();

            this.tbvLista.setDisable(false);
            this.btnCadastrar.setDisable(false);
            this.btnAtualizar.setDisable(false);
            this.btnVoltar.setDisable(false);
            this.btnExcluir.setDisable(false);
            this.tfBuscar.setDisable(false);
            this.btnLimpar.setDisable(false);
            this.btnConfirmar.setVisible(false);
            this.btnCancelar.setVisible(false);

            this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));

        } catch (NegocioException neg) {

            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("ERRO");
            erro.setHeaderText("Funcionário");
            erro.setContentText(neg.getMessage());
            erro.showAndWait();

            System.out.println("Erro: " + neg.getMessage());
        }
        this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));
    }

    public void cancelarCadastro() {

        this.limparCamposDeTexto();
        this.limparCampoDeBusca();

        this.tbvLista.setDisable(false);
        this.btnCadastrar.setDisable(false);
        this.btnAtualizar.setDisable(false);
        this.btnVoltar.setDisable(false);
        this.btnExcluir.setDisable(false);
        this.tfBuscar.setDisable(false);
        this.btnLimpar.setDisable(false);
        this.btnConfirmar.setVisible(false);
        this.btnCancelar.setVisible(false);
    }

    public void cadastrarFuncionario() {

        this.limparCamposDeTexto();
        this.limparCampoDeBusca();

        this.tbvLista.setDisable(true);
        this.btnCadastrar.setDisable(true);
        this.btnAtualizar.setDisable(true);
        this.btnVoltar.setDisable(true);
        this.btnExcluir.setDisable(true);
        this.tfBuscar.setDisable(true);
        this.btnLimpar.setDisable(true);
        this.btnConfirmar.setVisible(true);
        this.btnCancelar.setVisible(true);

    }

    public void excluirFuncionario() {

        if (this.sistema.excluirFuncionario(this.pressionado) == true) {
            System.out.println("Funcionario Excluido " + sistema.listaFuncionario().size());
        } else {
            System.out.println("Erro ao excluir");
        }

        this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));
        this.limparCamposDeTexto();

        this.pressionado = null;
    }

    public void atualizarFuncionario() {

        if (this.pressionado != null) {
            Endereco auxEndereco = new Endereco(this.tfLogradouro.getText(), this.tfNumero.getText(), this.tfComplemento.getText(),
                    this.tfCidade.getText(), this.tfEstado.getText());

            Funcionario auxFuncionario = new Funcionario();

            auxFuncionario.setEndereco(auxEndereco);
            auxFuncionario.setNome(this.tfNome.getText());
            auxFuncionario.setId(this.pressionado.getId());

            if (this.tfUsuario.isDisabled() != true | this.tfSenha.isDisabled() != true) {

                auxFuncionario.setLogin(this.tfUsuario.getText());
                auxFuncionario.setSenha(this.tfSenha.getText());
            } else {

                auxFuncionario.setLogin(null);
                auxFuncionario.setSenha(null);
            }

            auxFuncionario.setCargo((String) this.cbCargo.getValue());

            try {

                auxFuncionario.setSalario(Double.parseDouble(this.tfSalario.getText()));

            } catch (NumberFormatException num) {

                auxFuncionario.setSalario(-1);

            }

            try {

                this.sistema.alterarInfoFuncionario(pressionado, auxFuncionario);

                System.out.println(auxFuncionario.toString());

                System.out.println("\nAtualizado");

            } catch (NegocioException neg) {

                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("ERRO");
                erro.setHeaderText("Funcionário");
                erro.setContentText(neg.getMessage());
                erro.showAndWait();

                System.out.println("Erro: " + neg.getMessage());
            }
        }
        this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));
    }

    public void buscarFuncionario() {

        ArrayList<Funcionario> auxLista = new ArrayList();

        this.limparCamposDeTexto();

        for (int i = 0; i < this.sistema.listaFuncionario().size(); i++) {

            if (this.sistema.listaFuncionario().get(i).getNome().contains(this.tfBuscar.getText())) {
                auxLista.add(this.sistema.listaFuncionario().get(i));
            }
        }

        this.carregarItensNaTabela(FXCollections.observableArrayList(auxLista));

    }

    public void limparCampoDeBusca() {

        this.tfBuscar.clear();
        this.carregarItensNaTabela(FXCollections.observableArrayList(sistema.listaFuncionario()));
    }

    public void botaoVoltar() {
        
        MainFuncionarioTest.setCenaAnterior();
    }

}
