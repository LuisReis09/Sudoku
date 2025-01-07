import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main {
    // atributos da janela
    // cores
    static Color corDeFundo = new Color(0xbfbfbf);
    // imagens
    static ImageIcon icone = new ImageIcon("assets\\icon.png");
    // components
    static Info info;
    static Search search = new Search();
    static Solutions solutions;
    static Analytics analytics = new Analytics();

    public static void main(String[] args) {
        System.gc();

        // Anter de carregar a janela, carrega o sudoku
        System.out.println("Carregando o arquivo Sudoku...");
        LeitorArquivo leitor = new LeitorArquivo("sudoku_ordenado.csv", 9_000_000);

        ListaSaltos<Sudoku> listaSaltos;
        ListaIndexada<Sudoku> listaIndex;

        long tempoListaIndexada = 0;
        long tempoListaSaltos = 0;

        try{
            long ti = System.currentTimeMillis();
            listaIndex = leitor.Sudoku_TabIndexada();
            long tf = System.currentTimeMillis();
            tempoListaIndexada = tf - ti;
            System.out.println("Lista indexada carregada com sucesso!  Tempo: " + tempoListaIndexada + "ms");

            ti = System.currentTimeMillis();
            listaSaltos = leitor.Sudoku_TabSaltos2();
            tf = System.currentTimeMillis();
            tempoListaSaltos = tf - ti;
            System.out.println("Lista com saltos carregada com sucesso!  Tempo: " + tempoListaSaltos + "ms");

        } catch(Exception e){
            System.out.println("Erro ao ler arquivo!");
            e.printStackTrace();
            return;
        }

        info = new Info(tempoListaIndexada, tempoListaSaltos);        
        
        JFrame janela = new JFrame("Sudoku - Search");
        // janela.setSize(750, 600);
        janela.setResizable(false);        
        janela.setIconImage(icone.getImage());
        
        // componente Info
        // janela.add(info);
        info.getStartBtn().addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                janela.remove(info);
                janela.add(search);
                janela.revalidate();
                janela.repaint();
            }
        });

        search.searchBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){                
                String input = search.t.getTabuleiro();
        
                if(!input.matches("[0-9]+")){
                    JOptionPane.showMessageDialog(janela, "Sudoku must have 81 characters, and only numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Resultados[] res = new Resultados[3];
                Buscas buscar = new Buscas();
                
                // Busca usando a lista com saltos:
                res[0] = new Resultados();
                res[0].metodo = "SkipList";
                long ti = System.nanoTime();
                res[0].sudoku = buscar.elemento_metodo2(
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
                long tf = System.nanoTime();
                res[0].tempo = (tf - ti)/1000;
                res[0].operacoes = buscar.qOperacoes();

                // Busca usando a busca binaria:
                res[1] = new Resultados();
                res[1].metodo = "Binary Search";
                ti = System.nanoTime();
                int busc = listaIndex.buscaBinaria((el) -> {
                    int comp = el.puzzle.compareTo(input);
                    if(comp == 0) return 0;
                    if(comp < 0) return -1;
                    return 1;
                });
                tf = System.nanoTime();
                res[1].operacoes = listaIndex.qOperacoes();
                res[1].sudoku = listaIndex.elemento(busc);

                res[1].tempo = (tf - ti)/1000;

                // Busca interpolada:
                res[2] = new Resultados();
                res[2].metodo = "Interpolated Search";
                ti = System.nanoTime();
                res[2].sudoku = buscar.buscaInterpolada(input, listaIndex);
                tf = System.nanoTime();
                res[2].tempo = (tf - ti)/1000;
                res[2].operacoes = buscar.qOperacoes();

                if (res[0].sudoku == null || res[1].sudoku == null || res[2].sudoku == null){
                    JOptionPane.showMessageDialog(janela, "Sudoku not founded", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                // System.out.println("Buscas feitas");
                
                solutions = new Solutions(input, res[1].sudoku.solution);
                analytics.update(res);
                
                setButtons(janela);
                janela.setSize(930, 600);
                janela.setLocationRelativeTo(null);
                janela.remove(search);
                janela.add(solutions);
                janela.revalidate();
                janela.repaint();
            }
        });

        search.randomBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String puzzle = listaIndex.elemento((int) (Math.random() * listaIndex.tamanho())).puzzle;
                search.inputLineField.setText(puzzle);
            }
        });

        // analytics.newPuzzleBtn.addMouseMotionListener(new MouseAdapter(){
        //     public void mouseClicked(MouseEvent e){
        //         janela.remove(analytics);
        //         janela.add(search);
        //         search.inputLineField.setText("");
        //             janela.revalidate();
        //         janela.repaint();
        //     }
        // });
        
        // componente Search
        
        janela.add(info);
        
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setLocationRelativeTo(null);
    }

    public static void setButtons(JFrame janela){
        solutions.analyticsBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                janela.remove(solutions);
                janela.add(analytics);
                janela.revalidate();
                janela.repaint();
            }
        });

        solutions.newPuzzleBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                janela.remove(solutions);
                janela.add(search);
                search.inputLineField.setText("");
                janela.revalidate();
                janela.repaint();
            }
        });

        analytics.newPuzzleBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                janela.remove(analytics);
                janela.add(search);
                search.inputLineField.setText("");
                janela.revalidate();
                janela.repaint();
            }
        });

        analytics.solutionBtn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                janela.remove(analytics);
                janela.add(solutions);
                janela.revalidate();
                janela.repaint();
            }
        });
    }
}
