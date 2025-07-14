package com.example;

import io.helidon.config.Config;
import org.neo4j.driver.*;

public class Neo4jHelidonApp {

    public static void main(String[] args) {
        Config config = Config.create();
        String uri = config.get("neo4j.uri").asString().orElse("neo4j://127.0.0.1:7687");
        String user = config.get("neo4j.username").asString().orElse("neo4j");
        String password = config.get("neo4j.password").asString().orElse("neo4j2025");

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
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
