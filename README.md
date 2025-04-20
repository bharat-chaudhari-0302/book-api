# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin/packaging-oci-image.html)

### Additional LinksHere's your complete and updated `README.md` content with PostgreSQL, Prometheus, and virtual threads config included. You can copy the whole thing in one go:

---

```markdown
# 📚 Book API

A Spring Boot 3-based REST API for managing books with CRUD operations using PostgreSQL, Actuator monitoring, and Prometheus integration.

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 21
- Gradle
- PostgreSQL
- Postman (optional)

---

### 🛠️ Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/bharat-chaudhari-0302/book-api.git
   cd book-api
   ```

2. **PostgreSQL Configuration**

   Make sure PostgreSQL is running and create a database:

   ```sql
   CREATE DATABASE bookdb;
   ```

   Your `application.yml` should look like:

   ```yaml
   spring:
     application:
       name: Book Api
     datasource:
       url: jdbc:postgresql://localhost:5432/bookdb
       username: postgres
       password: root
       driver-class-name: org.postgresql.Driver
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
       properties:
         hibernate:
           dialect: org.hibernate.dialect.PostgreSQLDialect
     threads:
       virtual:
         enabled: true
   management:
     endpoints:
       web:
         exposure:
           include: health, metrics, prometheus
     metrics:
       tags:
         application: book-api
   server:
     port: 8081
   logging:
     level:
       com.learning.book.api: INFO
   ```

3. **Build and Run**

   ```bash
   ./gradlew bootRun
   ```

   API will be available at:  
   [http://localhost:8081](http://localhost:8081)

---

## 🔌 API Endpoints

Base URL: `/api/v1/books`

| Method  | Endpoint               | Description          |
|---------|------------------------|----------------------|
| POST    | `/api/v1/books`        | Create a new book    |
| GET     | `/api/v1/books`        | Get all books        |
| GET     | `/api/v1/books/{id}`   | Get book by ID       |
| PUT     | `/api/v1/books/{id}`   | Update book by ID    |
| DELETE  | `/api/v1/books/{id}`   | Delete book by ID    |

---

## 🧪 Example Payloads (Postman)

### ➕ Create Book

**POST** `/api/v1/books`

```json
{
  "title": "Book 1",
  "author": "Bharat Chaudhari",
  "isbn": "1000000000000",
  "price": 2000
}
```

### 🔁 Update Book

**PUT** `/api/v1/books/1`

```json
{
  "title": "Book 1 Updated",
  "author": "Bharat Chaudhari",
  "isbn": "1000000000000",
  "price": 2100
}
```

---

## 📊 Actuator & Monitoring

- Health Check: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)
- Metrics: [http://localhost:8081/actuator/metrics](http://localhost:8081/actuator/metrics)
- Prometheus: [http://localhost:8081/actuator/prometheus](http://localhost:8081/actuator/prometheus)

---

## 📦 Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Gradle
- Lombok
- MapStruct
- Spring Boot Actuator
- Prometheus
- Virtual Threads (Project Loom)

---

## ✅ Run Tests

```bash
./gradlew test
```

---

## 📎 Useful Links

- [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
- [Gradle Docs](https://docs.gradle.org)
- [Prometheus Monitoring](https://prometheus.io/)
```

Let me know if you want a Docker version or badge support (build passing, license, etc.) added too.

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

