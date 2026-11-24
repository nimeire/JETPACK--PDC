# Atividade 07 — Calculadora de Desconto (Jetpack Compose)

Aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose** e **Material 3** para calcular o preço final de um produto a partir do valor original e de um percentual de desconto.

---

## Respostas às Questões Teóricas

### 1. O que é o Jetpack Compose e qual a sua principal diferença em relação ao sistema tradicional de Views (XML)?
O **Jetpack Compose** é o toolkit moderno e nativo do Android para construção de interfaces de usuário (UI) de forma totalmente declarativa em Kotlin. A principal diferença para o sistema tradicional baseado em **XML** (imperativo) é que, no XML, criamos a árvore de componentes estaticamente e precisamos buscar e mutar cada `View` manualmente no código (via `findViewById` ou `ViewBinding`) sempre que o estado muda. No Compose, a interface é descrita por funções `@Composable` que recebem dados e declaram como a tela deve parecer naquele estado; quando os dados mudam, o próprio framework atualiza apenas os componentes afetados.

---

### 2. O que significa a anotação `@Composable`?
A anotação `@Composable` informa ao compilador do Kotlin que aquela função tem o objetivo de converter dados em elementos de interface gráfica. Funções marcadas com `@Composable` só podem ser chamadas dentro de outras funções `@Composable` e possuem acesso ao contexto de composição e recomposição do Jetpack Compose.

---

### 3. O que é Recomposição (Recomposition) no Jetpack Compose?
**Recomposição** é o processo pelo qual o Jetpack Compose executa novamente as funções `@Composable` afetadas quando algum estado (`State`) observado por elas sofre alteração. Em vez de redesenhar a tela inteira, o Compose identifica de forma inteligente quais componentes dependem do dado modificado e atualiza apenas esses trechos na interface.

---

### 4. Para que servem `remember` e `mutableStateOf`?
* **`mutableStateOf(valorInicial)`**: Cria um objeto de estado observável pelo Compose. Sempre que seu valor (`.value`) é alterado, o Compose agenda uma recomposição para os componentes que o leem.
* **`remember { ... }`**: Armazena esse objeto na memória da composição entre uma recomposição e outra. Sem o `remember`, toda vez que a função `@Composable` fosse reexecutada, a variável seria reinicializada para o valor original e o estado digitado pelo usuário seria perdido.

---

### 5. Qual a diferença entre `remember` e `rememberSaveable`?
* **`remember`**: Mantém o estado apenas enquanto o `@Composable` permanece ativo na composição, perdendo os dados em mudanças de configuração do sistema (como rotacionar a tela ou alternar o modo escuro) ou se o processo for recriado.
* **`rememberSaveable`**: Salva o estado automaticamente em um `Bundle` do Android, preservando os dados mesmo após rotação de tela, troca de idioma ou recriação da `Activity` pelo sistema operacional.

---

### 6. Como funciona o conceito de *State Hoisting* (Elevação de Estado)?
*State Hoisting* é um padrão de arquitetura no Compose que consiste em mover o estado de um componente filho para o componente pai que o chama, tornando o componente filho *stateless* (sem estado próprio). Para isso, substitui-se a variável interna por dois parâmetros:
1. `value: T` — o valor atual a ser exibido.
2. `onValueChange: (T) -> Unit` — uma função de callback disparada quando o usuário interage e solicita mudança no valor.

Isso facilita o reuso, os testes e o compartilhamento do mesmo estado entre vários componentes.

---

### 7. Qual a função dos componentes de layout `Column`, `Row` e `Box`?
* **`Column`**: Organiza seus elementos filhos verticalmente, um abaixo do outro (equivalente a um `LinearLayout` com orientação vertical).
* **`Row`**: Organiza seus elementos filhos horizontalmente, lado a lado (equivalente a um `LinearLayout` com orientação horizontal).
* **`Box`**: Sobrepõe seus elementos filhos uns sobre os outros em camadas no eixo Z (semelhante ao `FrameLayout`), permitindo alinhar itens nos cantos ou no centro de um mesmo contêiner.

---

### 8. Para que serve o parâmetro `Modifier` no Jetpack Compose?
O `Modifier` permite decorar, dimensionar, posicionar e adicionar comportamento aos componentes `@Composable` sem precisar criar subclasses ou atributos XML específicos. Com ele definimos margens internas (`padding`), largura/altura (`fillMaxWidth`, `height`), cliques (`clickable`), cor de fundo (`background`), bordas, alinhamento e acessibilidade, encadeando chamadas na ordem em que devem ser aplicadas.

---

### 9. Qual a diferença entre `TextField` e `OutlinedTextField` no Material 3?
Ambos são campos de entrada de texto do Material Design 3 com a mesma funcionalidade e assinatura de parâmetros, diferindo apenas no estilo visual:
* **`TextField`**: Possui um contêiner preenchido (*filled*) com cor de fundo sólida e apenas uma linha indicadora na base.
* **`OutlinedTextField`**: Possui fundo transparente e uma borda contínua (*outline*) ao redor de todo o campo, destacando o rótulo (*label*) sobre a linha superior quando focado.

---

### 10. Por que é uma boa prática separar a lógica de cálculo da função `@Composable` da tela?
Separar a regra de negócio (como `calcularPrecoComDesconto(preco, percentualDesconto)`) da camada de interface traz três benefícios principais:
1. **Testabilidade**: Permite criar testes unitários puros e rápidos em Kotlin/JUnit sem precisar simular telas ou emuladores Android.
2. **Manutenção e Legibilidade**: Mantém o código da UI focado apenas na exibição e captura de eventos, evitando funções `@Composable` extensas e difíceis de ler.
3. **Reutilização**: A mesma função de cálculo pode ser reaproveitada em outras telas, ViewModels ou módulos sem depender do framework gráfico.