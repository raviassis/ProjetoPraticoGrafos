package com.grafos;

import com.domain.GrupoPesquisa;

import java.util.List;

public class Grafo<T> {
    public List<Vertice<T>> vertices;
    public List<Aresta> arestas;

    public Grafo(){}

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
        return null;
    }
}
