
# Gestor de Tareas – Task Management Application

Gestor de Tareas is a full-stack web application built with Spring Boot and MySQL that allows authenticated users to manage their personal tasks.

La aplicación permite a usuarios autenticados gestionar sus tareas personales mediante una arquitectura en capas y autenticación segura.

---

## Overview / Descripción General

The application allows users to:
- Register a new account
- Authenticate using HTTP Basic Authentication
- Create tasks
- View their own tasks
- Update task status (PENDIENTE, EN_PROGRESO, COMPLETADA)
- Delete tasks

Cada usuario solo puede acceder y gestionar sus propias tareas.

---

## Technologies / Tecnologías

### Backend
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL

### Frontend
- HTML5
- CSS3
- JavaScript (Fetch API)

---

## Architecture / Arquitectura

The application follows a layered architecture:

- Controller layer (REST endpoints)
- Service layer (business logic)
- Repository layer (data access)
- Entity layer (JPA models)
- DTO layer (data transfer objects)
- Mapper layer (entity ↔ DTO conversion)
- Security layer (authentication and authorization)

Flujo:

Controller → Service → Repository → Database  
             ↓  
           Mapper  
             ↓  
             DTO  

Los DTOs desacoplan los modelos internos de las respuestas de la API.
Los Mappers transforman entidades en DTOs y viceversa.

---

## Data Model / Modelo de Datos

### Usuario
- id
- username
- password (encriptada con BCrypt)
- rol

### Tarea
- id
- titulo
- descripcion
- estado (ENUM)
- user_id (clave foránea)

Relación: Un Usuario → Muchas Tareas

---

## API Endpoints

Public:
- POST /api/auth/register

Protected:
- GET /api/tareas/mias
- POST /api/tareas
- PUT /api/tareas/{id}/pendiente
- PUT /api/tareas/{id}/progreso
- PUT /api/tareas/{id}/completar
- DELETE /api/tareas/{id}

---

## How to Run / Cómo Ejecutar

1. Create database:

CREATE DATABASE tareas_db;

2. Configure application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/tareas_db
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
server.port=3035

3. Run:

mvn spring-boot:run

Access:

http://localhost:3035/login.html

---

## Security

Authentication is implemented using HTTP Basic Authentication.
Passwords are encrypted using BCrypt.

---

## Future Improvements

- Implement JWT authentication
- Add filtering and pagination
- Improve UI with Kanban board
- Add automated tests
- Cloud deployment

---

## Author

Sofía Concatti  
Java Full-Stack Developer (Junior Level)
