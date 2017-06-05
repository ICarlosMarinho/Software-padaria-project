package basico;

public class Endereco {
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String cidade;
	private String estado;
	
	
	// construtores
	public Endereco() {}
	public Endereco(String logradouro, String numero, String complemento
				   , String cidade, String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	
	
	
	
	
	
	// metodos get
	public String getLogradouro() {
		return this.logradouro;
	}
	public String getNumero() {
		return this.numero;
	}
	public String getComplemento() {
		return this.complemento;
	}
	public String getCidade() {
		return this.cidade;
	}
	public String getEstado() {
		return this.estado;
	}
	
	
	// metodos set
	public void setLogradouro(String novoLogradouro) {
		this.logradouro = novoLogradouro;
	}
	public void setNumero(String novoNumero) {
		this.numero = novoNumero;
	}
	public void setComplemento(String novoComplemento) {
		this.complemento = novoComplemento;
	}
	public void setCidade(String novoCidade) {
		this.cidade = novoCidade;
	}
	public void setEstado(String novoEstado) {
		this.estado = novoEstado;
	}

	
	// metodo toString
	public String toString() {
		return this.logradouro + " nº " + this.numero + " | " + this.complemento + ", " + this.cidade + ", " + this.estado;
	}
	
	
	// metodo equals
	public boolean equals(Endereco e) {
		if( e == null) {
			return false;
		}
		
		String endereco = e.toString();
		
		if( endereco.equals(this.toString()) ) {
			return true;
		}
		
		return false;
	}
	
	


}
