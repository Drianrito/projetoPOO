package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.objects.GeneroLivro;
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
        try {
           if(sistema.cadastrarLivro(tituloLivro, genero, autor, codLivro)){
               sistema.gravardados();
               JOptionPane.showMessageDialog(janelaPrincipal, "Livro cadastrado no sistema.");
           }
        } catch (LivroJaExisteException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Ocorreu um erro ao tentar cadastrar.");
        }
    }
}
