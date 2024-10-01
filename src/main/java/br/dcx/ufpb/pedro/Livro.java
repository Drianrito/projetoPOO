package br.dcx.ufpb.pedro;

import java.util.Objects;

public class Livro implements Comparable<Livro>{

    private String titulo;
    private String autor;
    private String codLivro;

    public Livro(String titulo, String autor, String codLivro) {
        this.titulo = titulo;
        this.autor = autor;
        this.codLivro = codLivro;
    }

    public Livro() {
        this("", "","");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodLivro() {
        return codLivro;
    }

    public void setCodLivro(String isbn) {
        this.codLivro = isbn;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\n Autor: " + autor +
                "\n Código do Livro: " + codLivro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;
        return Objects.equals(codLivro, livro.codLivro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codLivro);
    }

    @Override
    public int compareTo(Livro o) {
        return this.titulo.compareTo(o.getTitulo());
    }
}
