package br.dcx.ufpb.bibioteca.Controllers;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.MesInformadoNaoExisteException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showInputDialog;


public class BiblioBuscarEmpMesController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioBuscarEmpMesController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int mes = Integer.parseInt(showInputDialog(janelaPrincipal, "Informe o mês que deseja buscar os empréstimos: [1-12]"));
            try {
                JOptionPane.showMessageDialog(janelaPrincipal, sistema.buscarEmprestimosRealizadosNoMes(mes));
            } catch (MesInformadoNaoExisteException ex) {
                JOptionPane.showMessageDialog(janelaPrincipal, "O mês informado é inválido.");

            }
        }
    }
