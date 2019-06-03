package com.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GrupoPesquisa {
    public int area;

    public List<Aluno> alunos = new ArrayList<Aluno>();

    public GrupoPesquisa(){ }

    public GrupoPesquisa(int areaPesquisa){
        this.area = areaPesquisa;
    }

    @Override
    public String toString() {
        var sAlunos = alunos.stream()
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        return String.format("GrupoPesquisa: {codArea = %s, alunos =[%s]}", area, sAlunos);
    }
}