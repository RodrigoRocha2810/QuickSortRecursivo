import java.util.Arrays;
import java.util.Random;

public class TesteEmpirico {

    private static final int TAMANHO_VETOR = 1000;
    private static final int NUM_TESTES_POR_M = 1000; // Aumentar para ter uma média mais robusta
    private static final int NUM_EXECUCOES_AQUECIMENTO = 10000; // Novo: fase de aquecimento

    public static int run() {
        
        int[] vetorOriginal = gerarVetorAleatorio(TAMANHO_VETOR);

        // --- FASE DE AQUECIMENTO (WARM-UP) ---
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
            long tempoTotal = 0;
            
            for (int i = 0; i < NUM_TESTES_POR_M; i++) {
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

    public static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt();
        }
        return vetor;
    }
}