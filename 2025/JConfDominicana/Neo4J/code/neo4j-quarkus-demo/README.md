# Neo4j Quarkus Demo

Este proyecto usa Quarkus con el driver oficial de Neo4j para ejecutar dos consultas Cypher:

1. Amigos directos de Pedro.
2. Amigos de los amigos de Pedro (excluyendo amigos directos y a Pedro).

## 🚀 Requisitos

- Java 21
- Maven
- Neo4j ejecutándose en `neo4j://127.0.0.1:7687` con user/pass `neo4j / neo4j2025`

## 🔧 Compilación

```bash
mvn clean install
```

## ▶️ Ejecución

```bash
 java -jar .\target\quarkus-app\quarkus-run.jar
```

