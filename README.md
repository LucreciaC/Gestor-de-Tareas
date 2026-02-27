
# Gestor de Tareas – Task Management Application

---

# ENGLISH VERSION

## Task Manager – Task Management Application

Task Manager is a full-stack web application built with Spring Boot and MySQL that allows authenticated users to manage their personal tasks.

This project demonstrates backend architecture, security configuration, RESTful API design, DTO abstraction, entity–DTO mapping, and frontend integration using HTML, CSS, and JavaScript.

---

## Overview

The application allows users to:

- Register a new account
- Authenticate using HTTP Basic Authentication
- Create tasks
- View their own tasks
- Update task status (PENDING, IN_PROGRESS, COMPLETED)
- Delete tasks

Each user can only access and manage their own tasks.

---

## Technologies

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

## Architecture

The application follows a layered architecture:

- Controller layer (REST endpoints)
- Service layer (business logic)
- Repository layer (data access)
- Entity layer (JPA models)
- DTO layer (data transfer objects)
- Mapper layer (entity ↔ DTO conversion)
- Security layer (authentication and authorization)

DTOs are used to decouple internal database models from API responses.

Mappers handle the transformation between Entity objects and DTOs, improving separation of concerns and maintainability.

Architecture flow:

Controller → Service → Repository → Database  
↓  
Mapper  
↓  
DTO

---

## Data Model

### User
- id
- username
- password (encrypted with BCrypt)
- role

### Task
- id
- title
- description
- status (ENUM)
- user_id (foreign key)

Relationship:
One User → Many Tasks

---

## API Endpoints

Public endpoint:
- POST /api/auth/register

Protected endpoints:
- GET /api/tareas/mias
- POST /api/tareas
- PUT /api/tareas/{id}/pendiente
- PUT /api/tareas/{id}/progreso
- PUT /api/tareas/{id}/completar
- DELETE /api/tareas/{id}

All protected endpoints require HTTP Basic Authentication.

---

## How to Run

1. Clone the repository

git clone https://github.com/YOUR_USERNAME/gestor-de-tareas.git

2. Create the database

CREATE DATABASE tareas_db;

3. Configure application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/tareas_db  
spring.datasource.username=YOUR_USER  
spring.datasource.password=YOUR_PASSWORD  
spring.jpa.hibernate.ddl-auto=update  
server.port=3035

4. Run the application

mvn spring-boot:run

Access the application at:

http://localhost:3035/login.html

---

## Security Notes

The project uses HTTP Basic Authentication for demonstration purposes.

Passwords are encrypted using BCrypt before being stored in the database.

In a production environment, authentication should be upgraded to JWT or token-based authentication.

---

## Future Improvements

- Implement JWT authentication
- Add filtering and pagination
- Improve UI with a Kanban-style board
- Add automated testing
- Deploy to a cloud platform

---

## Author

Lucrecia Concatti  
Java Full-Stack Developer (Junior Level)

---

# VERSIÓN EN ESPAÑOL

## Gestor de Tareas – Aplicación de Gestión de Tareas

Gestor de Tareas es una aplicación web full-stack desarrollada con Spring Boot y MySQL que permite a usuarios autenticados gestionar sus tareas personales.

Este proyecto demuestra arquitectura backend, configuración de seguridad, diseño de API REST, uso de DTOs para abstracción de datos, conversión entre entidades y DTOs e integración frontend con HTML, CSS y JavaScript.

---

## Descripción General

La aplicación permite:

- Registrar nuevos usuarios
- Autenticarse mediante HTTP Basic Authentication
- Crear tareas
- Visualizar tareas propias
- Actualizar el estado de las tareas (PENDIENTE, EN_PROGRESO, COMPLETADA)
- Eliminar tareas

Cada usuario solo puede acceder y gestionar sus propias tareas.

---

## Tecnologías Utilizadas

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

## Arquitectura

La aplicación sigue una arquitectura en capas:

- Capa Controller (endpoints REST)
- Capa Service (lógica de negocio)
- Capa Repository (acceso a datos)
- Capa Entity (modelos JPA)
- Capa DTO (objetos de transferencia de datos)
- Capa Mapper (conversión entre Entity y DTO)
- Capa Security (autenticación y autorización)

Los DTOs permiten desacoplar los modelos internos de la base de datos de las respuestas expuestas por la API.

Los Mappers transforman entidades en DTOs y viceversa, mejorando la separación de responsabilidades y la mantenibilidad.

Flujo de arquitectura:

Controller → Service → Repository → Base de Datos  
↓  
Mapper  
↓  
DTO

---

## Modelo de Datos

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

Relación:
Un Usuario → Muchas Tareas

---

## Endpoints Principales

Endpoint público:
- POST /api/auth/register

Endpoints protegidos:
- GET /api/tareas/mias
- POST /api/tareas
- PUT /api/tareas/{id}/pendiente
- PUT /api/tareas/{id}/progreso
- PUT /api/tareas/{id}/completar
- DELETE /api/tareas/{id}

Todos los endpoints protegidos requieren autenticación HTTP Basic.

---

## Cómo Ejecutar el Proyecto

1. Clonar el repositorio

git clone https://github.com/TU_USUARIO/gestor-de-tareas.git

2. Crear la base de datos

CREATE DATABASE tareas_db;

3. Configurar application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/tareas_db  
spring.datasource.username=TU_USUARIO  
spring.datasource.password=TU_PASSWORD  
spring.jpa.hibernate.ddl-auto=update  
server.port=3035

4. Ejecutar la aplicación

mvn spring-boot:run

Acceder desde:

http://localhost:3035/login.html

---

## Notas de Seguridad

El proyecto utiliza HTTP Basic Authentication con fines demostrativos.

Las contraseñas se almacenan encriptadas utilizando BCrypt.

En un entorno productivo se recomienda migrar a JWT o autenticación basada en tokens.

---

## Posibles Mejoras

- Implementar autenticación JWT
- Agregar filtros y paginación
- Mejorar la interfaz con vista tipo Kanban
- Agregar tests automatizados
- Deploy en la nube

---

## Autora

Lucrecia Concatti  
Desarrolladora Java Full-Stack (Nivel Junior)