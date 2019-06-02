package com.domain;

import java.util.ArrayList;
import java.util.List;

public class GrupoPesquisa {
    public int area;

    public List<Aluno> alunos = new ArrayList<Aluno>();

    public GrupoPesquisa(){ }

    public GrupoPesquisa(int areaPesquisa){
        this.area = areaPesquisa;
    }
}
