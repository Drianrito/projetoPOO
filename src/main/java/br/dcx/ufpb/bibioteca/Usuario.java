package br.dcx.ufpb.bibioteca;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private String nome;
    private String matricula;
    private String email;

    public Usuario(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public Usuario() {
        this("", "","");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return Objects.equals(matricula, usuario.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }
}

