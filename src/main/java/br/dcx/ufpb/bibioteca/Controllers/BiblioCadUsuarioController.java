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
        try {
            if(sistema.cadastrarUsuario(nome, matricula, email)){
                sistema.gravardados();
                JOptionPane.showMessageDialog(janelaPrincipal, "Usuário cadastrado no sistema.");
            };
        } catch (UsuarioJaExisteException ex) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Ocorreu um erro ao tentar cadastrar.");
        }
    }
}
