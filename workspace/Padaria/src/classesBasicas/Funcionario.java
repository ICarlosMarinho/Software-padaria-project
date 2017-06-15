package classesBasicas;

public class Funcionario 
{
    private String nome;
    private String cargo;
    private String login;
    private String senha;
    private Endereco endereco;
    private int id;
    private double valorVendas;
    private double salario;


    // construtores
    public Funcionario() 
    {
    }
    
    public Funcionario(String nome,
                        String cargo,
                        String login,
                        String senha,
                        Endereco endereco, 
                        int id,
                        double valorVendas, 
                        double salario) 
    {
            this.nome = nome;
            this.cargo = cargo;
            this.login = login;
            this.senha = senha;
            this.endereco = endereco;
            this.id = id;
            this.valorVendas = valorVendas;
            this.salario = salario;
    }


    // metodos set
    public void setNome(String nome){
            this.nome = nome;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getLogin() {
        return login;
    }
    public String getSenha() {
        return senha;
    }
    public void setEndereco(Endereco endereco){
            this.endereco = endereco;
    }
    public void setId(int id){
            this.id = id;
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
    public String getCargo() {
        return cargo;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    } 
    public Endereco getEndereco() {
            return this.endereco;
    }
    public int getId() {
            return this.id;
    }
    public double getValorVendas() {
            return this.valorVendas;
    }
    public double getSalario() {
            return this.salario;
    }


    // metodo toString
    
    public String toString() 
    {
        return "Funcionario:\n" 
                + "\n*Nome: " + this.nome 
                + "\n*Cargo: " + this.cargo 
                + "\n*Login: " + this.login 
                + "\n*Senha: " + this.senha 
                + "\n*Endereco: " + this.endereco.toString()
                + "\n*ID: " + this.id 
                + "\n*Total em vendas: " + this.valorVendas 
                + "\n*Salario: R$" + this.salario;
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
}
