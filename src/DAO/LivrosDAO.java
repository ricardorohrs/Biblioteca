package DAO;

import model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivrosDAO{

    public void create(Livro t) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO livros (ISBN,autor,edicao,editora,nome,ano,reservado,emprestado) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, t.getISBN());
            ps.setString(2, t.getAutor());
            ps.setInt(3, t.getEdicao());
            ps.setString(4, t.getEditora());
            ps.setString(5, t.getNome());
            ps.setInt(6, t.getAno());
            ps.setInt(7,t.getReservado());
            ps.setInt(8,t.getEmprestado());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Livro read (int id) {
        Livro t = new Livro();
        try {
            Connection con = ConnectionFactory.getConnection();

            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM livros WHERE id = ? && reservado = ?");
            ps.setInt(1, id);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setNome(rs.getString("nome"));
                t.setEditora(rs.getString("editora"));
                t.setAno(rs.getInt("ano"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    public Livro readbyISBN(int ISBN) {
        Livro t = new Livro();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM livros WHERE ISBN = ?");
            ps.setInt(1, ISBN);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setNome(rs.getString("nome"));
                t.setEditora(rs.getString("editora"));
                t.setAno(rs.getInt("ano"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public void update(Livro t) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE livros SET ISBN = ?, autor = ?, edicao = ?, editora = ?, nome = ?, ano = ? WHERE ID = ?");
            ps.setInt(1, t.getISBN());
            ps.setString(2,t.getAutor());
            ps.setInt(3,t.getEdicao());
            ps.setString(4,t.getEditora());
            ps.setString(5,t.getNome());
            ps.setInt(6,t.getAno());
            ps.setInt(7,t.getID());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void marcarEmprestimo(int ID, int res){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("UPDATE livros SET emprestado = ? WHERE ID = ?");
            ps.setInt(1, res);
            ps.setInt(2, ID);
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void marcarReserva(int ID, int res){
        try{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE livros SET reservado = ? WHERE ID = ?");
            ps.setInt(1,res);
            ps.setInt(2,ID);
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Livro t) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE from livros WHERE ID = ?");
            ps.setInt(1, t.getID());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Livro> readbyParteNome(String parteNome){
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM livros WHERE nome LIKE '%" + parteNome + "%'");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Livro t = new Livro();
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setEditora(rs.getString("editora"));
                t.setNome(rs.getString("nome"));
                t.setAno(rs.getInt("ano"));
                t.setEmprestado(rs.getInt("emprestado"));
                t.setReservado(rs.getInt("reservado"));
                livros.add(t);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public ArrayList<Livro> listarLivros(){
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM livros");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Livro t = new Livro();
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setEditora(rs.getString("editora"));
                t.setNome(rs.getString("nome"));
                t.setAno(rs.getInt("ano"));
                t.setEmprestado(rs.getInt("emprestado"));
                t.setReservado(rs.getInt("reservado"));
                livros.add(t);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public int checaEmprestimo(int ID){
        int resp = 0;
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM livros WHERE ID = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                resp = rs.getInt("emprestado");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public int checaReserva(int ID){
        int resp = 0;
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM livros WHERE ID = ?");
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                resp = rs.getInt("reservado");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public ArrayList<Livro> readbyISBN(String isbn) {
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM livros WHERE ISBN LIKE '%" + isbn + "%'");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Livro t = new Livro();
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setEditora(rs.getString("editora"));
                t.setNome(rs.getString("nome"));
                t.setAno(rs.getInt("ano"));
                t.setEmprestado(rs.getInt("emprestado"));
                t.setReservado(rs.getInt("reservado"));
                livros.add(t);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public ArrayList<Livro> buscarLivro (String parteNome){
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM livros WHERE nome LIKE '%" + parteNome + "%'");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Livro t = new Livro();
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setEditora(rs.getString("editora"));
                t.setNome(rs.getString("nome"));
                t.setAno(rs.getInt("ano"));
                t.setEmprestado(rs.getInt("emprestado"));
                t.setReservado(rs.getInt("reservado"));
                livros.add(t);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public ArrayList<Livro> readbyEditora(String parteNome){
        ArrayList<Livro> livros = new ArrayList<>();
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement s = con.createStatement();
            String sql = ("SELECT * FROM livros WHERE editora LIKE '%" + parteNome + "%'");
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Livro t = new Livro();
                t.setID(rs.getInt("ID"));
                t.setISBN(rs.getInt("ISBN"));
                t.setAutor(rs.getString("autor"));
                t.setEdicao(rs.getInt("edicao"));
                t.setEditora(rs.getString("editora"));
                t.setNome(rs.getString("nome"));
                t.setAno(rs.getInt("ano"));
                t.setEmprestado(rs.getInt("emprestado"));
                t.setReservado(rs.getInt("reservado"));
                livros.add(t);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }
}