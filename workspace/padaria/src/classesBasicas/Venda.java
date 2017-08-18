package classesBasicas;

import java.util.ArrayList;
import java.util.Calendar;


public class Venda {
	
	// atributos
	private Calendar    data;
	private ArrayList<DadoVenda> vendido;
	private Cliente     comprador;
	private Funcionario vendedor;
	
	
	// constructor
	public Venda( Calendar data, ArrayList<DadoVenda> vendido, Cliente comprador, Funcionario vendedor ) {
		
		this.data = data;
		this.vendido = vendido;
		this.comprador = comprador;
		this.vendedor = vendedor;
		
	}
	
	
	// metodos set
	public void setData( Calendar novaData ) {
		this.data = novaData;
	}
	public void setProduto( ArrayList<DadoVenda> novoProduto ) {
		this.vendido = novoProduto;
	}
	public void setComprador( Cliente novoComprador ) {
		this.comprador = novoComprador;
	}
	public void setVendedor( Funcionario novoVendedor ) {
		this.vendedor = novoVendedor;
	}
	
	
	// metodos get
	public Calendar getData() {
		return this.data;
	}
	public ArrayList<DadoVenda> getVendido() {
		return this.vendido;
	}
	public Cliente getComprador() {
		return this.comprador;
	}
	public Funcionario getVendedor() {
		return this.vendedor;
	}
	
	
	
	public boolean equals( Venda comparar ) {
		if( comparar == null ) {
			return false;
		}
		
		if( this.vendido.equals( comparar.getVendido() ) && this.data.equals( comparar.getData() )  ) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
