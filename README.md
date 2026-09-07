# 💳 Digital Wallet

<p align="center">
  <b>A secure and scalable Digital Wallet REST API built with Spring Boot.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/Spring%20Security-Security-green?style=for-the-badge&logo=springsecurity" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql" />
  <img src="https://img.shields.io/badge/Redis-Cache-red?style=for-the-badge&logo=redis" />
  <img src="https://img.shields.io/badge/Flyway-Migrations-red?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven" />
</p>

# 💳 Digital Wallet — Banking & Wallet Platform

A backend system for digital wallet and financial transaction management, built with a clean layered architecture and modern Spring ecosystem tools.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Redis](https://img.shields.io/badge/Redis-Cache-red)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)

---

## 📖 Overview

This project simulates core digital wallet operations — user authentication, wallet management, and transaction processing — following production-grade backend engineering practices: layered architecture, DTO validation, centralized exception handling, and versioned database migrations.

---

## 📑 Table of Contents

- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Tech Stack](#-tech-stack)
- [Database Design](#-database-design)
- [Redis](#-redis)
- [Security](#-security)
- [Request Flow](#-request-flow)
- [Getting Started](#-getting-started)
- [API](#-api)
- [Future Improvements](#-future-improvements)
- [Security Considerations](#-security-considerations)
- [Design Principles](#-design-principles)
- [Author](#-author)
- [License](#-license)

---

## 🏗️ Architecture

The project follows a clean, layered backend architecture:

```
                    ┌─────────────────────┐
                    │        Client         │
                    │  Postman / Frontend   │
                    └──────────┬────────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controllers       │
                    │     REST API Layer    │
                    └──────────┬────────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Services          │
                    │   Business Logic      │
                    └──────────┬────────────┘
                               │
                    ┌──────────┴──────────┐
                    ▼                     ▼
          ┌─────────────────┐   ┌─────────────────┐
          │  Repositories     │   │      Redis        │
          │  Data Access      │   │      Cache        │
          └────────┬──────────┘   └─────────────────┘
                    │
                    ▼
          ┌─────────────────┐
          │   PostgreSQL      │
          │    Database       │
          └─────────────────┘
                    ▲
                    │
          ┌─────────────────┐
          │     Flyway          │
          │ DB Migrations       │
          └─────────────────┘
```

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/digitalwallet/
│   │   ├── config/          → Application configuration
│   │   ├── controller/      → REST API controllers
│   │   ├── service/         → Business logic layer
│   │   ├── repository/      → Database repositories
│   │   ├── entity/          → JPA database entities
│   │   ├── dto/              → Data Transfer Objects
│   │   ├── enums/           → Application & transaction enums
│   │   ├── exception/       → Custom exceptions & global handling
│   │   ├── util/            → Utility / helper classes
│   │   └── DigitalWalletApplication.java
│   │
│   └── resources/
│       └── db/migration/
│           ├── V1__create_users_table.sql
│           ├── V2__create_wallets_table.sql
│           └── V3__create_transactions_table.sql
│
└── test/
    └── java/com/digitalwallet/
```

> Note: package names normalized to lowercase (`config`, `service`, `dto`) to follow standard Java conventions.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| ☕ Java 17 | Core programming language |
| 🌱 Spring Boot | Application framework |
| 🔐 Spring Security | Authentication & authorization |
| 🔑 JWT | Stateless token-based auth |
| 🐘 PostgreSQL | Primary relational database |
| ⚡ Redis | Caching / fast in-memory access |
| 🔄 Flyway | Database migration management |
| 🗃️ Spring Data JPA | ORM / database access |
| 📦 Maven | Dependency & build management |
| 🧪 JUnit | Unit & integration testing |
| 🐳 Docker | Containerization |

---

## 🗄️ Database Design

PostgreSQL is the primary data store. Schema evolution is fully managed via **Flyway migrations**, applied in order:

```
V1__create_users_table.sql
        │
        ▼
V2__create_wallets_table.sql
        │
        ▼
V3__create_transactions_table.sql
```

This guarantees database changes are:
- ✅ Version controlled
- ✅ Repeatable
- ✅ Trackable
- ✅ Consistent across environments

---

## ⚡ Redis

Redis provides fast in-memory data access alongside PostgreSQL, used for:

- Caching frequently accessed data
- Reducing database load
- Improving API response time
- Temporary/session-related data storage

Redis is a **supporting cache layer** — PostgreSQL remains the source of truth for persisted data.

---

## 🔐 Security

Authentication is handled via **Spring Security + JWT**:

```
Client
  │  Login (credentials)
  ▼
Authentication API → Validate Credentials → Generate JWT → Return Token
  │
  │  Authorization: Bearer <JWT>
  ▼
Protected API → Spring Security Filter → Controller
```

---

## 🔄 Request Flow

```
HTTP Request
     │
     ▼
Controller  →  DTO Validation  →  Service Layer (Business Logic)
     │
     ▼
Repository  →  PostgreSQL
     │
     ▼
Response DTO  →  HTTP Response
```

This separation keeps each layer independently testable and maintainable.

---

## 🧑‍💻 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/adhamgamal22/digital-wallet.git
cd digital-wallet
```

### 2. Requirements
- Java 17
- Maven
- PostgreSQL
- Redis
- Git

### 3. Create the PostgreSQL database
```sql
CREATE DATABASE digital_wallet;
```

### 4. Start Redis
Ensure Redis is running on `localhost:6379`.

### 5. Configure the application
Create your local `application-local.yml` (or `.env`) with:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/digital_wallet
    username: postgres
    password: YOUR_PASSWORD

  data:
    redis:
      host: localhost
      port: 6379

jwt:
  secret: YOUR_JWT_SECRET
  expiration: 3600000
```

> ⚠️ Never commit real passwords, API keys, or JWT secrets to Git.

### 6. Run the application
```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

---

## 📡 API

Base path: `/api/v1`

The API is organized around core resources:

| Resource | Description |
|---|---|
| `/auth` | Registration, login, token issuance |
| `/users` | User profile management |
| `/wallets` | Wallet creation & balance operations |
| `/transactions` | Transaction history & processing |

> Full endpoint documentation (Swagger/OpenAPI) is planned — see Future Improvements.

---

## 📈 Future Improvements

- [ ] Swagger / OpenAPI documentation
- [ ] Docker Compose environment
- [ ] Full integration test coverage
- [ ] Transaction idempotency
- [ ] Transaction locking & concurrency handling
- [ ] Rate limiting
- [ ] Refresh token mechanism
- [ ] Audit logging
- [ ] Paginated transaction history
- [ ] Wallet-to-wallet transfers
- [ ] Role-based access control (RBAC)
- [ ] Monitoring & observability (Prometheus & Grafana)
- [ ] CI/CD pipeline (GitHub Actions)
- [ ] Production-ready Docker setup

---

## 🔒 Security Considerations

Since this project handles financial-like operations, the following are core design priorities:

- Authentication required on all protected resources
- Proper authorization checks per endpoint
- Secure password hashing (BCrypt)
- JWT validation & expiration handling
- Strict input validation via DTOs
- Centralized/global exception handling
- Transactional integrity for wallet operations
- Protection against duplicate/replayed transactions
- Safe handling of concurrent wallet updates
- No sensitive data committed to Git history

---

## 🧠 Design Principles

- Separation of Concerns
- Single Responsibility Principle
- Clean Layered Architecture
- DTO Pattern
- Repository Pattern
- Dependency Injection
- Centralized Exception Handling
- Versioned Database Migrations
- Secure Configuration Management

---

## 📌 Project Status

🚧 **Actively under development.** Features, tests, documentation, and infrastructure are being added incrementally.

---

## 👨‍💻 Author

**Adham Gamal**
Java Backend Developer focused on:
Java · Spring Boot · Spring Security · PostgreSQL · Redis · DevOps · Fintech Backend Systems

[GitHub](https://github.com/adhamgamal22) · [LinkedIn](https://linkedin.com/in/adhamgamal74)

---

## ⭐ Support

If you find this project useful, consider giving it a ⭐ on GitHub — it helps a lot!

---

## 📄 License

This project is currently intended for **educational and portfolio purposes**.
