package com.domain;

public class Aluno {
    public int codAluno;
    public int codAreaPesquisa;

    public Aluno(){}

    public Aluno(int codAluno, int codAreaPesquisa){
        this.codAluno = codAluno;
        this.codAreaPesquisa = codAreaPesquisa;
    }

    @Override
    public String toString() {
        return String.format("Aluno: {CodAluno = %s, CodAreaPesquisa = %s}", codAluno, codAreaPesquisa);
    }
}
