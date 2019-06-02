package com.data;

import com.grafos.MatrizDissimilaridade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoMatriz {

    public static MatrizDissimilaridade Ler(String path){
        MatrizDissimilaridade matriz = new MatrizDissimilaridade();
        ArrayList<String[]> linhas = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while(br.ready()){
                String[] linha = br.readLine().split(" ");
                linhas.add(linha);
            }
            br.close();
        }catch(IOException ioe){
            System.out.println("Error: Não foi possível ler o arquivo " + path + ".\n" + ioe);
        }

        matriz.matriz = Converter(linhas);
        return matriz;
    }

    private static int[][] Converter(ArrayList<String[]> linhas) {
        int[][] matriz = new int[linhas.size()][];

        for(int i = 0; i < matriz.length; i++){
            String[] linha = linhas.get(i);
            matriz[i] = new int[linha.length];
            for (int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = Integer.parseInt(linha[j]);
            }
        }

        return  matriz;
    }
}
