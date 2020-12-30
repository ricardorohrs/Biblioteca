package DAO;

import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO {

    public void create(Usuario u){
        Connection con;

        try {
            con = ConnectionFactory.getConnection();

            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO users (nome,login,senha,cargo,reservas,multa) VALUES (?,?,?,?,?,?)");
            ps.setString(1,u.getNome());
            ps.setString(2,u.getLogin());
            ps.setString(3,u.getSenha());
            ps.setInt(4,u.getCargo());
            ps.setInt(5, u.getReservas());
            ps.setInt(6, u.getMulta());
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
            Statement s = (Statement) con.createStatement();
            String sql = ("SELECT * FROM users");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setCargo(rs.getInt("cargo"));
                u.setNome(rs.getString("nome"));
                u.setReservas(rs.getInt("reservas"));
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

    public void update(Usuario u) {
        Connection con;

        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE users SET login = ?, senha = ?, cargo = ?, nome = ? WHERE login = ?");
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

    public Usuario read (String login){

        Usuario u = new Usuario();
        Connection con;
        try {
            con = ConnectionFactory.getConnection();
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
        Connection con;
        try {
            con = ConnectionFactory.getConnection();

            PreparedStatement ps = (PreparedStatement) con.prepareStatement("DELETE from users WHERE login = ?");
            ps.setString(1,u.getLogin());
            ps.execute();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public ArrayList<Usuario> readAll(){
        Connection con;
        Collection<Usuario> usuarios = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            Statement s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM users");
            while(rs.next()){
                Usuario temp = new Usuario();
                temp.setLogin(rs.getString("login"));
                temp.setNome(rs.getString("nome"));
                temp.setCargo(rs.getInt("cargo"));
                temp.setReservas(rs.getInt("reservas"));
                usuarios.add(temp);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Usuario>) usuarios;

    }

    public void updateReservas(Usuario u, int num){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE users SET reservas = ? WHERE login = ?");
            ps.setInt(1, u.getReservas() + num);
            ps.setString(2, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMultas(Usuario u, int multa){
        try{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE users SET multa = ? WHERE login = ?");
            ps.setInt(1, u.getMulta() + multa);
            ps.setString(2, u.getLogin());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
