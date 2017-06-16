package negocio;

import classesBasicas.*;

public class SistemaPadaria 
{
   private static SistemaPadaria system;
   
   private OperacoesFuncionario opFuncionarios;
   private IntermediarioCliente opCliente;
   private IntermediarioProduto opProduto;
   
   
   private SistemaPadaria()
   {
	   opFuncionarios = new OperacoesFuncionario();
	   opCliente      = new IntermediarioCliente();
	   opProduto      = new IntermediarioProduto();
   }
   
   public static SistemaPadaria getInstancia()
   {
        if(system == null)
        {
            system = new SistemaPadaria();
        }
        
        return system;
   }
   
   //**METODOS DE ACESSO A CLASSE OPERACOESFUNCIONARIO
   
   public boolean cadastrarFuncionario(Funcionario auxFun)
   {
       return this.opFuncionarios.cadastrar(auxFun);
   }
   
   public boolean excluirFuncionario(int posicaoFun)
   {
       return this.opFuncionarios.excluir(posicaoFun);
   }
   
   public boolean alterarInfoFuncionario(Funcionario auxFun)
   {
       return this.opFuncionarios.alterarInfo(auxFun);
   }
   
   public Funcionario buscarFuncionario(int auxId)
   {
       return this.opFuncionarios.buscar(auxId);
   }
   
   public Funcionario buscarFuncionario(String auxLogin)
   {
       return this.opFuncionarios.buscar(auxLogin);
   }
   
   public int retornarPosicaoFuncionario(int auxId)
   {
       return this.opFuncionarios.retornarPosicao(auxId);
   }
   
   public int atribuirIdFuncionario(Funcionario auxFun)
   {
       return this.opFuncionarios.atribuirId(auxFun);
   }
   
   
   //**METODOS DE ACESSO A CLASSE INTERMEDIARIOPRODUTO
   public boolean cadastrarProduto( String nome, String descricao
		   						  , int dia, int mes, int ano
		   						  , int quantidade, double preco ) {
	   
	   return this.opProduto.cadastrar( nome, descricao
			   						  , dia, mes, ano
			   						  , quantidade, preco );
   }
   
   public boolean removerProduto( int id ) {
	   return this.opProduto.remover(id);
   }
   
   public boolean modificarProduto( int id, int opcao, String valor ) {
	   return this.opProduto.modificar(id, opcao, valor);
   }
   public boolean modificarProduto( int id, int opcao
		   				   , int dia, int mes, int ano ) {
	   
	   return this.opProduto.modificar(id, opcao, dia, mes, ano);
   }
   public boolean modificarProduto( int id, int opcao, int valor ) {
	   return this.opProduto.modificar( id, opcao, valor);
   }
   public boolean modificarProduto( int id, int opcao, double valor ) {
	   return this.opProduto.modificar(id, opcao, valor);
   }
   
   public Produto buscarProduto( int id ) {
	   return this.opProduto.buscar(id);
   }
   public Produto buscarProduto( String nome ) {
	   return this.opProduto.buscar(nome);
   }
   
   public Produto[] listaProduto() {
	   return this.opProduto.todos();
   }
   
   public int totalProduto() {
	   return this.opProduto.total();
   }
   
   
   
   //**METODOS DE ACESSO A CLASSE INTERMEDIARIOCLIENTE
   public boolean cadastrarCliente( String nome, String logradouro, String numero
			                      , String complemento, String cidade, String estado ) {
	   
	   return this.opCliente.cadastrar( nome, logradouro, numero
			   						  , complemento, cidade, estado );
   }
   
   public boolean removerCliente( int id ) {
	   return this.opCliente.remover(id);
   }
   public boolean removerCliente( String nome ) {
	   return this.opCliente.remover(nome);
   }
   
   public Cliente buscarCliente( int id ) {
	   return this.opCliente.buscar(id);
   }
   public Cliente buscarCliente( String nome ) {
	   return this.opCliente.buscar(nome);
   }
   
   public int totalCliente() {
	   return this.opCliente.total();
   }
   
   public Cliente[] listaCliente() {
	   return this.opCliente.todos();
   }
   
   public boolean modificarCliente( int id, int campo, String valor ) {
	   return this.opCliente.modificar( id, campo, valor );
   }
   
}
