## Presentación
### PDF
<ul>
<li>
       <a href="JConfDominicana_RUGI_Grafos_Java_NoSQL.pdf">JConfDominicana_RUGI_Grafos_Java_NoSQL.pdf</a>
</li>
</ul>
## Consultas
Consultas en cipher para la presentacion.

```cypher
MATCH (n)
DETACH DELETE n;

// Crea los nodos Persona
CREATE (:Persona {nombre: 'Lucas'}),
       (:Persona {nombre: 'Pedro'}),
       (:Persona {nombre: 'Andres'}),
       (:Persona {nombre: 'Mateo'}),
       (:Persona {nombre: 'Marcos'}),
       (:Persona {nombre: 'Juan'});

// Crea las relaciones de amistad
MATCH (lucas:Persona {nombre: 'Lucas'}),
      (marcos:Persona {nombre: 'Marcos'}),
      (mateo:Persona {nombre: 'Mateo'}),
      (juan:Persona {nombre: 'Juan'}),
      (pedro:Persona {nombre: 'Pedro'}),
      (andres:Persona {nombre: 'Andres'})

CREATE (lucas)-[:AMIGO_DE]->(marcos),
       (marcos)-[:AMIGO_DE]->(mateo),
       (juan)-[:AMIGO_DE]->(mateo),
       (pedro)-[:AMIGO_DE]->(lucas),
       (pedro)-[:AMIGO_DE]->(juan),
       (pedro)-[:AMIGO_DE]->(andres);

// Muestra todo
MATCH (p:Persona)-[r:AMIGO_DE]-(a:Persona)
RETURN p, r, a;

// Amigos de Pedro
MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]-(amigo:Persona)
RETURN pedro, r, amigo;

// Amigos de Amigos de Pedro.
MATCH (pedro:Persona {nombre: 'Pedro'})-[r:AMIGO_DE]->(amigo1:Persona)-[:AMIGO_DE]->(amigo2:Persona)
RETURN DISTINCT amigo2.nombre AS AmigoDeAmigo;
```
