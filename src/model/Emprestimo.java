package model;

import java.sql.Date;

public class Emprestimo {
    private int ID_emprestimo;
    private int ID_livro;
    private String login;
    private String nome;
    private Date retirado;
    private Date devolucao;

    public Emprestimo() {

    }

    public int getID_livro() {
        return ID_livro;
    }

    public void setID_livro(int ID_livro) {
        this.ID_livro = ID_livro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID_emprestimo() {
        return ID_emprestimo;
    }

    public void setID_emprestimo(int ID_emprestimo) {
        this.ID_emprestimo = ID_emprestimo;
    }

    public Date getRetirado() {
        return retirado;
    }

    public void setRetirado(Date retirado) {
        this.retirado = retirado;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }

    public Date getDevolucao() {
        return devolucao;
    }
}