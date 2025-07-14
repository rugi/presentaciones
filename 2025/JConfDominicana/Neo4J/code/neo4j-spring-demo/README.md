# Neo4j Spring Boot Demo

Este proyecto usa Spring Boot y Spring Data Neo4j para conectarse a una instancia local de Neo4j y ejecutar dos consultas:

1. Amigos directos de Pedro.
2. Amigos de los amigos de Pedro (sin incluir amigos directos ni a Pedro).

## 🚀 Requisitos

- Java 21
- Maven
- Neo4j corriendo en `neo4j://127.0.0.1:7687` con usuario `neo4j` y contraseña `neo4j2025`.

## 🔧 Compilación

```bash
mvn clean package
```

## ▶️ Ejecución

```bash
java -jar target/neo4j-spring-demo-1.0.0.jar
```

Este `.jar` ya incluye todas las dependencias.
