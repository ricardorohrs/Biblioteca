package DAO;

import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO {

    public void create(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (nome,login,senha,cargo,reservas,emprestimos,multa) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,u.getNome());
            ps.setString(2,u.getLogin());
            ps.setString(3,u.getSenha());
            ps.setInt(4,u.getCargo());
            ps.setInt(5, u.getReservas());
            ps.setInt(6, u.getEmprestimos());
            ps.setInt(7, u.getMulta());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Usuario> listarUsers(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM users");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setCargo(rs.getInt("cargo"));
                u.setNome(rs.getString("nome"));
                u.setReservas(rs.getInt("reservas"));
                u.setEmprestimos(rs.getInt("emprestimos"));
                u.setMulta(rs.getInt("multa"));
                usuarios.add(u);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public void update (Usuario u) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET login = ?, senha = ?, cargo = ?, nome = ? WHERE login = ?");
            ps.setString(1,u.getLogin());
            ps.setString(2,u.getSenha());
            ps.setInt(3,u.getCargo());
            ps.setString(4,u.getNome());
            ps.setString(5,u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Usuario> readParteNome (String parteNome){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement un = con.createStatement();
            String sql = ("SELECT * FROM users WHERE nome LIKE '%" + parteNome + "%'");
            ResultSet rs = un.executeQuery(sql);

            while(rs.next()){
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setCargo(rs.getInt("cargo"));
                u.setNome(rs.getString("nome"));
                u.setReservas(rs.getInt("reservas"));
                u.setEmprestimos(rs.getInt("emprestimos"));
                u.setMulta(rs.getInt("multa"));
                usuarios.add(u);
            }
            rs.close();
            un.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public ArrayList<Usuario> readLogin (String login){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM users WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setCargo(rs.getInt("cargo"));
                u.setNome(rs.getString("nome"));
                u.setReservas(rs.getInt("reservas"));
                u.setEmprestimos(rs.getInt("emprestimos"));
                u.setMulta(rs.getInt("multa"));
                usuarios.add(u);
            }
            ps.close();
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public Usuario read (String login){
        Usuario u = new Usuario();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM users WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setCargo(rs.getInt("cargo"));
                u.setNome(rs.getString("nome"));
                u.setReservas(rs.getInt("reservas"));
                u.setEmprestimos(rs.getInt("emprestimos"));
                u.setMulta(rs.getInt("multa"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void delete(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE from users WHERE login = ?");
            ps.setString(1,u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addReservas(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET reservas = reservas+1 WHERE login = ?");
            ps.setString(1, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletaReservas(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET reservas = reservas-1 WHERE login = ?");
            ps.setString(1, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void marcarEmprestimoUser(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET emprestimos = emprestimos+1 WHERE login = ?");
            ps.setString(1, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desmarcarEmprestimoUser(Usuario u){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET emprestimos = emprestimos-1 WHERE login = ?");
            ps.setString(1, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMultas(Usuario u, int multa){
        try{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET multa = ? WHERE login = ?");
            ps.setInt(1, multa);
            ps.setString(2, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}