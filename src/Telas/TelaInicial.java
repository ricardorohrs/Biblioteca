package Telas;

import DAO.UsersDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaInicial extends javax.swing.JFrame {
    private JPanel login;
    private JPasswordField passwordField;
    private JButton LOGINButton;
    private JTextField userField;

    public TelaInicial() {
        add(login);
        setTitle("Biblioteca - UFSM");
        setSize(250, 300);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    login();
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    login();
            }
        });
    }

    private void login() {
        UsersDAO u = new UsersDAO();
        Usuario user = u.read(userField.getText());

        if (userField.getText().equals(user.getLogin()) && passwordField.getText().equals(user.getSenha())) {
            TelaUsuario telaUser = new TelaUsuario();
            telaUser.setLocationRelativeTo(null);
            telaUser.setVisible(true);
            telaUser.setResizable(false);
            //telaUser.usuarioLogado(user);
            //telaUser.atualizaMultas();
        } else if (userField.getText().equals("admin")) {
            TelaAdmin telaAdmin = new TelaAdmin();
            telaAdmin.setLocationRelativeTo(null);
            telaAdmin.setVisible(true);
            telaAdmin.setResizable(false);
        } else
            JOptionPane.showMessageDialog(null, "Usuário inválido!");
    }
}