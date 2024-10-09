package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BiblioBuscarEmpMatController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioBuscarEmpMatController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
