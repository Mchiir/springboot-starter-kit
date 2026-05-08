# Spring Boot Starter Kit

A modern, production-ready Spring Boot backend starter template built for fast API development, clean architecture, and scalable backend systems.

This project is part of my reusable backend foundation for:

- Fast prototyping
- REST API development
- National practical exams
- Portfolio projects
- Freelance backend systems
- Enterprise-style backend applications

---

# Tech Stack

## Core Stack

- Java 21
- Spring Boot 3.5.x
- Maven
- PostgreSQL 17

---

# Included Spring Boot Modules

- Spring Web
- Spring Security
- Spring Data JPA
- Spring Validation
- Spring Mail
- Spring Boot Actuator

---

# Additional Tools & Libraries

## Database

- PostgreSQL Driver
- Flyway Database Migrations

## API Documentation

- SpringDoc OpenAPI
- Swagger UI

## Boilerplate Reduction

- Lombok

## DTO Mapping

- MapStruct

## Logging

- SLF4J
- Logback (default Spring Boot logger)

---

# Features

- RESTful API architecture
- DTO-based structure
- Clean modular organization
- PostgreSQL integration
- Flyway SQL migrations
- Swagger/OpenAPI documentation
- Validation support
- Monitoring with Actuator
- Environment variable support
- Production-ready configuration structure

---

# Project Structure

```text
src/main/java/com/mchiir/starterkit

├── config
├── security
├── auth
├── common
├── exception
├── util
├── notification

├── feature
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   ├── entity
│   └── mapper
```

This project follows a feature-based modular architecture where each feature owns its:

- Controller
- Service
- Repository
- DTOs
- Entities
- Mappers

---

# Requirements

Before running the project, install:

- Java 21+
- Maven 3.9+
- PostgreSQL 17+
- IntelliJ IDEA (recommended)

---

# Environment Variables

The project uses environment-variable-friendly configuration.

Example datasource variables:

```properties
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=starterkit
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
```

---

# Running the Project

## 1. Clone the repository

```bash
git clone <your-repository-url>
```

---

## 2. Navigate into the project

```bash
cd springboot-starter-kit
```

---

## 3. Install dependencies

```bash
mvn clean install
```

---

## 4. Run the application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

# Swagger / OpenAPI

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI Docs:

```text
http://localhost:8080/api-docs
```

---

# Actuator Endpoints

Base Path:

```text
http://localhost:8080/actuator
```

Available endpoints:

- `/actuator/health`
- `/actuator/info`
- `/actuator/metrics`

---

# Flyway Migrations

Migration files should be placed inside:

```text
src/main/resources/db/migration
```

Example:

```text
V1__init_schema.sql
```

---

# Development Notes

## Hot Reload

To enable automatic restart during development, uncomment:

```xml
spring-boot-devtools
```

inside `pom.xml`.

---

## IntelliJ Annotation Processing

Enable annotation processing for:

- Lombok
- MapStruct

Path:

```text
Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors
```

---

# Stack Versions

| Component   | Version |
| ----------- | ------- |
| Java        | 21      |
| Spring Boot | 3.5.14  |
| Flyway      | 11.7.2  |
| SpringDoc   | 2.8.16  |
| MapStruct   | 1.6.3   |
| PostgreSQL  | 17      |

---

---

# Author

Built and maintained by [Mugisha Chrispin](https://github.com/Mchiir).

---

# License

MIT License
