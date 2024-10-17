package br.dcx.ufpb.bibioteca.gui;

import br.dcx.ufpb.bibioteca.Controllers.BiblioBuscarEmpMatController;
import br.dcx.ufpb.bibioteca.Controllers.BiblioBuscarEmpMesController;
import br.dcx.ufpb.bibioteca.Controllers.BiblioRealizaEmpController;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.swing.*;
import java.awt.*;

public class GerenciarEmprestimosGUI extends JFrame {
    JLabel linha1;
    SistemaBiblioteca sistema = new SistemaBiblioteca();
    ImageIcon iconeRealizarEmprestimo = new ImageIcon("./imgs/realizarEmprestimo.png");
    ImageIcon iconePesqEmpMatricula = new ImageIcon("./imgs/pesquisarEmprestimo.png");
    ImageIcon iconePesqEmpNoMes = new ImageIcon("./imgs/pesquisarEmprestimo.png");

    public GerenciarEmprestimosGUI() {
        setTitle("Sistema Biblioteca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(150, 150);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iconeRealizarEmprestimo = new ImageIcon(iconeRealizarEmprestimo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        iconePesqEmpMatricula = new ImageIcon(iconePesqEmpMatricula.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        iconePesqEmpNoMes = new ImageIcon(iconePesqEmpNoMes.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(new Color(254, 239, 159));
        painelFundo.setLayout(new BorderLayout());

        getContentPane().add(painelFundo);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpcoes = new JMenu("Opções");
        JMenuItem retornarMenu = new JMenuItem("Retornar para o MENU");

        retornarMenu.addActionListener(e -> {
            BibliotecaGUI bibliotecaGUI = new BibliotecaGUI();
            dispose();
            bibliotecaGUI.setVisible(true);
        });

        menuOpcoes.add(retornarMenu);
        menuBar.add(menuOpcoes);
        setJMenuBar(menuBar);

        linha1 = new JLabel("GERENCIAMENTO DE EMPRÉSTIMOS: ", JLabel.CENTER);
        linha1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        painelFundo.add(linha1, BorderLayout.NORTH);

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setBackground(new Color(254, 239, 159));
        painelDeBotoes.setLayout(new FlowLayout());

        JButton botaoRealizarEmprestimo = new JButton("Realizar Empréstimo", iconeRealizarEmprestimo);
        botaoRealizarEmprestimo.setPreferredSize(new Dimension(280, 60));

        botaoRealizarEmprestimo.addActionListener(new BiblioRealizaEmpController(this.sistema, this));

        JButton botaoEmpPorMatricula = new JButton("Pesq. Empréstimos por Mátricula", iconePesqEmpMatricula);
        botaoEmpPorMatricula.setPreferredSize(new Dimension(280, 60));

        botaoEmpPorMatricula.addActionListener(new BiblioBuscarEmpMatController(this.sistema, this));

        JButton botaoEmpNoMes = new JButton("Pesq. Empréstimos no Mês", iconePesqEmpNoMes);
        botaoEmpNoMes.setPreferredSize(new Dimension(280, 60));

        botaoEmpNoMes.addActionListener(new BiblioBuscarEmpMesController(this.sistema, this));

        painelDeBotoes.add(botaoRealizarEmprestimo);
        painelDeBotoes.add(botaoEmpPorMatricula);
        painelDeBotoes.add(botaoEmpNoMes);
        painelFundo.add(painelDeBotoes, BorderLayout.CENTER);
    }
}
