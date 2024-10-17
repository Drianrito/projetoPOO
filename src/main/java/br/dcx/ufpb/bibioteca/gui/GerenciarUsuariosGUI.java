package br.dcx.ufpb.bibioteca.gui;

import br.dcx.ufpb.bibioteca.Controllers.BiblioCadUsuarioController;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.swing.*;
import java.awt.*;

public class GerenciarUsuariosGUI extends JFrame {
    JLabel linha1;
    SistemaBiblioteca sistema = new SistemaBiblioteca();
    ImageIcon iconeCadastrarUsuario = new ImageIcon("./imgs/adicionarUsuario.png");

    public GerenciarUsuariosGUI() {
        sistema.lerDados();
        setTitle("Sistema Biblioteca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(150, 150);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iconeCadastrarUsuario = new ImageIcon(iconeCadastrarUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

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

        linha1 = new JLabel("GERENCIAMENTO DE USUÁRIOS: ", JLabel.CENTER);
        linha1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        painelFundo.add(linha1, BorderLayout.NORTH);

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setBackground(new Color(254, 239, 159));
        painelDeBotoes.setLayout(new FlowLayout());

        JButton botaoCadastrarUsuarios = new JButton("Cadastrar Usuário", iconeCadastrarUsuario);
        botaoCadastrarUsuarios.setPreferredSize(new Dimension(280, 60));

        botaoCadastrarUsuarios.addActionListener(new BiblioCadUsuarioController(this.sistema, this));

        painelDeBotoes.add(botaoCadastrarUsuarios);
        painelFundo.add(painelDeBotoes, BorderLayout.CENTER);
    }
}
