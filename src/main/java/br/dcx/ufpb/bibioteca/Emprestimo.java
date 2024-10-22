package br.dcx.ufpb.bibioteca;

import java.io.Serializable;

public class Emprestimo implements Serializable {

    private String matricula;
    private String tituloLivro;
    private PeriodoEmprestimo periodoEmprestimo;

    public Emprestimo(String matricula, String tituloLivro, PeriodoEmprestimo periodoEmprestimo) {
        this.matricula = matricula;
        this.tituloLivro = tituloLivro;
        this.periodoEmprestimo = periodoEmprestimo;
    }

    public Emprestimo() {
        this(null, null, null);
    }

    public String getUsuario() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public PeriodoEmprestimo getPeriodoEmprestimo() {
        return periodoEmprestimo;
    }

    public void setPeriodoEmprestimo(PeriodoEmprestimo periodoEmprestimo) {
        this.periodoEmprestimo = periodoEmprestimo;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "\n Emprestimo:" +
                "\n matricula='" + matricula +
                ",\n tituloLivro='" + tituloLivro +
                ", \n dataEmprestimo='" + getPeriodoEmprestimo().getDataEmprestimo() +
                ", \n dataDevolucao='" + getPeriodoEmprestimo().getDataDevolucao();
    }
}
