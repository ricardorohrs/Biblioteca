package Telas;

import DAO.ReservasDAO;
import model.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TelaReservas extends javax.swing.JFrame {
    private JTable table2;
    private JButton cancelarReservaButton;
    private JButton voltarButton;
    private JPanel reservas;

    public TelaReservas() {
        add(reservas);
        setSize(550, 400);
        setTitle("Minhas Reservas - UFSM ");

        voltarButton.addActionListener(e -> dispose());
    }

    public void atualizaTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Reserva");
        modelo.addColumn("ID livro");

        ArrayList<Reserva> reservas = new ArrayList<>();
        ReservasDAO rd = new ReservasDAO();
        //reservas = rd.readAll(usuariologado);

        if (reservas.isEmpty())
            modelo.addRow(new String[]{"-", "-"});
        else {
            for (Reserva reserva : reservas)
                modelo.addRow(new String[]{"" + reserva.getID_reserva(), "" + reserva.getID_livro()});
        }
        table2.setModel(modelo);
    }
}

