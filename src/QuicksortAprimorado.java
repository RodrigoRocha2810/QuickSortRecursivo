public class QuicksortAprimorado {

    private final int M;

    public QuicksortAprimorado(int m) {
        this.M = m;
    }

    public void ordenar(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        quicksort(array, 0, array.length - 1);
        // A chamada final ao Insertion Sort continua sendo uma boa prática
        if (M > 1) { // Só é necessário se o Quicksort não ordenou tudo
             InsertionSort.sortTest(array, 0, array.length - 1);
        }
    }

    private void quicksort(int[] array, int inicio, int fim) {
        // A condição de parada agora é baseada no tamanho M
        if (fim - inicio + 1 < M) {
            return; // Sub-vetores pequenos serão ordenados pelo Insertion Sort no final
        }
        
        int pivo = medianaDeTres(array, inicio, fim);
        
        // --- LÓGICA DE PARTIÇÃO DE HOARE ---
        int i = inicio - 1;
        int j = fim;

        while (true) {
            // Avança 'i' enquanto os elementos forem menores que o pivô
            while (array[++i] < pivo);

            // Recua 'j' enquanto os elementos forem maiores que o pivô
            while (array[--j] > pivo);

            // Se os ponteiros se cruzaram ou se encontraram, a partição terminou
            if (i >= j) {
                break;
            }

            // Troca os elementos que estão fora do lugar
            trocar(array, i, j);
        }
        // ------------------------------------

        // Chamadas recursivas
        quicksort(array, inicio, j);
        quicksort(array, j + 1, fim);
    }
    
    private int medianaDeTres(int[] array, int inicio, int fim) {
        int meio = inicio + (fim - inicio) / 2;

        if (array[inicio] > array[meio]) trocar(array, inicio, meio);
        if (array[inicio] > array[fim]) trocar(array, inicio, fim);
        if (array[meio] > array[fim]) trocar(array, meio, fim);

        // O pivô (mediana) é o valor em 'meio'.
        // Não precisamos mais movê-lo, pois o esquema de Hoare pode lidar com ele em qualquer lugar.
        // Mas movê-lo para o final simplifica a lógica de partição, então vamos fazer isso.
        trocar(array, meio, fim);
        return array[fim];
    }
    
    // --- MÉTODOS AUXILIARES (sem alteração) ---
    private void insertionSort(int[] array, int inicio, int fim) { /* ...código inalterado... */ }
    private void trocar(int[] array, int i, int j) { /* ...código inalterado... */ }
}