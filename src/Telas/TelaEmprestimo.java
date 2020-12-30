package Telas;

import DAO.EmprestimoDAO;
import model.Emprestimo;

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

    public TelaEmprestimo() {
        add(emprestimo);
        setSize(500, 400);
        setTitle("Meus Emprestimos - UFSM ");

        voltarButton.addActionListener(e -> dispose());
    }

    public void atualizaTabela(){
        EmprestimoDAO ed = new EmprestimoDAO();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Emprestimo");
        modelo.addColumn("ID");
        modelo.addColumn("Retirado");
        modelo.addColumn("Devolucao");

        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
       // emprestimos = ed.readAll(usuariologado.getLogin());
        if(emprestimos.isEmpty()){
            modelo.addRow(new String[]{"-","-","-","-"});
        }else{
            for (Emprestimo emprestimo : emprestimos) {
                modelo.addRow(new String[]{""+emprestimo.getIDEmprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
            }
        }
        table3.setModel(modelo);
    }
}
