public class InsertionSort {
     // Implementação do Insertion Sort para um array completo.
 public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
 
 
    // Implementação do Insertion Sort para um sub-vetor.
    public static void sortTest(int[] array, int inicio, int fim) {
        for (int i = inicio + 1; i <= fim; i++) {
            int chave = array[i];
            int j = i - 1;

            // Move os elementos de array[inicio..i-1] que são maiores que a chave
            // para uma posição à frente de sua posição atual
            while (j >= inicio && array[j] > chave) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = chave;
        }
    }


}
