

# Comparativa de Frameworks para Neo4j

| Framework / Enfoque         | Flexibilidad en consultas | Facilidad para manipular resultados | Actualizaciones y mantenimiento |
|-----------------------------|----------------------------|--------------------------------------|----------------------------------|
| **Código puro (Neo4j Driver)** | 🔥 Máxima (Cypher libre)   | 🟡 Media (requiere mapeo manual)    | ✅ Neo4j Driver es activo y mantenido |
| **Helidon 3**               | ✅ Alta (Driver manual)    | 🟡 Media (sin ORM, trabajo manual)   | ✅ Helidon 3 es activo, soportado por Oracle |
| **Micronaut**               | ✅ Alta (Driver manual)    | 🟡 Media (sin librerías específicas) | ✅ Muy activo, lanzamientos frecuentes |
| **Quarkus**                 | 🟢 Media-Alta (con `quarkus-neo4j`) | ✅ Alta (manejo reactivo opcional, integración con Panache) | ✅ Muy activo, ritmo rápido |
| **Spring (Spring Data Neo4j)** | 🟢 Media (repositorios, pero permite `@Query`) | 🟢 Alta (conversión automática a entidades) | ✅ Muy activo, con soporte de Neo4j y VMware |

