package br.dcx.ufpb.bibioteca;

import br.dcx.ufpb.bibioteca.gui.BibliotecaGUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BibliotecaPrograma {
    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        sistema.lerEmprestimos();
        JFrame janela = new BibliotecaGUI();
        janela.setVisible(true);
        WindowListener fechadorDeJanela = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sistema.gravarEmprestimos();
                System.exit(0);
            }
        };
        janela.addWindowListener(fechadorDeJanela);
    }
}
