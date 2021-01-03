package Telas;

import DAO.EmprestimoDAO;
import model.Emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TelaVerEmprestimo extends javax.swing.JFrame {
    private JTable table3;
    private JButton buscarPorNome;
    private JButton voltarButton;
    private JButton buscarPorUsuárioButton;
    private JPanel verEmprestimo;
    private JTextField textField1;

    public TelaVerEmprestimo() {
        add(verEmprestimo);
        setSize(550, 400);
        setTitle("Emprestimos - Administrador - UFSM");

        voltarButton.addActionListener(e -> dispose());

        buscarPorNome.addActionListener(e -> {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID Emprestimo");
            modelo.addColumn("ID Livro");
            modelo.addColumn("Nome");
            modelo.addColumn("Usuário");
            modelo.addColumn("Retirado");
            modelo.addColumn("Devolucao");

            EmprestimoDAO ed = new EmprestimoDAO();
            ArrayList<Emprestimo> emprestimos = ed.buscarEmprestimoNome(textField1.getText());

            if (emprestimos.isEmpty())
                modelo.addRow(new String[]{"-","-","-","-","-","-"});
            else {
                for (Emprestimo emprestimo : emprestimos)
                    modelo.addRow(new String[]{""+emprestimo.getID_emprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getNome(),""+emprestimo.getLogin(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
            }
            table3.setModel(modelo);
        });

        buscarPorUsuárioButton.addActionListener(e -> {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID Emprestimo");
            modelo.addColumn("ID Livro");
            modelo.addColumn("Nome");
            modelo.addColumn("Usuário");
            modelo.addColumn("Retirado");
            modelo.addColumn("Devolucao");

            EmprestimoDAO ed = new EmprestimoDAO();
            ArrayList<Emprestimo> emprestimos = ed.buscarLogin(textField1.getText());

            if (emprestimos.isEmpty())
                modelo.addRow(new String[]{"-","-","-","-","-","-"});
            else {
                for (Emprestimo emprestimo : emprestimos)
                    modelo.addRow(new String[]{""+emprestimo.getID_emprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getNome(),""+emprestimo.getLogin(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
            }
            table3.setModel(modelo);
        });
    }

    public void atualizaTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Emprestimo");
        modelo.addColumn("ID Livro");
        modelo.addColumn("Nome");
        modelo.addColumn("Usuário");
        modelo.addColumn("Retirado");
        modelo.addColumn("Devolucao");

        ArrayList<Emprestimo> emprestimos;
        EmprestimoDAO ed = new EmprestimoDAO();
        emprestimos = ed.listarEmprestimos();
        if (emprestimos.isEmpty())
            modelo.addRow(new String[]{"-","-","-","-","-","-"});
        else {
            for (Emprestimo emprestimo : emprestimos)
                modelo.addRow(new String[]{""+emprestimo.getID_emprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getNome(),""+emprestimo.getLogin(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
        }
        table3.setModel(modelo);
    }
}
