package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.GeneroLivro;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BiblioCadLivroController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioCadLivroController(SistemaBiblioteca sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tituloLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o título do livro?");
        GeneroLivro genero = (GeneroLivro) JOptionPane.showInputDialog(janelaPrincipal,"Selecione o gênero:","Genero", JOptionPane.QUESTION_MESSAGE, null, GeneroLivro.values(), GeneroLivro.values()[0]);
        String autor = JOptionPane.showInputDialog(janelaPrincipal, "Qual o nome do autor?");
        String codLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o código do livro?");
        boolean cadastrou = false;
        try {
            cadastrou = sistema.cadastrarLivro(tituloLivro, genero, autor, codLivro);
            sistema.gravardados();
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
