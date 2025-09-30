import java.util.Arrays;
import java.util.Random;

public class TesteEmpirico {

    private static final int TAMANHO_VETOR = 1000;
    private static final int NUM_TESTES_POR_M = 100; // Para ter uma média confiável

    public static int run() {
        System.out.println("Iniciando teste empírico para encontrar o melhor M...");
        System.out.printf("Tamanho do vetor: %d | Testes por M: %d\n\n", TAMANHO_VETOR, NUM_TESTES_POR_M);

        int[] vetorOriginal = generateRandomArray(TAMANHO_VETOR);
        long melhorTempo = Long.MAX_VALUE;
        int melhorM = 0;

        // Vamos testar valores de M de 5 a 50
        for (int M = 5; M <= 50; M++) {
            long tempoTotal = 0;
            
            for (int i = 0; i < NUM_TESTES_POR_M; i++) {
                // É CRUCIAL usar uma cópia para cada teste!
                int[] copiaVetor = Arrays.copyOf(vetorOriginal, vetorOriginal.length);
                QuicksortHibrido sorter = new QuicksortHibrido(M);

                long inicio = System.nanoTime();
                sorter.ordenar(copiaVetor);
                long fim = System.nanoTime();
                
                tempoTotal += (fim - inicio);
            }

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

    /**
     * Gera um vetor de inteiros com valores aleatórios.
     */



    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(); // Gera qualquer valor int
        }
        return array;
    }
}


