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

### Objetivos

- Criar projeto Spring Boot
- Configurar Maven
- Configurar Java 21
- Configurar Git
- Configurar GitHub
- Implementar endpoint de Health Check
- Configurar application.yml
- Configurar application-local.example.yml
- Configurar profiles de ambiente
- Permitir inicialização da aplicação sem banco de dados

### Conceitos

- Spring Boot Bootstrap
- Maven
- Profiles
- YAML
- Versionamento com Git
- Publicação no GitHub
- Auto Configuration
- DataSource Auto Configuration
- 
</details>

---

<details>
<summary>✅ Etapa 2 - API REST de Tenant</summary>

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
<summary>✅ Etapa 3 - Validação de Dados</summary>

### Objetivos

- @NotNull
- @NotBlank
- @Email
- @Size
- Validação automática do Spring

### Conceitos

- Bean Validation
- Contratos de API

</details>

---

<details>
<summary>⬜ Etapa 4 - Persistência com PostgreSQL e JPA</summary>

### Objetivos

- Configurar PostgreSQL
- Configurar DataSource
- Configurar Profiles
- Integrar aplicação com banco de dados
- Criar entidade Tenant
- Criar Repository
- Implementar persistência de Tenant
- Substituir armazenamento em memória por persistência real

### Conceitos

- JDBC
- Connection Pool
- Configuração por ambiente
- ORM
- JPA
- Hibernate
- Entity
- Repository
- JpaRepository
- Ciclo de vida das entidades

</details>

---

<details>
<summary>⬜ Etapa 5 - Relacionamentos</summary>

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
<summary>⬜ Etapa 6 - Tratamento de Erros</summary>

### Objetivos

- Exceptions customizadas
- Global Exception Handler
- Padronização de respostas
- Mensagens de erro amigáveis

### Conceitos

- @RestControllerAdvice
- @ExceptionHandler

</details>

---

<details>
<summary>⬜ Etapa 7 - Testes</summary>

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
<summary>⬜ Etapa 8 - Infraestrutura</summary>

### Objetivos

- Docker
- Docker Compose
- PostgreSQL em container
- Ambientes locais padronizados

</details>

---

<details>
<summary>⬜ Etapa 9 - Arquitetura e Produção</summary>

### Objetivos

- Multi-tenancy
- Observabilidade
- Logs estruturados
- Métricas
- Health Checks

</details>
