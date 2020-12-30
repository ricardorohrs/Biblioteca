package Telas;

import DAO.LivrosDAO;
import model.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public TelaUsuario() {
        add(acervo);
        setSize(750, 450);
        setTitle("Busca Acervo - UFSM ");

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                //modelo.addColumn("ID");
                //modelo.addColumn("ISBN");
                modelo.addColumn("Nome");
                modelo.addColumn("Autor");
                modelo.addColumn("Edição");
                //modelo.addColumn("Editora");
                modelo.addColumn("Ano");
                modelo.addColumn("Reservado");
                modelo.addColumn("Emprestado");
                LivrosDAO ld = new LivrosDAO();
                ArrayList<Livro> livros = ld.readbyParteNome(buscarPorNomeTextField.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table1.setModel(modelo);
            }
        });
        minhasReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaReservas telaReserva = new TelaReservas();
                //telaReserva.passaUser(usuariologado);
                telaReserva.atualizaTabela();
                telaReserva.setLocationRelativeTo(null);
                telaReserva.setVisible(true);
            }
        });
        meusEmprestimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaEmprestimo telaEmprestimo = new TelaEmprestimo();
                //telaReserva.passaUser(usuariologado);
                telaEmprestimo.atualizaTabela();
                telaEmprestimo.setLocationRelativeTo(null);
                telaEmprestimo.setVisible(true);
            }
        });

        pagarMultasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                UsersDAO ud = new UsersDAO();
//                Usuarios u = ud.read(usuariologado.getLogin());
//                if(u.getMulta() > 0) {
//                    JOptionPane.showMessageDialog(null, "VocÃª pagou R$"+u.getMulta()+" de multas!");
//                    ud.updateMultas(u, 0 - u.getMulta());
//                } else
//                    JOptionPane.showMessageDialog(null, "VocÃª nÃ£o possui multas!");
            }
        });

        sairButton.addActionListener(e -> dispose());

        buscarIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                //modelo.addColumn("ID");
                //modelo.addColumn("ISBN");
                modelo.addColumn("Nome");
                modelo.addColumn("Autor");
                modelo.addColumn("Edição");
                //modelo.addColumn("Editora");
                modelo.addColumn("Ano");
                modelo.addColumn("Reservado");
                modelo.addColumn("Emprestado");
                LivrosDAO ld = new LivrosDAO();
                ArrayList<Livro> livros = ld.readbyISBN(buscarPorIDTextField.getText());

                if (livros.isEmpty())
                    modelo.addRow(new String[]{"-","-","-","-","-","-"});
                else {
                    for (Livro livro : livros)
                        modelo.addRow(new String[]{""+livro.getNome(), "" + livro.getAutor(), "" + livro.getEdicao(), "" + livro.getAno(), "" + livro.getReservado(), "" + livro.getEmprestado()});
                }
                table1.setModel(modelo);
            }
        });

    }
}
