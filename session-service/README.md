# Session Service

A Java Spring Boot application (OpenJDK 17) providing CRUD operations for session bookmarks and check-ins, using OpenAPI (Swagger) for API documentation. No persistence layer is included.

## Features
- CRUD for session bookmarks and check-ins
- Accepts transmissions via a unique code
- OpenAPI/Swagger UI available in development

## Requirements
- Java 17+
- Maven 3.6+

## Running the Application

```
mvn spring-boot:run
```

Swagger UI will be available at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Project Structure
- `src/main/java/com/example/sessionservice/` - Java source code
- `src/main/resources/` - Application resources
- `pom.xml` - Maven build file
