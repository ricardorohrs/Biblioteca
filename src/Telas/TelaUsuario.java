package Telas;

import DAO.EmprestimoDAO;
import DAO.LivrosDAO;
import DAO.ReservasDAO;
import DAO.UsersDAO;
import model.Emprestimo;
import model.Livro;
import model.Reserva;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private JTextField multaField;
    private JTextField buscarEditoraTextField;
    private JButton buscarEditoraButton;
    private Usuario usuariologado;

    public void passaUser (Usuario user){
        usuariologado = user;
        testeUser.setText(String.valueOf(usuariologado.getLogin()));
        multaField.setText(String.valueOf(usuariologado.getMulta()));
    }

    public TelaUsuario(Usuario user) {
        add(acervo);
        setSize(950, 450);
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
            TelaReservas telaReserva = new TelaReservas(this, user);
            telaReserva.passaUser(usuariologado);
            telaReserva.atualizaTabela();
            telaReserva.setLocationRelativeTo(null);
            telaReserva.setVisible(true);
            telaReserva.setResizable(false);
        });

        meusEmprestimos.addActionListener(e -> {
            TelaEmprestimo telaEmprestimo = new TelaEmprestimo(this, user);
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
                JOptionPane.showMessageDialog(null,"Este livro já está reservado no momento!");
            else if (user.getMulta() > 0)
                JOptionPane.showMessageDialog(null, "Reserva proibida: Você tem multas pendentes!");
            else {
                reserva.setID_livro(livro.getID());
                reserva.setLogin(testeUser.getText());
                reserva.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                rd.create(reserva);
                ud.addReservas(usuariologado);
                atualizaTabela();
                ld.marcarReserva(livro.getID(), 1);
                JOptionPane.showMessageDialog(null,"Reserva feita!");
                atualizaTabela();
            }
        });

        alugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmprestimoDAO ed = new EmprestimoDAO();
                Emprestimo emprestimo = new Emprestimo();
                LivrosDAO ld = new LivrosDAO();

                Livro livro = new Livro();
                livro.setID(Integer.parseInt(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0))));

                int emprestado = ld.checaEmprestimo(livro.getID());
                int reservado = ld.checaReserva(livro.getID());

                if (emprestado == 1)
                    JOptionPane.showMessageDialog(null,"Esse livro já foi emprestado!");
                else if (reservado == 1) {
                    Reserva reserva = new Reserva();
                    ReservasDAO rd = new ReservasDAO();
                    reserva = rd.readByIdLivro(livro.getID());
                    if(reserva.getLogin().equals(testeUser.getText())){
                        UsersDAO ud = new UsersDAO();
                        Usuario u = new Usuario();
                        u = ud.read(testeUser.getText());
                        if(u.getCargo() == 1){
                            if(u.getReservas() < 3){
                                emprestimo.setID_livro(livro.getID());
                                emprestimo.setLogin(testeUser.getText());
                                java.util.Date dataUtilAtual = new java.util.Date();
                                java.sql.Date dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                                emprestimo.setRetirado(dataSqlAtual);
                                dataUtilAtual.setDate(dataUtilAtual.getDate() + 7);
                                dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                                emprestimo.setDevolucao(dataSqlAtual);
                                emprestimo.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                                ed.create(emprestimo);
                                System.out.println(user.getReservas());
                                ud.deletaReservas(u);
                                System.out.println(usuariologado.getReservas());
                                ld.marcarReserva(emprestimo.getID_livro(), 0);
                                ud.marcarEmprestimoUser(u);
                                ld.marcarEmprestimo(livro.getID(), 1);
                                atualizaTabela();
                                JOptionPane.showMessageDialog(null, "Emprestimo Feito!");
                            } else
                                JOptionPane.showMessageDialog(null, "Limite de reservas ou empréstimos alcançado. Você precisa devolver um livro primeiro!");
                        } else if (u.getCargo() == 2) {
                            if (u.getReservas() < 5) {
                                emprestimo.setID_livro(livro.getID());
                                emprestimo.setLogin(testeUser.getText());
                                java.util.Date dataUtilAtual = new java.util.Date();
                                java.sql.Date dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                                emprestimo.setRetirado(dataSqlAtual);
                                dataUtilAtual.setDate(dataUtilAtual.getDate() + 15);
                                dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                                emprestimo.setDevolucao(dataSqlAtual);
                                emprestimo.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                                ed.create(emprestimo);
                                ld.marcarReserva(emprestimo.getID_livro(), 0);
                                ud.marcarEmprestimoUser(u);
                                ld.marcarEmprestimo(livro.getID(), 1);
                                atualizaTabela();
                                JOptionPane.showMessageDialog(null, "Emprestimo Feito!");
                            } else
                                JOptionPane.showMessageDialog(null, "Limite de reservas ou empréstimos alcançado. Você precisa devolver um livro primeiro!");
                        }
                    } else
                        JOptionPane.showMessageDialog(null,"Esse livro já está reservado!");
                }else{
                    UsersDAO ud = new UsersDAO();
                    Usuario u = new Usuario();
                    u = ud.read(testeUser.getText());
                    if(u.getCargo() == 1){
                        if(u.getReservas() < 3){
                            emprestimo.setID_livro(livro.getID());
                            emprestimo.setLogin(testeUser.getText());
                            java.util.Date dataUtilAtual = new java.util.Date();
                            java.sql.Date dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                            emprestimo.setRetirado(dataSqlAtual);
                            dataUtilAtual.setDate(dataUtilAtual.getDate() + 7);
                            dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                            emprestimo.setDevolucao(dataSqlAtual);
                            emprestimo.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                            ed.create(emprestimo);
                            ld.marcarReserva(emprestimo.getID_livro(), 0);
                            ud.marcarEmprestimoUser(u);
                            ld.marcarEmprestimo(livro.getID(), 1);
                            atualizaTabela();
                            JOptionPane.showMessageDialog(null,"Emprestimo Feito!");
                        } else
                            JOptionPane.showMessageDialog(null, "Limite de reservas ou empréstimos alcançado. Você precisa devolver um livro primeiro!");
                    } else if (u.getCargo() == 2) {
                        if (u.getReservas() < 5) {
                            emprestimo.setID_livro(livro.getID());
                            emprestimo.setLogin(testeUser.getText());
                            java.util.Date dataUtilAtual = new java.util.Date();
                            java.sql.Date dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                            emprestimo.setRetirado(dataSqlAtual);
                            dataUtilAtual.setDate(dataUtilAtual.getDate() + 15);
                            dataSqlAtual = new java.sql.Date(dataUtilAtual.getTime());
                            emprestimo.setDevolucao(dataSqlAtual);
                            emprestimo.setNome(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                            ed.create(emprestimo);
                            ud.marcarEmprestimoUser(u);
                            ld.marcarEmprestimo(livro.getID(), 1);
                            atualizaTabela();
                            JOptionPane.showMessageDialog(null,"Emprestimo Feito!");
                        } else
                            JOptionPane.showMessageDialog(null, "Limite de reservas ou empréstimos alcançado. Você precisa devolver um livro primeiro!");
                    }
                }
            }
        });

        pagarMultasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersDAO ud = new UsersDAO();
                Usuario u = ud.read(usuariologado.getLogin());
                if(u.getMulta() > 0){
                    JOptionPane.showMessageDialog(null, "VocÃª pagou R$"+u.getMulta()+" de multas!");
                    ud.updateMultas(u, 0 - u.getMulta());
                }else
                    JOptionPane.showMessageDialog(null, "Você não possui multas!");
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
                ArrayList<Livro> livros = ld.readbyEditora(buscarEditoraTextField.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getID(), ""+livro.getISBN(), ""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), ""+livro.getEditora(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table1.setModel(modelo);
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

    public void atualizaMultas(){
        ArrayList<Emprestimo> emprestimos;
        EmprestimoDAO ed = new EmprestimoDAO();
        emprestimos = ed.readAll(usuariologado);

        for(Emprestimo emprestimo: emprestimos){
            java.sql.Date dataDevolucao = emprestimo.getDevolucao();
            LocalDate atual = LocalDate.now();
            LocalDate devolucao = dataDevolucao.toLocalDate();
            long diferencaemdias = ChronoUnit.DAYS.between(devolucao,atual);
            if(diferencaemdias > 0){
                UsersDAO ud = new UsersDAO();
                ud.updateMultas(usuariologado, (int)diferencaemdias);
            }
        }
    }
}