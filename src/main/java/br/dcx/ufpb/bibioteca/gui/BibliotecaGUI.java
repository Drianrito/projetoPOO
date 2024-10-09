package br.dcx.ufpb.bibioteca.gui;


import br.dcx.ufpb.bibioteca.Controllers.*;

import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BibliotecaGUI extends JFrame {

    JLabel linha1, linha2;
    ImageIcon biblioteca = new ImageIcon("./imagens/biblioteca.jpg");
    SistemaBiblioteca sistema = new SistemaBiblioteca();
    JMenuBar barraDeMenu = new JMenuBar();

    public BibliotecaGUI() {
        setTitle("Sistema Biblioteca");
        setSize(800, 900);
        setLocation(150, 150);
        setResizable(true);
        setBackground(Color.LIGHT_GRAY);
        linha1 = new JLabel("Biblioteca Acumulando Conhecimentos", JLabel.CENTER);
        linha1.setForeground(Color.red);
        linha1.setFont(new Font("Serif", Font.BOLD, 24));
        linha2 = new JLabel(biblioteca, JLabel.CENTER);
        setLayout(new GridLayout(3, 1));
        add(linha1);
        add(linha2);
        add(new JLabel());
        JMenu menuCadastrar = new JMenu("Cadastrar");
        JMenu menuBuscar = new JMenu("Buscar");
        JMenu menuRemover = new JMenu("Remover");
        JMenu menuEmprestimo = new JMenu("Empréstimo");

    JMenuItem menuCadLivro = new JMenuItem("Cadastrar Livro");
        menuCadastrar.add(menuCadLivro);

        JMenuItem menuCadUsuario =  new JMenuItem("Cadastrar Usuário");
       menuCadastrar.add(menuCadUsuario);

        JMenuItem menuBuscarLivroPorTitulo = new JMenuItem("Buscar Livro Por Título");
        menuBuscar.add(menuBuscarLivroPorTitulo);


        JMenuItem menuRemoverLivro = new JMenuItem("Remover Livro");
       menuRemover.add(menuRemoverLivro);

        JMenuItem menuRealizarEmprestimo = new JMenuItem("Realizar Empréstimo");
        menuEmprestimo.add(menuRealizarEmprestimo);

        JMenuItem menuBuscarLivroPorGenero = new JMenuItem("Buscar Livros Por Gênero");
        menuBuscar.add(menuBuscarLivroPorGenero);

        JMenuItem menuBuscarEmprestimoPorMes = new JMenuItem("Buscar Empréstimo Por Mês");
        menuEmprestimo.add(menuBuscarEmprestimoPorMes);


        menuCadLivro.addActionListener(new BiblioCadLivroController(sistema, this));
        menuCadUsuario.addActionListener(new BiblioCadUsuarioController(sistema, this));
        menuBuscarLivroPorTitulo.addActionListener(new BiblioBuscarLivroPorTituloController(sistema, this));
        menuRemoverLivro.addActionListener(new BiblioRemoveLivroController(sistema, this));
        menuRealizarEmprestimo.addActionListener(new BiblioRealizaEmpController(sistema, this));
        menuBuscarLivroPorGenero.addActionListener(new BiblioBuscarLivrosPorGeneroController(sistema, this));
        menuBuscarEmprestimoPorMes.addActionListener(new BiblioBuscarEmpMesController(sistema, this));
        barraDeMenu.add(menuEmprestimo);
        barraDeMenu.add(menuCadastrar);
        barraDeMenu.add(menuBuscar);
        barraDeMenu.add(menuRemover);
        setJMenuBar(barraDeMenu);
    }

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
