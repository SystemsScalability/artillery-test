# Cars API Documentation

## Project Overview
This project is a simple RESTful API for managing a collection of cars, built using **Java (Spring Boot) and PostgreSQL**. The API provides endpoints for basic CRUD operations, including adding, retrieving, updating, and deleting cars.

## Technologies Used
- **Java 17** (Spring Boot)
- **Spring Boot** (Spring Web, Spring Data JPA)
- **Spring Boot Starter Web** (for building REST APIs)
- **PostgreSQL** (Relational Database Management System)
- **Artillery** (for Load Testing)
- **Docker & Docker Compose** (for containerization)
- **Maven** (for dependency management)

---
## Getting Started

### 1. Prerequisites
Before running the project, make sure you have the following installed:
- **Java 17**
- **Maven**
- **PostgreSQL**
- **Docker & Docker Compose** (for running PostgreSQL)
- **Artillery** (for load testing, install using `npm install -g artillery`)

### 2. Clone the Repository
```bash
git clone <repository-url>
cd artillery-test
```

### 3. Database Setup
1. Install and start PostgreSQL.
2. Create a new database named `cars_db`.
3. Set up the `.env` file in the project root with the following details:

```.env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/cars_db
SPRING_DATASOURCE_USERNAME=your_db_user
SPRING_DATASOURCE_PASSWORD=your_password
SERVER_PORT=8181
```

### 4. Set up the Database with Docker (Optional)
Alternatively, you can use Docker to run a PostgreSQL instance by creating a `docker-compose.yml` file with the following content:

```yaml
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_DB: cars_db
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

Then, start PostgreSQL using:
```bash
docker compose -f docker-compose.yml up pgadmin
```

### 5. Run the Spring Boot Application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8181`

---

# Artillery Load Testing Documentation

Artillery is used to **load test the API endpoints** to measure performance and reliability.

## Installation
Install Artillery globally using npm:
```bash
npm install -g artillery
```

## Running Load Tests
1. **Ensure the API is running**
   ```bash
   mvn spring-boot:run
   ```
2. Run Artillery using the following commands:
   ```bash
   artillery run --environment=default artillery-config.yml
   ```
   ```bash
   artillery run artillery-config.yml
   ```
3. Create a `.env` file for environment variables if needed:
   ```ini
   API_BASE_URL=http://localhost:8181
   ```

## Artillery Test Configuration
The test script is located in `artillery-config.yml` and includes the following test scenarios:
- Retrieve all cars (`GET /cars`)
- Add a new car (`POST /cars`)
- Get a car by ID (`GET /cars/{id}`)
- Filter cars by year range (`GET /cars/years?startYear=X&endYear=Y`)
- Filter cars by price range (`GET /cars/prices?minPrice=X&maxPrice=Y`)
- Update an existing car (`PUT /cars/{id}`)
- Delete a car (`DELETE /cars/{id}`)

## Example Artillery Command
To execute:
```bash
artillery run --environment=default artillery-config.yml
```
or
```bash
artillery run artillery-config.yml
```
