package negocio;

import repositorio.RepositorioFuncionario;
import classesBasicas.Funcionario;
import classesBasicas.Endereco;

public class IntermediarioFuncionario {
	
	// atributos
	private RepositorioFuncionario funcionario;
	
	
	// construtores
	public IntermediarioFuncionario() {}
	
	
	/*
	 * este metodo cadastra um novo funcionario no repositorio de funcionarios
	 * 
	 * 
	 * @ parametro nome        --- nome do novo funcionario
	 * @ parametro gerente     --- true se for gerente false se nao
	 * @ parametro salario     --- salario inicial do funcionario
	 * *** informacoes de endereco ***
	 * @ parametro logradouro  --- logradouro do novo funcionario
	 * @ parametro numero      --- numero
	 * @ parametro complemento --- complemento
	 * @ parametro cidade      --- cidade
	 * @ parametro estado      --- estado do novo funcionario por extenso Ex.:
	 * 							   nao sera PE e sim Pernambuco
	 */
	public boolean cadastrar(String nome, boolean gerente, double salarioInicial
					, String logradouro, String numero, String complemento
					, String cidade, String estado) {
		
		int qtdVendida = 0;
		double valorVendas = 0.0;
		
		/*
		 * Verificar se ja nao existe funcionario no repositorio com o mesmo nome
		 */
		if( funcionario.buscar(nome) == null ) {
			return false;
		}
		
		/*
		 * Procurar o valor do id do novo funcionario
		 */
		Funcionario aux;
		int id;
		for(id = 0; true ; id++) {
			
			
			aux = new Funcionario(null, null, id, false, 0, 0, 0);
			if( funcionario.buscar(aux) == null ) { // nao existe funcionario com este id
				break; // id achado
			}

		}
		
		
		Endereco novoEndereco = new Endereco( logradouro, numero, complemento, cidade, estado );
		
		Funcionario novoFuncionario = new Funcionario ( nome, novoEndereco, id, gerente, qtdVendida, valorVendas, salarioInicial);
		
		
		this.funcionario.adicionar(novoFuncionario); // funcionario adicionado com id unico
		return true;
	}
	
	//TODO fazer funcoes remove() atualizar() buscar() todos() total()
	
	
	
}
