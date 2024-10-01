package br.dcx.ufpb.pedro;

import br.dcx.ufpb.pedro.exception.LivroJaExisteException;
import br.dcx.ufpb.pedro.exception.UsuarioJaExisteException;

import java.util.List;

public interface SistemaInterfaceBiblioteca {
    public boolean cadastrarLivro(String titulo, String autor, String codLivro) throws LivroJaExisteException;
    public boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException;
    public Livro buscarLivroPorTitulo(String titulo);
    public boolean removerLivro(String codLivro);
    public void realizarEmprestimo(String matricula, String tituloLivro, String dataEmprestimo, String dataDevolucao);
}

