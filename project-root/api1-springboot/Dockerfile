# Dockerfile para API Spring Boot
FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY projeto_t_a_c/pom.xml .
COPY projeto_t_a_c/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

