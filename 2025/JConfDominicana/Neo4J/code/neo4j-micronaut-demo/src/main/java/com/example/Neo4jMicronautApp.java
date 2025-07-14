package com.example;

import io.micronaut.context.ApplicationContext;
import org.neo4j.driver.*;

public class Neo4jMicronautApp {

    public static void main(String[] args) {
        try (ApplicationContext context = ApplicationContext.run()) {

            String uri = context.getProperty("neo4j.uri", String.class, "neo4j://127.0.0.1:7687");
            String user = context.getProperty("neo4j.username", String.class, "neo4j");
            String password = context.getProperty("neo4j.password", String.class, "neo4j2025");

            try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password)); Session session = driver.session()) {

                System.out.println("=== Amigos directos de Pedro ===");
                session.readTransaction(tx -> {
                    Result result = tx.run("""
                        MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]-(amigo:Persona)
                        RETURN pedro.nombre AS Pedro, type(r) AS Relacion, amigo.nombre AS Amigo
                    """);
                    while (result.hasNext()) {
                        org.neo4j.driver.Record record = result.next();
                        System.out.printf("Pedro --[%s]--> %s%n",
                                record.get("Relacion").asString(),
                                record.get("Amigo").asString());
                    }
                    return null;
                });

                System.out.println("\n=== Amigos de los amigos de Pedro (sin incluir amigos directos ni a Pedro) ===");
                session.readTransaction(tx -> {
                    Result result = tx.run("""
                        MATCH (pedro:Persona {nombre: 'Pedro'})-[:AMIGO_DE]->(amigo1:Persona)-[:AMIGO_DE]->(amigo2:Persona)
                        WHERE NOT (pedro)-[:AMIGO_DE]-(amigo2) AND amigo2.nombre <> 'Pedro'
                        RETURN DISTINCT amigo2.nombre AS AmigoDeAmigo
                    """);
                    while (result.hasNext()) {
                        org.neo4j.driver.Record record = result.next();
                        System.out.printf("-> %s%n", record.get("AmigoDeAmigo").asString());
                    }
                    return null;
                });

            } catch (Exception e) {
                System.err.println("Error en la conexión o ejecución de la consulta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
