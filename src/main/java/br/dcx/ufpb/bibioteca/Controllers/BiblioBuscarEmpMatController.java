package br.dcx.ufpb.bibioteca.Controllers;

import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.MatriculaNaoEncontradaException;

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
        String matricula = JOptionPane.showInputDialog(janelaPrincipal, "Qual a matrícula do usuário?");
        try{
            JOptionPane.showMessageDialog(janelaPrincipal,sistema.buscarEmprestimoPorMatricula(matricula));
        } catch (MatriculaNaoEncontradaException ex){
            JOptionPane.showMessageDialog(janelaPrincipal, "Matrícula não encontrada no sistema.");
        }
    }
}
