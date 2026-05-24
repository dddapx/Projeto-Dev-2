# Monitor de Investimentos - Backend

Este é o repositório do backend do **Monitor de Investimentos**, uma API RESTful completa desenvolvida com **Java e Spring Boot**. A API foi projetada para ser o núcleo do sistema, fornecendo todos os endpoints necessários para o gerenciamento de investidores, carteiras, ativos financeiros e transações, com integração em tempo real à bolsa de valores.

---

## Arquitetura e Visão Geral

O Monitor de Investimentos adota uma arquitetura desacoplada, separando a interface do usuário (frontend) da lógica de negócio e persistência de dados (backend).

- **Backend (este repositório):** Desenvolvido como uma API RESTful, é responsável por toda a lógica de negócio, comunicação com o banco de dados PostgreSQL via Supabase, integração com a API externa HG Brasil Finance para cotações em tempo real, e exposição de endpoints seguros e bem definidos.

- **Frontend:** Desenvolvido em React e disponível em um repositório separado, consome os endpoints fornecidos pelo backend para renderizar a interface, gerenciar interações do usuário e apresentar os dados de forma dinâmica, sem nunca acessar o banco de dados diretamente.

---

## Funcionalidades da API

A API fornece endpoints completos para todas as funcionalidades do sistema:

- **Gerenciamento de Investidores:** CRUD completo para cadastro e manutenção de investidores.
- **Gerenciamento de Carteiras:** Criação e manutenção de carteiras de investimento vinculadas a investidores.
- **Ativos Financeiros:** Cadastro de ativos com busca automática de cotação em tempo real via HG Brasil Finance no momento do cadastro, com persistência offline da cotação no banco local.
- **Transações:** Registro de operações de compra e venda de ativos dentro de carteiras.
- **Relatório Agregado:** Endpoint que combina dados locais com cotações em tempo real, retornando o valor total investido e o valor atual da carteira com base nos preços do mercado.
- **Persistência Offline:** Todas as cotações recuperadas da API externa são salvas localmente para consulta sem dependência de internet.
- **Tratamento de Exceções:** Respostas padronizadas com os status HTTP corretos (200, 201, 404, 502, 500) para todos os cenários de erro.

---

## Documentação da API (Swagger)

A API está documentada com Springdoc (Swagger UI). Após iniciar a aplicação, a documentação interativa fica disponível para consulta e teste de todos os endpoints.

**URL da Documentação (projeto deve estar rodando):**
```
http://localhost:8080/swagger-ui/index.html
```

---

## Tecnologias Utilizadas

- **Java 17+** — Linguagem principal do projeto.
- **Spring Boot** — Framework principal para construção da API.
- **Spring Data JPA / Hibernate** — Para persistência de dados e mapeamento objeto-relacional.
- **PostgreSQL (via Supabase)** — Banco de dados relacional hospedado e gerenciado pelo Supabase.
- **HG Brasil Finance API** — API externa para busca de cotações de ações em tempo real.
- **Springdoc (Swagger UI)** — Para documentação interativa da API.
- **Maven** — Gerenciador de dependências e build do projeto.
- **RestTemplate** — Para consumo da API externa HG Brasil.

---

## Modelagem do Banco de Dados

O sistema possui 5 tabelas relacionadas:

| Tabela | Descrição |
|---|---|
| `tb_investidor` | Dados dos investidores cadastrados |
| `tb_carteira` | Carteiras vinculadas a investidores (N:1) |
| `tb_ativo_financeiro` | Ativos da bolsa cadastrados no sistema |
| `tb_transacao` | Operações de compra/venda (N:1 com carteira e ativo) |
| `tb_cotacao_historica` | Histórico de cotações salvas da API externa (N:1 com ativo) |

---

## Pré-requisitos

- Java (JDK) 17 ou superior
- Maven 3.8+
- O projeto já está configurado para utilizar o banco de dados PostgreSQL no Supabase. Não é necessário criar um novo banco.

---

## Configuração e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/dddapx/Projeto-Dev-2.git
cd Projeto-Dev-2/projeto-semestre
```

### 2. Execute o projeto

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

A documentação Swagger estará disponível em `http://localhost:8080/swagger-ui/index.html`.

---

## Endpoints Disponíveis

### Investidores
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/investidores` | Lista todos os investidores |
| GET | `/api/investidores/{id}` | Busca investidor por ID |
| POST | `/api/investidores` | Cria novo investidor |
| PUT | `/api/investidores/{id}` | Atualiza investidor |
| DELETE | `/api/investidores/{id}` | Remove investidor |

### Carteiras
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/carteiras` | Lista carteiras (filtro opcional por `?investidorId=`) |
| GET | `/api/carteiras/{id}` | Busca carteira por ID |
| GET | `/api/carteiras/{id}/resumo` | Relatório com valor investido e valor atual (tempo real) |
| POST | `/api/carteiras` | Cria nova carteira |
| PUT | `/api/carteiras/{id}` | Atualiza carteira |
| DELETE | `/api/carteiras/{id}` | Remove carteira |

### Ativos Financeiros
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/ativos` | Lista todos os ativos |
| GET | `/api/ativos/{id}` | Busca ativo por ID |
| POST | `/api/ativos` | Cadastra ativo e busca cotação automaticamente |
| DELETE | `/api/ativos/{id}` | Remove ativo |

### Transações
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/transacoes` | Lista transações (filtro opcional por `?carteiraId=`) |
| POST | `/api/transacoes` | Registra nova transação de compra ou venda |

---

## Executando o Ecossistema Completo

Para que o Monitor de Investimentos funcione em sua totalidade, o frontend e o backend devem ser executados simultaneamente.

**Terminal 1 — Backend:**
```bash
cd projeto-semestre
mvn spring-boot:run
```

**Terminal 2 — Frontend:**
```bash
cd seu-frontend
npm run dev
```

Com ambas as aplicações em execução, acesse o frontend em `http://localhost:5173` para ter a experiência completa do sistema.
