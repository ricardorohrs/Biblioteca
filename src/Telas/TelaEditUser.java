package Telas;

import DAO.UsersDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditUser extends javax.swing.JFrame {
    private JPanel addUsers;
    private JTextField nome;
    private JButton atualizarUsuárioButton;
    private JTextField login;
    private JButton sairButton;
    private JTextField senha;
    private JComboBox cargo;
    private JPanel editUser;
    private Usuario u;

    public TelaEditUser(TelaAdmin telaAdmin, String loginSelecionado) {
        UsersDAO ud = new UsersDAO();
        u = ud.read(loginSelecionado);
        nome.setText(u.getNome());
        login.setText(u.getLogin());
        senha.setText(u.getSenha());
        cargo.setSelectedIndex(u.getCargo() - 1);

        add(editUser);
        setTitle("Editando o usuário " + u.getNome() + " - UFSM");
        setSize(350, 450);

        sairButton.addActionListener(e -> dispose());

        atualizarUsuárioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersDAO ud = new UsersDAO();
                Usuario user = new Usuario();

                user.setNome(nome.getText());
                user.setLogin(login.getText());
                user.setSenha(senha.getText());
                user.setCargo(cargo.getSelectedIndex() + 1);
                ud.update(user);
                telaAdmin.atualizaTabelaUsuarios();
                JOptionPane.showMessageDialog(null, "Usuário " + user.getNome() + " Atualizado!");
                dispose();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
