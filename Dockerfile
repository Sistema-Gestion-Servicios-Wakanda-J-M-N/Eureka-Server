# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim
  
  # Establecer el directorio de trabajo
WORKDIR /app
  
  # Copiar el archivo JAR del servidor Eureka
COPY target/Eureka-Server-0.0.1-SNAPSHOT.jar eureka-server.jar
  
  # Exponer el puerto del servidor Eureka
EXPOSE 8761
  
  # Ejecutar el servidor Eureka
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
