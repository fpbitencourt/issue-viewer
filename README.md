# Issues Viewer
Projeto Android nativo desenvolvido em Kotlin, que lista e detalha "Issues" de um repositório público do GitHub.

## Funcionalidades

### 1. Tela de Lista de Issues

Apresenta uma lista de issues de um repositório público informado pelo usuário. Por padrão o repositório inicial é o https://api.github.com/repos/JetBrains/kotlin/issues.

Cada item na lista exibe:

- Um título do issue
- Uma imagem que representa o estado do issue (aberto/fechado)

![Tela 1](https://i.ibb.co/6Rny441/Screenshot-20240114-154515.png)

### 2. Tela de Detalhes do Issue

Mostra detalhes do issue selecionado na lista

- Título do issue
- Texto de Descrição do issue
- Nome e Avatar do usuário que criou o issue
- Data de criação do issue
- Botão para abrir o browser com o link issue no site do GitHub
  
![Tela 2](https://i.ibb.co/jH73YQf/Screenshot-20240114-154546.png)

## Principais tecnologias utilizadas

- Kotlin
- AndroidX
- MVVM
- RxJava 2
- Koin
- OkHttp3 
- Gson
- Coroutines
- LiveData
- Testes Unitários (mocck/JUnit)
