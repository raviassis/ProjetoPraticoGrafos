import com.data.ArquivoAluno;
import com.data.ArquivoMatriz;
import com.domain.Aluno;
import com.domain.GrupoPesquisa;
import com.grafos.Aresta;
import com.grafos.Grafo;
import com.grafos.MatrizDissimilaridade;
import com.grafos.Vertice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        String arqAlunos = "C:\\Users\\Dell\\Documents\\projects\\ProjetoPraticoGrafos\\alunos.txt";
        String arqMatriz = "C:\\Users\\Dell\\Documents\\projects\\ProjetoPraticoGrafos\\matriz.txt";
        int kProfessores = 5;

        MatrizDissimilaridade matriz = ArquivoMatriz.Ler(arqMatriz);
        List<Aluno> alunos = ArquivoAluno.Ler(arqAlunos);
        var gruposPesquisa = gruposPesquisa(alunos);
        var vertices = criarVertices(gruposPesquisa);
        var arestas = conectarTodosVertices(vertices, matriz);

        var grafo = new Grafo<GrupoPesquisa>(vertices, arestas);
        var grafoReduzido = grafo.AGMKruskal();

        imprimirGrafo(grafoReduzido);

        while (grafoReduzido.vertices.size() > kProfessores){
            grafoReduzido.mesclarArestaComMenorPeso();
        }

        imprimirGrafo(grafoReduzido);

    }

    private static void imprimirGrafo(Grafo<GrupoPesquisa> grafo) {
        System.out.println("Quantidade de grupos: " + grafo.vertices.size());
        grafo.vertices.forEach(v -> {
            System.out.println(v.dado);
            System.out.println();
        });
    }

    private static List<GrupoPesquisa> gruposPesquisa(List<Aluno> alunos){
        var gruposPesquisa = alunos.stream()
                .map(a -> a.codAreaPesquisa)
                .distinct()
                .sorted()
                .map(cod -> new GrupoPesquisa(cod))
                .collect(Collectors.toList());
        gruposPesquisa.forEach(g -> {
            g.alunos.addAll(
                    alunos.stream()
                            .filter(a -> g.areas.contains(a.codAreaPesquisa))
                            .collect(Collectors.toList())
            );
        });

        return gruposPesquisa;
    }

    private static List<Vertice<GrupoPesquisa>> criarVertices(List<GrupoPesquisa> grupos){
        return grupos.stream()
                .map(g -> new Vertice<GrupoPesquisa>(g))
                .collect(Collectors.toList());
    }

    private static List<Aresta> conectarTodosVertices(List<Vertice<GrupoPesquisa>> vertices, MatrizDissimilaridade matriz){
        List<Aresta> arestas = new ArrayList<>();
        List<Vertice> jaForamIterados = new ArrayList<>();

        vertices.stream()
                .filter(v -> !jaForamIterados.contains(v))
                .forEach(v -> {
                    var arestasI = vertices.stream()
                                            .filter(v1 -> !jaForamIterados.contains(v1))
                                            .filter(v1 -> v1 != v)
                                            .map(v1 -> new Aresta(
                                                    new Vertice[]{v, v1},
                                                    matriz.matriz[ v.dado.areas.get(0) -1][v1.dado.areas.get(0) - 1] )
                                            )
                                            .collect(Collectors.toList());
                    jaForamIterados.add(v);
                    arestas.addAll(arestasI);
                });

        return  arestas;
    }
}
