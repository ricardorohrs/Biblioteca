package Telas;

import DAO.EmprestimoDAO;
import DAO.LivrosDAO;
import DAO.UsersDAO;
import model.Emprestimo;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public TelaEmprestimo(TelaUsuario telaUsuario, Usuario user) {
        add(emprestimo);
        setSize(500, 400);
        setTitle("Meus Emprestimos - " + user.getNome() + " - UFSM");

        voltarButton.addActionListener(e -> dispose());

        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmprestimoDAO ed = new EmprestimoDAO();
                Emprestimo emprestimo = new Emprestimo();

                emprestimo.setID_emprestimo(Integer.parseInt(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))));
                emprestimo = ed.readByID_emprest(emprestimo.getID_emprestimo());

                if(emprestimo.getLogin().equals("")){
                    JOptionPane.showMessageDialog(null,"ID invalido!");
                }else{
                    LivrosDAO ld = new LivrosDAO();
                    UsersDAO ud = new UsersDAO();
                    Usuario user = new Usuario();
                    user = ud.read(emprestimo.getLogin());
                    ld.marcarEmprestimo(emprestimo.getID_livro(), 0);
                    ud.desmarcarEmprestimoUser(user);
                    ed.delete(emprestimo);
                    JOptionPane.showMessageDialog(null,"Livro devolvido!");
                    atualizaTabela();
                    telaUsuario.atualizaTabela();
                }
            }
        });

        renovarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmprestimoDAO ed = new EmprestimoDAO();
                Emprestimo emprestimo = new Emprestimo();

                emprestimo.setID_emprestimo(Integer.parseInt(String.valueOf(table3.getValueAt(table3.getSelectedRow(), 0))));
                emprestimo = ed.readByID_emprest(emprestimo.getID_emprestimo());

                if(emprestimo.getLogin().equals("")){
                    JOptionPane.showMessageDialog(null, "Livro invalido!");
                } else {
                    if (user.getCargo() == 1) {
                        java.util.Date dataAtual = new java.util.Date();
                        java.sql.Date dataSqlAtual = new java.sql.Date(dataAtual.getTime());
                        dataSqlAtual.setDate(dataAtual.getDate() + 7);
                        emprestimo.setDevolucao(dataSqlAtual);
                        ed.updateDevolucao(emprestimo, emprestimo.getID_livro());
                        atualizaTabela();
                        JOptionPane.showMessageDialog(null, "Livro renovado para daqui a 7 dias!");
                    } else if (user.getCargo() == 2){
                        java.util.Date dataAtual = new java.util.Date();
                        java.sql.Date dataSqlAtual = new java.sql.Date(dataAtual.getTime());
                        dataSqlAtual.setDate(dataAtual.getDate() + 15);
                        emprestimo.setDevolucao(dataSqlAtual);
                        ed.updateDevolucao(emprestimo, emprestimo.getID_livro());
                        atualizaTabela();
                        JOptionPane.showMessageDialog(null, "Livro renovado para daqui a 15 dias!");
                    }
                }
            }
        });
    }

    public void atualizaTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Emprestimo");
        modelo.addColumn("ID Livro");
        modelo.addColumn("Nome");
        modelo.addColumn("Retirado");
        modelo.addColumn("Devolucao");

        ArrayList<Emprestimo> emprestimos;
        EmprestimoDAO ed = new EmprestimoDAO();
        emprestimos = ed.readAll(usuariologado);
        if (emprestimos.isEmpty())
            modelo.addRow(new String[]{"-","-","-","-","-"});
        else {
            for (Emprestimo emprestimo : emprestimos)
                modelo.addRow(new String[]{""+emprestimo.getID_emprestimo(),""+emprestimo.getID_livro(),""+emprestimo.getNome(),""+emprestimo.getRetirado(),""+emprestimo.getDevolucao()});
        }
        table3.setModel(modelo);
    }
}
