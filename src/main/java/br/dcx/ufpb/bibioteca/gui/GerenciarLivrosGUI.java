package br.dcx.ufpb.bibioteca.gui;

import br.dcx.ufpb.bibioteca.Controllers.BiblioBuscarLivroPorTituloController;
import br.dcx.ufpb.bibioteca.Controllers.BiblioBuscarLivrosPorGeneroController;
import br.dcx.ufpb.bibioteca.Controllers.BiblioCadLivroController;
import br.dcx.ufpb.bibioteca.Controllers.BiblioRemoveLivroController;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.swing.*;
import java.awt.*;

public class GerenciarLivrosGUI extends JFrame {
    JLabel linha1;
    SistemaBiblioteca sistema = new SistemaBiblioteca();
    ImageIcon iconeCadastrarLivro = new ImageIcon("./imgs/adicionarLivro.png");
    ImageIcon iconePesqLivroPorTitulo = new ImageIcon("./imgs/pesquisarLivro.png");
    ImageIcon iconePesqLivroPorGenero = new ImageIcon("./imgs/pesquisarLivro.png");
    ImageIcon iconeRemoveLivro = new ImageIcon("./imgs/removerLivro.png");

    public GerenciarLivrosGUI() {
        sistema.lerDados();
        setTitle("Sistema Biblioteca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(150, 150);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iconeCadastrarLivro = new ImageIcon(iconeCadastrarLivro.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        iconePesqLivroPorTitulo = new ImageIcon(iconePesqLivroPorTitulo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        iconePesqLivroPorGenero = new ImageIcon(iconePesqLivroPorGenero.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        iconeRemoveLivro = new ImageIcon(iconeRemoveLivro.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

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

        linha1 = new JLabel("GERENCIAMENTO DE LIVROS: ", JLabel.CENTER);
        linha1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        painelFundo.add(linha1, BorderLayout.NORTH);

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setBackground(new Color(254, 239, 159));
        painelDeBotoes.setLayout(new FlowLayout());

        JButton botaoCadastrarLivros = new JButton("Cadastrar Livro", iconeCadastrarLivro);
        botaoCadastrarLivros.setPreferredSize(new Dimension(280, 60));

        botaoCadastrarLivros.addActionListener(new BiblioCadLivroController(this.sistema, this));

        JButton botaoPesqLivrosPorTitulo = new JButton("Pesq. Livro por Título", iconePesqLivroPorTitulo);
        botaoPesqLivrosPorTitulo.setPreferredSize(new Dimension(280, 60));

        botaoPesqLivrosPorTitulo.addActionListener(new BiblioBuscarLivroPorTituloController(this.sistema, this));

        JButton botaoPesqLivrosPorGenero = new JButton("Pesq. Livros por Gênero", iconePesqLivroPorGenero);
        botaoPesqLivrosPorGenero.setPreferredSize(new Dimension(280, 60));

        botaoPesqLivrosPorGenero.addActionListener(new BiblioBuscarLivrosPorGeneroController(this.sistema, this));

        JButton botaoRemoverLivro = new JButton("Remover Livro", iconeRemoveLivro);
        botaoRemoverLivro.setPreferredSize(new Dimension(280, 60));

        botaoRemoverLivro.addActionListener(new BiblioRemoveLivroController(this.sistema, this));

        painelDeBotoes.add(botaoCadastrarLivros);
        painelDeBotoes.add(botaoPesqLivrosPorTitulo);
        painelDeBotoes.add(botaoPesqLivrosPorGenero);
        painelDeBotoes.add(botaoRemoverLivro);
        painelFundo.add(painelDeBotoes, BorderLayout.CENTER);
    }
}
