package com.grafos;

import com.domain.GrupoPesquisa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grafo<T extends IMesclavel> {
    public List<Vertice<T>> vertices;
    public List<Aresta> arestas;

    public Grafo(){
        vertices = new ArrayList<Vertice<T>>();
        arestas = new ArrayList<Aresta>();
    }

    public Grafo(List<Vertice<T>> vertices, List<Aresta> arestas){
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public void setVertice(Vertice vertice) {
        this.vertices.add(vertice);
    }

    public void setAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    public Vertice mesclarArestaComMenorPeso(){
        var arestas = this.ordenarArestasPorPeso(this.arestas);
        return this.mesclarVertices(arestas.get(0));
    }

    public Vertice mesclarVertices(Aresta aresta){
        var v1 = aresta.vertices[0];
        var v2 = aresta.vertices[1];
        this.arestas.remove(aresta);
        var novoVertice = v1.mesclar(v2);

        var arestasV1 = arestas.stream()
                                .filter(a -> a.contem(v1))
                                .collect(Collectors.toList());

        var arestasV2 = arestas.stream()
                                .filter(a -> a.contem(v2))
                                .collect(Collectors.toList());

        arestasV1.forEach(a -> {
            if(a.vertices[0] == v1){
                a.vertices[0] = novoVertice;
                a.peso += aresta.peso / 2;
            }else if (a.vertices[1] == v1){
                a.vertices[1] = novoVertice;
                a.peso += aresta.peso / 2;
            }
        });

        arestasV2.forEach(a -> {
            if(a.vertices[0] == v2){
                a.vertices[0] = novoVertice;
                a.peso += aresta.peso / 2;
            }else if (a.vertices[1] == v2){
                a.vertices[1] = novoVertice;
                a.peso += aresta.peso / 2;
            }
        });

        this.vertices.remove(v1);
        this.vertices.remove(v2);
        this.vertices.add(novoVertice);

        return novoVertice;
    }

    public Grafo<T> AGMKruskal (){
        Grafo agm = new Grafo<T>();
        this.arestas = this.ordenarArestasPorPeso(this.arestas);
        int i = 0;
        while (agm.arestas.size() < this.vertices.size() -1){
            var aresta = this.arestas.get(i);
            if(!agm.fechaClico(aresta)){
                agm.incluir(aresta);
            }
            i++;
        }
        return agm;
    }

    public boolean contem(Vertice v){
        return this.vertices.contains(v);
    }

    public void incluir(Aresta aresta) {
        if(!this.contem(aresta.vertices[0])){
            this.setVertice(aresta.vertices[0]);
        }

        if(!this.contem(aresta.vertices[1])){
            this.setVertice(aresta.vertices[1]);
        }

        this.setAresta(aresta);
    }

    public boolean fechaClico(Aresta aresta) {
        return contem(aresta.vertices[0]) && contem(aresta.vertices[1]);
    }

    public List<Aresta> ordenarArestasPorPeso(List<Aresta> arestas){
        return ordenarArestasPorPeso(arestas, "asc");
    }

    public List<Aresta> ordenarArestasPorPeso(List<Aresta> arestas, String ordem) {
        var stream = arestas.stream();
        if(ordem.toLowerCase() == "desc")
            stream = stream.sorted((x, y) -> y.peso - x.peso);
        else{
            stream = stream.sorted((x, y) -> x.peso - y.peso);
        }
        return stream.collect(Collectors.toList());
    }
}
