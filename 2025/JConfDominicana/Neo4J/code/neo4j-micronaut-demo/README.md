# Neo4j Micronaut Demo

Este proyecto usa **Micronaut** y el **driver oficial de Neo4j** para ejecutar dos consultas Cypher:

1. Amigos directos de Pedro.
2. Amigos de los amigos de Pedro (sin incluir a Pedro ni sus amigos directos).

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
java -jar target/neo4j-micronaut-demo-1.0.0-jar-with-dependencies.jar
```
