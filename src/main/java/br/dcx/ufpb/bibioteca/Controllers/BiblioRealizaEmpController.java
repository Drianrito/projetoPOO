package br.dcx.ufpb.bibioteca.Controllers;
import br.dcx.ufpb.bibioteca.PeriodoEmprestimo;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroNaoExisteException;
import br.dcx.ufpb.bibioteca.exception.MatriculaNaoEncontradaException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        String codigoLivro = JOptionPane.showInputDialog(janelaPrincipal, "Qual o código do livro?");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEmprestimo = LocalDate.parse(JOptionPane.showInputDialog(janelaPrincipal, "Qual a data do empréstimo? (dd/MM/yyyy)"), formatter);
        LocalDate dataDevolucao = LocalDate.parse(JOptionPane.showInputDialog(janelaPrincipal, "Qual a data de devolução? (dd/MM/yyyy)"), formatter);
        PeriodoEmprestimo periodo = new PeriodoEmprestimo(dataEmprestimo, dataDevolucao);
        try {
            if (sistema.realizarEmprestimo(matricula, codigoLivro, periodo)) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Empréstimo realizado.");
                sistema.gravardados();
            }
        } catch (LivroNaoExisteException | MatriculaNaoEncontradaException ex){
            JOptionPane.showMessageDialog(janelaPrincipal, "Não foi possível realizar o empréstimo.");
        }
    }

}