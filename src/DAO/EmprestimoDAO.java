package DAO;

import model.Emprestimo;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmprestimoDAO {

    public void create(Emprestimo t){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO emprestimos (livro,usuario,nome,retirado,devolucao) VALUES (?,?,?,?,?)");
            ps.setInt(1,t.getID_livro());
            ps.setString(2,t.getLogin());
            ps.setString(3, t.getNome());
            ps.setDate(4, t.getRetirado());
            ps.setDate(5, t.getDevolucao());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDevolucao (Emprestimo t, int id){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE emprestimos SET devolucao = ? WHERE livro = ?");
            ps.setDate(1, t.getDevolucao());
            ps.setInt(2, id);
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Emprestimo> buscarEmprestimoNome(String nome){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * from emprestimos WHERE nome LIKE '%" + nome + "%'");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Emprestimo t = new Emprestimo();
                t.setID_emprestimo(rs.getInt("id_emprestimo"));
                t.setID_livro(rs.getInt("livro"));
                t.setNome(rs.getString("nome"));
                t.setLogin(rs.getString("usuario"));
                t.setDevolucao(rs.getDate("devolucao"));
                t.setRetirado(rs.getDate("retirado"));
                emprestimos.add(t);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }

    public Emprestimo readByID_emprest(int id){
        Emprestimo t = new Emprestimo();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM emprestimos WHERE id_emprestimo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID_livro(rs.getInt("livro"));
                t.setLogin(rs.getString("usuario"));
                t.setID_emprestimo(rs.getInt("id_emprestimo"));
                t.setDevolucao(rs.getDate("devolucao"));
                t.setRetirado(rs.getDate("retirado"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public void delete(Emprestimo t) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("DELETE FROM emprestimos WHERE id_emprestimo = ?");
            ps.setInt(1, t.getID_emprestimo());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Emprestimo> readAll(Usuario user){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM emprestimos WHERE usuario = ?");
            ps.setString(1, user.getLogin());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Emprestimo temp = new Emprestimo();
                    temp.setID_emprestimo(rs.getInt("id_emprestimo"));
                    temp.setID_livro(rs.getInt("livro"));
                    temp.setNome(rs.getString("nome"));
                    temp.setDevolucao(rs.getDate("devolucao"));
                    temp.setRetirado(rs.getDate("retirado"));
                    emprestimos.add(temp);
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }

    public ArrayList<Emprestimo> listarEmprestimos(){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM emprestimos");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Emprestimo temp = new Emprestimo();
                    temp.setID_emprestimo(rs.getInt("id_emprestimo"));
                    temp.setID_livro(rs.getInt("livro"));
                    temp.setNome(rs.getString("nome"));
                    temp.setLogin(rs.getString("usuario"));
                    temp.setDevolucao(rs.getDate("devolucao"));
                    temp.setRetirado(rs.getDate("retirado"));
                    emprestimos.add(temp);
                }
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }

    public ArrayList<Emprestimo> buscarLogin (String login){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM emprestimos WHERE usuario = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Emprestimo t = new Emprestimo();
                t.setID_emprestimo(rs.getInt("id_emprestimo"));
                t.setLogin(rs.getString("usuario"));
                t.setID_livro(rs.getInt("livro"));
                t.setNome(rs.getString("nome"));
                t.setDevolucao(rs.getDate("devolucao"));
                t.setRetirado(rs.getDate("retirado"));
                emprestimos.add(t);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimos;
    }
}
