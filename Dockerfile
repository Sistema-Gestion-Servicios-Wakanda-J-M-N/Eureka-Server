# Etapa de construcción
FROM openjdk:17-jdk-slim AS build

# Instalamos Maven
RUN apt-get update && apt-get install -y maven

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el resto del proyecto
COPY . /app

# Ejecutamos Maven para construir el proyecto y generar el JAR
RUN mvn clean package -DskipTests

# Imagen final liviana
FROM openjdk:17-jdk-slim

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/target/Eureka-Server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Exponemos el puerto en el que el servidor escuchará
EXPOSE 8761

# Comando para iniciar Eureka
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]
