package model;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private int reservas;
    private int cargo;
    private int multa;

    public Usuario(String nome, String login, String senha, int cargo, int reservas, int multa) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.reservas = reservas;
        this.multa = multa;
    }

    public Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
    
}
