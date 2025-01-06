

import java.util.function.Consumer;
import java.util.function.Predicate;


/*
    -----  METODOS DISPONIVEIS  -----

    void inserir(T elemento)
        - Insere um elemento na lista
    
    void redimensionar(int capacidade)
        - Redimensiona a lista para a capacidade informada
    
    int tamanho()
        - Retorna o tamanho da lista, ou seja, a quantidade de elementos presentes na lista

    void alterar(int posicao, T elemento)
        - Altera o elemento na posição informada
    
    T elemento(int posicao)
        - Retorna o elemento na posição informada
    
    void exibir()
        - Exibe todos os elementos da lista
    
    void exibir(Consumer<T> funcao)
        - Exibe todos os elementos da lista utilizando uma função de exibição passada como parâmetro

    int posicao(T elemento)
        - Retorna a posição do elemento informado na lista
    
    int posicao(Predicate<T> funcao)
        - Retorna a posição do elemento que atende a condição passada como parâmetro

    void limpar()
        - Limpa a lista, removendo e desalocando todos os elementos
    
    T[] toArray()
        - Retorna um array contendo todos os elementos da lista

    int qOperacoes()
        - Retorna a quantidade de operações realizadas na lista

    int buscaBinaria(Object comparador, Comparacao<T> comparacao)
        - Realiza uma busca binária na lista, retornando a posição do elemento que atende a condição passada como parâmetro

    int buscaInterpolada(T elemento, Comparacao<T> comparator)
        - Realiza uma busca interpolada na lista, retornando a posição do elemento que atende a condição passada como parâmetro



*/

public class ListaIndexada<T> {
    private T[] lista;
    private int tamanho = 0;
    private int capacidade = 0;
    private int qOperacoes = 0;

    @SuppressWarnings("unchecked")
    public void inserir(T elemento){
        if (tamanho == capacidade) {
            capacidade += 1; 
            T[] novoArray = (T[]) new Object[capacidade];
            try{
                System.arraycopy(lista, 0, novoArray, 0, tamanho); 
            }catch(Exception e){
                // n faz nada
            }
            lista = novoArray;
            System.gc();
        }

        lista[tamanho++] = elemento;
    }

    @SuppressWarnings("unchecked")
    public void redimensionar(int capacidade){
        T[] novoArray = (T[]) new Object[capacidade];

        if(lista != null)
            System.arraycopy(lista, 0, novoArray, 0, tamanho); 
            
        lista = novoArray;
        this.capacidade = capacidade;
        
        System.gc();
    }

    public int tamanho(){
        return tamanho;
    }

    public void alterar(int posicao, T elemento){
        if(posicao < 0 || posicao > tamanho){
            System.out.println("Indice invalido");
            return;
        }

        lista[posicao] = elemento;
    }

    public T elemento(int posicao){
        if(posicao < 0 || posicao > tamanho){
            System.out.println("Indice invalido");
            return null;
        }

        return lista[posicao];
    }

    public void exibir(){
        qOperacoes = 0;
        for(int i = 0; i < tamanho; i++){
            qOperacoes++;
            System.out.println("\tlista[" + i + "] = " + lista[i]);
        }
    }

    /*
        Exibir com uma funcao propria do tipo T
    */
    public void exibir(Consumer<T> funcao){
        try{

            for(int i = 0; i < tamanho; i++){
                funcao.accept(lista[i]);
            }

        }catch(Exception e){
            System.out.println("Erro ao exibir");
        }
    }

    public int posicao(T elemento){
        qOperacoes = 0;
        for(int i = 0; i < tamanho; i++){
            qOperacoes++;
            if(lista[i].equals(elemento)){
                return i;
            }
        }

        System.out.println("Elemento nao encontrado");
        return -1;
    }

    // Retorna a primeira posicao em que a funcao passada como parametro retorna true
    public int posicao(Predicate<T> condicao){
        qOperacoes = 0;
        for(int i = 0; i < tamanho; i++){
            qOperacoes++;
            if(condicao.test(lista[i])){
                return i;
            }
        }

        System.out.println("Elemento nao encontrado");
        return -1;

    }

    public void limpar(){
        qOperacoes = 0;
        for(int i = 0; i < tamanho; i++){
            qOperacoes++;
            lista[i] = null;
        }
        tamanho = 0;
        lista = null;
        capacidade = 0;
        System.gc();
    }
    
    @SuppressWarnings("unchecked")
    public T[] toArray(){
        T[] novoArray = (T[]) new Object[tamanho];
        System.arraycopy(lista, 0, novoArray, 0, tamanho);
        return novoArray;
    }

    public int qOperacoes(){
        return qOperacoes;
    }

    public int buscaBinaria(Comparacao<T> comparacao) {
        int esquerda = 0;
        int direita = this.tamanho - 1;
        int meio;
        qOperacoes = 0;

        while (esquerda <= direita) {
            qOperacoes++;
            meio = esquerda + (direita - esquerda)/2;
            int resultadoComparacao = comparacao.comparar(this.lista[meio]);

            if (resultadoComparacao == 0) {
                return meio;
            } else if (resultadoComparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }

}