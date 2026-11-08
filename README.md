# Auxy API

Projeto criado para estudo e prática de desenvolvimento backend utilizando Java e Spring Boot.

O objetivo é exercitar conceitos modernos do ecossistema Java, arquitetura backend, APIs REST, persistência de dados e práticas comuns encontradas em aplicações corporativas.

## Tecnologias

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- Spring Data JPA / Hibernate
- Validation
- Git / GitHub

## Domínio do projeto

A aplicação está sendo modelada como uma plataforma multi-tenant.

```text
Tenant
 └── Cliente
      └── Orçamento
           └── Serviço
                └── Fatura
```

Todos os dados de negócio pertencem a um Tenant, permitindo o isolamento lógico entre organizações.

---

<details>
<summary>✅ Etapa 1 - Estrutura Inicial do Projeto</summary>

### Concluído

- Projeto Spring Boot criado
- Configuração Maven
- Java 21
- Integração com Git
- Integração com GitHub
- Health Check endpoint
- application.yml
- application-local.example.yml
- Configuração de profiles
- Desativação temporária do DataSource para desenvolvimento sem banco

### Objetivo

Permitir a inicialização da aplicação e criação da estrutura base do projeto antes da configuração da persistência.

</details>

---

<details>
<summary>⬜ Etapa 2 - API REST de Tenant</summary>

### Objetivos

- Estruturar as camadas da aplicação
- Implementar CRUD de Tenant
- Expor endpoints REST
- Criar contratos de Request e Response

### Endpoints

- GET /tenants
- GET /tenants/{id}
- POST /tenants
- PUT /tenants/{id}
- DELETE /tenants/{id}

### Estrutura

- Controller
- Service
- DTO Response
- DTO Request

### Conceitos

- Injeção de Dependência
- IoC Container
- Serialização JSON
- PathVariable
- RequestBody
- ResponseEntity
- Status HTTP

</details>

---

<details>
<summary>⬜ Etapa 3 - Validação de Dados</summary>

### Objetivos

- @NotNull
- @NotBlank
- @Email
- @Size
- Validação automática do Spring

### Conceitos

- Bean Validation
- Mensagens de erro
- Contratos de API

</details>

---

<details>
<summary>⬜ Etapa 4 - PostgreSQL</summary>

### Objetivos

- Configurar PostgreSQL
- Configurar DataSource
- Configurar Profiles
- Integração da aplicação com banco

### Conceitos

- JDBC
- Connection Pool
- Configuração por ambiente

</details>

---

<details>
<summary>⬜ Etapa 5 - JPA e Hibernate</summary>

### Objetivos

- Entity
- Repository
- JpaRepository
- Persistência de Tenant

### Conceitos

- ORM
- JPA
- Hibernate
- Ciclo de vida das entidades

</details>

---

<details>
<summary>⬜ Etapa 6 - Relacionamentos</summary>

### Objetivos

- Tenant → Cliente
- Cliente → Orçamento
- Orçamento → Serviço
- Serviço → Fatura

### Conceitos

- @OneToMany
- @ManyToOne
- Cascades
- Fetch Types

</details>

---

<details>
<summary>⬜ Etapa 7 - Tratamento de Erros</summary>

### Objetivos

- Exceptions customizadas
- Global Exception Handler
- Padronização de respostas

### Conceitos

- @RestControllerAdvice
- @ExceptionHandler

</details>

---

<details>
<summary>⬜ Etapa 8 - Testes</summary>

### Objetivos

- Testes unitários
- Testes de integração
- Testes de Controller
- Testes de Service

### Conceitos

- JUnit
- Mockito
- Spring Boot Test

</details>

---

<details>
<summary>⬜ Etapa 9 - Infraestrutura</summary>

### Objetivos

- Docker
- Docker Compose
- PostgreSQL em container
- Ambientes locais padronizados

</details>

---

<details>
<summary>⬜ Etapa 10 - Arquitetura e Produção</summary>

### Objetivos

- Multi-tenancy
- Observabilidade
- Logs estruturados
- Métricas
- Health Checks

</details>
