

import javax.swing.*;
import java.awt.*;

public class Tabuleiro extends JPanel{
    // atributos
    private Quadrante[][] quadrantes = new Quadrante[3][3];
    private boolean isSolution;
    // construtores
    public Tabuleiro(String line, boolean isSolution){
        this.isSolution = isSolution;

        // logica
        String[] linhas = new String[9];
        String[] quadrante = new String[9];
        for (int i = 0; i < 9; i++) {
            quadrante[i] = "";
        }

        if(line.length() > 81){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }else if(line.length() < 81){
            for(int i = 0; i < 81 - line.length(); i++){
                line += "0";
            }
        }
        // separa a string em linhas
        for(int i = 0; i < 9; i++){
            linhas[i] = line.substring(i*9, (i+1)*9);
        }
        // separa as linhas em quadrantes
        for (int i = 0; i < 9; i++) { 
            for (int j = 0; j < 9; j++) { 
                int quadIndex = (i / 3) * 3 + (j / 3);  
                quadrante[quadIndex] += linhas[i].charAt(j);
            }
        }
        // cria os quadrantes
        for(int i = 0; i < 9; i++){
            this.quadrantes[i/3][i%3] = new Quadrante(quadrante[i], this.isSolution);
        }

        this.setPreferredSize(new Dimension(360, 360));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setLayout(new GridLayout(3,3,0,0));

        for (Quadrante[] linha : quadrantes) {
            for (Quadrante qua : linha) {
                this.add(qua);
            }
        }


        // configuracoes
    }

    public Tabuleiro(String input, String output, boolean isSolution){
        this.isSolution = isSolution;

        // logica
        String[] linhasInput = new String[9];
        String[] linhasOutput = new String[9];
        String[] quadrante = new String[9];
        for (int i = 0; i < 9; i++) {
            quadrante[i] = "";
        }
        String[] quadranteOutput = new String[9];
        for (int i = 0; i < 9; i++) {
            quadranteOutput[i] = "";
        }

        if(input.length() > 81){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }else if(input.length() < 81){
            for(int i = 0; i < 81 - input.length(); i++){
                input += "0";
            }
        }

        if(output.length() > 81){
            throw new IllegalArgumentException("Tamanho da string inválido");
        }else if(output.length() < 81){
            for(int i = 0; i < 81 - output.length(); i++){
                output += "0";
            }
        }
        // separa a string em linhasInput
        // separa a string em linhasOutput
        for(int i = 0; i < 9; i++){
            linhasInput[i] = input.substring(i*9, (i+1)*9);
            linhasOutput[i] = output.substring(i*9, (i+1)*9);
        }
        // separa as linhasInput em quadrantes
        for (int i = 0; i < 9; i++) { 
            for (int j = 0; j < 9; j++) { 
                int quadIndex = (i / 3) * 3 + (j / 3);  
                quadrante[quadIndex] += linhasInput[i].charAt(j);
                quadranteOutput[quadIndex] += linhasOutput[i].charAt(j);
            }
        }
        // cria os quadrantes
        for(int i = 0; i < 9; i++){
            this.quadrantes[i/3][i%3] = new Quadrante(quadrante[i], quadranteOutput[i], this.isSolution);
        }

        this.setPreferredSize(new Dimension(360, 360));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setLayout(new GridLayout(3,3,0,0));

        for (Quadrante[] linha : quadrantes) {
            for (Quadrante qua : linha) {
                this.add(qua);
            }
        }
    }


    
    // metodos
    public void mostrar(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.quadrantes[i][j].mostrar();
                System.out.println();
            }
        }
    }

    public void update(String line){
        
        String[] linhas = new String[9];
        String[] quadrante = new String[9];
        for (int i = 0; i < 9; i++) {
            quadrante[i] = "";
        }

        // separa a string em linhas
        for(int i = 0; i < 9; i++){
            linhas[i] = line.substring(i*9, (i+1)*9);
        }
        // separa as linhas em quadrantes
        for (int i = 0; i < 9; i++) { 
            for (int j = 0; j < 9; j++) { 
                int quadIndex = (i / 3) * 3 + (j / 3);  
                quadrante[quadIndex] += linhas[i].charAt(j);
            }
        }
        // cria os quadrantes
        for(int i = 0; i < 9; i++){
            this.quadrantes[i/3][i%3].update(quadrante[i]);
        }

    }

    // public void update(String input, String output){
        
    //     // logica
    //     String[] linhasInput = new String[9];
    //     String[] linhasOutput = new String[9];
    //     String[] quadrante = new String[9];
    //     for (int i = 0; i < 9; i++) {
    //         quadrante[i] = "";
    //     }
    //     String[] quadranteOutput = new String[9];
    //     for (int i = 0; i < 9; i++) {
    //         quadranteOutput[i] = "";
    //     }

    //     if(input.length() > 81){
    //         throw new IllegalArgumentException("Tamanho da string inválido");
    //     }else if(input.length() < 81){
    //         for(int i = 0; i < 81 - input.length(); i++){
    //             input += "0";
    //         }
    //     }

    //     if(output.length() > 81){
    //         throw new IllegalArgumentException("Tamanho da string inválido");
    //     }else if(output.length() < 81){
    //         for(int i = 0; i < 81 - output.length(); i++){
    //             output += "0";
    //         }
    //     }
    //     // separa a string em linhasInput
    //     // separa a string em linhasOutput
    //     for(int i = 0; i < 9; i++){
    //         linhasInput[i] = input.substring(i*9, (i+1)*9);
    //         linhasOutput[i] = output.substring(i*9, (i+1)*9);
    //     }
    //     // separa as linhasInput em quadrantes
    //     for (int i = 0; i < 9; i++) { 
    //         for (int j = 0; j < 9; j++) { 
    //             int quadIndex = (i / 3) * 3 + (j / 3);  
    //             quadrante[quadIndex] += linhasInput[i].charAt(j);
    //             quadranteOutput[quadIndex] += linhasOutput[i].charAt(j);
    //         }
    //     }

    //     // cria os quadrantes
    //     for(int i = 0; i < 9; i++){
    //         this.quadrantes[i/3][i%3].update(quadrante[i], quadranteOutput[i], this.isSolution);
    //     }

    // }

    // getters e setters
    public Quadrante getQuadrante(int i, int j){
        return this.quadrantes[i][j];
    }
    public Quadrante[][] getQuadrantes(){
        return this.quadrantes;
    }

    public String getTabuleiro(){
        String tabuleiro = new String("");

        for(int i = 0; i < 3; i++){
            String sublinha1, sublinha2, sublinha3;
            String[] linhas = new String[3];

            for(int k = 0; k < 3; k++){
                for(int j = 0; j < 3; j++){
                    linhas[j] = new String("");
                    linhas[j] += this.quadrantes[i][j].getQuadrante();
                }
                sublinha1 = linhas[0].substring(k*3, (k+1)*3);
                sublinha2 = linhas[1].substring(k*3, (k+1)*3);
                sublinha3 = linhas[2].substring(k*3, (k+1)*3);

                tabuleiro += sublinha1 + sublinha2 + sublinha3;
            }
        }

        return tabuleiro;
    }

}
