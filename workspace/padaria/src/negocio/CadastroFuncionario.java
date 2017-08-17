package negocio;

import repositorio.IRepositorioFuncionario;
import classesBasicas.*;
import java.util.ArrayList;
import repositorio.RepositorioFuncionario;

public class CadastroFuncionario {

    private IRepositorioFuncionario repo;

    public CadastroFuncionario() {
        repo = new RepositorioFuncionario();
    }

    public Funcionario verificarInformacoes(Funcionario auxFun) {
        //**VERIFICAR FUNCIONARIO OU ENDEREÃ‡O NULO
        if (auxFun == null | auxFun.getEndereco() == null) {
            return null;
        } //**VERIFICAR SE A STRING REFERENTE AO NOME Ã‰ NULA
        else if (auxFun.getNome() == null | auxFun.getNome().isEmpty() == true) {
            return null;
        } //**VERIFICA SE O CARGO DO FUNCIONARIO Ã‰ VALIDO 
        else if(auxFun.getCargo() ==null){
            
            return null;
        }
        else if (auxFun.getCargo().equalsIgnoreCase("Gerente") == false
                && auxFun.getCargo().equalsIgnoreCase("Caixa") == false
                && auxFun.getCargo().equalsIgnoreCase("Padeiro") == false) {
            return null;
        } /*
        *Verifica algumas informaÃ§Ãµes sobre login e senha caso o funcionÃ¡rio seja gerente ou caixa,
        *pois somente esses cargos tem acesso ao sistema.
         */ else if (auxFun.getCargo().equalsIgnoreCase("Gerente") | auxFun.getCargo().equalsIgnoreCase("Caixa")) {
            //**VERIFICA SE O LOGIN E A SENHA ESTÃƒO NULOS
            if (auxFun.getLogin() == null | auxFun.getSenha() == null) {
                return null;
            } //**VERIFICA O TAMANHO DO LOGIN E SENHA (A REGRA AQUI Ã‰ ESTAR ENTRE 4~10 CARACTERES)
            else if (auxFun.getLogin().length() < 4 | auxFun.getLogin().length() > 10
                    | auxFun.getSenha().length() < 4 | auxFun.getSenha().length() > 10) {
                return null;
            }

            //**VERIFICA SE O LOGIN DO FUNCIONARIO A SER CADASTRADO JA EXISTE
            if (this.repo.buscar(auxFun.getLogin()) != null && auxFun.getId() != this.repo.buscar(auxFun.getLogin()).getId()) {

                return null;
            }

        } //**VERIFICA SE O SALARIO Ã‰ MAIOR QUE ZERO
        else if (auxFun.getSalario() <= 0f) {
            return null;
        }

        //**VERIFICA SE ALGUMA INFORMAÃ‡ÃƒO DO ENDEREÃ‡O ESTÃ� NULA
        if (auxFun.getEndereco().getLogradouro().isEmpty()
                | auxFun.getEndereco().getCidade().isEmpty()
                | auxFun.getEndereco().getComplemento().isEmpty()
                | auxFun.getEndereco().getEstado().isEmpty()
                | auxFun.getEndereco().getNumero().isEmpty()) {
            return null;
        }

        return auxFun;
    }

    public boolean cadastrar(Funcionario novoFun) {
        if (this.verificarInformacoes(novoFun) == null) {
            return false;
        }

        this.repo.adicionar(novoFun);

        return true;
    }

    public boolean excluir(Funcionario auxFun) {
        if (auxFun == null) {
            return false;
        }

        this.repo.remover(auxFun);

        return true;
    }

    public boolean alterarInfo(Funcionario antigo, Funcionario novo) {
        if (this.verificarInformacoes(novo) != null) {
            return this.repo.atualizar(antigo, novo);
        }

        return false;
    }

    public Funcionario buscar(int auxId)//**BUSCAR FUNCIONARIO A PARTIR DO ID
    {
        if (auxId < 0) {
            return null;
        }

        return this.repo.buscar(auxId);
    }

    public Funcionario buscar(String login) {

        if (login != null) {

            return this.repo.buscar(login);
        }

        return null;
    }

    public int atribuirId() {

        int auxId = 10000 + repo.tamanho();

        while (this.repo.buscar(auxId) != null) {

            auxId++;
        }

        return auxId;
    }
    
    public ArrayList<Funcionario> listaFuncionario(){
        
        return this.repo.listaFuncionario();
    }
}
