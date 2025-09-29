
public class QuicksortHibrido {

     // Este valor será determinado empiricamente.
    private int M;

    public QuicksortHibrido(int m) {
        if (m < 1) {
            throw new IllegalArgumentException("M deve ser pelo menos 1.");
        }
        this.M = m;
    }

    /**
     * Método público que inicia a ordenação.
     * @param array O array a ser ordenado.
     */
    public void ordenar(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        QuickSortR.quicksortM(array, 0, array.length - 1, M);
        InsertionSort.sortTest(array, 0, array.length - 1);
    }


    // private void quicksort(int[] array, int inicio, int fim) {
    //     // A condição de parada agora é baseada no tamanho M
    //     if ((fim - inicio + 1) >= M) {
    //         int indicePivo = particionar(array, inicio, fim);
    //         quicksort(array, inicio, indicePivo - 1);
    //         quicksort(array, indicePivo + 1, fim);
    //     }
    // }

    /**
     * Método de particionamento (exatamente o mesmo do Quicksort padrão).
     */
    // private int particionar(int[] array, int inicio, int fim) {
    //     int pivo = array[fim];
    //     int i = (inicio - 1);

    //     for (int j = inicio; j < fim; j++) {
    //         if (array[j] <= pivo) {
    //             i++;
    //             trocar(array, i, j);
    //         }
    //     }
    //     trocar(array, i + 1, fim);
    //     return i + 1;
    // }

    /**
     * Implementação do Insertion Sort para um sub-vetor.
     */
    // private void insertionSort(int[] array, int inicio, int fim) {
    //     for (int i = inicio + 1; i <= fim; i++) {
    //         int chave = array[i];
    //         int j = i - 1;

    //         // Move os elementos de array[inicio..i-1] que são maiores que a chave
    //         // para uma posição à frente de sua posição atual
    //         while (j >= inicio && array[j] > chave) {
    //             array[j + 1] = array[j];
    //             j = j - 1;
    //         }
    //         array[j + 1] = chave;
    //     }
    // }

    /**
     * Método auxiliar para trocar elementos no array.
     */
    // private void trocar(int[] array, int i, int j) {
    //     int temp = array[i];
    //     array[i] = array[j];
    //     array[j] = temp;
    // }
}