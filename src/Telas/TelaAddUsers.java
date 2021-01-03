package Telas;

import DAO.UsersDAO;
import model.Usuario;

import javax.swing.*;

public class TelaAddUsers extends javax.swing.JFrame {
    private JPanel addUsers;
    private JTextField nome;
    private JTextField senha;
    private JButton adicionarUsuárioButton;
    private JTextField login;
    private JButton sairButton;
    private JTextField reservas;
    private JTextField multas;
    private JComboBox comboBox1;

    public TelaAddUsers(TelaAdmin telaAdmin) {
        add(addUsers);
        setTitle("Adicionar Usuário - UFSM");
        setSize(290, 320);

        adicionarUsuárioButton.addActionListener(e -> {
            UsersDAO u = new UsersDAO();
            Usuario user = new Usuario();

            user.setNome(nome.getText());
            user.setLogin(login.getText());
            user.setSenha(senha.getText());
            user.setReservas(Integer.parseInt(reservas.getText()));
            user.setMulta(Integer.parseInt(multas.getText()));
            user.setCargo(comboBox1.getSelectedIndex() + 1);

            u.create(user);
            telaAdmin.atualizaTabelaUsuarios();
            JOptionPane.showMessageDialog(null, "Usuário adicionado!");
        });

        sairButton.addActionListener(e -> dispose());
    }
}
