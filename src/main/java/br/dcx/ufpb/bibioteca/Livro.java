package br.dcx.ufpb.bibioteca;

import java.util.Objects;

public class Livro implements Comparable<Livro>{

    private String titulo;
    private GeneroLivro generoLivro;
    private String autor;
    private String codLivro;

    public Livro(String titulo, GeneroLivro generoLivro, String autor, String codLivro) {
        this.titulo = titulo;
        this.generoLivro = generoLivro;
        this.autor = autor;
        this.codLivro = codLivro;
    }

    public Livro() {
        this("", null, "","");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public GeneroLivro getGeneroLivro() {
        return generoLivro;
    }

    public void setGeneroLivro(GeneroLivro generoLivro) {
        this.generoLivro = generoLivro;
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
        return "\n Titulo: " + titulo +
                "\n Gênero do Livro: " + generoLivro +
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
