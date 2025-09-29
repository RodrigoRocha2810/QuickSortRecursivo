import java.util.Random;

public class Main {
    static int maxV = 1000000;
    public static void main(String[] args) throws Exception {

        

        System.out.println("Testando QuickSort com " + maxV + " valores aleatórios");

        // Preenche o array com maxV valores aleatórios
        int[] randomArray = generateRandomArray(maxV);

        // Mostra os primeiros 20 valores antes da ordenação
        System.out.println("Primeiros 20 valores antes da ordenação:");
        printFirstN(randomArray, 20);

        // Mede o tempo de execução do QuickSort
        long startTime = System.nanoTime();
        QuickSortR.quicksort(randomArray, 0, randomArray.length - 1);
        long endTime = System.nanoTime();

        // Calcula o tempo de execução
        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos

        // Mostra o array completo ordenado
        System.out.println("\nArray completo ordenado:");
        printArray(randomArray);

        // Exibe os resultados
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Tamanho do array: " + randomArray.length);
        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
    }

    
      //Gera um array com inteiros aleatórios
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxV) + 1; // Valores aleatórios de 1 a 10000
        }
        return array;
    }

    
     // Imprime os primeiros N elementos de um array
   
    private static void printFirstN(int[] array, int n) {
        for (int i = 0; i < Math.min(n, array.length); i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("...");
    }

    
     //Imprime 10% finais dos elementos de um array
     
    private static void printArray(int[] array) {
        for (int i = (int)(0.9*maxV); i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}