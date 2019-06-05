package com.grafos;

public class Vertice<T extends IMesclavel> {
    public T dado;

    public Vertice(T dado){
        this.dado = dado;
    }

    public Vertice(){}

    public Vertice<T> mesclar(Vertice v) {
        T dado = (T) this.dado.mesclar(v.dado);

        return new Vertice<T>(dado);
    }
}
