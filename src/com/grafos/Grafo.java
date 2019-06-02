package com.grafos;

import java.util.ArrayList;

public class Grafo {
    public ArrayList<Vertice> vertices;
    public ArrayList<Aresta> arestas;

    public Grafo(){}

    public Grafo(ArrayList<Vertice> vertices, ArrayList<Aresta> arestas){
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
