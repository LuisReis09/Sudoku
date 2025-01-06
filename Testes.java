
import java.io.*;


public class Testes{
    public static void main(String[] args) throws IOException{

        LeitorArquivo leitor = new LeitorArquivo("sudoku_ordenado.csv", 9_000_000);
        String[] pesquisadas = new String[100000];
        Buscas buscar = new Buscas();

        long tempoInicio;
        long[] tempoMetodo1, tempoMetodo2;
        int qOperacoes1[] = new int[100000];
        int qOperacoes2[] = new int[100000];
        tempoMetodo1 = new long[100000];
        tempoMetodo2 = new long[100000];


        ListaSaltos<Sudoku> listaSaltos;
        try{
            listaSaltos = leitor.Sudoku_TabSaltos2();
        } catch(Exception e){
            System.out.println("Erro ao ler arquivo!");
            e.printStackTrace();
            return;
        }


        BufferedReader reader = new BufferedReader(new FileReader("sudoku_ordenado.csv"));
        
        for(int i = 0; i < 100000; i++){
            pesquisadas[i] = reader.readLine().split(",")[0];
            final String input = pesquisadas[i];
            tempoInicio = System.nanoTime();
            Sudoku pesq = buscar.elemento_metodo2(
                new Aceitacao<Sudoku>() {
                    public boolean aceitar(Sudoku el){
                        return el.puzzle.compareTo(input) == 0;
                    }
                },
                (el) -> {
                    return el.puzzle.compareTo(input) > 0;
                },
                listaSaltos
            );
            tempoMetodo2[i] = System.nanoTime() - tempoInicio;
            qOperacoes2[i] = buscar.qOperacoes();
            if(pesq.puzzle.compareTo(input) != 0){
                System.out.println("MET2 - Erro na pesquisa " + i);
                break;
            }
        }

        System.out.println("--------------------");

        int j;
        for(j = 0; j < 100000; j++){
            // A cada 50 pesquisas, redefine a lista

            if(j % 1000 == 0){
                listaSaltos.limpar();
                listaSaltos = null;
                System.gc();
                try{
                    listaSaltos = leitor.Sudoku_TabSaltos();
                } catch(Exception e){
                    System.out.println("Erro ao ler arquivo!");
                    e.printStackTrace();
                    return;
                }
                System.out.println("Recriou lista");
            }

            final String input = pesquisadas[j];
            tempoInicio = System.nanoTime();
            Sudoku pesq = buscar.elemento(
                new Aceitacao<Sudoku>() {
                    public boolean aceitar(Sudoku el){
                        return el.puzzle.compareTo(input) == 0;
                    }
                },
                (el) -> {
                    return el.puzzle.compareTo(input) > 0;
                },
                listaSaltos
            );
            tempoMetodo1[j] = System.nanoTime() - tempoInicio;
            qOperacoes1[j] = buscar.qOperacoes();
            if(pesq==null){
                System.out.println("MET1 - Erro na pesquisa " + input);
                break;
            }
        }


        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultados_comparacoes2.txt"));
            int s1 = 0, s2 = 0;
            for(int i=0; i<j; i++){
                writer.write(pesquisadas[i] + "\t(" + tempoMetodo1[i] + ", " + qOperacoes1[i] + ") --- (" + tempoMetodo2[i] + ", " + qOperacoes2[i] + ")\n");
                if(tempoMetodo1[i] < tempoMetodo2[i])
                    s1++;
                else
                    s2++;
            }
            writer.write("Metodo 1: " + s1 + "\nMetodo 2: " + s2);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        reader.close();
    }

}
