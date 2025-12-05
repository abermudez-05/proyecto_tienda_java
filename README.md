# Videoclub

Tienda online de películas desarrollada con Java + Spring Boot. Proyecto creado/importado con Eclipse.

Resumen
- Backend: Spring Boot (REST API).
- Persistencia: configurable (H2 en memoria por defecto / MySQL, PostgreSQL, etc.).
- IDE recomendado: Eclipse (o IntelliJ IDEA).
- Build: Maven (o Gradle según el proyecto).

Versiones recomendadas
- JDK: 17 (LTS) — compatible con 11+
- Spring Boot: 3.x
- Maven: 3.8+
- Eclipse: 2022‑12 o superior

Requisitos
- Java JDK instalado y configurado (JAVA_HOME).
- Maven instalado (si se usa Maven).
- Base de datos (opcional) — por defecto el proyecto puede usar H2.

Instalación y uso (línea de comandos)

1. Clonar el repositorio
   git clone <url-del-repositorio>
   cd videoclub

2. Importar en Eclipse
   - File → Import → Existing Maven Projects → seleccionar la carpeta del proyecto.
   - Esperar a que Eclipse descargue dependencias.
   - Ejecutar la clase principal anotada con @SpringBootApplication (Run as → Java Application) o Run as → Spring Boot App.

3. Usando Maven (build y run)
   - Compilar y ejecutar tests:
     mvn clean test
   - Empaquetar:
     mvn clean package
   - Ejecutar JAR:
     java -jar target/*.jar

4. Configurar base de datos
   - Editar src/main/resources/application.properties (o application.yml) para cambiar la configuración de la BD.
   - Ejemplo minimal (H2):
     spring.datasource.url=jdbc:h2:mem:videodb
     spring.datasource.driver-class-name=org.h2.Driver
     spring.h2.console.enabled=true
   - Ejemplo MySQL:
     spring.datasource.url=jdbc:mysql://localhost:3306/videodb
     spring.datasource.username=usuario
     spring.datasource.password=clave
     spring.jpa.hibernate.ddl-auto=update

Endpoints útiles (ejemplos)
- GET /api/peliculas — listar películas
- GET /api/peliculas/{id} — obtener película por id
- POST /api/carrito — agregar producto al carrito
- GET /api/carrito — ver carrito

Notas
- Revisar src/main/resources/application.properties para puertos y otras opciones (por defecto Spring Boot usa el puerto 8080).
- Asegurar app.properties con credenciales en entorno de producción.
- Para desarrollo rápido usar H2 y habilitar consola (http://localhost:8080/h2-console).

Tests
- Ejecutar:
  mvn test

Despliegue (rápido)
- Construir JAR y ejecutar en servidor:
  mvn clean package
  java -jar target/videoclub-<version>.jar

Contacto / ayuda
- Revisar paquetes: src/main/java (controladores REST, servicios, repositorios).
- Si faltan configuraciones específicas, editar application.properties o consultar documentación de Spring Boot.
