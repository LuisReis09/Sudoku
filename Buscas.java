import java.math.BigInteger;
import java.util.function.Predicate;


public class Buscas {
    private int qOperacoes = 0;
    
    public Sudoku buscaInterpolada(String input, ListaIndexada<Sudoku> listaIndex){
        int inf = 0;
        int sup = listaIndex.tamanho() - 1;
        int meio;
        qOperacoes = 0;
        
        while (inf <= sup) {
            qOperacoes++;
            
            meio = calcularMeio(inf, sup, input, listaIndex).intValue();
            if(meio < 0 || meio >= listaIndex.tamanho()){
                return null;
            }

            String pesquisa = listaIndex.elemento(meio).puzzle;
            if(pesquisa.equals(input)){
                return listaIndex.elemento(meio);
            }else if(pesquisa.compareTo(input) < 0){
                inf = meio + 1;
            }else{
                sup = meio - 1;
            }

        }


        return null;
    }

    public BigInteger calcularMeio(int inf, int sup, String chave, ListaIndexada<Sudoku> listaIndex){
        BigInteger infB = BigInteger.valueOf(inf);
        BigInteger supB = BigInteger.valueOf(sup);
        BigInteger chaveB = new BigInteger(chave);

        // (sup - inf)
        BigInteger meio = supB.subtract(infB);

        // (sup-inf) * (chave - lista[inf])
        meio = meio.multiply(chaveB.subtract(new BigInteger(listaIndex.elemento(inf).puzzle)));

        // (sup-inf) * (chave - lista[inf]) / (lista[sup] - lista[inf])
        try{
            meio = meio.divide(new BigInteger(listaIndex.elemento(sup).puzzle).subtract(new BigInteger(listaIndex.elemento(inf).puzzle)));
        }catch(ArithmeticException e){
            return BigInteger.valueOf(-1);
        }

        // inf + (sup-inf) * (chave - lista[inf]) / (lista[sup] - lista[inf])
        meio = infB.add(meio);

        return meio;
    }

    public Sudoku elemento(Aceitacao<Sudoku> aceitacao, Predicate<Sudoku> continuacao, ListaSaltos<Sudoku> listaSaltos){
        
        /*
            Continuacao deve ser uma funcao que retorna true se a procura deve seguir
            para a proxima chave (no mesmo nivel), e false caso contrario (ou seja, tenha que descer um nivel)
            
            Aceitacao deve ser uma funcao que retorna true se o elemento for aceito, e false caso contrario
        */
        qOperacoes = 0;

        int nivel_maximo = listaSaltos.getNivelMaximo();
        NoSaltos<Sudoku> atual, ultimo;
        NoSaltos<Sudoku>[] cabeca = listaSaltos.getCabeca();

        // Inicializa o ponteiro atual e ultimo no nivel maximo
        int i = nivel_maximo;
        while(true){
            if(cabeca[i] != null){
                atual = cabeca[i];
                ultimo = atual;
                break;
            }
            i--;
        }

        while(atual != null){
            qOperacoes++;
            
            if(!continuacao.test(atual.elemento)){

                // Se o elemento atual for aceito, retorna-o
                if(aceitacao.aceitar(atual.elemento))
                    return atual.elemento;

                // Se o elemento atual nao for aceito, temos de descer um nivel

                if(atual == ultimo){
                    i--;
                    while(i >= 0){
                        if(cabeca[i] != null){
                            atual = cabeca[i];
                            ultimo = atual;
                            break;
                        }
                        i--;
                    }

                }else{
                    i--;
                    if(i < 0) return null;
                    atual = ultimo.proximo[i];

                }

            }else{
                
                // Se chegar aqui, eh porque podemos continuar no mesmo nivel, se houver proximo elemento
                if(atual.proximo[i] != null){
                    ultimo = atual;
                    atual = atual.proximo[i];

                }else{
                    // Se nao houver proximo elemento, teremos que descer um nivel
                    i--;
                    while(i >= 0){
                        if(cabeca[i] != null){
                            atual = cabeca[i];
                            ultimo = atual;
                            break;
                        }
                        i--;
                    }
                }
            }

        }

        return null;
    }

    public Sudoku elemento_metodo2(Aceitacao<Sudoku> aceitacao, Predicate<Sudoku> continuacao, ListaSaltos<Sudoku> listaSaltos){
        qOperacoes = 0;

        int nivel_maximo = listaSaltos.getNivelMaximo();
        NoSaltos<Sudoku> atual = listaSaltos.getCabeca()[nivel_maximo];
        NoSaltos<Sudoku> ultimo = atual;
        NoSaltos<Sudoku>[] cabeca = listaSaltos.getCabeca();

        int i = nivel_maximo;

        while(atual != null){
            qOperacoes++;
            
            if(!continuacao.test(atual.elemento)){

                if(aceitacao.aceitar(atual.elemento))
                    return atual.elemento;
            

                if(atual == ultimo){
                    i--;
                    if (i < 0) return null;
                    atual = cabeca[i];
                    ultimo = atual;
                }else{
                    // Se o elemento em analise nao for o primeiro do nivel, entao temos que voltar para o ultimo e descer mais um nivel em relacao ao atual
                    i--;
                    if(i < 0) return null;
                    atual = ultimo.proximo[i];
                }
                
            
            }else{
 
                i--;
                while(i >= 0){
                    if(atual.proximo[i] != null){
                        ultimo = atual;
                        atual = atual.proximo[i];
                        break;
                    }
                    i--;
                }
 
            }
 
        }
 
        return null;
 
    }


    public int qOperacoes(){
        return qOperacoes;
    }
}
