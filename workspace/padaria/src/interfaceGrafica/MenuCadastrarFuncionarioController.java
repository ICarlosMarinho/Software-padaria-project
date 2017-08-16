/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import classesBasicas.Endereco;
import classesBasicas.Funcionario;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import negocio.SistemaPadaria;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MenuCadastrarFuncionarioController implements Initializable {

    SistemaPadaria sistema = SistemaPadaria.getInstancia();

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
    private Button btnCadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.cbCargo.getItems().addAll("Gerente", "Caixa", "Padeiro");

        this.tfUsuario.disableProperty().bind(this.cbCargo.valueProperty().isNotEqualTo("Gerente").and(this.cbCargo.valueProperty().isNotEqualTo("Caixa")));
        this.tfSenha.disableProperty().bind(this.cbCargo.valueProperty().isNotEqualTo("Gerente").and(this.cbCargo.valueProperty().isNotEqualTo("Caixa")));
    }

    public void cadastrarFuncionario() {

        Endereco auxEndereco = new Endereco(this.tfLogradouro.getText(), this.tfNumero.getText(), this.tfComplemento.getText(),
                this.tfCidade.getText(), this.tfEstado.getText());

        Funcionario auxFuncionario = new Funcionario();

        auxFuncionario.setEndereco(auxEndereco);
        auxFuncionario.setNome(this.tfNome.getText());

        if (this.tfUsuario.isDisabled() != true | this.tfSenha.isDisabled() != true) {

            auxFuncionario.setLogin(this.tfUsuario.getText());
            auxFuncionario.setSenha(this.tfSenha.getText());
        }

        auxFuncionario.setCargo((String) this.cbCargo.getValue());

        if (this.tfSalario.getText().isEmpty() != true) {
            
            try{
                
            auxFuncionario.setSalario(Double.parseDouble(this.tfSalario.getText()));
            
            }catch(NumberFormatException num){
                
                System.out.println("Numero Invalido");
                
                System.exit(1);
            }
        }

        auxFuncionario.setId(sistema.atribuirIdFuncionario());

        if (sistema.cadastrarFuncionario(auxFuncionario) == true) {

            System.out.println("Funcionario Cadastrado");
        } else {

            System.out.println("Erro no cadastro");
        }

        System.out.println(auxFuncionario.toString());
    }
}
