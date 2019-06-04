package com.grafos;

import com.domain.GrupoPesquisa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grafo<T> {
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

    public Vertice mesclarVertices(Aresta aresta){
        return null;
    }

    public Grafo AGMKruskal (){
        Grafo agm = new Grafo();
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
        /*boolean temVerticeEsq = false;
        boolean temVerticeDir = false;

        for(var a : arestas){
            temVerticeEsq = (contem(aresta.vertices[0]))
                                    ? true
                                    : temVerticeEsq;

            temVerticeDir = (a.vertices[1] == aresta.vertices[1])
                                    ? true
                                    : temVerticeDir;
        }*/

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
