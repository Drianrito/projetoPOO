package br.dcx.ufpb.pedro;

public class Emprestimo {

    private String matricula;
    private String tituloLivro;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(String matricula, String tituloLivro, String dataEmprestimo, String dataDevolucao) {
        this.matricula = matricula;
        this.tituloLivro = tituloLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo() {
        this(null, null, "", "");
    }

    public String getUsuario() {
        return matricula;
    }

    public void setUsuario(String matricula) {
        this.matricula = matricula;
    }

    public String getLivro() {
        return tituloLivro;
    }

    public void setLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
