package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BiblioCadUsuarioController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioCadUsuarioController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = JOptionPane.showInputDialog(janelaPrincipal, "Qual o nome do usuário?");
        String matricula = JOptionPane.showInputDialog(janelaPrincipal, "Qual a matrícula do usuário?");
        String email = JOptionPane.showInputDialog(janelaPrincipal, "Qual o e-mail do usuário?");
        boolean cadastrou = false;
        try {
            cadastrou = sistema.cadastrarUsuario(nome, matricula, email);
        } catch (UsuarioJaExisteException ex) {
            throw new RuntimeException(ex);
        }
        if (cadastrou) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Usuário cadastrado no sistema.");
        } else {
            JOptionPane.showMessageDialog(janelaPrincipal, "Ocorreu um erro ao tentar cadastrar.");
        }
    }
}
