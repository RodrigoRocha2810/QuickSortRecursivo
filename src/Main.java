
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int maxV = 1000000, M = -1, escolha;
    static long startTime, endTime;
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (escolha != 4) {
                clearScreen(); 
                System.out.println("=== MENU PRINCIPAL ===");
                System.out.println("[1] - QuickSort Recursivo");
                System.out.println("[2] - Teste Empírico");
                System.out.println("[3] - QuickSort Híbrido (requer teste empírico para definir M)");
                System.out.println("[4] - Sair");
                System.out.println("[C] - Limpar Tela");
                System.out.print("\nEscolha uma opção: ");
                
                String input = scanner.nextLine().trim();
                
                // limpa a tela se o usuário digitar 'C' ou 'c'
                if (input.equalsIgnoreCase("C")) {
                    clearScreen();
                    continue;
                }
                
                try {
                    escolha = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida! Pressione Enter para continuar...");
                    scanner.nextLine();
                    continue;
                }

                switch (escolha) {
                    case 1 -> {
                        // Preenche o array com maxV valores aleatórios
                        int[] randomArray = generateRandomArray(maxV);

                        // Mostra os primeiros 20 valores antes da ordenação
                        System.out.println("Primeiros 20 valores antes da ordenação:");
                        printFirstN(randomArray, 20);

                        startTime = System.nanoTime();
                        QuickSortR.quicksort(randomArray, 0, randomArray.length - 1);
                        endTime = System.nanoTime();
                        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos

                        System.out.println("\nArray completo ordenado:");
                        printArray(randomArray);

                        System.out.println("\n=== RESULTADOS ===");
                        System.out.println("Tamanho do array: " + randomArray.length);
                        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
                        
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 2 -> {
                        // Chame aqui o método do teste empírico
                        M = TesteEmpirico.run();
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 3 -> {
                        if (M == -1) {
                            System.out.println("Você precisa rodar o Teste Empírico primeiro para definir M.");
                            pressEnterToClear(scanner);
                            escolha = -1; 
                            return;
                        }
                        QuicksortHibrido sorter = new QuicksortHibrido(M);
                        // to do
                        System.out.println("QuickSort Híbrido executado com M = " + M);
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 4 -> {
                        clearScreen();
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> {
                        System.out.println("Opção inválida! Pressione Enter para continuar...");
                        scanner.nextLine();
                        escolha = -1; // Reset to continue menu loop
                    }
                }
            }
        }

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
        for (int i = (int) (0.9 * maxV); i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
     /**
     * Clears the console screen
     */
    public static void clearScreen() {
        try {
            // For Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix/Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Fallback: print multiple newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Waits for user to press Enter and then clears the screen
     */
    public static void pressEnterToClear(Scanner scanner) {
        System.out.println("\nPressione Enter para limpar a tela e continuar...");
        scanner.nextLine();
        clearScreen();
    }
}
