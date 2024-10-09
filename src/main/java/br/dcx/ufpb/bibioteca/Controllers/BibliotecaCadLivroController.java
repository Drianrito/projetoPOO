package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.GeneroLivro;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BibliotecaCadLivroController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BibliotecaCadLivroController(SistemaBiblioteca sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tituloLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o título do livro?");
        String genero =(JOptionPane.showInputDialog(janelaPrincipal, "Qual o título do livro?"));
        String autor = JOptionPane.showInputDialog(janelaPrincipal, "Qual o nome do autor?");
        String codLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o código do livro?");
        boolean cadastrou = false;
        try {
            cadastrou = sistema.cadastrarLivro(tituloLivro, GeneroLivro.valueOf(genero), autor, codLivro);
        } catch (LivroJaExisteException ex) {
            throw new RuntimeException(ex);
        }
        if (cadastrou) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Livro cadastrado no sistema.");
        } else {
            JOptionPane.showMessageDialog(janelaPrincipal, "Ocorreu um erro ao tentar cadastrar.");
        }
    }
}
