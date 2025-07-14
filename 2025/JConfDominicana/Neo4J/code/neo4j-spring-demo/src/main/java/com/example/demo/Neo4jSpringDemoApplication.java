package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;

@SpringBootApplication
public class Neo4jSpringDemoApplication implements CommandLineRunner {

    private final Neo4jClient neo4jClient;

    public Neo4jSpringDemoApplication(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Neo4jSpringDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Amigos directos de Pedro ===");
        neo4jClient.query("""
                MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]-(amigo:Persona)
                RETURN pedro.nombre AS Pedro, type(r) AS Relacion, amigo.nombre AS Amigo
                """)
                .fetch()
                .all()
                .forEach(record -> System.out.printf("Pedro --[%s]--> %s%n",
                record.get("Relacion"), record.get("Amigo")));

        System.out.println("\n=== Amigos de los amigos de Pedro (sin incluir amigos directos ni a Pedro) ===");
        neo4jClient.query("""
                MATCH (pedro:Persona {nombre: 'Pedro'})-[:AMIGO_DE]->(amigo1:Persona)-[:AMIGO_DE]->(amigo2:Persona)
                WHERE NOT (pedro)-[:AMIGO_DE]-(amigo2) AND amigo2.nombre <> 'Pedro'
                RETURN DISTINCT amigo2.nombre AS AmigoDeAmigo
                """)
                .fetch()
                .all()
                .forEach(record -> System.out.printf("-> %s%n", record.get("AmigoDeAmigo")));
    }
}
