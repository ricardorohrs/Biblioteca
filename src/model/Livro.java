package model;

public class Livro {

    private int ID;
    private int ISBN;
    private String autor;
    private int edicao;
    private String editora;
    private String nome;
    private int ano;
    private int emprestado;
    private int reservado;

    public Livro() {
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    @Override
    public String toString() {
        return "Livros{" + "ID=" + ID + ", autor=" + autor + ", edicao=" + edicao + ", editora=" + editora + ", nome=" + nome + ", ano=" + ano + '}';
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
      public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public int getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(int emprestado) {
        this.emprestado = emprestado;
    }

}
