package com.example;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

@QuarkusMain
public class Neo4jQuarkusApp implements QuarkusApplication {

    @Override
    public int run(String... args) {
        String uri = "neo4j://127.0.0.1:7687";
        String user = "neo4j";
        String password = "neo4j2025";

        try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password)); Session session = driver.session()) {

            System.out.println("=== Amigos directos de Pedro ===");
            String directQuery = """
                MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]-(amigo:Persona)
                RETURN pedro.nombre AS Pedro, type(r) AS Relacion, amigo.nombre AS Amigo
                """;
            session.readTransaction(tx -> {
                Result result = tx.run(directQuery);
                while (result.hasNext()) {
                    Record record = result.next();
                    System.out.printf("Pedro --[%s]--> %s%n",
                            record.get("Relacion").asString(),
                            record.get("Amigo").asString());
                }
                return null;
            });

            System.out.println("\n=== Amigos de los amigos de Pedro (sin incluir amigos directos ni a Pedro) ===");
            String fofQuery = """
                MATCH (pedro:Persona {nombre: 'Pedro'})-[:AMIGO_DE]->(amigo1:Persona)-[:AMIGO_DE]->(amigo2:Persona)
                WHERE NOT (pedro)-[:AMIGO_DE]-(amigo2) AND amigo2.nombre <> 'Pedro'
                RETURN DISTINCT amigo2.nombre AS AmigoDeAmigo
                """;
            session.readTransaction(tx -> {
                Result result = tx.run(fofQuery);
                while (result.hasNext()) {
                    Record record = result.next();
                    System.out.printf("-> %s%n", record.get("AmigoDeAmigo").asString());
                }
                return null;
            });

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
