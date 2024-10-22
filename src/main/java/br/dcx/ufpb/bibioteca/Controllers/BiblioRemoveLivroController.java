package br.dcx.ufpb.bibioteca.Controllers;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroNaoExisteException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BiblioRemoveLivroController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioRemoveLivroController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            String codigo = JOptionPane.showInputDialog(janelaPrincipal, "Qual a código do livro?");
            try{
                sistema.removerLivro(codigo);
                sistema.gravardados();
            } catch (LivroNaoExisteException ex){
                JOptionPane.showMessageDialog(janelaPrincipal,"Livro não cadastrado ano sistema.");
            }

    }
}
