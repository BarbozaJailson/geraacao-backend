# Etapa 1: Compilar o projeto
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

# Copia os arquivos do Maven
COPY pom.xml .
COPY src ./src

# Compila o projeto (gera o .jar na pasta target)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Etapa 2: Rodar o projeto
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o .jar gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para iniciar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
