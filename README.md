## PONTIFÍCIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS
## INSTITUTO DE CIÊNCIAS EXATAS E INFORMÁTICA
## Bacharelado em Engenharia de Software

##### Lucas Maia Herneque Valadares & Ravi Antônio Gonçalves de Assis

##### TRABALHO PRÁTICO DE ALGORITMOS COMPUTACIONAIS EM GRAFOS

##### RESOLVENDO PROBLEMA DE ALOCAÇÃO DE PROFESSORES POR GRUPOS DE PESQUISA UTILIZANDO GRAFOS

### Introdução

O presente trabalho tem como objetivo apresentar a resolução do problema de alocação de professores em grupos de pesquisas, para orientar em trabalhos de pesquisas de alunos do Departamento de Computação, utilizando de programação e estrutura de dados em grafos.

O problema foi apresentado no seguinte contexto: 
> Em uma universidade existe um departamento de computação com cursos de graduação, mestrado e doutorado. Ao todo, existem 𝑛 alunos e 𝑘 professores nos três níveis de ensino. Todos os alunos do departamento estão envolvidos com trabalhos de pesquisa em alguma área da computação. O grau de relacionamento entre os alunos é medido pela proximidade entre os temas de seus trabalhos de pesquisa. Dessa forma, deseja-se alocar os 𝑘 professores do departamento de computação para orientar os trabalhos de pesquisa dos alunos, sendo que cada professor deve ser alocado para orientar um grupo de alunos com trabalhos semelhantes (ou o mais semelhante possível). O número 𝑛 de alunos da instituição é maior do que o número 𝑘 de professores, ou seja, 𝑘 ≤ 𝑛. Assim, um professor irá orientar um ou mais alunos.“Em uma universidade existe um departamento de computação com cursos de graduação, mestrado e doutorado. Ao todo, existem 𝑛 alunos e 𝑘 professores nos três níveis de ensino. Todos os alunos do departamento estão envolvidos com trabalhos de pesquisa em alguma área da computação. O grau de relacionamento entre os alunos é medido pela proximidade entre os temas de seus trabalhos de pesquisa. Dessa forma, deseja-se alocar os 𝑘 professores do departamento de computação para orientar os trabalhos de pesquisa dos alunos, sendo que cada professor deve ser alocado para orientar um grupo de alunos com trabalhos semelhantes (ou o mais semelhante possível). O número 𝑛 de alunos da instituição é maior do que o número 𝑘 de professores, ou seja, 𝑘 ≤ 𝑛. Assim, um professor irá orientar um ou mais alunos.

> Seu objetivo nesse trabalho prático é modelar e resolver o problema de encontrar quais alunos pertencerão a qual grupo de pesquisa (cluster), sendo que alunos de um mesmo grupo possuirão temas de trabalho de pesquisa “semelhantes”. O número de grupos será determinado pelo número 𝑘 de professores do departamento de computação."

Será apresentado neste trabalho como foi modelado o problema em estrutura de grafos, algoritmos e decisões escolhidas, desenvolvimento do programa, testes realizados e os seus resultados, e a conclusão.

### Desenvolvimento
#### Linguagem
Foi escolhido para o desenvolvimento deste trabalho a linguagem de programação Java devido a familiaridade de ambos participantes do trabalho nessa linguagem.

#### Detalhes da solução
Para solucionar esse problema modelamos o mesmo em um grafo em que os vértices representam os grupos de pesquisa, contendo os alunos e as áreas de pesquisa, e as arestas entre eles tendo um peso que é o grau de dissimilaridade entre os grupos de pesquisa. Sendo a quantidade de grupos de pesquisa disponíveis determinada pela quantidade de professores K, e levando em consideração que a quantidade de áreas de pesquisa pode ser maior que a quantidade de professores disponíveis resolvemos o problema da seguinte maneira:
- Geramos um grafo completo no qual os Vértices eram os grupos de pesquisa. Cada grupo de pesquisa teria uma área de pesquisa inicialmente e teria uma lista com todos os alunos daquela área de pesquisa. As Arestas eram a relação entre as áreas de pesquisa e o seu peso seria o grau de dissimilaridade entre as áreas de pesquisa.
- Aplicamos o algoritmo de Kruskal para obter a Árvore Geradora Mínima do Grafo. Assim o grafo teriam conexão apenas das arestas com menor peso.
- Mesclamos os Vértices das arestas com menor peso até que o número de vértices fosse igual a K professores.
O resultado desses passos seria um grafo no qual cada vértice seria um grupo de pesquisa, e esse grupo de pesquisa abrange-se alunos de áreas de pesquisas correlacionadas e que um professor ficaria responsável por esse grupo.

O algoritmo de Kruskal implementado por nós pode ser visto a seguir:


```java
public Grafo<T> AGMKruskal (){
    Grafo agm = new Grafo<T>();
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
```

Observe que alguns dos métodos usados são simplesmente para verificar se existe um ciclo ou para incluir a aresta no grafo da AGM, não os incluímos pois não mudam o entendimento da solução.
	Para fazer a mesclagem dos grupos de pesquisa criamos os métodos a seguir:

```java
public Vertice mesclarArestaComMenorPeso(){
       	var arestas = this.ordenarArestasPorPeso(this.arestas);
       	return this.mesclarVertices(arestas.get(0));
}
```
```java
public Vertice mesclarVertices(Aresta aresta){
	var v1 = aresta.vertices[0];
	var v2 = aresta.vertices[1];
	this.arestas.remove(aresta);
	var novoVertice = v1.mesclar(v2);

	var arestasV1 = arestas.stream()
			.filter(a -> a.contem(v1))
			.collect(Collectors.toList());

 var arestasV2 = arestas.stream()
			.filter(a -> a.contem(v2))
			.collect(Collectors.toList());

	arestasV1.forEach(a -> {
		if(a.vertices[0] == v1){
			a.vertices[0] = novoVertice;
			a.peso += aresta.peso / 2;
		} else if (a.vertices[1] == v1){
			a.vertices[1] = novoVertice;
			a.peso += aresta.peso / 2;
		}
	});

	arestasV2.forEach(a -> {
		if(a.vertices[0] == v2){
			a.vertices[0] = novoVertice;
			a.peso += aresta.peso / 2;
		} else if (a.vertices[1] == v2){
			a.vertices[1] = novoVertice;
			a.peso += aresta.peso / 2;
		}
	});

	this.vertices.remove(v1);
	this.vertices.remove(v2);
	this.vertices.add(novoVertice);
	return novoVertice;
}
```
### Testes Realizados
#### Primeiro teste
Para testar utilizamos alguns arquivos de entrada de diferentes formas para garantir a resiliência do programa e garantir que o mesmo sempre retornará de forma correta.

Matriz de dissimilaridade:


    0 10 20 30 40 50 60 70 80 90
    10 0 20 30 40 50 60 70 80 90
    20 20 0 30 40 50 60 70 80 90
    30 30 30 0 40 50 60 70 80 90
    40 40 40 40 0 50 60 70 80 90
    50 50 50 50 50 0 60 70 80 90
    60 60 60 60 60 60 0 70 80 90
    70 70 70 70 70 70 70 0 80 90
    80 80 80 80 80 80 80 80 0 90
    90 90 90 90 90 90 90 90 90 0

Alunos e grupos de pesquisa: 


    01 04
    02 08
    03 01
    04 03
    05 02
    06 07
    07 10
    08 06
    09 05
    10 09
    11 04
    12 08
    13 01
    14 03
    15 02
    16 07
    17 10
    18 06
    19 05
    20 09

Resultados apresentados quando executamos o código com essas entradas e considerando K = 5, o programa irá imprimir o seguinte resultado:



    Quantidade de grupos: 5 
    GrupoPesquisa: Areas = 7 
    Alunos
    Aluno: {CodAluno = 6, CodAreaPesquisa = 7}
    Aluno: {CodAluno = 16, CodAreaPesquisa = 7}
    
    GrupoPesquisa: Areas = 8 
    Alunos
    Aluno: {CodAluno = 2, CodAreaPesquisa = 8}
    Aluno: {CodAluno = 12, CodAreaPesquisa = 8}
    
    GrupoPesquisa: Areas = 9 
    Alunos
    Aluno: {CodAluno = 10, CodAreaPesquisa = 9}
    Aluno: {CodAluno = 20, CodAreaPesquisa = 9}
    
    GrupoPesquisa: Areas = 10 
    Alunos
    Aluno: {CodAluno = 7, CodAreaPesquisa = 10}
    Aluno: {CodAluno = 17, CodAreaPesquisa = 10}
    
    GrupoPesquisa: Areas = 1,2,3,4,5,6 
    Alunos
    Aluno: {CodAluno = 3, CodAreaPesquisa = 1}
    Aluno: {CodAluno = 13, CodAreaPesquisa = 1}
    Aluno: {CodAluno = 5, CodAreaPesquisa = 2}
    Aluno: {CodAluno = 15, CodAreaPesquisa = 2}
    Aluno: {CodAluno = 4, CodAreaPesquisa = 3}
    Aluno: {CodAluno = 14, CodAreaPesquisa = 3}
    Aluno: {CodAluno = 1, CodAreaPesquisa = 4}
    Aluno: {CodAluno = 11, CodAreaPesquisa = 4}
    Aluno: {CodAluno = 9, CodAreaPesquisa = 5}
    Aluno: {CodAluno = 19, CodAreaPesquisa = 5}
    Aluno: {CodAluno = 8, CodAreaPesquisa = 6}
    Aluno: {CodAluno = 18, CodAreaPesquisa = 6}

Observe que o programa mesclou os primeiros seis grupos já que eram os que tinham o menor grau de dissimilaridade entre eles.

#### Segundo teste
Testando com outra matriz de dissimilaridade e os mesmos alunos e grupos de pesquisa:

Matriz de dissimilaridade:


    0 40 50 60 70 80 90 40 50 60
    40 0 30 40 50 60 70 80 90 10
    50 30 0 60 70 80 90 40 50 60
    60 40 60 0 40 70 40 90 60 10
    70 50 70 40 0 60 70 80 90 90
    80 60 80 70 60 0 40 50 60 70
    90 70 90 40 70 40 0 40 50 60
    40 80 40 80 80 50 40 0 80 90
    50 90 50 60 90 60 50 80 0 90
    60 10 60 10 90 70 60 90 90 0

Alunos e grupos de pesquisa: 


    01 04
    02 08
    03 01
    04 03
    05 02
    06 07
    07 10
    08 06
    09 05
    10 09
    11 04
    12 08
    13 01
    14 03
    15 02
    16 07
    17 10
    18 06
    19 05
    20 09

Resultados apresentados quando executamos o código com essas entradas e considerando K = 5, o programa irá imprimir o seguinte resultado:



    Quantidade de grupos: 5
    GrupoPesquisa: Areas = 5 
    Alunos
    Aluno: {CodAluno = 9, CodAreaPesquisa = 5}
    Aluno: {CodAluno = 19, CodAreaPesquisa = 5}
    
    GrupoPesquisa: Areas = 9 
    Alunos
    Aluno: {CodAluno = 10, CodAreaPesquisa = 9}
    Aluno: {CodAluno = 20, CodAreaPesquisa = 9}
    
    GrupoPesquisa: Areas = 1,8 
    Alunos
    Aluno: {CodAluno = 3, CodAreaPesquisa = 1}
    Aluno: {CodAluno = 13, CodAreaPesquisa = 1}
    Aluno: {CodAluno = 2, CodAreaPesquisa = 8}
    Aluno: {CodAluno = 12, CodAreaPesquisa = 8}
    
    GrupoPesquisa: Areas = 6,7 
    Alunos
    Aluno: {CodAluno = 8, CodAreaPesquisa = 6}
    Aluno: {CodAluno = 18, CodAreaPesquisa = 6}
    Aluno: {CodAluno = 6, CodAreaPesquisa = 7}
    Aluno: {CodAluno = 16, CodAreaPesquisa = 7}
    
    GrupoPesquisa: Areas = 4,2,10,3 
    Alunos
    Aluno: {CodAluno = 1, CodAreaPesquisa = 4}
    Aluno: {CodAluno = 11, CodAreaPesquisa = 4}
    Aluno: {CodAluno = 5, CodAreaPesquisa = 2}
    Aluno: {CodAluno = 15, CodAreaPesquisa = 2}
    Aluno: {CodAluno = 7, CodAreaPesquisa = 10}
    Aluno: {CodAluno = 17, CodAreaPesquisa = 10}
    Aluno: {CodAluno = 4, CodAreaPesquisa = 3}
    Aluno: {CodAluno = 14, CodAreaPesquisa = 3}
### Terceiro teste
Para o terceiro teste mudaremos os alunos, diminuindo a quantidade total e usaremos a mesma matriz de dissimilaridades do primeiro teste e um K menor, sendo K = 3.
	
Matriz de dissimilaridade:


    0 10 20 30 40 50 60 70 80 90
    10 0 20 30 40 50 60 70 80 90
    20 20 0 30 40 50 60 70 80 90
    30 30 30 0 40 50 60 70 80 90
    40 40 40 40 0 50 60 70 80 90
    50 50 50 50 50 0 60 70 80 90
    60 60 60 60 60 60 0 70 80 90
    70 70 70 70 70 70 70 0 80 90
    80 80 80 80 80 80 80 80 0 90
    90 90 90 90 90 90 90 90 90 0

Alunos e grupos de pesquisa:


    01 07
    02 05
    03 10
    04 06
    05 08
    06 02
    07 01
    08 09
    09 04
    10 03

Resultados exatamente como esperado, como a matriz de dissimilaridade vai crescendo de acordo com o grupo de pesquisa era esperado que 9 e 10 ficassem sozinhos.



    Quantidade de grupos: 3
    GrupoPesquisa: Areas = 9 
    Alunos
    Aluno: {CodAluno = 8, CodAreaPesquisa = 9}
    
    GrupoPesquisa: Areas = 10 
    Alunos
    Aluno: {CodAluno = 3, CodAreaPesquisa = 10}
    
    GrupoPesquisa: Areas = 1,2,3,4,5,6,7,8 
    Alunos
    Aluno: {CodAluno = 7, CodAreaPesquisa = 1}
    Aluno: {CodAluno = 6, CodAreaPesquisa = 2}
    Aluno: {CodAluno = 10, CodAreaPesquisa = 3}
    Aluno: {CodAluno = 9, CodAreaPesquisa = 4}
    Aluno: {CodAluno = 2, CodAreaPesquisa = 5}
    Aluno: {CodAluno = 4, CodAreaPesquisa = 6}
    Aluno: {CodAluno = 1, CodAreaPesquisa = 7}
    Aluno: {CodAluno = 5, CodAreaPesquisa = 8}
### Conclusão
Com este trabalho pudemos colocar em prática os algoritmos estudados em aula durante o semestre. E apesar de termos usado o algoritmo de Kruskal para resolver a matriz de dissimilaridade e aproximar os vértices para então resolver o problema, acredito que o mesmo possa ser resolvido com o uso de algoritmos diferentes como o de coloração talvez.
Logo percebe-se que os algoritmos para grafos podem ser usados em vários cenários diferentes e o uso ou não de um algoritmo depende de como está se modelando o problema proposto e qual informação é mais valiosa e qual algoritmo te dará a resposta de forma mais simples para o problema.
	

