package br.dcx.ufpb.bibioteca.Controllers;
import br.dcx.ufpb.bibioteca.PeriodoEmprestimo;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class BiblioRealizaEmpController implements ActionListener {
    private SistemaBiblioteca sistema;
    private JFrame janelaPrincipal;

    public BiblioRealizaEmpController(SistemaBiblioteca sistema, JFrame janela) {
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String matricula = JOptionPane.showInputDialog(janelaPrincipal, "Qual a matrícula do usuário?");
        String tituloLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o título do usuário?");
        LocalDate dataEmprestimo = LocalDate.parse(JOptionPane.showInputDialog(janelaPrincipal, "Qual a data do empréstimo?"));
        LocalDate dataDevolucao = LocalDate.parse(JOptionPane.showInputDialog(janelaPrincipal, "Qual  a data de devolução?"));
        PeriodoEmprestimo periodo = new PeriodoEmprestimo(dataEmprestimo, dataDevolucao);
        sistema.realizarEmprestimo(matricula,tituloLivro,periodo);
        JOptionPane.showMessageDialog(janelaPrincipal, "Empréstimo realizado.");
        //TODO: try n catch, caso coloque exception
    }
}