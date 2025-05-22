# Employee Service - Babel

Microservicio desarrollado en Java con Spring Boot para la gestión de empleados que se pide como pruebatécnica.  
Incluye endpoints REST documentados con Swagger y base de datos embebida (H2) lista para usarse.

---

## Características

- CRUD de empleados
- Validaciones con grupos (`OnCreate`, `OnUpdate`)
- Base de datos **H2 persistente** en archivo (`./data/employee-db.mv.db`)
- Swagger UI para documentación y pruebas
- Logs  con Logback
- Pruebas unitarias con Mockito y MockMvc

---

## Tecnologías

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- Spring Web
- Spring Validation
- H2 Database
- Swagger (springdoc-openapi)
- JUnit 5 + Mockito


## Endpoints principales

- `GET    /api/v1/employees`  
- `POST   /api/v1/employees`  
- `PATCH  /api/v1/employees/{id}`  
- `DELETE /api/v1/employees/{id}`

---

##  Swagger UI

http://localhost:8080/swagger-ui.html

## Base de datos H2

- Se encuentra configurada en modo persistente (no in-memory)
- Se guarda en archivo `./data/employee-db.mv.db`
- Ya incluye datos precargados para pruebas (Un empleado)