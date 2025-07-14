# Neo4j  Demo

Este proyecto usa el driver oficial de Neo4j para ejecutar dos consultas Cypher:

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
 java -jar .\target\neo4j-demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

