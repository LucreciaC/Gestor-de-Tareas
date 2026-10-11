# Etapa 1: compilar el .jar con Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Primero solo el pom, para que Docker cachee las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
# Los tests necesitan una base de datos, por eso se saltean en la imagen
RUN mvn package -DskipTests -B

# Etapa 2: imagen final, solo con el JRE y el .jar
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 3035
ENTRYPOINT ["java", "-jar", "app.jar"]
