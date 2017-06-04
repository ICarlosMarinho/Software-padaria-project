package basico;

public class Funcionario {
	
	private String nome;
	private Endereco endereco;
	private int id;
	private boolean gerente;
	private int qtdVendida;
	private double valorVendas;
	private double salario;
	
	
	// construtores
	public Funcionario() {}
	public Funcionario(String nome, Endereco endereco, int id, boolean gerente,
					   int qtdVendida, double valorVendas, double salario) {
		this.nome = nome;
		this.endereco = endereco;
		this.id = id;
		this.gerente = gerente;
		this.qtdVendida = qtdVendida;
		this.valorVendas = valorVendas;
		this.salario = salario;
	}
	
	
	// metodos set
	public void setNome(String nome){
		this.nome = nome;
	}
	public void setEndereco(Endereco endereco){
		this.endereco = endereco;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setGerente(boolean value){
		this.gerente = value;
	}
	public void setQtdVendida(int qtdVendida){
		this.qtdVendida = qtdVendida;
	}
	public void setValorVendas(double valorVendas){
		this.valorVendas = valorVendas;
	}
	public void setSalario(double salario){
		this.salario = salario;
	}
	
	
	// metodos get
	public String getNome() {
		return this.nome;
	}
	public Endereco getEndereco() {
		return this.endereco;
	}
	public int getId() {
		return this.id;
	}
	public boolean getGerente() {
		return this.gerente;
	}
	public int getQtdVendida() {
		return this.qtdVendida;
	}
	public double getValorVendas() {
		return this.valorVendas;
	}
	public double getSalario() {
		return this.salario;
	}
	
	
	// metodo toString
	public String toString() {
		return this.nome + " | " + this.id;
	}
	
	
	// metodo equals
	public boolean equals(Funcionario f) {
		if(f == null) {
			return false;
		}
		
		if( this.id == f.id ) {
			return true;
		}
		
		return false;
	}
	
	
	// Este metodo diz se o funcionario em questao e gerente
	public boolean eGerente() {
		return this.gerente;
	}
	
	
}
