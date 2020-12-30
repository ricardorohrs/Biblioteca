package model;

import java.sql.Date;

public class Emprestimo {
    private int ID_livro;
    private String login;
    private int IDEmprestimo;
    private Date retirado;
    private Date devolucao;
    
    public Emprestimo() {
    }

    public Emprestimo(int ID_livro, String login) {
        this.ID_livro = ID_livro;
        this.login = login;
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

    public int getIDEmprestimo() {
        return IDEmprestimo;
    }

    public void setIDEmprestimo(int IDEmprestimo) {
        this.IDEmprestimo = IDEmprestimo;
    }

    public Date getRetirado() {
        return retirado;
    }

    public void setRetirado(Date retirado) {
        this.retirado = retirado;
    }

    public Date getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }
        
}
