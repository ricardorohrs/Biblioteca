package Telas;

import DAO.LivrosDAO;
import DAO.UsersDAO;
import model.Livro;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public TelaAdmin() {
        add(admin);
        setTitle("Administrador - UFSM");
        setSize(550, 700);

        atualizaTabelaLivros();
        atualizaTabelaUsuarios();

        sairButton.addActionListener(e -> {
            dispose();
        });

        adicionarLivroButton.addActionListener(e -> abreTelaAdicionaLivro());
        adicionarUsuárioButton.addActionListener(e -> abreTelaAdicionaUsuario());
        excluirUsuárioButton.addActionListener(e -> excluiUsuario());
        excluirLivroButton.addActionListener(e -> excluiLivros());
        editarLivroButton.addActionListener(e -> abreTelaEditaLivros());
        editarUsuárioButton.addActionListener(e -> abreTelaEditaUsuario());

    }

    public void atualizaTabelaUsuarios() {
        DefaultTableModel listaUsers = new DefaultTableModel();
        listaUsers.addColumn("login");
        listaUsers.addColumn("nome");
        listaUsers.addColumn("cargo");
        listaUsers.addColumn("reservas");
        listaUsers.addColumn("multa");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        UsersDAO ud = new UsersDAO();
        usuarios = ud.listarUsers();

        if (usuarios.isEmpty())
            listaUsers.addRow(new String[]{"-", "-", "-", "-", "-"});
        else {
            for (Usuario users: usuarios) {
                listaUsers.addRow(new String[]{""+users.getLogin(),""+users.getNome(),""+users.getCargo(),""+users.getReservas(),""+users.getMulta(),""+users.getNome()});
            }
        }
        table5.setModel(listaUsers);
    }

    public void atualizaTabelaLivros() {
        DefaultTableModel listaLivros = new DefaultTableModel();
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
                listaLivros.addRow(new String[]{""+livro.getISBN(),""+livro.getNome(),""+livro.getAutor(),""+livro.getEdicao(),""+livro.getEditora(),""+livro.getAno(),""+livro.getReservado(),""+livro.getEmprestado()});
        }
        table4.setModel(listaLivros);
    }

    private void abreTelaAdicionaLivro() {
        TelaAddLivros addLivros = new TelaAddLivros(this);
        //addLivros.passaUser(usuariologado);
        addLivros.setLocationRelativeTo(null);
        addLivros.setVisible(true);
    }

    private void abreTelaAdicionaUsuario() {
        TelaAddUsers addUsers = new TelaAddUsers(this);
        //addUsers.passaUser(usuariologado);
        addUsers.setLocationRelativeTo(null);
        addUsers.setVisible(true);
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
        //addLivros.passaUser(usuariologado);
        editLivros.setLocationRelativeTo(null);
        editLivros.setVisible(true);
    }

    private void abreTelaEditaUsuario() {
        String login = String.valueOf(table5.getValueAt(table5.getSelectedRow(), 0));
        TelaEditUser editUser = new TelaEditUser(this, login);
        //addLivros.passaUser(usuariologado);
        editUser.setLocationRelativeTo(null);
        editUser.setVisible(true);
    }

}
