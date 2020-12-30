package model;

public class    Admin {
    private int login;
    private String senha;

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Admin{" + "login=" + login + ", senha=" + senha + '}';
    }

    public Admin(int login, String senha) {
        this.login = login;
        this.senha = senha;
    }

}
