package br.dcx.ufpb.bibioteca.gui;


import br.dcx.ufpb.bibioteca.Controllers.*;
import br.dcx.ufpb.bibioteca.Emprestimo;

import br.dcx.ufpb.bibioteca.Livro;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;

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
        JMenu menuCadastramento = new JMenu("Sistema de Cadastramento");
        JMenu menuGerenciamento = new JMenu("Sistema de Gerenciamento");

    JMenuItem menuCadLivro = new JMenuItem("Cadastrar Livro");
        menuCadastramento.add(menuCadLivro);

        JMenuItem menuCadUsuario =  new JMenuItem("Cadastrar Usuário");
        menuCadastramento.add(menuCadUsuario);

        JMenuItem menuBuscarLivroPorTitulo = new JMenuItem("Pesquisar Livro Por Título");
        menuGerenciamento.add(menuBuscarLivroPorTitulo);


        JMenuItem menuRemoverLivro = new JMenuItem("Remover Livro");
       menuGerenciamento.add(menuRemoverLivro);

        JMenuItem menuRealizarEmprestimo = new JMenuItem("Realizar Empréstimo");
        menuGerenciamento.add(menuRealizarEmprestimo);

        menuCadLivro.addActionListener(new BibliotecaCadLivroController(sistema, this));
        menuCadUsuario.addActionListener(new BibliotecaCadUsuarioController(sistema, this));
        menuBuscarLivroPorTitulo.addActionListener(new BibliotecaBuscarLivroController(sistema, this));
        menuRemoverLivro.addActionListener(new BibliotecaRemoveLivroController(sistema, this));
        menuRealizarEmprestimo.addActionListener(new BibliotecaRealizaEmpController(sistema, this));
        barraDeMenu.add(menuCadastramento);
        barraDeMenu.add(menuGerenciamento);
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
