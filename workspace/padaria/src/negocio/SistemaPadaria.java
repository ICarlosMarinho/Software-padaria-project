package negocio;

import java.util.ArrayList;

import classesBasicas.*;
import exceptions.NegocioException;
import exceptions.SistemaException;

public class SistemaPadaria {

    private static SistemaPadaria system;

    private CadastroFuncionario opFuncionarios;
    private CadastroCliente opCliente;
    private CadastroProduto opProduto;
    private CadastroVendas opVendas;

    private SistemaPadaria() {
        opFuncionarios = new CadastroFuncionario();
        opCliente = new CadastroCliente();
        opProduto = new CadastroProduto();
        opVendas = new CadastroVendas();
    }

    public static SistemaPadaria getInstancia() {
        if (system == null) {
            system = new SistemaPadaria();
        }

        return system;
    }

    //**METODOS DE ACESSO A CLASSE OPERACOESFUNCIONARIO
    public boolean cadastrarFuncionario(Funcionario auxFun) {
        return this.opFuncionarios.cadastrar(auxFun);
    }

    public boolean excluirFuncionario(Funcionario auxFun) {
        return this.opFuncionarios.excluir(auxFun);
    }

    public boolean alterarInfoFuncionario(Funcionario antigo, Funcionario novo) {
        return this.opFuncionarios.alterarInfo(antigo, novo);
    }

    public Funcionario buscarFuncionario(int auxId) {
        return this.opFuncionarios.buscar(auxId);
    }

    public Funcionario buscarFuncionario(String nomeUsuario) {
        return this.opFuncionarios.buscar(nomeUsuario);
    }

    public int atribuirIdFuncionario() {
        return this.opFuncionarios.atribuirId();
    }
    
    public ArrayList<Funcionario> listaFuncionario(){
        return this.opFuncionarios.listaFuncionario();
    }

    //**METODOS DE ACESSO A CLASSE OPERACOESPRODUTO
    public boolean setPorcentual(double porcentual) {
        return this.setPorcentual(porcentual);
    }

    public boolean setMax(double max) {
        return this.setMax(max);
    }

    public boolean cadastrarProduto(String nome, String descricao,
            int dia, int mes, int ano,
            double quantidade, double preco) throws NegocioException {

        return this.opProduto.cadastrar(nome, descricao,
                dia, mes, ano,
                quantidade, preco);
    }
    
    public boolean cadastrarProduto(String nome, String descricao
		    , String dia, String mes, String ano
		    , String quantidade, String preco) throws NegocioException {
    	
    	return this.opProduto.cadastrar(nome, descricao, dia, mes, ano, quantidade, preco);
    }

    public boolean validadeProduto(int dia, int mes, int ano) {
        return this.opProduto.validadeOK(dia, mes, ano);
    }

    public boolean removerProduto(int id) throws NegocioException {
        return this.opProduto.remover(id);
    }

    public boolean modificarProduto(int id, int opcao, String valor)
            throws NegocioException, SistemaException {
        return this.opProduto.modificar(id, opcao, valor);
    }

    public boolean modificarProduto(int id, int opcao,
            int dia, int mes, int ano) throws NegocioException, SistemaException {

        return this.opProduto.modificar(id, opcao, dia, mes, ano);
    }

    public boolean modificarProduto(int id, int opcao, double valor)
            throws NegocioException, SistemaException {
        return this.opProduto.modificar(id, opcao, valor);
    }

    public boolean modificarProduto(int id, String nome, String descricao
			, String dia, String mes, String ano
			, String quantidade, String preco) throws NegocioException, SistemaException {
    	
    	return this.opProduto.modificar(id, nome, descricao, dia, mes, ano, quantidade, preco);
    }
    
    public Produto buscarProduto(int id) {
        return this.opProduto.buscar(id);
    }

    public Produto buscarProduto(String nome) {
        return this.opProduto.buscar(nome);
    }

    public ArrayList<Produto> buscarProdutoOcorrencia(String ocorrencia) {
        return this.opProduto.buscarOcorrencia(ocorrencia);
    }
    
    public ArrayList<Produto> listaProduto() {
        return this.opProduto.todos();
    }

    public int totalProduto() {
        return this.opProduto.total();
    }

    //**METODOS DE ACESSO A CLASSE OPERACOESCLIENTE
    public boolean cadastrarCliente(String nome, String logradouro, String numero,
             String complemento, String cidade, String estado) throws NegocioException, SistemaException {

        return this.opCliente.cadastrar(nome, logradouro, numero,
                complemento, cidade, estado);
    }

    public boolean removerCliente(int id) throws NegocioException {
        return this.opCliente.remover(id);
    }

    public boolean removerCliente(String nome) throws NegocioException {
        return this.opCliente.remover(nome);
    }

    public Cliente buscarCliente(int id) {
        return this.opCliente.buscar(id);
    }

    public Cliente buscarCliente(String nome) {
        return this.opCliente.buscar(nome);
    }

    public boolean atualizarCliente(Cliente antigo, Cliente novo) {
        return this.opCliente.atualizar(antigo, novo);
    }

    public int totalCliente() {
        return this.opCliente.total();
    }

    public ArrayList<Cliente> buscarClienteOcorrencia(String ocorrencia) {
    	return this.opCliente.buscarOcorrencia(ocorrencia);
    }
    
    public ArrayList<Cliente> listaCliente() {
        return this.opCliente.todos();
    }

    public boolean modificarCliente(int id, int campo, String valor) throws NegocioException, SistemaException {
        return this.opCliente.modificar(id, campo, valor);
    }

    public boolean modificarCliente(int id, String nome, String logradouro
			, String numero, String complemento, String cidade
			, String estado) throws NegocioException, SistemaException {
    	
    	return this.opCliente.modificar(id, nome, logradouro, numero, complemento, cidade, estado);
    }
    
    //**METODO DE ACESSO A CLASSE CADASTROVENDAS
    public Venda[] listaVenda() {
        return opVendas.lista();
    }

    public boolean adicionarVenda(ArrayList<DadoVenda> vendido, Funcionario vendedor) throws SistemaException {
        return opVendas.adicionar(vendido, vendedor);
    }

    public boolean adicionarVenda(ArrayList<DadoVenda> vendido, Funcionario vendedor, Cliente comprador) throws SistemaException {
        return opVendas.adicionar(vendido, vendedor, comprador);
    }

    public boolean efetuarVenda( ArrayList<DadoVenda> vendido, int idFuncionario )
			throws NegocioException, SistemaException {
        return this.opVendas.vender(vendido, idFuncionario );
    }

    public boolean efetuarVenda( ArrayList<DadoVenda> vendido, int idCliente, int idFuncionario ) 
    		throws NegocioException, SistemaException {
        return this.opVendas.vender(vendido, idCliente, idFuncionario );
    }

}
