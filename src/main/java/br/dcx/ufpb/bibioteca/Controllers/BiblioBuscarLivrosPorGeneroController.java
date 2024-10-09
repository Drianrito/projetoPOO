package br.dcx.ufpb.bibioteca.Controllers;
import br.dcx.ufpb.bibioteca.GeneroLivro;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BiblioBuscarLivrosPorGeneroController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioBuscarLivrosPorGeneroController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GeneroLivro genero = (GeneroLivro) JOptionPane.showInputDialog(janelaPrincipal,"Selecione o gênero:","Genero", JOptionPane.QUESTION_MESSAGE, null, GeneroLivro.values(), GeneroLivro.values()[0]);
        if (genero != null) {
        JOptionPane.showMessageDialog(janelaPrincipal, sistema.buscarLivrosPorGenero(genero));
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal, "Não foi encontrado nenhum livro deste gênero cadastrado no sistema.");
        }
    }
}