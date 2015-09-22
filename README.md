# ADS


Construir um simulador baseado em eventos para uma fila de capacidade limitada e c servidores.

O simulador deve ter uma parte independente da realidade, com:
- geração de números pseudo aleatórios segundo congruência linear;
- controle de eventos, avanço do tempo, teste de fim da simulação;
- disparo de rotinas de tratamento de eventos; e
- contabilização de resultados.

O simulador deve receber os seguintes parâmetros:
- capacidade máxima (número máximo de clientes);
- tempos mínimo e máximo entre chegadas de clientes;
- tempos mínimo e máximo de atendimento de clientes; 
- número de servidores; e
- tempo final de simulação.

A saída da simulação deve informar a probabilidade de cada estado da fila (vetor de probabilidades marginais, além dos índices:
D - Vazão média da fila;
U - Utilização média da fila;
N - População média da fila;
W - Tempo médio de resposta.

Além do simulador, deve ser fornecido também a solução analítica (por processo de nascimento e morte M/M/c/k ou por Cadeia de Markov) que calcule a probabilidade marginal da fila e os quatro índices (D, U, N e W).

Será necessário que cada grupo simule e calcule alguns exemplos (2 a 10) para ilustrar a diferença de resultados obtidos via simulador e solução analítica.

Material a ser enviado via moodle:
a) executáveis e explicação/instruções de uso do simulador;
b) fontes documentados do simulador;
c) resultados númericos (probabilidade marginal de cada fila e índices) obtidos via simulador e solução analítica para pelo menos dois exemplos;
d) comentários sobre a discrepância obtida nos resultados de simulação e solução analítica; e
e) outros comentários referente a realização do trabalho que julgar pertinente à avaliação.
