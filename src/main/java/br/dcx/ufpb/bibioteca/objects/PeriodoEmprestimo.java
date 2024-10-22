package br.dcx.ufpb.bibioteca.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class PeriodoEmprestimo implements Serializable {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public PeriodoEmprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
