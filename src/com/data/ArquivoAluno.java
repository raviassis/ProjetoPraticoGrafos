package com.data;

import com.domain.Aluno;
import com.grafos.MatrizDissimilaridade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoAluno {

    public static List<Aluno> Ler(String path){
        ArrayList<Aluno> alunos = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while(br.ready()){
                String[] linha = br.readLine().split(" ");
                Aluno aluno = new Aluno(
                        Integer.parseInt(linha[0]),
                        Integer.parseInt(linha[1])
                );

                alunos.add(aluno);
            }
            br.close();
        }catch(IOException ioe){
            System.out.println("Error: Não foi possível ler o arquivo " + path + ".\n" + ioe);
        }

        return alunos;
    }
}
