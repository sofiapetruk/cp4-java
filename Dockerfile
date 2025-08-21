FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

CMD ["java", "-jar", "target/cp1-0.0.1-SNAPSHOT.jar"]