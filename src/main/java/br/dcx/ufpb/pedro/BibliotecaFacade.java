package br.dcx.ufpb.pedro;

import br.dcx.ufpb.pedro.exception.LivroJaExisteException;
import br.dcx.ufpb.pedro.exception.UsuarioJaExisteException;

import javax.swing.*;

public class BibliotecaFacade {

    private SistemaBiblioteca sistemaBiblioteca;
    private JFrame janelaPrincipal;

    public BibliotecaFacade() {
        this.sistemaBiblioteca = new SistemaBiblioteca();
        this.janelaPrincipal = janelaPrincipal;
    }

    public boolean cadastrarLivro(String titulo, String autor, String codLivro) {
            try {
                return sistemaBiblioteca.cadastrarLivro(titulo, autor, codLivro);
            } catch (LivroJaExisteException e) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Livro informado já está cadastrado.");
                return false;
            }
        }

    public boolean cadastrarUsuario(String nome, String matricula, String email) {
        try {
            return sistemaBiblioteca.cadastrarUsuario(nome, matricula, email);
        } catch (UsuarioJaExisteException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Usuário informado já está cadastrado.");
            return false;
        }
    }

    public void realizarEmprestimo(String matricula, String tituloLivro, String dataEmprestimo, String dataDevolucao) {
        sistemaBiblioteca.realizarEmprestimo(matricula, tituloLivro, dataEmprestimo, dataDevolucao);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return sistemaBiblioteca.buscarLivroPorTitulo(titulo);
    }

    public boolean removerLivro(String codLivro) {
        return sistemaBiblioteca.removerLivro(codLivro);
    }
}
