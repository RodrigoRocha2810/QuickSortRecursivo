public class QuickSortR {

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particiona(vetor, inicio, fim);
            quickSort(vetor, inicio, indicePivo - 1);
            quickSort(vetor, indicePivo + 1, fim);
        }
    }

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

    public static void main(String[] args) {
        int[] numeros = { 10, 7, 8, 9, 1, 5 };
        quickSort(numeros, 0, numeros.length - 1);
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}