public class QuickSort {

    public static void quickSort(int[] vetor, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particionar(vetor, baixo, alto);

            quickSort(vetor, baixo, indicePivo - 1);
            quickSort(vetor, indicePivo + 1, alto);
        }
    }

    private static int particionar(int[] vetor, int baixo, int alto) {
        int pivo = vetor[alto];
        int i = (baixo - 1);

        for (int j = baixo; j < alto; j++) {
            if (vetor[j] < pivo) {
                i++;
                trocar(vetor, i, j);
            }
        }
        trocar(vetor, i + 1, alto);
        return i + 1;
    }

    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        int[] vetor = {10, 7, 8, 9, 1, 5};
        quickSort(vetor, 0, vetor.length - 1);
        for (int num : vetor) {
            System.out.print(num + " ");
        }
    }
}