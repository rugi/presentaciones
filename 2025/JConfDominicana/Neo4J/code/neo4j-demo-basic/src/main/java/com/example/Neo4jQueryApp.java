package com.example;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

public class Neo4jQueryApp {

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String user = "neo4j";
        String password = "neo4j2025";

        try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password)); Session session = driver.session()) {

            System.out.println("=== Amigos directos de Pedro ===");
            String directFriendsQuery = """
                MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]-(amigo:Persona)
                RETURN pedro.nombre AS Pedro, type(r) AS Relacion, amigo.nombre AS Amigo
                """;
            session.readTransaction(tx -> {
                Result result = tx.run(directFriendsQuery);
                while (result.hasNext()) {
                    Record record = result.next();
                    System.out.printf("Pedro --[%s]--> %s%n",
                            record.get("Relacion").asString(),
                            record.get("Amigo").asString());
                }
                return null;
            });

            System.out.println("\n=== Amigos de los amigos de Pedro (sin incluir amigos directos ni a Pedro) ===");
            String friendsOfFriendsQuery = """
                MATCH (pedro:Persona {nombre: 'Pedro'})-[:AMIGO_DE]->(amigo1:Persona)-[:AMIGO_DE]->(amigo2:Persona)
                WHERE NOT (pedro)-[:AMIGO_DE]-(amigo2) AND amigo2.nombre <> 'Pedro'
                RETURN DISTINCT amigo2.nombre AS AmigoDeAmigo
                """;
            session.readTransaction(tx -> {
                Result result = tx.run(friendsOfFriendsQuery);
                while (result.hasNext()) {
                    Record record = result.next();
                    System.out.printf("-> %s%n", record.get("AmigoDeAmigo").asString());
                }
                return null;
            });

        } catch (Exception e) {
            System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
