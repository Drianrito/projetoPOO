package br.dcx.ufpb.pedro;

import br.dcx.ufpb.pedro.exception.LivroJaExisteException;
import br.dcx.ufpb.pedro.exception.UsuarioJaExisteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SistemaBiblioteca implements SistemaInterfaceBiblioteca {
    private HashMap<String, Livro> livros = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public boolean cadastrarLivro(String titulo, String autor, String codLivro) throws LivroJaExisteException {
        if (!this.livros.containsKey(codLivro)) {
            Livro novoLivro = new Livro(titulo, autor, codLivro);
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

    public Livro buscarLivroPorTitulo(String titulo) {
        Livro livroBuscado = new Livro();
        for (Livro livro: this.livros.values()) {
            if (livro.getTitulo().equals(titulo)) {
                livroBuscado = livro;
            }
        }
        return livroBuscado;
    }

    public boolean removerLivro(String codLivro) {
        if (this.livros.containsKey(codLivro)) {
            this.livros.remove(codLivro);
            return true;
        } return false;
    }

    public void realizarEmprestimo(String matricula, String tituloLivro, String dataEmprestimo, String dataDevolucao) {
        Emprestimo emprestimo = new Emprestimo(matricula, tituloLivro, dataEmprestimo, dataDevolucao);
        this.emprestimos.add(emprestimo);
    }
}

