package com.domain;

import com.grafos.IMesclavel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GrupoPesquisa implements IMesclavel {
    public List<Integer> areas = new ArrayList<>();

    public List<Aluno> alunos = new ArrayList<Aluno>();

    public GrupoPesquisa(){ }

    public GrupoPesquisa(int areaPesquisa){
        this.areas.add(areaPesquisa);
    }

    @Override
    public String toString() {
        var sAlunos = alunos.stream()
                .map(a -> a.toString())
                .collect(Collectors.joining("\n"));
        var area = areas.stream()
                    .map(a -> a.toString())
                    .collect(Collectors.joining(","));
        return String.format("GrupoPesquisa: Areas = %s \nAlunos\n%s", area, sAlunos);
    }

    @Override
    public IMesclavel mesclar(IMesclavel m) {
        var grupo = (GrupoPesquisa) m;
        var novoGrupo = new GrupoPesquisa();
        novoGrupo.areas.addAll(this.areas);
        novoGrupo.areas.addAll(grupo.areas);
        novoGrupo.alunos.addAll(this.alunos);
        novoGrupo.alunos.addAll(grupo.alunos);
        return novoGrupo;
    }
}
