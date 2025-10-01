import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Resultados {
    String nomeAlgoritmo;  
    int tamanhoArray;
    int numTrocas;
    int numComparacoes;
    double tempoExecucao; // em milissegundos
    String dataHora; // timestamp da execução
    int valorM; // valor de M usado (se aplicável)
    int maxV; // valor máximo usado na geração dos arrays
    
    // Lista estática para armazenar todos os resultados
    private static List<Resultados> historicoResultados = new ArrayList<>();

    public Resultados(String nomeAlgoritmo, int tamanhoArray, int numTrocas, int numComparacoes, double tempoExecucao, int valorM, int maxV) {
        this.nomeAlgoritmo = nomeAlgoritmo;
        this.tamanhoArray = tamanhoArray;
        this.numTrocas = numTrocas;
        this.numComparacoes = numComparacoes;
        this.tempoExecucao = tempoExecucao;
        this.valorM = valorM;
        this.maxV = maxV;
        this.dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    
    // Construtor para algoritmos que não usam M
    public Resultados(String nomeAlgoritmo, int tamanhoArray, int numTrocas, int numComparacoes, double tempoExecucao, int maxV) {
        this(nomeAlgoritmo, tamanhoArray, numTrocas, numComparacoes, tempoExecucao, -1, maxV);
    }
    
    // Método para adicionar resultado ao histórico
    public static void adicionarResultado(Resultados resultado) {
        historicoResultados.add(resultado);
        System.out.println("✓ Resultado salvo no histórico!");
    }
    
    // Método para exibir todos os resultados
    public static void exibirHistorico() {
        if (historicoResultados.isEmpty()) {
            System.out.println("Nenhum resultado armazenado ainda.");
            return;
        }
        
        System.out.println("\n=== HISTÓRICO DE RESULTADOS ===");
        System.out.println("------------------------------------------------");
        
        for (int i = 0; i < historicoResultados.size(); i++) {
            Resultados r = historicoResultados.get(i);
            System.out.println("Teste #" + (i + 1));
            System.out.println("Algoritmo: " + r.nomeAlgoritmo);
            System.out.println("Data/Hora: " + r.dataHora);
            System.out.println("Tamanho do array: " + r.tamanhoArray);
            System.out.println("Valor máximo (maxV): " + r.maxV);
            System.out.println("Tempo de execução: " + String.format("%.2f", r.tempoExecucao) + " ms");
            System.out.println("Número de trocas: " + r.numTrocas);
            System.out.println("Número de comparações: " + r.numComparacoes);
            if (r.valorM != -1) {
                System.out.println("Valor de M: " + r.valorM);
            }
            System.out.println("------------------------------------------------");
        }
    }
    
    // Método para comparar resultados do último teste com outros algoritmos
    public static void compararUltimoResultado() {
        if (historicoResultados.size() < 2) {
            System.out.println("É necessário pelo menos 2 resultados para comparar.");
            return;
        }
        
        Resultados ultimo = historicoResultados.get(historicoResultados.size() - 1);
        System.out.println("\n=== COMPARAÇÃO COM ÚLTIMO RESULTADO ===");
        System.out.println("Último teste: " + ultimo.nomeAlgoritmo);
        System.out.println("------------------------------------------------");
        
        for (int i = historicoResultados.size() - 2; i >= 0; i--) {
            Resultados anterior = historicoResultados.get(i);
            if (!anterior.nomeAlgoritmo.equals(ultimo.nomeAlgoritmo) && 
                anterior.tamanhoArray == ultimo.tamanhoArray) {
                
                System.out.println("Comparando com: " + anterior.nomeAlgoritmo);
                
                // Comparar tempo
                double diferencaTempo = ultimo.tempoExecucao - anterior.tempoExecucao;
                String tempoStatus = diferencaTempo < 0 ? "mais rápido" : "mais lento";
                System.out.println("Tempo: " + Math.abs(diferencaTempo) + " ms " + tempoStatus);
                
                // Comparar trocas
                int diferencaTrocas = ultimo.numTrocas - anterior.numTrocas;
                String trocasStatus = diferencaTrocas < 0 ? "menos trocas" : "mais trocas";
                System.out.println("Trocas: " + Math.abs(diferencaTrocas) + " " + trocasStatus);
                
                // Comparar comparações
                int diferencaComp = ultimo.numComparacoes - anterior.numComparacoes;
                String compStatus = diferencaComp < 0 ? "menos comparações" : "mais comparações";
                System.out.println("Comparações: " + Math.abs(diferencaComp) + " " + compStatus);
                
                System.out.println("------------------------------------------------");
                break; // Compara apenas com o último resultado diferente
            }
        }
    }
    
    // Método para limpar o histórico
    public static void limparHistorico() {
        historicoResultados.clear();
        System.out.println("Histórico limpo!");
    }
    
    // Getters
    public String getNomeAlgoritmo() { return nomeAlgoritmo; }
    public int getTamanhoArray() { return tamanhoArray; }
    public int getNumTrocas() { return numTrocas; }
    public int getNumComparacoes() { return numComparacoes; }
    public double getTempoExecucao() { return tempoExecucao; }
    public String getDataHora() { return dataHora; }
    public int getValorM() { return valorM; }
    public int getMaxV() { return maxV; }
    
    // Método para obter quantidade de resultados armazenados
    public static int getTotalResultados() {
        return historicoResultados.size();
    }
}
