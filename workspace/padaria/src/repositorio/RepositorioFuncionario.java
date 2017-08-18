package repositorio;

import classesBasicas.Funcionario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        for (int i = 0; i < this.repositorio.size(); i++) {

            if (this.repositorio.get(i) != null) {

                if (((Funcionario) this.repositorio.get(i)).getId() == auxiliarId) {

                    return (Funcionario) this.repositorio.get(i);
                }
            }
        }

        return null;
    }

    @Override
    public boolean atualizar(Funcionario antigo, Funcionario novo) {
        return super.atualizar(antigo, novo);
    }

    @Override
    public Funcionario buscar(String login) {

        for (int i = 0; i < this.repositorio.size(); i++) {

            if (((Funcionario) this.repositorio.get(i)).getLogin() != null) {

                if (((Funcionario) this.repositorio.get(i)).getLogin().equalsIgnoreCase(login) == true) {

                    return (Funcionario) this.repositorio.get(i);
                }
            }
        }

        return null;
    }

    @Override
    public ArrayList<Funcionario> listaFuncionario() {

        ArrayList<Funcionario> auxFuncionarios = new ArrayList<Funcionario>();

        for (int i = 0; i < this.repositorio.size(); i++) {
            auxFuncionarios.add((Funcionario) this.repositorio.get(i));
        }

        return auxFuncionarios;
    }
    
    public Repositorio getRepositorio() {
		return this;
	}
    
//    @Override
//    public void lerDoArquivo(ArrayList repositorio, FileInputStream arquivo){
//        super.lerDoArquivo(repositorio, arquivo);
//    }
    
//    @Override
//    public void gravarNoArquivo(ArrayList repositorio, FileOutputStream arquivo){
//        super.gravarNoArquivo(repositorio, arquivo);
//    }
}
