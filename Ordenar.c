#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char puzzle[82];
    char solution[82];
} Sudoku;

typedef unsigned long long llu;

// Função de comparação para o qsort
int compare(const void *a, const void *b) {
    Sudoku *p1 = (Sudoku *)a;
    Sudoku *p2 = (Sudoku *)b;
    return strcmp(p1->puzzle, p2->puzzle);
}

int main() {
    FILE *arq;

    arq = fopen("sudoku.csv", "r");
    if (!arq) {
        printf("Erro ao abrir o arquivo de entrada\n");
        return 1;
    }

    // Aloca memória para 9 milhões de registros
    Sudoku *jogos = (Sudoku *) malloc(9000001 * sizeof(Sudoku));
    if (!jogos) {
        printf("Erro de memória\n");
        fclose(arq);
        return 1;
    }

    llu i = 0;
    while (fscanf(arq, "%81s,%81s", jogos[i].puzzle, jogos[i].solution) == 2) {
        i++;
        if (i >= 9000001) {
            printf("Limite de registros excedido\n");
            break;
        }
    }

    fclose(arq);
    printf("Terminei de ler %d registros, agora vou escrever\n", i);

    // Ordena o array de estruturas
    qsort(jogos, i, sizeof(Sudoku), compare);

    // Escreve no arquivo de saída
    arq = fopen("sudoku_ordenado.csv", "w");
    if (!arq) {
        printf("Erro ao abrir o arquivo para escrita\n");
        free(jogos);
        return 1;
    }

    for (llu j = 0; j < i; j++) {
        fprintf(arq, "%s,%s\n", jogos[j].puzzle, jogos[j].solution);
    }

    fclose(arq);
    free(jogos);

    printf("Arquivo ordenado salvo com sucesso!\n");
    return 0;
}
