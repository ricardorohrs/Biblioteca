package DAO;

import model.Emprestimo;

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

            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO emprestimos (livro,usuario,retirado,devolucao) VALUES (?,?,?,?)");
            ps.setInt(1,t.getID_livro());
            ps.setString(2,t.getLogin());
            ps.setDate(3, t.getRetirado());
            ps.setDate(4, t.getDevolucao());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Emprestimo read(int id){
        Emprestimo t = new Emprestimo();

        Connection con;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * from emprestimos WHERE livro = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID_livro(rs.getInt("livro"));
                t.setLogin(rs.getString("usuario"));
                t.setIDEmprestimo(rs.getInt("id_emprest"));
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

    public Emprestimo readByIdEmprest(int id){
        Emprestimo t = new Emprestimo();

        Connection con;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM emprestimos WHERE id_emprestimo = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID_livro(rs.getInt("livro"));
                t.setLogin(rs.getString("usuario"));
                t.setIDEmprestimo(rs.getInt("id_emprest"));
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
        Connection con;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("DELETE FROM emprestimos WHERE id_emprestimo = ?");
            ps.setInt(1, t.getIDEmprestimo());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Emprestimo> readAll(String login){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();

        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM emprestimos WHERE usuario = ?");
            ps.setString(1,login);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Emprestimo temp = new Emprestimo();
                    temp.setIDEmprestimo(rs.getInt("id_emprest"));
                    temp.setID_livro(rs.getInt("livro"));
                    temp.setDevolucao(rs.getDate("devolucao"));
                    temp.setRetirado(rs.getDate("retirado"));
                }
                rs.close();
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emprestimos;
    }

}
