import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
    Essa classe contem metodos especificamente projetados para a leitura do arquivo csv ordenado de entrada.

    Seu metodo construtor recebe o caminho do arquivo e a quantidade de linhas a serem lidas.
    A partir disso, disponibiliza os seguintes metodos publicos:

    1. Sudoku_TabIndexada()
        - Retorna uma lista indexada de Sudoku, com os dados do arquivo csv.

    2. Sudoku_TabEncadeada()
        - Retorna uma lista encadeada de Sudoku, com os dados do arquivo csv.

    3. Sudoku_TabSaltos(char)
        - Retorna uma lista de saltos de Sudoku, com os dados do arquivo csv.
        - Recebe um char que indica qual metodo de saltos sera utilizado.
            * '1' para o metodo tradicional, em que usa os niveis sao definidos de forma aleatoria.
            * '2' para o nosso metodo, em que os niveis serao pre-definidos de forma a tentar maximizar a eficiencia.

*/

public class LeitorArquivo{
    String caminhoArquivo;
    int qtd_linhas;

    public LeitorArquivo(String caminhoArquivo, int qtd_linhas){
        this.caminhoArquivo = caminhoArquivo;
        this.qtd_linhas = qtd_linhas;
    }

    public ListaIndexada<Sudoku> Sudoku_TabIndexada() throws IOException {
        BufferedReader arq = new BufferedReader(new FileReader(caminhoArquivo));

        ListaIndexada<Sudoku> lista = new ListaIndexada<Sudoku>();
        lista.redimensionar(9_000_000);
        String[] linha;

        for(int i=0; i < qtd_linhas; i++){
            linha = arq.readLine().split(",");
            Sudoku s = new Sudoku(linha[0], linha[1]);
            lista.inserir(s);
            // System.out.println(lista.elemento(i).puzzle);
        }

        arq.close();
        return lista;
    }

    public ListaEncadeada<Sudoku> Sudoku_TabEncadeada() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(caminhoArquivo));

        ListaEncadeada<Sudoku> lista = new ListaEncadeada<Sudoku>();
        String[] linha;
        
        for(int i=0; i < qtd_linhas; i++){
            linha = scan.nextLine().split(",");
            Sudoku s = new Sudoku(linha[0], linha[1]);
            lista.inserir(s);
        }

        scan.close();
        return lista;
    }

    public ListaSaltos<Sudoku> Sudoku_TabSaltos(char metodo) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(caminhoArquivo));

        ListaSaltos<Sudoku> lista = new ListaSaltos<Sudoku>(9_000_001, metodo);
        String[] linha;
        
        for(int i=0; i < qtd_linhas; i++){
            linha = scan.nextLine().split(",");
            Sudoku s = new Sudoku(linha[0], linha[1]);
            lista.inserir(s);
        }

        scan.close();
        return lista;
    }
}