package com.grafos;

public class MatrizDissimilaridade {
    public int [][] matriz;

    public MatrizDissimilaridade(){ }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
                s.append(matriz[i][j] + " ");
            }
            s.append("\n");
        }

        return s.toString();
    }
}
