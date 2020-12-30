package Telas;

import DAO.LivrosDAO;
import model.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditLivro extends javax.swing.JFrame {
    private JPanel addLivros;
    private JTextField isbn;
    private JTextField autor;
    private JTextField editora;
    private JTextField edicao;
    private JTextField ano;
    private JButton atualizarLivroButton;
    private JTextField nome;
    private JButton sairButton;
    private JPanel editLivro;
    private Livro l;

    public TelaEditLivro(TelaAdmin telaAdmin, int isbnSelecionado) {
        LivrosDAO ld = new LivrosDAO();
        l = ld.readbyISBN(isbnSelecionado);
        isbn.setText(String.valueOf(l.getISBN()));
        nome.setText(l.getNome());
        autor.setText(l.getAutor());
        edicao.setText(String.valueOf(l.getEdicao()));
        editora.setText(l.getEditora());
        ano.setText(String.valueOf(l.getAno()));

        add(editLivro);
        setTitle("Editando o livro " + l.getNome() + " - UFSM");
        setSize(400, 500);

        sairButton.addActionListener(e -> dispose());

        atualizarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivrosDAO ld = new LivrosDAO();
                Livro livro = new Livro();

                livro.setID(l.getID());
                livro.setISBN(Integer.parseInt(isbn.getText()));
                livro.setAutor(autor.getText());
                livro.setEdicao(Integer.parseInt(edicao.getText()));
                livro.setEditora(editora.getText());
                livro.setNome(nome.getText());
                livro.setAno(Integer.parseInt(ano.getText()));
                ld.update(livro);
                telaAdmin.atualizaTabelaLivros();
                JOptionPane.showMessageDialog(null, "Livro Atualizado!");
                dispose();
            }
        });
    }
}
