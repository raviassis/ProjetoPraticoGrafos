import com.data.ArquivoAluno;
import com.data.ArquivoMatriz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String arqAlunos = "C:\\Users\\Dell\\Documents\\projects\\ProjetoPraticoGrafos\\alunos.txt";
        String arqMatriz = "C:\\Users\\Dell\\Documents\\projects\\ProjetoPraticoGrafos\\matriz.txt";

        System.out.println( ArquivoMatriz.Ler(arqMatriz) );

        ArquivoAluno.Ler(arqAlunos).forEach(a -> System.out.println(a));
    }
}
