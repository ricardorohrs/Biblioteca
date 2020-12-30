package model;

public class Reserva {
    private String login;
    private int ID_livro;
    private int ID_reserva;

    public Reserva(String login, int ID_livro, int ID_reserva) {
        this.login = login;
        this.ID_livro = ID_livro;
        this.ID_reserva = ID_reserva;
    }

    public Reserva() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getID_livro() {
        return ID_livro;
    }

    public void setID_livro(int ID_livro) {
        this.ID_livro = ID_livro;
    }

    public int getID_reserva() {
        return ID_reserva;
    }

    public void setID_reserva(int ID_reserva) {
        this.ID_reserva = ID_reserva;
    }

    @Override
    public String toString() {
        return "Reservas{" + "login=" + login + ", ID_livro=" + ID_livro + ", ID_reserva=" + ID_reserva + '}';
    }
    
    
}
