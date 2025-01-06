

import java.awt.*;
import javax.swing.*;

public class Quadrante extends JPanel{
    // atributos
    private Casa[][] casas = new Casa[3][3];
    private boolean isSolution;

    // contrutores
    public Quadrante(String line, boolean isSolution){
        this.isSolution = isSolution;
        // logica 
        if(line.length() != 9){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }

        for(int i = 0; i < 9; i++){
            this.casas[i/3][i%3] = new Casa(line.charAt(i), isSolution);
        }


        // configuracoes
        this.setSize(120, 120);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x1e1e1e), 2));
        this.setLayout(new GridLayout(3,3,0,0));

        for (Casa[] linha : casas) {
            for (Casa casa : linha) {
                this.add(casa);
            }
        }
    }
    public Quadrante(String input, String output, boolean isSolution){
        this.isSolution = isSolution;
        // logica 
        if(input.length() != 9){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }

        for(int i = 0; i < 9; i++){
            this.casas[i/3][i%3] = new Casa(input.charAt(i), output.charAt(i), isSolution);
        }


        // configuracoes
        this.setSize(120, 120);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x1e1e1e), 2));
        this.setLayout(new GridLayout(3,3,0,0));

        for (Casa[] linha : casas) {
            for (Casa casa : linha) {
                this.add(casa);
            }
        }
    }

    // metodos
    public void mostrar(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(this.casas[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String getQuadrante(){
        String casas = "";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                casas += this.casas[i][j].getValor();
            }
        }
        return casas;
    }
    
    public void update(String line){
        if(line.length() != 9){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }

        // for(int i = 0; i < 9; i++){
        //     this.casas[i/3][i%3] = new Casa(line.charAt(i), this.isSolution);
        // }

        int lin = 0, col = 0;
        for(int i = 0; i < 9; i++){
            this.casas[lin][col].update(line.charAt(i));
            
            if(col == 2){
                col = 0;
                lin++;
            }else{
                col++;
            }
        }

    }
    
    public boolean isSolution() {
        return isSolution;
    }

    // public void update(String line){
    //     if(line.length() != 9){
    //         throw new IllegalArgumentException("Tamanho da string inválido");
    //     }

    //     // for(int i = 0; i < 9; i++){
    //     //     this.casas[i/3][i%3] = new Casa(line.charAt(i), this.isSolution);
    //     // }

    //     int lin = 0, col = 0;
    //     for(int i = 0; i < 9; i++){
    //         this.casas[lin][col].update(line.charAt(i));
            
    //         if(col == 2){
    //             col = 0;
    //             lin++;
    //         }else{
    //             col++;
    //         }
    //     }

    // }
}
