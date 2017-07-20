package exceptions;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensagemParaDesenvolvedor;
	
	public SistemaException( String mensagem, String problema ) {
		super(mensagem);
		this.mensagemParaDesenvolvedor = problema;
	}

	public String getMensagemParaDesenvolvedor() {
		return mensagemParaDesenvolvedor;
	}
	public void setMensagemParaDesenvolvedor(String mensagemParaDesenvolvedor) {
		this.mensagemParaDesenvolvedor = mensagemParaDesenvolvedor;
	}
	
	
	
}
