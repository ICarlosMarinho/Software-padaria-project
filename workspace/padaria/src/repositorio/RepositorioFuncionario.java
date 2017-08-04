package repositorio;

import classesBasicas.Funcionario;
import java.util.ArrayList;

public class RepositorioFuncionario extends Repositorio implements IRepositorioFuncionario {

    //**Metodos construtores
    public RepositorioFuncionario() {
        super();
    }

    public RepositorioFuncionario(int TamInicial) {
        super(TamInicial);
    }

    @Override
    public boolean adicionar(Funcionario novo) {

        return super.adicionar(novo);
    }

    @Override
    public boolean remover(Funcionario Procurado) {

        return super.remover(this.buscar(Procurado));
    }

    @Override
    public Funcionario buscar(Funcionario procurado) {

        return (Funcionario) super.buscar(procurado);
    }

    @Override
    public int tamanho() {
        return super.tamanho();
    }

    @Override
    public Funcionario buscar(int auxiliarId) {
        Object[] listaFuncionario = new Object[this.tamanho()];
        Funcionario auxFuncionario;

        listaFuncionario = super.listar();

        for (int i = 0; i < this.tamanho(); i++) {

            auxFuncionario = (Funcionario) listaFuncionario[i];

            if (auxFuncionario.getId() == auxiliarId) {
                return auxFuncionario;
            }

        }

        return null;
    }
    
    @Override
    public boolean atualizar(Funcionario antigo, Funcionario novo)
    {
        return super.atualizar(antigo, novo);
    }
}