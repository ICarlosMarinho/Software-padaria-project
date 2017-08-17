package repositorio;

import classesBasicas.Funcionario;
import java.util.ArrayList;

public interface IRepositorioFuncionario {

    public boolean adicionar(Funcionario novo);

    public boolean remover(Funcionario Procurado);

    public Funcionario buscar(Funcionario procurado);

    public Funcionario buscar(int auxiliarId);

    public int tamanho();
    
    public boolean atualizar(Funcionario antigo, Funcionario novo);

    public Funcionario buscar(String login);
    
    public ArrayList<Funcionario> listaFuncionario();

}
