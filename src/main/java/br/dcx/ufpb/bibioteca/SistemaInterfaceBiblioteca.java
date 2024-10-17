package br.dcx.ufpb.bibioteca;

import br.dcx.ufpb.bibioteca.exception.*;

import java.io.IOException;
import java.util.Collection;

import java.util.Collection;

public interface SistemaInterfaceBiblioteca {

    public boolean cadastrarLivro(String titulo, GeneroLivro generoLivro, String autor, String codLivro) throws LivroJaExisteException;
    public boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException;
    public Collection<Livro> buscarLivroPorTitulo(String titulo);
    public Collection<Livro> buscarLivrosPorGenero(GeneroLivro genero);
    public boolean removerLivro(String codLivro) throws LivroNaoExisteException;
    public void realizarEmprestimo(String matricula, String tituloLivro, PeriodoEmprestimo periodoEmprestimo);
    public Emprestimo buscarEmprestimoPorMatricula(String matricula) throws MatriculaNaoEncontradaException;
    public Collection<Emprestimo> buscarEmprestimosRealizadosNoMes(int mes) throws MesInformadoNaoExisteException;


}

