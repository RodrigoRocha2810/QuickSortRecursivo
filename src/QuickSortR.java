public class QuickSortR {
    // Implementação do QuickSort Recursivo
    public static void quicksort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particiona(vetor, inicio, fim);
            quicksort(vetor, inicio, indicePivo - 1);
            quicksort(vetor, indicePivo + 1, fim);
        }
    }
    // Implementação do QuickSort Recursivo com tamanho M
    public static void quicksortM(int[] array, int inicio, int fim, int M) {
        // A condição de parada agora é baseada no tamanho M
        if ((fim - inicio + 1) >= M) {
            int indicePivo = particiona(array, inicio, fim);
            quicksortM(array, inicio, indicePivo - 1, M);
            quicksortM(array, indicePivo + 1, fim, M);
        }
    }
    // Implementação do QuickSort Recursivo com mediana de três e tamanho M
    























  private static int particiona(int[] vetor, int inicio, int fim) {


        int pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                troca(vetor, i, j);
            }
        }
        troca(vetor, i + 1, fim);
        return i + 1;
    }

    private static void troca(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}