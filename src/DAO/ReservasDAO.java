package DAO;

import model.Reserva;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservasDAO {

    public void create(Reserva t){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO reservas (ID_livro, login, nome) VALUES (?,?,?)");
            ps.setInt(1, t.getID_livro());
            ps.setString(2, t.getLogin());
            ps.setString(3, t.getNome());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reserva read (int ID){
        Connection con;
        Reserva t = new Reserva();
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reservas WHERE ID_reserva = ?");
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                t.setID_livro(rs.getInt("ID_livro"));
                t.setID_reserva(rs.getInt("ID_reserva"));
                t.setLogin(rs.getString("login"));
                t.setNome(rs.getString("nome"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;
    }

    public Reserva readByIdLivro (int ID){
        Reserva t = new Reserva();
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reservas WHERE ID_livro = ?");
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setID_livro(rs.getInt("ID_livro"));
                t.setID_reserva(rs.getInt("ID_reserva"));
                t.setLogin(rs.getString("login"));
                t.setNome(rs.getString("nome"));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public void delete (Reserva t){
        Connection con;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM reservas WHERE ID_reserva = ?");
            ps.setInt(1,t.getID_reserva());
            ps.execute();
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Reserva> readAll (Usuario user){
        ArrayList<Reserva> reservas = new ArrayList<>();

        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reservas WHERE login = ?");
            ps.setString(1,user.getLogin());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reserva temp = new Reserva();
                temp.setLogin(rs.getString("login"));
                temp.setID_livro(rs.getInt("ID_livro"));
                temp.setID_reserva(rs.getInt("ID_reserva"));
                temp.setNome(rs.getString("nome"));
                reservas.add(temp);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }
}
