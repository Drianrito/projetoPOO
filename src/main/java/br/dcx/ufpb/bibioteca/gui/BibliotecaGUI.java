package br.dcx.ufpb.bibioteca.gui;

import br.dcx.ufpb.bibioteca.SistemaBiblioteca;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BibliotecaGUI extends JFrame {
    JLabel linha1;
    ImageIcon ilustracaoBibliotecaRS;
    SistemaBiblioteca sistema = new SistemaBiblioteca();
    BufferedImage imagemOriginal;

    public BibliotecaGUI() {
        sistema.lerDados();
        setTitle("Sistema Biblioteca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(150, 150);
        setResizable(true);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        String caminhoParaImagem = "./imgs/biblioteca.png";
        try {
            imagemOriginal = ImageIO.read(new File(caminhoParaImagem));
        } catch (IOException e) {
            System.err.println("Não foi possível carregar a imagem: " + e.getMessage());
            linha1 = new JLabel("Não foi possível carregar a imagem", JLabel.CENTER);
        }

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);

        linha1 = new JLabel("", JLabel.CENTER);
        painelCentral.add(linha1, BorderLayout.CENTER);
        getContentPane().add(painelCentral, BorderLayout.CENTER);

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setBackground(new Color(254, 239, 159));
        painelDeBotoes.setLayout(new FlowLayout());

        JButton botaoEmprestimos = new JButton("Gerenciar Empréstimos");
        JButton botaoLivros = new JButton("Gerenciar Livros");
        JButton botaoUsuarios = new JButton("Gerenciar Usuários");

        botaoEmprestimos.addActionListener(e -> {
            GerenciarEmprestimosGUI emprestimosGUI = new GerenciarEmprestimosGUI();
            dispose();
            emprestimosGUI.setVisible(true);
        });


        botaoLivros.addActionListener(e -> {
            GerenciarLivrosGUI livrosGUI = new GerenciarLivrosGUI();
            dispose();
            livrosGUI.setVisible(true);
        });

        botaoUsuarios.addActionListener(e -> {
            GerenciarUsuariosGUI usuariosGUI = new GerenciarUsuariosGUI();
            dispose();
            usuariosGUI.setVisible(true);
        });

        painelDeBotoes.add(botaoEmprestimos);
        painelDeBotoes.add(botaoLivros);
        painelDeBotoes.add(botaoUsuarios);

        getContentPane().add(painelDeBotoes, BorderLayout.NORTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redimensionarImagem();
            }
        });

        setVisible(true);
        validate();
        redimensionarImagem();
    }

    private void redimensionarImagem() {
        if (imagemOriginal != null) {
            int largura = getWidth();
            int altura = getHeight();

            if (largura > 0 && altura > 0) {
                Image imagemRedimensionada = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
                ilustracaoBibliotecaRS = new ImageIcon(imagemRedimensionada);
                linha1.setIcon(ilustracaoBibliotecaRS);
            }
        }
    }
}
