package Telas;

import DAO.EmprestimoDAO;
import model.Emprestimo;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TelaEmprestimo extends javax.swing.JFrame {
    private JPanel reservas;
    private JTable table3;
    private JButton devolverButton;
    private JButton voltarButton;
    private JButton renovarButton;
    private JPanel emprestimo;
    private Usuario usuariologado;

    public void passaUser(Usuario user){
        usuariologado = user;
    }

    public TelaEmprestimo(Usuario user) {
        add(emprestimo);
        setSize(500, 400);
        setTitle("Meus Emprestimos - " + user.getNome() + " - UFSM");

        voltarButton.addActionListener(e -> dispose());
    }

    public void atualizaTabela(){
        EmprestimoDAO ed = new EmprestimoDAO();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Emprestimo");
        modelo.addColumn("ID Livro");
        modelo.addColumn("Nome");
        modelo.addColumn("Retirado");
        modelo.addColumn("Devolucao");

        ArrayList<Emprestimo> emprestimos = ed.readAll(usuariologado.getLogin());
        if (emprestimos.isEmpty())
            modelo.addRow(new String[]{"-","-","-","-"});
        else {
            for (Emprestimo emprestimo : emprestimos)
                modelo.addRow(new String[]{""+emprestimo.getIDEmprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
        }
        table3.setModel(modelo);
    }
}
