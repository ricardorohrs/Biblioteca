package Telas;

import DAO.LivrosDAO;
import model.Livro;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAddLivros extends javax.swing.JFrame {
    private JTextField isbn;
    private JTextField autor;
    private JTextField editora;
    private JTextField edicao;
    private JTextField ano;
    private JButton adicionarLivroButton;
    private JTextField nome;
    private JPanel addLivros;
    private JButton sairButton;

    public TelaAddLivros(TelaAdmin telaAdmin) {
        add(addLivros);
        setTitle("Adicionar Livros - UFSM");
        setSize(325, 375);

        adicionarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivrosDAO l = new LivrosDAO();
                Livro livro = new Livro();

                livro.setISBN(Integer.parseInt(isbn.getText()));
                livro.setAutor(autor.getText());
                livro.setEdicao(Integer.parseInt(edicao.getText()));
                livro.setEditora(editora.getText());
                livro.setNome(nome.getText());
                livro.setAno(Integer.parseInt(ano.getText()));
                livro.setReservado(0);
                livro.setEmprestado(0);
                l.create(livro);
                telaAdmin.atualizaTabelaLivros();
                JOptionPane.showMessageDialog(null, "Livro Adicionado!");
                dispose();
            }
        });

        sairButton.addActionListener(e -> dispose());
    }

}
