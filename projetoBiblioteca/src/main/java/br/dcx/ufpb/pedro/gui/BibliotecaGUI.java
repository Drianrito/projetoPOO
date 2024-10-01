package br.dcx.ufpb.pedro.gui;

import br.dcx.ufpb.pedro.BibliotecaFacade;
import br.dcx.ufpb.pedro.Livro;

import javax.swing.*;
import java.awt.*;

public class BibliotecaGUI extends JFrame {

    JLabel linha1, linha2;
    ImageIcon biblioteca = new ImageIcon("./imagens/biblioteca.jpg");
    BibliotecaFacade sistemaBibliotecaFacade = new BibliotecaFacade();
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

        JMenuItem menuCadastraLivro = new JMenuItem("Cadastrar Livro");
        menuCadastraLivro.addActionListener(
                (ae) -> {
                    String tituloLivro = JOptionPane.showInputDialog(this, "Qual o título do livro?");
                    String autor = JOptionPane.showInputDialog(this, "Qual o nome do autor?");
                    String codLivro = JOptionPane.showInputDialog(this, "Qual o código do livro?");
                    boolean cadastrou = sistemaBibliotecaFacade.cadastrarLivro(tituloLivro, autor, codLivro);
                    if (cadastrou) {
                        JOptionPane.showMessageDialog(this, "Livro cadastrado no sistema.");
                    } else {
                            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar cadastrar.");
                    }
                }
        );

        JMenuItem menuCadastraUsuario = new JMenuItem("Cadastrar Usuario");
        menuCadastraUsuario.addActionListener(
                (ae) -> {
                    String nome = JOptionPane.showInputDialog(this, "Qual o nome do usuário?");
                    String matricula = JOptionPane.showInputDialog(this, "Qual a matrícula do usuário?");
                    String email = JOptionPane.showInputDialog(this, "Qual o e-mail do usuário?");
                    boolean cadastrou = sistemaBibliotecaFacade.cadastrarUsuario(nome, matricula, email);
                    if (cadastrou) {
                        JOptionPane.showMessageDialog(this, "Usuário cadastrado no sistema.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar cadastrar.");
                    }
                }
        );

        JMenuItem menuBuscarLivroPorTitulo = new JMenuItem("Pesquisar Livro Por Título");
        menuBuscarLivroPorTitulo.addActionListener(
                (ae) -> {
                    String tituloBusca = JOptionPane.showInputDialog(this, "Informe o nome do titulo a pesquisar:");
                    Livro livroBusca = sistemaBibliotecaFacade.buscarLivroPorTitulo(tituloBusca);
                    if (livroBusca != null) {
                        JOptionPane.showMessageDialog(this, livroBusca.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum livro com esse título.");
                    }
                }
        );

        JMenuItem menuRemoverLivro = new JMenuItem("Remover Livro");
        menuRemoverLivro.addActionListener(
                (ae) -> {
                    String codLivro = JOptionPane.showInputDialog(this, "Informe o código do titulo a ser removido:");
                    boolean removido = sistemaBibliotecaFacade.removerLivro(codLivro);
                    if (removido) {
                        JOptionPane.showMessageDialog(this, "Livro removido com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Não foi encontrado nenhum livro com esse código.");
                    }
                }
        );

        JMenuItem menuRealizarEmprestimo = new JMenuItem("Realizar Empréstimo");
        menuRealizarEmprestimo.addActionListener(
                (ae) -> {
                    String matricula = JOptionPane.showInputDialog(this, "Qual a matrícula do usuário?");
                    String tituloLivro = JOptionPane.showInputDialog(this, "Qual o título do usuário?");
                    String dataEmprestimo = JOptionPane.showInputDialog(this, "Qual a data do empréstimo?");
                    String dataDevolucao = JOptionPane.showInputDialog(this, "Qual o nome a data de devolução?");
                    sistemaBibliotecaFacade.realizarEmprestimo(matricula, tituloLivro, dataEmprestimo, dataDevolucao);
                    JOptionPane.showMessageDialog(this, "Empréstimo realizado.");
                }
        );

        menuCadastramento.add(menuCadastraLivro);
        menuCadastramento.add(menuCadastraUsuario);
        menuGerenciamento.add(menuBuscarLivroPorTitulo);
        menuGerenciamento.add(menuRemoverLivro);
        menuGerenciamento.add(menuRealizarEmprestimo);
        barraDeMenu.add(menuCadastramento);
        barraDeMenu.add(menuGerenciamento);
        setJMenuBar(barraDeMenu);
    }

    public static void main(String[] args) {
        JFrame janela = new BibliotecaGUI();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
