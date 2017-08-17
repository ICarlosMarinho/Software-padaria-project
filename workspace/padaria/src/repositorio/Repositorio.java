package repositorio;


// TODO esta classe eh a super classe dos repositorios
import java.util.*;

public class Repositorio {

    protected ArrayList<Object> repositorio;

    //construtores
    public Repositorio() {
        this.repositorio = new ArrayList<Object>();
    }

    public Repositorio(int tamanhoInicial) {
        this.repositorio = new ArrayList<Object>(tamanhoInicial);
    }

    private int retornarPosicao(Object procurado) {
        if (procurado == null) {
            return -1;
        }

        Object aux;

        for (int a = 0; a < this.repositorio.size(); a++) {

            aux = this.repositorio.get(a);

            if (aux.equals(procurado)) {
                return a;
            }
        }

        return -1;
    }

    public Object[] listar() {
        return this.repositorio.toArray();
    }
    
    public boolean adicionar(Object novo) {

        if (novo == null) {
            return false;
        }

        // o produto nao pode existir no estoque
        if (retornarPosicao(novo) != -1) {
            return false;
        }

        this.repositorio.add(novo);
        return true;

    }

    public boolean remover(Object procurado) {

        int posicao = this.retornarPosicao(procurado);

        if (posicao == -1) {
            return false;
        }

        this.repositorio.remove(posicao);
        return true;
    }

    public Object buscar(Object procurado) {

        int posicao = this.retornarPosicao(procurado);

        if (posicao == -1) {
            return null;
        }

        return this.repositorio.get(posicao);
    }

    public boolean atualizar(Object antigo, Object novo) {

        int posicao = this.retornarPosicao(antigo);

        if (posicao == -1 || novo == null) {
            return false;
        }

        this.repositorio.set(posicao, novo);
        return true;
    }

    public int tamanho() {
        return this.repositorio.size();
    }

}
