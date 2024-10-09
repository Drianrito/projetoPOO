package br.dcx.ufpb.bibioteca;


import br.dcx.ufpb.bibioteca.exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;
import br.dcx.ufpb.bibioteca.gravador.GravaDados;

import java.io.IOException;
import java.util.*;

public class SistemaBiblioteca implements SistemaInterfaceBiblioteca {
    private HashMap<String, Livro> livros = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private GravaDados gravador = new GravaDados();

    public boolean cadastrarLivro(String titulo, GeneroLivro generoLivro, String autor, String codLivro) throws LivroJaExisteException {
        if (!this.livros.containsKey(codLivro)) {
            Livro novoLivro = new Livro(titulo, generoLivro, autor, codLivro);
            livros.put(codLivro, novoLivro);
            return true;
        } else {
            throw new LivroJaExisteException("Já existe esse livro no sistema.");
        }
    }

    public boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException {
        Usuario novoUsuario = new Usuario(nome, matricula, email);
        if (!this.usuarios.contains(novoUsuario)) {
            this.usuarios.add(novoUsuario);
            return true;
        } else {
            throw new UsuarioJaExisteException("Já existe esse usuário no sistema.");
        }
    }

    public Collection<Livro> buscarLivroPorTitulo(String titulo) {
        return this.livros.values().stream().filter(livro -> livro.getTitulo().startsWith(titulo)).toList();
    }


    public Collection<Livro> buscarLivrosPorGenero(GeneroLivro generoLivro) {
        Collection<Livro> livrosPorGenero = new ArrayList<>();
        for (Livro livro: this.livros.values()) {
            if (livro.getGeneroLivro() == generoLivro) {
                livrosPorGenero.add(livro);
            }
        } return livrosPorGenero;
    }

    public boolean removerLivro(String codLivro) throws LivroNaoExisteException {
        if (this.livros.containsKey(codLivro)) {
            this.livros.remove(codLivro);
            return true;
        } else {
            throw new LivroNaoExisteException("Não existe livro no sistema com o código informado.");
        }
    }

    public void realizarEmprestimo(String matricula, String tituloLivro, PeriodoEmprestimo periodoEmprestimo) {
        Emprestimo emprestimo = new Emprestimo(matricula, tituloLivro, periodoEmprestimo);
        this.emprestimos.add(emprestimo);
    }

    public Emprestimo pesquisarEmprestimoPorMatricula(String matricula)throws MatriculaNaoEncontradaException{
        return this.emprestimos.stream().filter(emprestimo -> emprestimo.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    public void lerEmprestimos(){
        try {
            this.emprestimos = gravador.leEmprestimos();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void  gravarEmprestimos(){
        try {
            gravador.gravarEmprestimos(this.emprestimos);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    public Collection<Emprestimo> buscarEmprestimosRealizadosNoMes(int mes) throws MesInformadoNaoExisteException {
        Collection<Emprestimo> emprestimosRealizadosNoMes = new ArrayList<>();
        if (mes < 1 || mes > 12) {
            throw new MesInformadoNaoExisteException("O mês digitado não existe.");
        }
        for (Emprestimo emp: this.emprestimos) {
            if (emp.getPeriodoEmprestimo().getDataEmprestimo().getMonthValue() == mes) {
                emprestimosRealizadosNoMes.add(emp);
            }
        } return emprestimosRealizadosNoMes;
    }
}




