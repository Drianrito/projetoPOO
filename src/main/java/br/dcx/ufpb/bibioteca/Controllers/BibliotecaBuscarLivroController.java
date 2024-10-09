package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.Livro;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class BibliotecaBuscarLivroController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BibliotecaBuscarLivroController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tituloBusca = JOptionPane.showInputDialog(janelaPrincipal, "Informe o nome do titulo a pesquisar:");
        Collection<Livro> livroBusca = sistema.buscarLivroPorTitulo(tituloBusca);
        if (livroBusca != null) {
            JOptionPane.showMessageDialog(janelaPrincipal, livroBusca.toString());
        } else {
            JOptionPane.showMessageDialog(janelaPrincipal, "Não foi encontrado nenhum livro com esse título.");
        }
    }
}