package br.dcx.ufpb.bibioteca;

import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;

import java.util.Collection;

public interface SistemaInterfaceBiblioteca {
    boolean cadastrarLivro(String titulo, String autor, String codLivro) throws LivroJaExisteException;
    boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException;
    Collection<Livro> buscarLivroPorTitulo(String titulo);
    boolean removerLivro(String codLivro);
    void realizarEmprestimo(String matricula, String tituloLivro, String dataEmprestimo, String dataDevolucao);
}

