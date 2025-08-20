FROM ubuntu:latest
LABEL authors="sofia.petruk"

ENTRYPOINT ["top", "-b"]

# Use uma imagem base do Ubuntu para construção
FROM ubuntu:latest AS build

# Atualize o repositório e instale o JDK 21 e Maven
RUN apt-get update && apt-get install openjdk-21-jdk maven -y

# Defina o diretório de trabalho para a construção
WORKDIR /app

# Copie o arquivo pom.xml e o código-fonte do projeto para o contêiner
COPY pom.xml .
COPY src ./src

# Execute a construção do Maven ignorando os testes
RUN mvn clean install -DskipTests

# Use uma imagem base mais leve para executar o aplicativo
FROM openjdk:21-jdk-slim

# Criar usuário não-root para segurança
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Expor a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado para a imagem final
COPY --from=build /app/target/cp1-0.0.1-SNAPSHOT.jar app.jar

# Dar permissões adequadas ao usuário appuser
RUN chown -R appuser:appuser /app
RUN chmod +x app.jar

# Mudar para o usuário não-root
USER appuser

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]