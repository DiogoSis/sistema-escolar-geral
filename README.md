# Sistema de Gestão Escolar

Este é um projeto de Sistema de Gestão Escolar em desenvolvimento.

## Visão Geral

O objetivo deste projeto é criar um sistema completo para gerenciar as diversas atividades de uma instituição de ensino, incluindo matrículas, notas, frequência, comunicação, finanças, entre outros.

**Importante:** Este projeto está em estágio inicial de desenvolvimento. Muitas funcionalidades ainda não foram implementadas e o código existente está sujeito a grandes alterações.

## Tecnologias Utilizadas

O projeto é construído utilizando as seguintes tecnologias:

*   **Backend:**
    *   Java 17
    *   Spring Boot 3
    *   Spring Data JPA
    *   Maven
    *   PostgreSQL
*   **Frontend:**
    *   Node.js (para build)
    *   React (planejado)
    *   Nginx (para servir os arquivos estáticos)
*   **Banco de Dados:**
    *   PostgreSQL 16
*   **Contêinerização:**
    *   Docker
    *   Docker Compose

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
sistema-escola/
├── backend/                # Aplicação backend Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/               # Aplicação frontend React (ainda em construção)
│   ├── public/
│   ├── src/
│   ├── package.json
│   ├── nginx/
│   │   └── default.conf
│   └── Dockerfile
├── db/                     # Scripts de inicialização do banco de dados
│   └── init.sql
├── docker-compose.yml      # Arquivo de orquestração dos contêineres
└── README.md               # Este arquivo
```

## Como Executar o Projeto

**Pré-requisitos:**

*   Docker
*   Docker Compose

**Passos:**

1.  Clone o repositório:
    ```bash
    git clone <url-do-repositorio>
    cd sistema-escola
    ```

2.  Suba os contêineres utilizando Docker Compose:
    ```bash
    docker-compose up -d --build
    ```

    Isso irá construir as imagens e iniciar os seguintes serviços:
    *   `escola-db`: Banco de dados PostgreSQL, acessível na porta `5432`.
    *   `escola-backend`: Aplicação backend Spring Boot, acessível na porta `8080`.
    *   `escola-frontend`: Aplicação frontend React (servida pelo Nginx), acessível na porta `3000` (atualmente comentado no `docker-compose.yml`).

3.  Para parar os serviços:
    ```bash
    docker-compose down
    ```

## Status Atual e Próximos Passos

Como mencionado, o projeto está em sua fase inicial. As principais áreas de foco para o desenvolvimento futuro incluem:

*   **Backend:**
    *   Implementação das entidades JPA.
    *   Criação dos repositórios e serviços.
    *   Desenvolvimento das APIs REST.
    *   Configuração de segurança (Spring Security).
    *   Implementação de testes unitários e de integração.
*   **Frontend:**
    *   Desenvolvimento da interface do usuário com React.
    *   Integração com as APIs do backend.
    *   Criação de componentes reutilizáveis.
*   **Geral:**
    *   Definição detalhada dos requisitos e funcionalidades.
    *   Melhoria da documentação.
    *   Configuração de CI/CD.


