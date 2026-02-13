# Utiliza una imagen base con OpenJDK 17 y Gradle 7.4.0
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto al directorio de trabajo
COPY pom.xml .
COPY src ./src

# Construye tu aplicación con Gradle
RUN  mvn clean package -DskipTests

# Cambia a una imagen más ligera de OpenJDK 17 para la ejecución
FROM eclipse-temurin:21-jre-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al directorio de trabajo
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080
ENV PORT=8080

# Define el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
