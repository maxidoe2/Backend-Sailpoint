# Etapa 1: build con Maven y JDK 21
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q clean package -DskipTests

# Etapa 2: runtime solo con JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el JAR generado en la etapa de build
COPY --from=build /app/target/assertiva-sailpoint-backend-0.0.1-SNAPSHOT.jar app.jar

# Render setea $PORT, Spring Boot lo toma por server.port=${PORT:8080}
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
