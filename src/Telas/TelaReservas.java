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

public class TelaReservas extends javax.swing.JFrame {
    private JTable table2;
    private JButton cancelarReservaButton;
    private JButton voltarButton;
    private JPanel reservas;
    private Usuario usuariologado;

    public void passaUser(Usuario user){
        usuariologado = user;
    }

    public TelaReservas(TelaUsuario telaUsuario, Usuario user) {
        add(reservas);
        setSize(550, 400);
        setTitle("Minhas Reservas - " + user.getNome() + " - UFSM ");

        voltarButton.addActionListener(e -> {
            dispose();
        });

        cancelarReservaButton.addActionListener(e -> {
            ReservasDAO rd = new ReservasDAO();
            UsersDAO ud = new UsersDAO();
            Reserva reserva = new Reserva();
            Livro livro = new Livro();
            livro.setID(Integer.parseInt(String.valueOf(table2.getValueAt(table2.getSelectedRow(), 0))));
            reserva = rd.read(livro.getID());

            if(reserva.getLogin() == null)
                JOptionPane.showMessageDialog(null,"ID invalido!");
            else {
                LivrosDAO ld = new LivrosDAO();
                ld.marcarReserva(reserva.getID_livro(), 0);
                rd.delete(reserva);
                ud.deletaReservas(usuariologado);
                JOptionPane.showMessageDialog(null,"Reserva cancelada!");
                atualizaTabela();
                telaUsuario.atualizaTabela();
            }
        });
    }

    public void atualizaTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Reserva");
        modelo.addColumn("ID livro");
        modelo.addColumn("Nome");

        ArrayList<Reserva> reservas;
        ReservasDAO rd = new ReservasDAO();
        reservas = rd.readAll(usuariologado);

        if (reservas.isEmpty())
            modelo.addRow(new String[]{"-", "-", "-"});
        else {
            for (Reserva reserva : reservas)
                modelo.addRow(new String[]{"" + reserva.getID_reserva(), "" + reserva.getID_livro(), "" + reserva.getNome()});
        }
        table2.setModel(modelo);
    }
}

