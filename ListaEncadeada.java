

import java.util.function.Consumer;
import java.util.function.Predicate;

/*
    =====  METODOS DISPONIVEIS  =====

    void inserir(T elemento)
        - Insere um elemento na lista
    
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
    
    void limpar()
        - Limpa a lista, removendo e desalocando todos os elementos
*/


public class ListaEncadeada<T>{
    private NoEncadeado<T> primeiro = null;
    private NoEncadeado<T> ultimo = null;
    private int tamanho = 0;

    public void inserir(T elemento){
        NoEncadeado<T> novoNo = new NoEncadeado<T>(elemento);

        if(primeiro == null){
            primeiro = novoNo;
            ultimo = novoNo;
        }else{
            ultimo.proximo = novoNo;
            ultimo = novoNo;
        }

        tamanho++;
    }

    public void exibir(){
        NoEncadeado<T> noAtual = primeiro;
        int i = 0;

        while(noAtual != null){
            System.out.println("\tlista[" + i + "] = " + noAtual.elemento);
            noAtual = noAtual.proximo;
            i++;
        }
    }

    public void exibir(Consumer<T> funcao){
        NoEncadeado<T> noAtual = primeiro;

        while(noAtual != null){
            funcao.accept(noAtual.elemento);
            noAtual = noAtual.proximo;
        }
    }

    public int tamanho(){
        return tamanho;
    }

    public void alterar(int posicao, T elemento){
        int i = 0;
        NoEncadeado<T> noAtual = primeiro;

        while(noAtual != null){
            if(i == posicao){
                noAtual.elemento = elemento;
                break;
            }

            noAtual = noAtual.proximo;
            i++;
        }
    }

    public T elemento(int posicao){
        int i = 0;
        NoEncadeado<T> noAtual = primeiro;

        while(noAtual != null){
            if(i == posicao){
                return noAtual.elemento;
            }

            noAtual = noAtual.proximo;
            i++;
        }

        System.out.println("Indice invalido");
        return null;
    }

    public int posicao(T elemento){
        int i = 0;
        NoEncadeado<T> noAtual = primeiro;

        while(noAtual != null){
            if(noAtual.elemento.equals(elemento)){
                return i;
            }

            noAtual = noAtual.proximo;
            i++;
        }

        System.out.println("Elemento nao encontrado");
        return -1;
    }

    public int posicao(Predicate<T> condicao){
        int i = 0;
        NoEncadeado<T> noAtual = primeiro;

        while(noAtual != null){
            if(condicao.test(noAtual.elemento)){
                return i;
            }

            noAtual = noAtual.proximo;
            i++;
        }

        System.out.println("Elemento nao encontrado");
        return -1;
    }

    public void limpar(){
        NoEncadeado<T> noAtual = primeiro;
        NoEncadeado<T> proximo;

        while(noAtual != null){
            proximo = noAtual.proximo;
            noAtual.elemento = null; // Remove referência ao elemento armazenado
            noAtual.proximo = null; // Remove referência ao próximo nó
            noAtual = proximo;
        }

        primeiro = null;
        ultimo = null;
        tamanho = 0;
        System.gc();
    }
}