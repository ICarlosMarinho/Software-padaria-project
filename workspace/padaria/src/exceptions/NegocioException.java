package exceptions;

public class NegocioException extends Exception {
	
	// esta parte existe apenas por conta heranca de Exception
	private static final long serialVersionUID = 1L;
	
	Object instanciaDoProblema;
	
	public NegocioException( String mensagem, Object instanciaDoProblema ) {
		super( mensagem );
		this.instanciaDoProblema = instanciaDoProblema;
	}
	
	public Object getInstanciaDoProblema() {
		return this.instanciaDoProblema;
	}
	
	
}
