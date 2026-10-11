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

### DevOps
- Docker (multi-stage build)
- Docker Compose

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

```
Controller → Service → Repository → Database
     ↓
   Mapper
     ↓
    DTO
```

---

## Data Model

### User
- id
- username
- password (hashed with BCrypt)
- role

### Task
- id
- title
- description
- status (ENUM)
- user_id (foreign key)

Relationship: One User → Many Tasks

---

## API Endpoints

Public endpoint:
- `POST /api/auth/register`

Protected endpoints:
- `GET /api/tareas/mias`
- `POST /api/tareas`
- `PUT /api/tareas/{id}/pendiente`
- `PUT /api/tareas/{id}/progreso`
- `PUT /api/tareas/{id}/completar`
- `DELETE /api/tareas/{id}`

All protected endpoints require HTTP Basic Authentication.

---

## Run with Docker (recommended)

Only Docker is required — no local Java or MySQL installation.

1. Create a `.env` file in the project root with the database password:

```
DB_PASSWORD=choose_a_password
```

2. Start the application and the database:

```
docker compose up --build
```

Docker Compose starts two containers: MySQL 8.4 and the Spring Boot application. The application waits until MySQL is ready, and the data is kept in a Docker volume between restarts.

Access the application at: http://localhost:3035/login.html

To stop it: `docker compose down` (add `-v` to also delete the database data).

---

## How to Run (without Docker)

1. Clone the repository

```
git clone https://github.com/YOUR_USERNAME/gestor-de-tareas.git
```

2. Create the database

```sql
CREATE DATABASE db_tareas;
```

3. Set the environment variables

Database credentials are not stored in the code. The application reads them from environment variables, listed in `.env.example`:

| Variable | Required | Default |
|---|---|---|
| `DB_PASSWORD` | Yes | — |
| `DB_URL` | No | `jdbc:mysql://localhost:3306/db_tareas` |
| `DB_USERNAME` | No | `root` |

If `DB_PASSWORD` is missing, the application fails at startup.

4. Run the application

```
mvn spring-boot:run
```

Access the application at: http://localhost:3035/login.html

---

## Security Notes

- Database credentials are externalized through environment variables, so they are never committed to the repository.
- Passwords are hashed with BCrypt before being stored in the database.
- The project uses HTTP Basic Authentication for demonstration purposes. In a production environment, authentication should be upgraded to JWT or token-based authentication.

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

### DevOps
- Docker (build multi-etapa)
- Docker Compose

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

```
Controller → Service → Repository → Base de Datos
     ↓
   Mapper
     ↓
    DTO
```

---

## Modelo de Datos

### Usuario
- id
- username
- password (hasheada con BCrypt)
- rol

### Tarea
- id
- titulo
- descripcion
- estado (ENUM)
- user_id (clave foránea)

Relación: Un Usuario → Muchas Tareas

---

## Endpoints Principales

Endpoint público:
- `POST /api/auth/register`

Endpoints protegidos:
- `GET /api/tareas/mias`
- `POST /api/tareas`
- `PUT /api/tareas/{id}/pendiente`
- `PUT /api/tareas/{id}/progreso`
- `PUT /api/tareas/{id}/completar`
- `DELETE /api/tareas/{id}`

Todos los endpoints protegidos requieren autenticación HTTP Basic.

---

## Ejecutar con Docker (recomendado)

Solo se necesita Docker; no hace falta instalar Java ni MySQL.

1. Crear un archivo `.env` en la raíz del proyecto con la contraseña de la base de datos:

```
DB_PASSWORD=elegi_una_contraseña
```

2. Levantar la aplicación y la base de datos:

```
docker compose up --build
```

Docker Compose levanta dos contenedores: MySQL 8.4 y la aplicación Spring Boot. La aplicación espera a que MySQL esté listo, y los datos se guardan en un volumen de Docker entre reinicios.

Acceder desde: http://localhost:3035/login.html

Para detenerla: `docker compose down` (con `-v` también se borran los datos de la base).

---

## Cómo Ejecutar el Proyecto (sin Docker)

1. Clonar el repositorio

```
git clone https://github.com/TU_USUARIO/gestor-de-tareas.git
```

2. Crear la base de datos

```sql
CREATE DATABASE db_tareas;
```

3. Configurar las variables de entorno

Las credenciales de la base de datos no están en el código. La aplicación las lee de variables de entorno, listadas en `.env.example`:

| Variable | Obligatoria | Valor por defecto |
|---|---|---|
| `DB_PASSWORD` | Sí | — |
| `DB_URL` | No | `jdbc:mysql://localhost:3306/db_tareas` |
| `DB_USERNAME` | No | `root` |

Si falta `DB_PASSWORD`, la aplicación no arranca.

4. Ejecutar la aplicación

```
mvn spring-boot:run
```

Acceder desde: http://localhost:3035/login.html

---

## Notas de Seguridad

- Las credenciales de la base de datos se externalizan mediante variables de entorno, por lo que nunca se suben al repositorio.
- Las contraseñas se almacenan hasheadas con BCrypt.
- El proyecto utiliza HTTP Basic Authentication con fines demostrativos. En un entorno productivo se recomienda migrar a JWT o autenticación basada en tokens.

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