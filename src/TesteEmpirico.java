
import java.util.Arrays;
import java.util.Random;

public class TesteEmpirico {

    private static final int TAMANHO_VETOR = 50000;
    private static final int NUM_TESTES_POR_M = 1000; //para ter uma média robusta
    private static final int NUM_EXECUCOES_AQUECIMENTO = 10000; // fase de aquecimento
    //Antes do loop principal que testa os valores de M,
    //um loop que executa o código de ordenação milhares de vezes sem medir o tempo.
    //Isso força a compilação JIT a otimizar todos os métodos (ordenar, quicksort, particionar,
    //insertionSort) antes que qualquer medição real comece. Forçando a JVM a "esquentar" e otimizar o código,
    //garantimos que os tempos medidos reflitam o desempenho otimizado do algoritmo.

    public static int run() {

        int[] vetorOriginal = gerarVetorAleatorio(TAMANHO_VETOR);

        // --- FASE DE AQUECIMENTO  ---
        System.out.println("Iniciando fase de aquecimento da JVM...");
        QuicksortHibrido sorterAquecimento = new QuicksortHibrido(10); // Usa um M qualquer
        for (int i = 0; i < NUM_EXECUCOES_AQUECIMENTO; i++) {
            int[] copiaVetor = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
            sorterAquecimento.ordenar(copiaVetor); // Executa o código para forçar a otimização JIT
        }
        System.out.println("Aquecimento concluído. Iniciando testes reais...");
        // ------------------------------------

        System.out.printf("Tamanho do vetor: %d | Testes por M: %d\n\n", TAMANHO_VETOR, NUM_TESTES_POR_M);

        long melhorTempo = Long.MAX_VALUE;
        int melhorM = 0;

        for (int M = 5; M <= 50; M++) {
            QuicksortHibrido sorter = new QuicksortHibrido(M);
            long inicio = System.nanoTime();
            //Se escolhermos um M muito grande (ex: M=100 para um vetor de 1000 elementos),
            //estaremos forçando o algoritmo a parar de particionar cedo demais. Com isso, 
            //o Insertion Sort final teria que ordenar blocos muito grandes. Ordenar um bloco
            //de 100 elementos com um algoritmo O(n2) será significativamente mais lento do que
            //deixar o Quicksort (O(nlogn)) particioná-lo mais algumas vezes.
            //Para valores de M extremamente pequenos (como 2, 3 ou 4), a otimização quase não traz benefícios.
            for (int i = 0; i < NUM_TESTES_POR_M; i++) {
                int[] copiaVetor = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
                sorter.ordenar(copiaVetor);
            }

            // Termina a medição DEPOIS que todos os testes para M foram concluídos
            long fim = System.nanoTime();

            long tempoTotal = fim - inicio;
            // O tempo médio é o tempo total dividido pelo número de testes
            long tempoMedio = tempoTotal / NUM_TESTES_POR_M;

            System.out.printf("M = %2d  | Tempo médio: %d nanossegundos\n", M, tempoMedio);

            if (tempoMedio < melhorTempo) {
                melhorTempo = tempoMedio;
                melhorM = M;
            }
        }

        System.out.println("\n-------------------------------------------------");
        System.out.printf("Conclusão: O melhor valor de M encontrado foi %d com um tempo médio de %d ns.\n", melhorM, melhorTempo);
        System.out.println("-------------------------------------------------");
        return melhorM;
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt();
        }
        return vetor;
    }
}
