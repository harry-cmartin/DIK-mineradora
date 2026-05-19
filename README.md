# 🏔️ DIK-mineradora

Sistema de gerenciamento de propostas comerciais para uma mineradora, construído com arquitetura de microserviços utilizando Quarkus, Jakarta EE e tecnologias modernas de observabilidade e mensageria.

---

## Sobre o Projeto

O **DIK-mineradora** é uma aplicação distribuída que permite o gerenciamento completo de propostas comerciais de venda de minérios, incluindo:

- ✅ Criação e consulta de propostas de venda
- ✅ Geração de relatórios em CSV e JSON
- ✅ Autenticação e autorização via OAuth2/OIDC (Keycloak)
- ✅ Comunicação assíncrona via Apache Kafka
- ✅ Rastreamento distribuído com OpenTelemetry + Jaeger
- ✅ Documentação de APIs com OpenAPI/Swagger

---

##  Arquitetura

O projeto segue o padrão de **microserviços**, sendo dividido nas seguintes aplicações:

1. **API Gateway (`api-gateway`)**: Ponto de entrada único da aplicação. Responsável por receber as requisições externas, validar a autenticação e rotear para os microserviços adequados.
2. **Proposta (`proposta`)**: Serviço responsável por gerenciar (Criar, Ler, Atualizar, Deletar) as propostas comerciais.
3. **Cotação (`cotacao`)**: Serviço responsável por gerenciar ou buscar cotações de mercado necessárias para as propostas.
4. **Relatório (`relatorio`)**: Serviço responsável por consolidar os dados e gerar relatórios (CSV/JSON). Consome eventos do Kafka gerados pelos outros serviços.

---

## Tecnologias Utilizadas

- **Linguagem:** Java SDK 21
- **Framework:** Quarkus (v3.35.2) e Jakarta EE
- **Banco de Dados:** PostgreSQL (via Hibernate ORM com Panache)
- **Mensageria:** Apache Kafka
- **Segurança:** Keycloak (OAuth2 / OIDC) para emissão e validação de tokens JWT
- **Observabilidade:** OpenTelemetry e Jaeger (Tracing Distribuído)
- **Utilitários:** Lombok, Apache Commons CSV
- **Infraestrutura:** Docker e Docker Compose

---

## Rotas e Acessos

Assumindo que o projeto esteja rodando localmente com as configurações padrão do Quarkus, as principais rotas de acesso são:

### API Gateway
**API Gateway** é o ponto central de entrada da aplicação. Todas as requisições devem ser feitas para ele, que se encarregará de repassar para os microserviços competentes de Propostas e Relatórios.

### Autenticação (Keycloak)
Para acessar as rotas protegidas do Gateway, você precisa primeiro gerar um Token JWT de acesso (Access Token) via Keycloak.

## Antes disso os usuários devem estar cadastrados no Keycloak e as roles caso devem ser atribuidas a eles de acordo com o perfil de acesso desejado.

- **Rota de Login (Geração de Token):**
    - **Método:** `POST`
    - **URL:** `http://localhost:8180/realms/quarkus/protocol/openid-connect/token` *(A porta `8180` e o realm `quarkus` podem variar conforme a configuração do seu docker-compose e do arquivo realm.json)*
    - **Body (x-www-form-urlencoded):**
        - `client_id`: `<seu-client-id>`
        - `username`: `<usuario>`
        - `password`: `<senha>`
        - `grant_type`: `password`

Com o `access_token` em mãos, passe-o no Header das próximas requisições:
`Authorization: Bearer <seu_token>`


### Roles 
Mapeamento de roles do projeto.

- `user` - Usuário comum do sistema
- `manager` - Gerente da empresa
- `costumer` - Cliente que envia as propostas

#### Propostas 
Gerencia o ciclo de vida das propostas comerciais.

- `POST /api/trade` - Cria uma nova proposta.
- `GET /api/trade/{id}` - Lista proposta por id.
- `DELETE /api/trade/remove/{id}` - Remove uma proposta.

#### Relatórios / Registros 
Responsável por buscar os dados consolidados gerados de forma assíncrona.

- `GET /api/records/report` - Gera relatório em formato CSV .
- `GET /api/records/data` - Gera relatório em formato JSON.

---

### Documentação (Swagger UI)
A interface interativa da documentação da API pode ser acessada em:
- **Swagger UI:** `http://localhost:8092/q/swagger-ui` 
- **OpenAPI Schema:** `http://localhost:8092/q/openapi`

### Observabilidade (Jaeger)
O painel do Jaeger para visualização do rastreamento distribuído (Tracing) fica disponível em:
- **Jaeger UI:** `http://localhost:16686`

---

## Como Rodar o Projeto

### Pré-requisitos
- **Java 21** instalado.
- **Docker** e **Docker Compose** instalados.
- **Maven** (Opcional, pois o projeto usa o `mvnw` wrapper).

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd DIK-mineradora
   ```

2. **Suba a infraestrutura (Banco de Dados, Kafka, Keycloak, Jaeger):**
   Na raiz do projeto, execute o docker-compose para iniciar os serviços de infra:
   ```bash
   docker-compose up -d
   ```

3. **Execute os Microserviços:**
   Você precisará iniciar os serviços individualmente em modo de desenvolvimento. Em terminais separados, navegue até cada pasta e execute:

   *API Gateway:*
   ```bash
   cd api-gateway
   ./mvnw compile quarkus:dev
   ```

   *Proposta:*
   ```bash
   cd proposta
   ./mvnw compile quarkus:dev
   ```

   *Cotação:*
   ```bash
   cd cotacao
   ./mvnw compile quarkus:dev
   ```

   *Relatório:*
   ```bash
   cd relatorio
   ./mvnw compile quarkus:dev
   ```

   *(Nota: O Quarkus atribuirá portas diferentes automaticamente ou baseadas no seu `application.properties` para evitar conflito na máquina local).*

4. **Testando a Aplicação:**
    - Obtenha um token de acesso no Keycloak.
    - Acesse o Swagger UI do API Gateway para realizar as requisições autenticadas.
    - Verifique os traces no Jaeger em `http://localhost:16686` após realizar as requisições.