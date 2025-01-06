import java.util.Scanner;


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

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

    public ListaSaltos<Sudoku> Sudoku_TabSaltos() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(caminhoArquivo));

        ListaSaltos<Sudoku> lista = new ListaSaltos<Sudoku>(9_000_001);
        String[] linha;
        
        for(int i=0; i < qtd_linhas; i++){
            linha = scan.nextLine().split(",");
            Sudoku s = new Sudoku(linha[0], linha[1]);
            lista.inserir(s);
        }

        scan.close();
        return lista;
    }

    public ListaSaltos<Sudoku> Sudoku_TabSaltos2() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(caminhoArquivo));

        ListaSaltos<Sudoku> lista = new ListaSaltos<Sudoku>(9_000_001);
        String[] linha;
        
        for(int i=0; i < qtd_linhas; i++){
            linha = scan.nextLine().split(",");
            Sudoku s = new Sudoku(linha[0], linha[1]);
            lista.inserirMetodo2(s);
        }

        scan.close();
        return lista;
    }
}