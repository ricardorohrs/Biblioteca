package Telas;

import DAO.LivrosDAO;
import DAO.ReservasDAO;
import DAO.UsersDAO;
import model.Livro;
import model.Reserva;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TelaUsuario extends javax.swing.JFrame {
    private JTextField buscarPorIDTextField;
    private JButton buscarButton;
    private JButton reservarButton;
    private JButton alugarButton;
    private JTextField buscarPorNomeTextField;
    private JButton pagarMultasButton;
    private JButton minhasReservasButton;
    private JPanel acervo;
    private JTable table1;
    private JButton meusEmprestimos;
    private JButton sairButton;
    private JButton buscarIdButton;
    private JTextField testeUser;
    private Usuario usuariologado;

    public void passaUser (Usuario user){
        usuariologado = user;
        testeUser.setText(String.valueOf(usuariologado.getLogin()));
    }

    public TelaUsuario(Usuario user) {
        add(acervo);
        setSize(800, 450);
        setTitle("Busca Acervo - " + user.getNome() + " - UFSM ");

        buscarButton.addActionListener(e -> {
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
            ArrayList<Livro> livros = ld.readbyParteNome(buscarPorNomeTextField.getText());

            if (livros.isEmpty())
                modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
            else {
                for (Livro livro : livros)
                    modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
            }
            table1.setModel(modelo);
        });

        minhasReservasButton.addActionListener(e -> {
            UsersDAO ud = new UsersDAO();
            TelaReservas telaReserva = new TelaReservas(this, user);
            telaReserva.passaUser(usuariologado);
            telaReserva.atualizaTabela();
            telaReserva.setLocationRelativeTo(null);
            telaReserva.setVisible(true);
            telaReserva.setResizable(false);
        });

        meusEmprestimos.addActionListener(e -> {
            TelaEmprestimo telaEmprestimo = new TelaEmprestimo(user);
            telaEmprestimo.passaUser(usuariologado);
            telaEmprestimo.atualizaTabela();
            telaEmprestimo.setLocationRelativeTo(null);
            telaEmprestimo.setVisible(true);
            telaEmprestimo.setResizable(false);
        });

        sairButton.addActionListener(e -> dispose());

        buscarIdButton.addActionListener(e -> {
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
            ArrayList<Livro> livros = ld.readbyISBN(buscarPorIDTextField.getText());

            if (livros.isEmpty())
                modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
            else {
                for (Livro livro : livros)
                    modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
            }
            table1.setModel(modelo);
        });

        reservarButton.addActionListener(e -> {
            ReservasDAO rd = new ReservasDAO();
            LivrosDAO ld = new LivrosDAO();
            UsersDAO ud = new UsersDAO();
            Reserva reserva = new Reserva();
            Livro livro = new Livro();
            livro.setID(Integer.parseInt(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0))));
            int reservado = ld.checaReserva(livro.getID());

            if (reservado == 1)
                JOptionPane.showMessageDialog(null,"Este livro está reservado no momento!");
            else {
                reserva.setID_livro(livro.getID());
                reserva.setLogin(testeUser.getText());
                reserva.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                rd.create(reserva);
                ud.addReservas(usuariologado);
                ld.marcarReserva(livro.getID(), 1);
                JOptionPane.showMessageDialog(null,"Reserva feita!");
                atualizaTabela();
                System.out.println(usuariologado.getReservas());
            }
        });
    }

    public void atualizaTabela (){
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
        ArrayList<Livro> livros = ld.listarLivros();

        if (livros.isEmpty())
            modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
        else {
            for (Livro livro : livros)
                modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
        }
        table1.setModel(modelo);
    }
}