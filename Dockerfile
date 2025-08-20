# Use uma imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Criar usuário não-root para segurança
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Expor a porta 8080
EXPOSE 8080

# Defina o diretório de trabalho
WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build da aplicação
RUN mvn clean install -DskipTests

# Dar permissões adequadas
RUN chown -R appuser:appuser /app
RUN chmod +x target/cp1-0.0.1-SNAPSHOT.jar

# Mudar para o usuário não-root
USER appuser

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "target/cp1-0.0.1-SNAPSHOT.jar"]