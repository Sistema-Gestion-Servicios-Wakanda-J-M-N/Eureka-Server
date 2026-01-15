# Eureka-Server/Grafana/Prometheus

Organizacion con todos --> https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N 

1. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Frontend-Wakanda
2. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-API-Central   -> API-Central (Gestiona los microservicios y muestra un tablero de Wakanda).
3. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Eureka-Server   -> Eureka-Server/Grafana/Prometheus
4. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/InitManager   -> Lanza el Eureka-Server/Prometheus/Grafana y ejecuta un script para lanzar los microservicios.
5. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Python-Script-Launcher   -> Script para lanzar los microservicios en orden.
6. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend_Wakanda_Salud
7. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Agua
8. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Transporte-Movilidad
9. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Gobierno
10. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Cultura-Ocio-Turismo
11. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Trafico
12. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Seguridad
13. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Residuos
14. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Conectividad-Redes
15. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Energia-Sostenible-Eficiente
16. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Servicios-Emergencia
17. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Educacion

## Participantes del proyecto

- Jaime López Díaz
- Marcos García Benito
- Nicolas Jimenez
- Juan Manuel
  

# Servidor Eureka con integración de Prometheus y Grafana

Este repositorio contiene una implementación de un **Servidor Eureka basado en Spring Boot**, configurado para trabajar de manera integrada con **Prometheus** y **Grafana** para descubrimiento de servicios y monitorización. Este proyecto automatiza la configuración de todos los componentes (Servidor Eureka, Prometheus y Grafana) mediante un simple método `main`, utilizando Docker para la orquestación de contenedores.

---

## Tabla de contenidos

1. [Introducción](#introducción)
2. [Detalles Técnicos](#detalles-técnicos)
   - [Servidor Eureka](#servidor-eureka)
   - [Prometheus](#prometheus)
   - [Grafana](#grafana)
3. [Configuración](#configuración)
   - [Requisitos Previos](#requisitos-previos)
   - [Cómo Ejecutar](#cómo-ejecutar)
4. [Integración de Microservicios](#integración-de-microservicios)
5. [Configuración de Prometheus](#configuración-de-prometheus)
6. [Estructura del Proyecto](#estructura-del-proyecto)
7. [Preguntas Frecuentes](#preguntas-frecuentes)

---

## Introducción

El Servidor Eureka actúa como un registro de servicios que permite el descubrimiento de microservicios. Este proyecto amplía su utilidad al integrar **Prometheus** y **Grafana** para monitorizar tanto el Servidor Eureka como los microservicios conectados. Esta configuración automatiza:
- La construcción y ejecución del Servidor Eureka con Maven y Docker.
- La configuración de una red de Docker para comunicación entre contenedores.
- El despliegue de contenedores de Prometheus y Grafana para monitorización.

---

## Detalles Técnicos

### Servidor Eureka
- **Propósito**: Descubrimiento de servicios para microservicios.
- **Puerto**: Expuesto en `8762` (internamente funciona en `8761`).
- **Características principales**:
  - Configurado con Spring Boot y Spring Cloud Netflix.
  - Registro y descubrimiento dinámico de servicios.
  - Expone métricas de Spring Boot Actuator para Prometheus.

### Prometheus
- **Propósito**: Sistema de monitorización para recolectar y almacenar métricas.
- **Puerto**: `9090`.
- **Configuración**:
  - Recolecta métricas del Servidor Eureka y los microservicios registrados.
  - Configurable mediante `prometheus.yml`.

### Grafana
- **Propósito**: Visualización de métricas recolectadas por Prometheus.
- **Puerto**: `3000`.
- **Características**:
  - Configuración lista para usar con Prometheus como fuente de datos.
  - Dashboards personalizados para monitorización de servicios.

---

## Configuración

### Requisitos Previos
1. Docker instalado y en ejecución.
2. Maven (o Maven Wrapper).
3. Java 17 o superior.

### Cómo Ejecutar
1. Clona este repositorio:
   ```bash
   git clone <repository_url>
   cd <repository_name>
   ```
2. Ejecuta el proyecto directamente utilizando el método `main`:
   ```bash
   ./mvnw spring-boot:run
   ```
   Esto realizará:
   - La construcción del proyecto usando Maven.
   - La configuración de la red de Docker necesaria.
   - La construcción y ejecución de los contenedores de Eureka Server, Prometheus y Grafana.

3. Accede a los servicios:
   - Servidor Eureka: [http://localhost:8762](http://localhost:8762)
   - Prometheus: [http://localhost:9090](http://localhost:9090)
   - Grafana: [http://localhost:3000](http://localhost:3000)

---

## Integración de Microservicios

### Registro de Microservicios con Eureka
Para registrar un microservicio con Eureka:
1. Incluye la siguiente dependencia en el `pom.xml` del microservicio:
   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
   </dependency>
   ```
2. Agrega la siguiente configuración al `application.yml` del microservicio:
   ```yaml
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8762/eureka/
   ```

### Monitorización de Microservicios con Prometheus
Para habilitar la monitorización de Prometheus para un microservicio:
1. Asegúrate de que el microservicio exponga métricas de Spring Boot Actuator:
   ```yaml
   management:
     endpoints:
       web:
         exposure:
           include: prometheus
   ```
2. Agrega los detalles del microservicio a `prometheus.yml`:
   ```yaml
   - job_name: "<nombre-del-microservicio>"
     metrics_path: "/actuator/prometheus"
     static_configs:
       - targets: ["<host-del-microservicio>:<puerto>"]
   ```

---

## Configuración de Prometheus

El archivo `prometheus.yml` en el directorio principal incluye configuraciones predefinidas para:
- **Servidor Eureka**.
- **Múltiples microservicios backend**.

Ejemplo:
```yaml
scrape_configs:
  - job_name: "eureka-server"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["eureka-server:8761"]
        labels:
          service: "eureka-server"
  - job_name: "backend-microservice"
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["backend-microservice:8080"]
        labels:
          service: "backend-microservice"
```

---

## Estructura del Proyecto

- **`EurekaServerApplication.java`**:
  - Punto de entrada principal del proyecto.
  - Automatiza la construcción con Maven y la configuración de contenedores Docker.
- **`application.yml`**:
  - Configura el Servidor Eureka, los endpoints de actuator y los niveles de logging.
- **`docker-compose.yml`**:
  - Orquesta los contenedores de Eureka Server, Prometheus y Grafana.
- **`prometheus.yml`**:
  - Define configuraciones de recolección de métricas.
- **Dockerfile**:
  - Construcción multi-etapa para la imagen Docker del Servidor Eureka.

---

## Preguntas Frecuentes

1. **¿Cómo agrego un nuevo microservicio?**
   - Regístralo con Eureka usando la configuración `eureka.client.service-url`.
   - Agrega su ruta de métricas a `prometheus.yml`.

2. **¿Cómo puedo visualizar métricas en Grafana?**
   - Inicia sesión en Grafana en [http://localhost:3000](http://localhost:3000).
   - Agrega Prometheus como fuente de datos (`http://prometheus:9090`).
   - Importa o crea dashboards personalizados.

3. **¿Qué hago si la creación de la red de Docker falla?**
   - Crea la red manualmente utilizando:
     ```bash
     docker network create backend-network
     ```

---


