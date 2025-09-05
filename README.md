# 📘 Projeto Java API + MySQL com Docker Compose

# 👥 Membros da Equipe

- **JULIA MONTEIRO** - RM: 557023
- **LUCAS ASSIS FIALHO** - RM: 557884
- **SOFIA ANDRADE PETRUK** - RM: 556585


---

Este projeto consiste em uma aplicação **Java API** containerizada que se conecta a um banco de dados **MySQL**, ambos orquestrados com **Docker Compose**.



---

## 🖥️ Arquitetura do Projeto

A solução é composta por dois serviços principais:

- **Java API Container** → Responsável pela aplicação desenvolvida em Java (Spring Boot)
- **MySQL Database Container** → Banco de dados relacional utilizado pela aplicação

Ambos são gerenciados pelo **Docker Compose**.

---

## 🚀 Instruções de Uso

1. **Instale as dependências necessárias:**
   - [Docker](https://docs.docker.com/get-docker/)
   - [Docker Compose](https://docs.docker.com/compose/)

2. **Clone o repositório:**
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <PASTA_DO_PROJETO>
   ```

3. **Verifique os arquivos principais:**
   - `docker-compose.yml` → Serviços (API + banco MySQL), volumes e rede
   - `Dockerfile` → Build da aplicação Java
   - `application.properties` → Configurações da aplicação

4. **Suba os serviços:**
   ```bash
   docker-compose up -d
   ```

5. **Acesse a aplicação:**
   - **URL:** http://localhost:8282
   - **Porta configurada:** 8282

---

## 🛠️ Comandos Essenciais do Docker Compose

### Subir containers em segundo plano
```bash
docker-compose up -d
```

### Parar e remover containers
```bash
docker-compose down
```

### Reconstruir a imagem da aplicação
```bash
docker-compose build app
```

### Ver logs da aplicação
```bash
docker-compose logs -f app
```

### Ver logs do banco
```bash
docker-compose logs -f db
```

### Listar containers ativos
```bash
docker ps
```

---

## 📦 Processo de Deploy

1. **Build da aplicação Java (se houver alterações no código):**
   ```bash
   docker-compose build app
   ```

2. **Subir os serviços (API + MySQL):**
   ```bash
   docker-compose up -d
   ```

3. **Verificar se estão rodando:**
   ```bash
   docker-compose ps
   ```

4. **Testar a aplicação:**
   ```bash
   curl http://localhost:8282
   ```

---

## 🐞 Troubleshooting Básico

### Erro: Porta em uso (8282 ou 3306)
- Verifique quais processos estão usando a porta
- Altere as portas no `docker-compose.yml` se necessário

### Erro de autenticação no MySQL
- Confirme se `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD` estão corretos
- Ajuste no `application.properties` ou nas variáveis de ambiente

### API não conecta ao banco
- Verifique se o banco está saudável:
  ```bash
  docker-compose ps
  ```
- Aguarde o healthcheck finalizar

### Forçar rebuild e resetar volumes
```bash
docker-compose down -v
docker-compose up --build
```

---

## ⚙️ Configuração do application.properties

Exemplo de configuração para conectar ao MySQL via Docker:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://db:3306/nome_do_banco
spring.datasource.username=root
spring.datasource.password=senha_do_banco
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8282
```

---

## 📂 Estrutura do Projeto

```
projeto/
├── src/
│   └── main/
│       ├── java/
│       └── resources/
│           └── application.properties
├── docker-compose.yml
├── Dockerfile
└── README.md
```

---

## ✅ Conclusão

Este projeto demonstra como utilizar **Docker Compose** para orquestrar uma aplicação Java API integrada a um banco MySQL, garantindo:

- Isolamento dos serviços
- Comunicação via rede interna
- Persistência de dados
- Deploy simplificado

---