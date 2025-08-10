# Laboratorio 0.
## Prometheus + Java (Spring boot).
Este primer laboratorio es para "calentar motores" como decimos en méxico.
Tiene como objetivo:
- Familiarizarnos con el uso de herramientas actuales.
- Conocer Prometheus.
- Asimiliar el concepto de "concentrador" de logs.
- Cubrir un caso práctico que mañana mismo puede ya aplicar.

El escenario es:

<blockquote>
  Tengo un microservicio hecho en springboot, ya tiene activo el actuator pero quiero usar alguna otra herramienta que me permita visualizar de mejor manera lo que ya expone el proceso Java.
  Para ello, puedo modificar el proyecto de Springboot sin ser tan intrusivo y lograr que ahora, adicional a lo que el actuator me da, tenga yo disponibles otras métricas.

  La herramienta a usar es: Prometheus.
  Prometheus es parte del stack de proyectos de la CNCF.
</blockquote>

Como puedes observar, es un caso práctico.

### Pasos a realizar.
#### Del lado de Prometheus
* Instalar Prometehus.
* Ejecutar Prometheus.
* Validar la instalación de Prometheus.
* Conocer como hacer Query's.

### Del lado de la aplicación Java
* Descargar un proyecto funcionando con Springboot.
* Validar que su ejecución funcione satisfactoriamente.
* Modificar el POM del proyecto.
* Modificar el archivo properties.
* Volver a ejecutar la aplicación java.
* Validar métricas.

### Por último.
* Agregar configuración para que Prometheus pueda leer la información de nuestro proceso Java.
* Volver a ejecutar Prometheus.
* Validar que ya podemos usar las mètricas de nuestro proceso java en Prometheus y podemos hacer Query's.


¡Manos a la Obra!





