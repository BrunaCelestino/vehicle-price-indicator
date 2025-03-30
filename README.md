
# Vehicle Price Indicator

Este projeto oferece uma API para armazenar e recuperar informações sobre veículos e seus indicadores de preços. A aplicação permite o cadastro de dados de veículos e a busca por indicadores de preços como Fipe e Icarros.

## Sumário

1. [Visão Geral](#visão-geral)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Arquitetura](#arquitetura)
4. [Princípios SOLID](#princípios-solid)
5. [Funcionalidades](#funcionalidades)
6. [Endpoints](#endpoints)
    - [Cadastrar veículo](#cadastrar-veículo)
    - [Buscar indicadores de preço do veículo](#buscar-indicadores-de-preço-do-veículo)
7. [Configuração do Ambiente](#configuração-do-ambiente)
    - [Configuração do Maven](#configuração-do-maven)
    - [Banco de Dados](#banco-de-dados)
    - [Execução da Aplicação](#execução-da-aplicação)
    - [Testes Unitários](#testes-unitários)
    - [Checkstyle](#checkstyle)
8. [Documentação da API](#documentação-da-api)


## Visão Geral

O **Vehicle Price Indicator** é uma API desenvolvida com o objetivo de fornecer informações detalhadas sobre veículos e seus respectivos preços. Através dessa API, é possível cadastrar veículos e consultar seus indicadores de preços, como os preços de mercado baseados em fontes confiáveis, como a Tabela Fipe e Icarros.

A aplicação segue os princípios de **Clean Architecture** e **SOLID**, garantindo uma estrutura modular, de fácil manutenção e expansão. A API também é otimizada para a consulta rápida de dados de veículos, permitindo fácil integração com sistemas externos.


## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **H2 Database (para desenvolvimento e testes)**
- **Mockito (para testes unitários)**
- **Swagger UI** (para documentação da API)
- **Lombok** (para simplificação do código)

## Arquitetura

A arquitetura adotada para este projeto segue os princípios da **Clean Architecture**. O objetivo dessa arquitetura é separar as responsabilidades do sistema em camadas bem definidas, tornando o código mais flexível, testável e de fácil manutenção.

A estrutura do projeto foi organizada da seguinte maneira:

### Camadas do Sistema

#### 1. **Camada de Domínio**
A camada de domínio contém as **entidades** e **modelos** que representam o núcleo de negócio da aplicação. São as abstrações de dados que não dependem de nenhuma tecnologia externa (banco de dados, APIs, etc.). Aqui, encontramos:

- **Entidades**: Representam os dados fundamentais do sistema. Exemplos: `FipeIndicatorEntity`, `IcarrosIndicatorEntity`, `VehicleDataEntity`.
- **Modelos**: Representam as abstrações de dados que ajudam a organizar e manipular os dados de maneira mais eficiente para o domínio do sistema. Exemplos: `FipeIndicator`, `VehicleData`, `VehiclePriceIndicator`.

#### 2. **Camada de Aplicação**
A camada de aplicação é responsável pela lógica de controle do fluxo da aplicação, contendo os **casos de uso**. Os casos de uso orquestram a execução das operações no sistema. Além disso, a camada de aplicação também contém os **DTOs** (Data Transfer Objects) utilizados para transferência de dados entre as camadas da aplicação e o mundo exterior.

Exemplos:
- **Casos de uso**: `VehiclePriceIndicatorUseCase`, `VehicleDataProcessUseCase`
- **DTOs**: `VehiclePriceIndicatorDto`, `VehicleSavedDto`

#### 3. **Camada de Infraestrutura**
A camada de infraestrutura lida com a interação do sistema com recursos externos, como bancos de dados, APIs externas, e outros sistemas. A infraestrutura é responsável por fornecer a implementação concreta de repositórios e adaptadores.

Exemplos:
- **Adaptadores**: `FipeIndicatorAdapter`, `IcarrosIndicatorAdapter`, `MolicarIndicatorAdapter`
- **Repositórios**: `VehicleRepository`, `VehicleJpaRepository`

#### 4. **Camada de Interface/Controle**
A camada de interface/controle é responsável por expor as funcionalidades da aplicação para o mundo externo. Essa camada é composta pelos **controladores**, que lidam com as requisições HTTP e as direcionam para os casos de uso apropriados.

Exemplos:
- **Controladores**: `IndicatorController`, `VehicleController`

### Princípios e Benefícios

#### **Separation of Concerns (Separação de Preocupações)**
A Clean Architecture é baseada no princípio de separar as responsabilidades em camadas distintas, permitindo que o código seja mais fácil de entender, manter e testar. Cada camada tem uma responsabilidade única, o que reduz a complexidade e melhora a coesão.

#### **Baixo Acoplamento e Alta Coesão**
A arquitetura promove um baixo acoplamento entre as camadas, o que significa que mudanças em uma camada não afetam as demais. Além disso, cada camada é coesa, contendo apenas elementos que têm uma responsabilidade comum. Isso facilita a evolução e a manutenção do sistema.

#### **Testabilidade**
A arquitetura permite que cada camada seja testada de forma isolada. Isso torna o sistema altamente testável, com a possibilidade de realizar testes unitários nas camadas de domínio e aplicação, e testes de integração nas camadas de infraestrutura e controle.

#### **Flexibilidade**
A Clean Architecture permite que o sistema seja facilmente adaptado a mudanças. Caso seja necessário substituir ou atualizar uma tecnologia externa (como um banco de dados ou uma API), isso pode ser feito de forma isolada, sem afetar as outras camadas do sistema.


### Estrutura de Diretórios

```bash

├── src
│   ├── main
│   │   ├── java.br.com.bk.vehicle.price.indicator
│   │   │   ├── VehiclePriceIndicatorApplication.java
│   │   │   ├── application
│   │   │   │   ├── dtos
│   │   │   │   │   ├── FipeIndicatorDataDto.java
│   │   │   │   │   ├── FipeIndicatorDto.java
│   │   │   │   │   ├── IcarrosIndicatorDto.java
│   │   │   │   │   ├── MolicarIndicatorDto.java
│   │   │   │   │   ├── ProcessErrorDto.java
│   │   │   │   │   ├── VehicleDataDto.java
│   │   │   │   │   ├── VehiclePriceIndicatorDto.java
│   │   │   │   │   └── VehicleSavedDto.java
│   │   │   │   ├── exceptions
│   │   │   │   │   ├── EntityAlreadyExistsException.java
│   │   │   │   │   ├── EntityNotFoundException.java
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   └── ValidationFailedException.java
│   │   │   │   ├── ports
│   │   │   │   │   ├── VehicleDataProcessInputPort.java
│   │   │   │   │   └── VehiclePriceIndicatorInputPort.java
│   │   │   │   └── usecases
│   │   │   │       ├── VehicleDataProcessUseCase.java
│   │   │   │       └── VehiclePriceIndicatorUseCase.java
│   │   │   ├── domain
│   │   │   │   ├── entities
│   │   │   │   │   ├── FipeIndicatorDataEntity.java
│   │   │   │   │   ├── FipeIndicatorEntity.java
│   │   │   │   │   ├── IcarrosIndicatorEntity.java
│   │   │   │   │   ├── MolicarIndicatorEntity.java
│   │   │   │   │   └── VehicleDataEntity.java
│   │   │   │   ├── models
│   │   │   │   │   ├── FipeIndicator.java
│   │   │   │   │   ├── FipeIndicatorData.java
│   │   │   │   │   ├── IcarrosIndicator.java
│   │   │   │   │   ├── MolicarIndicator.java
│   │   │   │   │   ├── VehicleData.java
│   │   │   │   │   └── VehiclePriceIndicator.java
│   │   │   │   ├── repositories
│   │   │   │   │   └── VehicleJpaRepository.java
│   │   │   │   ├── services
│   │   │   │   │   ├── IndicatorService.java
│   │   │   │   │   └── VehicleService.java
│   │   │   │   └── types
│   │   │   │       ├── PriceIndicatorType.java
│   │   │   │       └── ProcessErrorType.java
│   │   │   └── infrastructure
│   │   │       ├── adapters
│   │   │       │   ├── FipeIndicatorAdapter.java
│   │   │       │   ├── FipeIndicatorDataAdapter.java
│   │   │       │   ├── FipeIndicatorDataEntityAdapter.java
│   │   │       │   ├── FipeIndicatorEntityAdapter.java
│   │   │       │   ├── IcarrosIndicatorAdapter.java
│   │   │       │   ├── IcarrosIndicatorEntityAdapter.java
│   │   │       │   ├── MolicarIndicatorAdapter.java
│   │   │       │   ├── MolicarIndicatorEntityAdapter.java
│   │   │       │   ├── VehicleDataAdapter.java
│   │   │       │   ├── VehicleDataEntityAdapter.java
│   │   │       │   └── VehiclePriceIndicatorAdapter.java
│   │   │       ├── config
│   │   │       │   └── OpenApiConfig.java
│   │   │       ├── controllers
│   │   │       │   ├── IndicatorController.java
│   │   │       │   └── VehicleController.java
│   │   │       ├── logger
│   │   │       │   └── LOG.java
│   │   │       ├── repositories
│   │   │       │   └── VehicleRepository.java
│   │   │       └── utils
│   │   │           ├── DateUtils.java
│   │   │           └── JsonUtils.java
│   │   └── resources
│   │       ├── application.yaml
│   │       ├── checkstyle.xml
│   │       ├── data.json
│   │       ├── logback.xml
│   │       └── swagger
│   │           └── api-docs.json


```


## Princípios SOLID

### 1. **Princípio da Responsabilidade Única (SRP)**

Cada classe no projeto é responsável por uma única tarefa ou operação. Por exemplo, a classe `VehiclePriceIndicatorUseCase` tem como única responsabilidade a lógica para calcular o preço do veículo, enquanto `VehicleController` lida apenas com a interface de entrada (requisições HTTP). Isso assegura que qualquer modificação em uma classe só afete a funcionalidade relacionada, evitando efeitos colaterais.

### 2. **Princípio do Aberto/Fechado (OCP)**

O projeto é projetado para ser extensível sem modificar código existente. Um exemplo disso é o uso de adaptadores como `FipeIndicatorAdapter` e `IcarrosIndicatorAdapter`, que podem ser adicionados ou modificados sem alterar os adaptadores já existentes, permitindo a inclusão de novas fontes de dados sem modificar a estrutura central.

### 3. **Princípio da Substituição de Liskov (LSP)**

As implementações concretas das interfaces podem ser substituídas por outras sem alterar o comportamento esperado do sistema. Por exemplo, a interface `VehicleRepository` pode ser substituída por uma nova implementação (por exemplo, um novo repositório de dados), sem que isso afete a lógica de negócios da aplicação, pois as classes dependem apenas da abstração.

### 4. **Princípio da Segregação de Interfaces (ISP)**

O projeto adota interfaces granulares para garantir que os consumidores de uma interface não dependam de métodos que não utilizam. Por exemplo, `VehicleDataProcessInputPort` e `VehiclePriceIndicatorInputPort` são interfaces específicas para cada caso de uso, evitando que as classes que implementam essas interfaces sejam sobrecarregadas com métodos desnecessários.

### 5. **Princípio da Inversão de Dependência (DIP)**

As classes de alto nível não dependem diretamente de classes de baixo nível. Em vez disso, ambas dependem de abstrações. Um exemplo disso é a injeção de dependência no repositório de dados, onde `VehicleService` depende da interface `VehicleRepository` e não da implementação concreta, permitindo que o repositório seja facilmente substituído sem afetar a lógica de negócios.


## Funcionalidades

1. **Cadastro de veículos**  
   Permite o cadastro de informações de veículos (placa, marca, modelo, ano, etc.) na base de dados.

2. **Consulta de indicadores de preço de veículos**  
   Permite a consulta de preços de veículos com base em sua placa e data, com diferentes tipos de indicadores:
    - `ALL`: Todos os tipos de indicadores.
    - `BOTH`: Somente se houver indicadores Fipe e Icarros.
    - `FIPE`: Somente indicador Fipe.
    - `ICARROS`: Somente indicador Icarros.

## Endpoints

### 1. **Cadastrar veículo**

- **URL:** `/v1/vehicles`
- **Método:** `POST`
- **Descrição:** Cadastra um veículo.
- **Corpo da requisição (JSON):**
  ```json
  {
  "codigo": "000",
  "mensagem": "Sucesso",
  "placa": "FUD5J98",
  "chassi": "3N1BB7ADXHY200644",
  "ufJurisdicao": "SP",
  "nomeMunicipioEmplacamento": "VALINHOS",
  "anoFabricacao": 2016,
  "anoModelo": 2017,
  "marca": "I",
  "modelo": "NISSAN SENTRA 20SV CVT",
  "tipoVeiculo": "Automovel",
  "tipoCarroceria": "NAO APLICAVEL",
  "numeroCarroceria": "",
  "corPredominante": "Cinza",
  "combustivel": "ALCOOL/GASOLINA",
  "potencia": 140,
  "cilindradas": 1997,
  "capacidadePassageiros": 5,
  "numeroCaixaCambio": "",
  "numeroEixos": 2,
  "numeroMotor": "MR20980798H",
  "dataEmplacamento": "09092016",
  "quantidadeAquisicoes": 8,
  "dataUltimaAquisicao": null,
  "ufUltimaAquisicao": "SP",
  "ufAtual": "SP",
  "nomeMunicipioUltimaAquisicao": "MOGI DAS CRUZES",
  "indicadoresFipe": [
    {
      "codigoFIPE": "023123-1",
      "valores": [
        {
          "valorFIPE": "67388.0",
          "dataConsulta": "03/2025",
          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
          "indiceComparacao": "0.50",
          "desvalorizacao": "true"
        },
        {
          "valorFIPE": "67727.0",
          "dataConsulta": "02/2025",
          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
          "indiceComparacao": "2.04",
          "desvalorizacao": "false"
        },
        {
          "valorFIPE": "66373.0",
          "dataConsulta": "01/2025",
          "nomenclaturaFIPE": "Nissan-Sentra SV 2.0 FlexStart 16V Aut.",
          "indiceComparacao": "3.50",
          "desvalorizacao": "false"
        }
      ]
    }
  ],
  "indicadoresMolicar": [
    {
      "codigo": "03701339-7",
      "descricao": "NISSAN SENTRA SV N.GERACAO 2.0 16V CVT FLEXSTART 4P Eta./Gas."
    }
  ],
  "indicadoresIcarros": [
    {
      "codigoICARROS": "26099",
      "valorICARROS": "67006.31",
      "mesConsulta": "02/2025",
      "nomenclaturaICARROS": "Sentra SV 2.0 16V CVT (Flex)",
      "indiceComparacao": "3.41",
      "desvalorizacao": "false"
    },
    {
      "codigoICARROS": "26099",
      "valorICARROS": "64798.66",
      "mesConsulta": "01/2025",
      "nomenclaturaICARROS": "Sentra SV 2.0 16V CVT (Flex)",
      "indiceComparacao": "2.80",
      "desvalorizacao": "false"
    },
    {
      "codigoICARROS": "26099",
      "valorICARROS": "63033.42",
      "mesConsulta": "12/2024",
      "nomenclaturaICARROS": "Sentra SV 2.0 16V CVT (Flex)",
      "indiceComparacao": "1.86",
      "desvalorizacao": "true"
    }
  ]
  }
  ```
- **Resposta (201):**
  ```json
  {
  "placa": "ABC1D23",
  "registradoEm": "2023-10-01T12:00:00Z"
  }
  ```
#### Regras de Negócio:

##### 1. Verificação de Existência de Veículo:
Quando um veículo é enviado para cadastro, o sistema verifica se já existe um veículo com a mesma placa registrada no banco de dados.

Se o veículo já estiver registrado, o sistema retorna uma resposta de erro:

````json
    [
        {
            "title": "VEHICLE_ALREADY_REGISTERED",
            "code": "CPI0002",
            "details": "Não foi possível cadastrar veículo. Placa já cadastrada."
        }
    ]
````

##### 2. Validação de Placa:

O sistema verifica se a placa foi informada. Se a placa do veículo não for informada, um erro é retornado:

```json
    [
      {
          "title": "REQUIRED_INFORMATION_MISSING",
          "code": "CPI0003",
          "details": "O campo 'placa' é obrigatório."
      }
    ]
```

##### 3. Validação de Data Inválida:

O sistema verifica se a data dos indicadores seguem o formato "MM/YYYY". Se a data não estiver no formato correto, um erro é retornado:

```json
    [
      {
          "title": "INVALID_DATE_FORMAT",
          "code": "CPI0005",
          "details": "A data deve seguir o formato MM/YYYY."
      }
    ]
```


#### 4. Erro Genérico:

Se ocorrer qualquer erro inesperado ao tentar processar a requisição, o sistema retorna um erro genérico:

```json
    [
        {
            "title": "GENERIC_ERROR",
            "code": "CPI0001",
            "details": "Erro no processamento da aplicação."
        }
    ]
```

### 2. **Buscar indicadores de preço do veículo**

- **URL:** `/v1/indicators`
- **Método:** `GET`
- **Parâmetros:**
    - `licensePlate` (obrigatório) - Placa do veículo.
    - `date` (opcional) - Data no formato "MM/yyyy".
    - `type` (opcional) - Tipo do indicador de preço. Valores possíveis: `ALL`, `BOTH`, `FIPE`, `ICARROS`. Padrão: `ALL`.

- **Exemplo de requisição:**
  ```
  GET /v1/indicators?licensePlate=ABC1234&date=03/2025&type=FIPE
  ```

- **Resposta (200):**
  ```json
  [
    {
      "valorFIPE": "50000",
      "data": "03/2025"
    }
  ]
  ```

#### Regras de Negócio:

##### 1. Verificação do Veículo:

O sistema verifica existe um veículo com a placa informada. Caso não exista, um erro é retornado:

```json
    [
      {
          "title": "VEHICLE_NOT_FOUND",
          "code": "CPI0006",
          "details": "Nenhum veículo foi encontrado com a placa 'ABC1234'."
      }
    ]
```

##### 2. Verificação de Indicadores:

O sistema verifica se há indicadores com a combinação de filtros informados. Caso não haja, um erro é retornado:

```json
    [
      {
          "title": "INDICATOR_NOT_FOUND",
          "code": "CPI0007",
          "details": "Nenhum indicador foi encontrado para os critérios informados."
      }
    ]
```

##### 3. Verificação de Campo obrigatório:

O sistema verifica os campos obrigatórios para a pesquisa foram informados. Caso não tenham sido, um erro é retornado:

```json
    [
      {
          "title": "REQUIRED_INFORMATION_MISSING",
          "code": "CPI0003",
          "details": "O campo 'licensePlate' é obrigatório."
      }
    ]
```

##### 4. Verificação de valor correto para filtro:

O sistema verifica se o filtro "type" foi preenchido com o valor esperado. Caso não tenha sido, um erro é retornado:

```json
    [
      {
          "title": "INVALID_PARAMETER_VALUE",
          "code": "CPI0004",
          "details": "O valor do campo 'type' é inválido."
      }
    ]
```

#### 5. Erro Genérico:

Se ocorrer qualquer erro inesperado ao tentar processar a requisição, o sistema retorna um erro genérico:

```json
    [
        {
            "title": "GENERIC_ERROR",
            "code": "CPI0001",
            "details": "Erro no processamento da aplicação."
        }
    ]
```

## Configuração do Ambiente

### 1. **Configuração do Maven**

O projeto utiliza o **Maven** para gerenciamento de dependências e build.

### 2. **Banco de Dados**

O banco de dados utilizado para desenvolvimento e testes é o **H2**. Durante a execução, ele será carregado em memória e as informações salvas em um arquivo.

### 3. **Execução da Aplicação**

Para rodar a aplicação localmente, use o comando Maven:

```bash
mvn spring-boot:run
```

A aplicação será iniciada no endereço `http://localhost:8080`.

### 4. **Testes Unitários**

Os testes unitários são realizados com o **JUnit 5** e o **Mockito**. A fim de avaliar a cobertura dos testes, **Jacoco** foi implementado. 
O projeto possui 100% de cobertura de testes. Para rodar os testes, utilize o comando Maven:

```bash
mvn test
```

### 5. Checkstyle
Este projeto está configurado para utilizar o Checkstyle com um conjunto de regras de codificação definidas no arquivo src/main/resources/checkstyle.xml. A ferramenta é executada automaticamente durante o ciclo de build do Maven, na fase de validação, e qualquer violação das regras irá resultar em falha no build.


## Documentação da API

A [documentação interativa da API](https://github.com/BrunaCelestino/vehicle-price-indicator/tree/main/src/main/resources/swagger/api-docs.json) é gerada automaticamente utilizando o **Swagger UI**. Para acessá-la, após rodar a aplicação, acesse:

```
http://localhost:8080/swagger-ui/index.html
```


---

Bruna Massuchini