package Telas;

import DAO.LivrosDAO;
import DAO.UsersDAO;
import model.Livro;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaAdmin extends javax.swing.JFrame {
    private JButton excluirLivroButton;
    private JButton adicionarLivroButton;
    private JButton editarLivroButton;
    private JButton adicionarUsuárioButton;
    private JButton editarUsuárioButton;
    private JButton excluirUsuárioButton;
    private JTable table4;
    private JTable table5;
    private JPanel admin;
    private JButton sairButton;
    private JButton buscarLivroButton;
    private JButton buscarUsuarioButton;
    private JTextField textFieldNomeLivro;
    private JTextField textFieldNomeUser;
    private JButton buscarLoginButton;
    private JButton buscarISBNButton;
    private JButton buscarEditoraButton;
    private JButton verEmpréstimosButton;

    public TelaAdmin() {
        add(admin);
        setTitle("Administrador - UFSM");
        setSize(700, 700);

        atualizaTabelaLivros();
        atualizaTabelaUsuarios();

        sairButton.addActionListener(e -> dispose());
        verEmpréstimosButton.addActionListener(e -> abreTelaVerEmprestimo());
        adicionarLivroButton.addActionListener(e -> abreTelaAdicionaLivro());
        adicionarUsuárioButton.addActionListener(e -> abreTelaAdicionaUsuario());
        excluirUsuárioButton.addActionListener(e -> excluiUsuario());
        excluirLivroButton.addActionListener(e -> excluiLivros());
        editarLivroButton.addActionListener(e -> abreTelaEditaLivros());
        editarUsuárioButton.addActionListener(e -> abreTelaEditaUsuario());

        buscarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel listaUsers = new DefaultTableModel();
                listaUsers.addColumn("login");
                listaUsers.addColumn("nome");
                listaUsers.addColumn("cargo");
                listaUsers.addColumn("reservas");
                listaUsers.addColumn("emprestimo");
                listaUsers.addColumn("multa");
                UsersDAO ud = new UsersDAO();
                ArrayList<Usuario> usuarios = ud.readParteNome(textFieldNomeUser.getText());

                if (usuarios.isEmpty())
                    listaUsers.addRow(new String[]{"-", "-", "-", "-", "-", "-"});
                else {
                    for (Usuario users: usuarios) {
                        listaUsers.addRow(new String[]{""+users.getLogin(),""+users.getNome(),""+users.getCargo(),""+users.getReservas(),""+users.getEmprestimos(),""+users.getMulta()});
                    }
                }
                table5.setModel(listaUsers);
            }
        });

        buscarLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel listaUsers = new DefaultTableModel();
                listaUsers.addColumn("login");
                listaUsers.addColumn("nome");
                listaUsers.addColumn("cargo");
                listaUsers.addColumn("reservas");
                listaUsers.addColumn("emprestimo");
                listaUsers.addColumn("multa");
                UsersDAO ud = new UsersDAO();
                ArrayList<Usuario> usuarios = ud.readLogin(textFieldNomeUser.getText());

                if (usuarios.isEmpty())
                    listaUsers.addRow(new String[]{"-", "-", "-", "-", "-", "-"});
                else {
                    for (Usuario users: usuarios) {
                        listaUsers.addRow(new String[]{""+users.getLogin(),""+users.getNome(),""+users.getCargo(),""+users.getReservas(),""+users.getEmprestimos(),""+users.getMulta()});
                    }
                }
                table5.setModel(listaUsers);
            }
        });

        buscarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("ISBN");
                modelo.addColumn("Nome");
                modelo.addColumn("Autor");
                modelo.addColumn("Edição");
                modelo.addColumn("Editora");
                modelo.addColumn("Ano");
                modelo.addColumn("Reservado");
                modelo.addColumn("Emprestado");
                LivrosDAO ld = new LivrosDAO();
                ArrayList<Livro> livros = ld.buscarLivro(textFieldNomeLivro.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table4.setModel(modelo);
            }
        });

        buscarISBNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("ISBN");
                modelo.addColumn("Nome");
                modelo.addColumn("Autor");
                modelo.addColumn("Edição");
                modelo.addColumn("Editora");
                modelo.addColumn("Ano");
                modelo.addColumn("Reservado");
                modelo.addColumn("Emprestado");
                LivrosDAO ld = new LivrosDAO();
                ArrayList<Livro> livros = ld.readbyISBN(textFieldNomeLivro.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table4.setModel(modelo);
            }
        });

        buscarEditoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("ISBN");
                modelo.addColumn("Nome");
                modelo.addColumn("Autor");
                modelo.addColumn("Edição");
                modelo.addColumn("Editora");
                modelo.addColumn("Ano");
                modelo.addColumn("Reservado");
                modelo.addColumn("Emprestado");
                LivrosDAO ld = new LivrosDAO();
                ArrayList<Livro> livros = ld.readbyEditora(textFieldNomeLivro.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table4.setModel(modelo);
            }
        });
    }

    public void atualizaTabelaUsuarios() {
        DefaultTableModel listaUsers = new DefaultTableModel();
        listaUsers.addColumn("login");
        listaUsers.addColumn("nome");
        listaUsers.addColumn("cargo");
        listaUsers.addColumn("reservas");
        listaUsers.addColumn("emprestimo");
        listaUsers.addColumn("multa");
        ArrayList<Usuario> usuarios;
        UsersDAO ud = new UsersDAO();
        usuarios = ud.listarUsers();

        if (usuarios.isEmpty())
            listaUsers.addRow(new String[]{"-", "-", "-", "-", "-", "-"});
        else {
            for (Usuario users: usuarios) {
                listaUsers.addRow(new String[]{""+users.getLogin(),""+users.getNome(),""+users.getCargo(),""+users.getReservas(),""+users.getEmprestimos(),""+users.getMulta()});
            }
        }
        table5.setModel(listaUsers);
    }

    public void atualizaTabelaLivros() {
        DefaultTableModel listaLivros = new DefaultTableModel();
        listaLivros.addColumn("ID");
        listaLivros.addColumn("ISBN");
        listaLivros.addColumn("Nome");
        listaLivros.addColumn("Autor");
        listaLivros.addColumn("Edição");
        listaLivros.addColumn("Editora");
        listaLivros.addColumn("Ano");
        listaLivros.addColumn("Reservado");
        listaLivros.addColumn("Emprestado");
        LivrosDAO ld = new LivrosDAO();
        ArrayList<Livro> livros = ld.listarLivros();

        if (livros.isEmpty())
            listaLivros.addRow(new String[]{"-","-","-","-","-","-","-","-"});
        else {
            for (Livro livro : livros)
                listaLivros.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(),""+livro.getNome(),""+livro.getAutor(),""+livro.getEdicao(),""+livro.getEditora(),""+livro.getAno(),""+livro.getReservado(),""+livro.getEmprestado()});
        }
        table4.setModel(listaLivros);
    }

    private void abreTelaAdicionaLivro() {
        TelaAddLivros addLivros = new TelaAddLivros(this);
        addLivros.setLocationRelativeTo(null);
        addLivros.setVisible(true);
        addLivros.setResizable(false);
    }

    private void abreTelaAdicionaUsuario() {
        TelaAddUsers addUsers = new TelaAddUsers(this);
        addUsers.setLocationRelativeTo(null);
        addUsers.setVisible(true);
        addUsers.setResizable(false);
    }

    private void excluiUsuario() {
        UsersDAO u = new UsersDAO();
        Usuario user = new Usuario();
        user.setLogin(String.valueOf(table5.getValueAt(table5.getSelectedRow(), 0)));;
        u.delete(user);
        this.atualizaTabelaUsuarios();
        JOptionPane.showMessageDialog(null, "Usuario "+ user.getLogin() + " deletado!");
    }

    private void excluiLivros() {
        LivrosDAO l = new LivrosDAO();
        Livro livro = new Livro();
        Livro nome = new Livro();
        livro.setID(Integer.parseInt(String.valueOf(table4.getValueAt(table4.getSelectedRow(), 0))));
        nome.setNome(String.valueOf(table4.getValueAt(table4.getSelectedRow(), 2)));
        l.delete(livro);
        this.atualizaTabelaLivros();
        JOptionPane.showMessageDialog(null,"Livro "+ nome.getNome() + " deletado!");
    }

    private void abreTelaEditaLivros() {
        int isbn = Integer.parseInt(String.valueOf(table4.getValueAt(table4.getSelectedRow(), 1)));
        TelaEditLivro editLivros = new TelaEditLivro(this, isbn);
        editLivros.setLocationRelativeTo(null);
        editLivros.setVisible(true);
        editLivros.setResizable(false);
    }

    private void abreTelaEditaUsuario() {
        String login = String.valueOf(table5.getValueAt(table5.getSelectedRow(), 0));
        TelaEditUser editUser = new TelaEditUser(this, login);
        editUser.setLocationRelativeTo(null);
        editUser.setVisible(true);
        editUser.setResizable(false);
    }

    private void abreTelaVerEmprestimo() {
        TelaVerEmprestimo verEmprestimo = new TelaVerEmprestimo();
        verEmprestimo.setLocationRelativeTo(null);
        verEmprestimo.atualizaTabela();
        verEmprestimo.setVisible(true);
        verEmprestimo.setResizable(false);
    }
}
