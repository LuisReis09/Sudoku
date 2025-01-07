# Sudoku Search

Este projeto foi desenvolvido como atividade da disciplina de **Estruturas de Dados e Algoritmos II** e tem como objetivo implementar algoritmos de busca para encontrar soluções de Sudoku em um arquivo CSV contendo aproximadamente 9 milhões de dados. Os algoritmos implementados incluem:

- **Lista com Saltos (Skip List)**
- **Busca Binária**
- **Busca Interpolada**

## Integrantes do Grupo
- [**Herick José de Freitas**](https://github.com/Herickjf)
- [**João Marcos Cunha Santos**](https://github.com/j4marcos)
- **Luis Gustavo Lacerda Reis**
- [**Rafael de França Silva**](https://github.com/rafaelfranca1)

## Tecnologias Utilizadas
- **Linguagem:** Java
- **Interface Gráfica:** Java Swing

## Funcionalidades
A aplicação possui as seguintes telas:

1. **Info (Tela Inicial):** Apresenta informações do projeto, como os membros do grupo e o tempo para carregar as listas.
2. **Search (Tela de Input):** Permite a entrada do Sudoku que será buscado.
3. **Solutions (Tela de Output Inicial):** Mostra o setup inicial da busca e o resultado encontrado.
4. **Analytics (Tela Secundária do Output):** Apresenta uma comparação detalhada entre os métodos de busca, incluindo a quantidade de operações realizadas e o tempo de execução.

## Como Executar

1. **Compilação:**
   ```bash
   javac Main.java
   ```

2. **Execução:**
   ```bash
   java -Xms2g -Xmx8g Main
   ```

## Preparação do Arquivo CSV

O arquivo CSV com os dados de Sudoku **não está presente no repositório** ou no arquivo ZIP, pois ele é muito pesado para ser incluído. 
Porém, é possível baixá-lo no seguinte link: https://www.kaggle.com/datasets/rohanrao/sudoku.

Após instalar, será necessário ordenar o arquivo para utilizá-lo. Para isso, siga os passos abaixo:

1. Utilize o programa `ordenar.c` para ordenar o arquivo CSV.
   - Certifique-se de ajustar o caminho e o nome do arquivo dentro do programa C, caso necessário.

2. Compile e execute o programa `ordenar.c` para gerar o arquivo CSV ordenado.
   ```bash
   gcc ordenar.c -o ordenar
   ./ordenar
   ```

3. Após a preparação do arquivo CSV, utilize-o na aplicação Java.

## Observações
- **ATENÇÃO**: o programa demanda muita memória RAM para ser executado, sugerimos que tenha pelo menos 8GB de memória RAM no computador que executar o programa.
- Certifique-se de alocar memória suficiente para a aplicação Java, conforme os parâmetros indicados nos comandos de execução.
- O arquivo CSV deve estar devidamente ordenado para garantir o funcionamento correto dos algoritmos de busca.

## Sobre o Projeto
Este projeto visa explorar e comparar a eficiência de diferentes algoritmos de busca em um grande conjunto de dados. A tela de Analytics oferece uma visão detalhada das operações realizadas por cada método, permitindo uma análise prática e teórica de sua eficiência.

Esperamos que esta aplicação seja útil para aprofundar o conhecimento em estruturas de dados e algoritmos avançados.

