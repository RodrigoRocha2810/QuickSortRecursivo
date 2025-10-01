
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int maxV = 10000000, M = -1, escolha;
    static long startTime, endTime;
    public static void main(String[] args) throws Exception {
        int[] randomArray = generateRandomArray(maxV);
        int[] randomArraycopy;
        try (Scanner scanner = new Scanner(System.in)) {
            while (escolha != 8) {
                clearScreen(); 
                System.out.println("=== MENU PRINCIPAL ===");
                System.out.println("[1] - QuickSort Recursivo");
                System.out.println("[2] - Teste Empírico");
                System.out.println("[3] - QuickSort Híbrido (requer teste empírico para definir M)");
                System.out.println("[4] - QuickSort Aprimorado com mediana de três(requer teste empírico para definir M)");
                System.out.println("[5] - Ver Histórico de Resultados");
                System.out.println("[6] - Último Resultado");
                System.out.println("[7] - Teste Completo (10 execuções de cada algoritmo)");
                System.out.println("[8] - Sair");
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
                        //reseta os timers
                        startTime = 0;
                        endTime = 0;
                        // copia o array original para preservar os dados
                        randomArraycopy = randomArray.clone();
                        // Mostra os primeiros 20 valores antes da ordenação
                        System.out.println("Primeiros 20 valores antes da ordenação:");
                        printFirstN(randomArraycopy, 20);

                        // Reseta os contadores
                        QuickSortR.trocas = 0;
                        QuickSortR.comparacoes = 0;
                        
                        startTime = System.nanoTime();
                        QuickSortR.quicksort(randomArraycopy, 0, randomArraycopy.length - 1);
                        endTime = System.nanoTime();
                        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos

                        System.out.println("\nArray completo ordenado:");
                        printArrayWithChoice(randomArraycopy, scanner);

                        System.out.println("\n=== RESULTADOS ===");
                        System.out.println("Tamanho do array: " + randomArraycopy.length);
                        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
                        System.out.println("Número de trocas: " + QuickSortR.trocas);
                        System.out.println("Número de comparações: " + QuickSortR.comparacoes);
                        
                        // Armazenar resultado
                        Resultados resultado = new Resultados("QuickSort Recursivo", randomArraycopy.length, 
                                                             QuickSortR.trocas, QuickSortR.comparacoes, executionTime, maxV);
                        Resultados.adicionarResultado(resultado);
                        
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 2 -> {
                        // Chame aqui o método do teste empírico(valores de comparações e trocas nao sao contabilizados)
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
                        
                        // Usa o mesmo array que o caso 1 para comparação justa
                        int[] hibridoArray = randomArray.clone();
                        
                        // Mostra os primeiros 20 valores antes da ordenação
                        System.out.println("Primeiros 20 valores antes da ordenação:");
                        printFirstN(hibridoArray, 20);
                        
                        // Reset counters before sorting
                        QuickSortR.trocas = 0;
                        QuickSortR.comparacoes = 0;
                        
                        QuicksortHibrido sorter = new QuicksortHibrido(M);
                        
                        startTime = System.nanoTime();
                        sorter.ordenar(hibridoArray);
                        endTime = System.nanoTime();
                        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos
                        
                        System.out.println("\nArray completo ordenado:");
                        printArrayWithChoice(hibridoArray, scanner);
                        
                        System.out.println("\n=== RESULTADOS QUICKSORT HÍBRIDO ===");
                        System.out.println("Tamanho do array: " + hibridoArray.length);
                        System.out.println("Valor de M usado: " + M);
                        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
                        System.out.println("Número de trocas: " + QuickSortR.trocas);
                        System.out.println("Número de comparações: " + QuickSortR.comparacoes);
                        
                        // Armazenar resultado
                        Resultados resultadoHibrido = new Resultados("QuickSort Híbrido", hibridoArray.length, 
                                                                    QuickSortR.trocas, QuickSortR.comparacoes, executionTime, M, maxV);
                        Resultados.adicionarResultado(resultadoHibrido);
                        
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 4 -> {
                        if (M == -1) {
                            System.out.println("Você precisa rodar o Teste Empírico primeiro para definir M.");
                            pressEnterToClear(scanner);
                            escolha = -1; 
                            return;
                        }
                        
                        // Usa o mesmo array que o caso 1 para comparação justa
                        int[] aprimoradoArray = randomArray.clone();
                        
                        // Mostra os primeiros 20 valores antes da ordenação
                        System.out.println("Primeiros 20 valores antes da ordenação:");
                        printFirstN(aprimoradoArray, 20);
                        
                        // Reset counters before sorting
                        QuickSortR.trocas = 0;
                        QuickSortR.comparacoes = 0;
                        
                        QuicksortAprimorado sorter = new QuicksortAprimorado(M);
                        
                        startTime = System.nanoTime();
                        sorter.ordenar(aprimoradoArray);
                        endTime = System.nanoTime();
                        double executionTime = (endTime - startTime) / 1_000_000.0; // Converte para milissegundos
                        
                        System.out.println("\nArray completo ordenado:");
                        printArrayWithChoice(aprimoradoArray, scanner);
                        
                        System.out.println("\n=== RESULTADOS QUICKSORT APRIMORADO ===");
                        System.out.println("Tamanho do array: " + aprimoradoArray.length);
                        System.out.println("Valor de M usado: " + M);
                        System.out.println("Tempo de execução: " + String.format("%.2f", executionTime) + " ms");
                        System.out.println("Número de trocas: " + QuickSortR.trocas);
                        System.out.println("Número de comparações: " + QuickSortR.comparacoes);
                        
                        // Armazenar resultado
                        Resultados resultadoAprimorado = new Resultados("QuickSort Aprimorado", aprimoradoArray.length, 
                                                                       QuickSortR.trocas, QuickSortR.comparacoes, executionTime, M, maxV);
                        Resultados.adicionarResultado(resultadoAprimorado);
                        
                        pressEnterToClear(scanner);
                        escolha = -1; 
                    }
                    case 5 -> {
                        Resultados.exibirHistorico();
                        pressEnterToClear(scanner);
                        escolha = -1;
                    }
                    case 6 -> {
                        Resultados.compararUltimoResultado();
                        pressEnterToClear(scanner);
                        escolha = -1;
                    }
                    case 7 -> {
                        // Teste Completo - 10 execuções de cada algoritmo
                        executarTesteCompleto(scanner);
                        pressEnterToClear(scanner);
                        escolha = -1;
                    }
                    case 8 -> {
                        clearScreen();
                        System.out.println("Saindo... Obrigado por usar o programa!");
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

    //Imprime os primeiros e últimos 1% dos elementos de um array
    private static void printArray(int[] array) {
        int length = array.length;
        
        //first 1% (0-1%)
        System.out.println("Primeiros 1% do array:");
        int first1Percent = (int) (0.01 * length);
        for (int i = 0; i < first1Percent; i++) {
            System.out.print(array[i]);
            if (i < first1Percent - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
        
        //last 1% (99-100%)
        System.out.println("Últimos 1% do array:");
        int start99 = (int) (0.99 * length);
        for (int i = start99; i < length; i++) {
            System.out.print(array[i]);
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    //Imprime apenas os primeiros e últimos 50 elementos de um array
    private static void printArray50(int[] array) {
        int length = array.length;
        
        if (length <= 100) {
            // Se o array tem 100 elementos ou menos, imprime tudo
            System.out.println("Array completo (" + length + " elementos):");
            for (int i = 0; i < length; i++) {
                System.out.print(array[i]);
                if (i < length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            // Primeiros 50 elementos
            System.out.println("Primeiros 50 elementos:");
            for (int i = 0; i < 50; i++) {
                System.out.print(array[i]);
                if (i < 49) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
            
            // Últimos 50 elementos
            System.out.println("Últimos 50 elementos:");
            for (int i = length - 50; i < length; i++) {
                System.out.print(array[i]);
                if (i < length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
    //Imprime array com opção de escolha do usuário
    private static void printArrayWithChoice(int[] array, Scanner scanner) {
        System.out.println("\nComo deseja visualizar o array ordenado?");
        System.out.println("[1] - Primeiros e últimos 1% (padrão)");
        System.out.println("[2] - Primeiros e últimos 50 elementos");
        System.out.print("Escolha uma opção (1 ou 2): ");
        
        String choice = scanner.nextLine().trim();
        
        if (choice.equals("2")) {
            printArray50(array);
        } else {
            // Padrão ou opção inválida
            printArray(array);
        }
    }

    //Clears the console screen
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

    //Waits for user to press Enter and then clears the screen
    public static void pressEnterToClear(Scanner scanner) {
        System.out.println("\nPressione Enter para limpar a tela e continuar...");
        scanner.nextLine();
        clearScreen();
    }
    
    /**
     * Executa teste completo com 10 execuções de cada algoritmo
     */
    private static void executarTesteCompleto(Scanner scanner) {
        System.out.println("\n=== TESTE COMPLETO - 10 EXECUÇÕES DE CADA ALGORITMO ===");
        System.out.println("Este teste executará:");
        System.out.println("- Teste Empírico (1x) para definir M");
        System.out.println("- QuickSort Recursivo (10x)");
        System.out.println("- QuickSort Híbrido (10x)");
        System.out.println("- QuickSort Aprimorado (10x)");
        System.out.println("\nDeseja continuar? (S/N): ");
        
        String confirmacao = scanner.nextLine().trim();
        if (!confirmacao.equalsIgnoreCase("S") && !confirmacao.equalsIgnoreCase("Y")) {
            System.out.println("Teste cancelado.");
            return;
        }
        
        clearScreen();
        System.out.println("=== INICIANDO TESTE COMPLETO ===");
        
        // 1. Executar teste empírico se M não estiver definido
        if (M == -1) {
            System.out.println("\n[1/4] Executando Teste Empírico para definir M...");
            M = TesteEmpirico.run();
            System.out.println("✓ Teste Empírico concluído. M = " + M);
        } else {
            System.out.println("✓ Valor M já definido: " + M);
        }
        
        // 2. Executar QuickSort Recursivo 10 vezes
        System.out.println("\n[2/4] Executando QuickSort Recursivo (10 execuções)...");
        executarTestesMultiplos("QuickSort Recursivo", 1, 10);
        
        // 3. Executar QuickSort Híbrido 10 vezes
        System.out.println("\n[3/4] Executando QuickSort Híbrido (10 execuções)...");
        executarTestesMultiplos("QuickSort Híbrido", 2, 10);
        
        // 4. Executar QuickSort Aprimorado 10 vezes
        System.out.println("\n[4/4] Executando QuickSort Aprimorado (10 execuções)...");
        executarTestesMultiplos("QuickSort Aprimorado", 3, 10);
        
        System.out.println("\n=== TESTE COMPLETO FINALIZADO ===");
        System.out.println("✓ Total de " + (30) + " execuções realizadas!");
        System.out.println("✓ Todos os resultados foram salvos no histórico.");
        System.out.println("✓ Use a opção [5] para ver o histórico completo.");
        System.out.println("✓ Use a opção [6] para comparar os resultados.");
    }
    
    /**
     * Executa múltiplos testes do mesmo algoritmo
     */
    private static void executarTestesMultiplos(String nomeAlgoritmo, int tipoAlgoritmo, int numExecucoes) {
        for (int i = 1; i <= numExecucoes; i++) {
            System.out.print("  Execução " + i + "/" + numExecucoes + "... ");
            
            // Gerar novo array para cada execução
            int[] testArray = generateRandomArray(maxV);
            
            // Reset counters
            QuickSortR.trocas = 0;
            QuickSortR.comparacoes = 0;
            
            long testStartTime = System.nanoTime();
            
            // Executar o algoritmo específico
            switch (tipoAlgoritmo) {
                case 1 -> { // QuickSort Recursivo
                    int[] arrayRecursivo = testArray.clone();
                    QuickSortR.quicksort(arrayRecursivo, 0, arrayRecursivo.length - 1);
                }
                case 2 -> { // QuickSort Híbrido
                    int[] arrayHibrido = testArray.clone();
                    QuicksortHibrido sorterHibrido = new QuicksortHibrido(M);
                    sorterHibrido.ordenar(arrayHibrido);
                }
                case 3 -> { // QuickSort Aprimorado
                    int[] arrayAprimorado = testArray.clone();
                    QuicksortAprimorado sorterAprimorado = new QuicksortAprimorado(M);
                    sorterAprimorado.ordenar(arrayAprimorado);
                }
            }
            
            long testEndTime = System.nanoTime();
            double executionTime = (testEndTime - testStartTime) / 1_000_000.0;
            
            // Salvar resultado
            if (tipoAlgoritmo == 1) {
                // QuickSort Recursivo (sem M)
                Resultados resultado = new Resultados(nomeAlgoritmo + " #" + i, testArray.length, 
                                                     QuickSortR.trocas, QuickSortR.comparacoes, executionTime, maxV);
                Resultados.adicionarResultado(resultado);
            } else {
                // QuickSort Híbrido ou Aprimorado (com M)
                Resultados resultado = new Resultados(nomeAlgoritmo + " #" + i, testArray.length, 
                                                     QuickSortR.trocas, QuickSortR.comparacoes, executionTime, M, maxV);
                Resultados.adicionarResultado(resultado);
            }
            
            System.out.println("✓ " + String.format("%.2f", executionTime) + " ms");
        }
        System.out.println("✓ " + nomeAlgoritmo + " concluído!");
    }
}