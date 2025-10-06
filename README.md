
# QuickSort Recursivo - Programa de Análise Comparativa

Este programa Java implementa e compara três variantes diferentes do algoritmo QuickSort com recursos abrangentes de teste e rastreamento de resultados.

## 📋 Visão Geral

Este programa fornece uma estrutura completa para analisar o desempenho do algoritmo QuickSort através de:
- Múltiplas implementações de QuickSort (Recursivo, Híbrido, Aprimorado)
- Testes empíricos para determinar parâmetros ótimos
- Medição e comparação de desempenho
- Rastreamento histórico de resultados
- Análise estatística através de múltiplas execuções

## 🚀 Funcionalidades

### Implementações de Algoritmos

1. **QuickSort Recursivo** (`QuickSortR.java`)
   - Implementação padrão do QuickSort recursivo
   - Rastreia número de trocas e comparações
   - Usa o primeiro elemento como pivô

2. **QuickSort Híbrido** (`QuicksortHibrido.java`)
   - Combina QuickSort com InsertionSort
   - Muda para InsertionSort quando o array é menor que o limiar M
   - Otimizado para melhor desempenho em subarrays pequenos

3. **QuickSort Aprimorado** (`QuicksortAprimorado.java`)
   - QuickSort avançado com seleção de pivô mediana-de-três
   - Melhor seleção de pivô reduz cenários de pior caso
   - Desempenho aprimorado para arrays ordenados/parcialmente ordenados

4. **InsertionSort** (`InsertionSort.java`)
   - Implementação padrão do insertion sort
   - Usado pelo QuickSort híbrido para arrays pequenos
   - Eficiente para conjuntos de dados pequenos

### Framework de Testes

- **Teste Individual de Algoritmos** - Testa cada algoritmo separadamente
- **Teste Abrangente** - Executa todos os algoritmos 10 vezes para análise estatística
- **Teste Empírico** - Determina o valor ótimo de M para o QuickSort híbrido
- **Comparação Justa** - Todos os algoritmos usam dados de entrada idênticos para comparações válidas

### Gerenciamento de Resultados

- **Armazenamento de Resultados** (`Resultados.java`) - Armazena tempo de execução, trocas, comparações e tamanho do array
- **Rastreamento Histórico** - Mantém histórico de todos os resultados de teste
- **Comparação de Desempenho** - Compara resultados mais recentes com execuções anteriores
- **Análise Estatística** - Analisa desempenho através de múltiplas execuções

## Opções do Menu

1. **Teste QuickSort Recursivo** - Testa o QuickSort recursivo individualmente
2. **Teste Empírico** - Encontra o valor ótimo de M para o QuickSort híbrido
3. **Teste QuickSort Híbrido** - Testa o QuickSort híbrido individualmente
4. **Teste QuickSort Aprimorado** - Testa o QuickSort aprimorado individualmente
5. **Teste Completo** - Executa todos os algoritmos 10 vezes cada para análise abrangente
6. **Exibir Histórico** - Exibe todos os resultados de teste anteriores
7. **Comparar Último Resultado** - Compara os resultados mais recentes entre algoritmos
8. **Sair** - Sair do programa

## Como Executar

### Pré-requisitos
- Java 21 ou superior

## 📊 Fluxo do Programa

### 1. Geração de Arrays
- Gera arrays de inteiros aleatórios do tamanho especificado
- Valor máximo (maxV) determina o intervalo dos números aleatórios
- Os mesmos dados de array são usados entre algoritmos para comparação justa

### 2. Medição de Desempenho
- **Tempo de Execução** - Medido em nanossegundos para precisão
- **Contagem de Trocas** - Número de trocas de elementos durante a ordenação
- **Contagem de Comparações** - Número de comparações de elementos realizadas

### 3. Opções de Exibição
O programa oferece opções flexíveis de exibição de array:
- **1%** - Mostra apenas 1% dos elementos do array
- **50 Elementos** - Mostra os primeiros e últimos 50 elementos
- **Escolha do Usuário** - Permite ao usuário decidir quantos elementos exibir

### 4. Armazenamento de Resultados
Cada resultado de teste inclui:
- Nome do algoritmo e timestamp
- Tamanho do array (parâmetro maxV)
- Tempo de execução em nanossegundos
- Número de trocas realizadas
- Número de comparações feitas

##  Cenários de Teste

### Testes Individuais (Opções 1, 3, 4)
- Testa algoritmo único com tamanho de array especificado pelo usuário
- Exibe arrays original e ordenado
- Mostra métricas de desempenho
- Armazena resultados para comparação posterior

### Teste Empírico (Opção 2)
- Testa diferentes valores de M (10, 50, 100) para o QuickSort híbrido
- Ajuda a determinar o limiar ótimo para mudar para InsertionSort
- Compara desempenho entre diferentes valores de M

### Teste Abrangente (Opção 5)
- Executa cada algoritmo 10 vezes com os mesmos dados de array
- Fornece análise estatística de desempenho
- Garante comparação justa usando dados de entrada idênticos
- Calcula métricas de desempenho médias

## Análise de Desempenho

### Sistema de Comparação Justa
- Todos os testes individuais usam dados de array idênticos (`randomArray.clone()`)
- Elimina variância devido a diferentes distribuições de entrada
- Garante que diferenças de desempenho reflitam eficiência algorítmica

### Validade Estatística
- Múltiplas execuções (10 iterações) fornecem médias confiáveis
- Reduz o impacto de variações de desempenho a nível de sistema
- Permite comparação significativa da eficiência dos algoritmos

### Rastreamento Histórico
- Mantém histórico completo de todas as execuções de teste
- Permite análise de tendências ao longo do tempo
- Suporta detecção de regressão de desempenho

## Detalhes Técnicos

### Limpeza de Tela
- Funcionalidade de limpeza de tela multiplataforma
- Melhora a experiência do usuário com interface limpa
- Limpeza automática entre operações do menu

### Gerenciamento de Memória
- Clonagem eficiente de arrays para comparações justas
- Limpeza adequada de recursos após operações
- Otimizado para manipulação de arrays grandes

### Tratamento de Erros
- Validação de entrada para seleções do menu
- Tratamento gracioso de tamanhos de array inválidos
- Mecanismos robustos de recuperação de erros

## 📝 Estrutura do Código

```
src/
├── Main.java              # Programa principal com interface de menu
├── QuickSortR.java        # Implementação do QuickSort recursivo
├── QuicksortHibrido.java  # QuickSort híbrido com InsertionSort
├── QuicksortAprimorado.java # QuickSort avançado com mediana-de-três
├── InsertionSort.java     # Implementação padrão do InsertionSort
├── Resultados.java        # Sistema de armazenamento e gerenciamento de resultados
└── TesteEmpirico.java     # Teste empírico para valor ótimo de M
```

## 📊 Saída de Exemplo

O programa fornece saída detalhada incluindo:
- Exibições de arrays original e ordenado (com dimensionamento flexível)
- Medições de tempo de execução
- Contagens de trocas e comparações
- Comparações de resultados históricos
- Análise estatística através de múltiplas execuções

**Autor**: Rodrigo Rocha, Rafael Brandão, Yuri Martins 
**Versão**: 1.0  
**Data**: Outubro 2025
