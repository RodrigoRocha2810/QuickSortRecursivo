import java.util.Random;
import java.util.Scanner;

public class Main {
    static int maxV = 1000000, M=-1;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o algoritmo:");
        System.out.println("1 - QuickSort Recursivo");
        System.out.println("2 - Teste Empírico");
        System.out.println("3 - QuickSort Híbrido");
        int escolha = scanner.nextInt();

        System.out.println("Testando QuickSort com " + maxV + " valores aleatórios");

        // Preenche o array com maxV valores aleatórios
        int[] randomArray = generateRandomArray(maxV);

        // Mostra os primeiros 20 valores antes da ordenação
        System.out.println("Primeiros 20 valores antes da ordenação:");
        printFirstN(randomArray, 20);

        long startTime = System.nanoTime();

        System.out.println("[1] - QuickSort Recursivo \n[2] - Teste Empírico \n[3] - QuickSort Híbrido(requer teste empírico para definir M)");
        while(escolha < 4) {
        escolha = scanner.nextInt();
        scanner.nextLine();
        switch (escolha) {
            case 1:
                QuickSortR.quicksort(randomArray, 0, randomArray.length - 1);
                break;
            case 2:
                // Chame aqui o método do teste empírico
                M = TesteEmpirico.run();
                break;
            case 3:
                if (M == -1) {
                    System.out.println("Você precisa rodar o Teste Empírico primeiro para definir M.");
                    return;
                }
                QuicksortHibrido sorter = new QuicksortHibrido(M);
                break;
            case 4:
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Opção inválida.");
                return;
        }
    }
        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos

        System.out.println("\nArray completo ordenado:");
        printArray(randomArray);

        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Tamanho do array: " + randomArray.length);
        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
        scanner.close();
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