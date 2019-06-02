package com.grafos;

public class Aresta {
    public Vertice[] vertices = new Vertice[2];
    public int peso = 0;

    public Aresta(){};

    public Aresta(Vertice[] vertices, int peso){
        this.vertices = vertices;
        this.peso = peso;
    }
}
