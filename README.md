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

---

## 📌 Overview

**Digital Wallet** is a backend REST API designed to simulate the core functionality of a modern digital wallet system.

The project focuses on building a secure, maintainable, and scalable backend using **Spring Boot** and industry-standard backend technologies.

The system is designed around users, wallets, and financial transactions while following a clean layered architecture.

---

## 🚀 Features

- 🔐 Secure authentication and authorization
- 👤 User management
- 💳 Digital wallet management
- 💰 Wallet balance management
- 💸 Transaction processing
- 🗄️ PostgreSQL database integration
- ⚡ Redis integration for caching and performance
- 🔄 Database versioning with Flyway
- 🛡️ Spring Security integration
- 🔑 JWT-based authentication
- 📦 DTO-based API design
- 🚨 Global exception handling
- 🧩 Layered architecture
- 🧪 Unit and integration testing structure
- 📋 Database migration scripts
- ⚙️ Environment-based configuration

---

# 🏗️ Architecture

The project follows a layered backend architecture:

```text
                    ┌─────────────────────┐
                    │       Client        │
                    │  Postman / Frontend │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controllers     │
                    │     REST API Layer  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Services       │
                    │   Business Logic    │
                    └──────────┬──────────┘
                               │
                    ┌──────────┴──────────┐
                    ▼                     ▼
          ┌─────────────────┐   ┌─────────────────┐
          │   Repositories  │   │      Redis      │
          │   Data Access   │   │      Cache      │
          └────────┬────────┘   └─────────────────┘
                   │
                   ▼
          ┌─────────────────┐
          │   PostgreSQL    │
          │    Database     │
          └─────────────────┘

                   ▲
                   │
          ┌─────────────────┐
          │     Flyway      │
          │ DB Migrations   │
          └─────────────────┘
📂 Project Structure
src/
├── main/
│   ├── java/com/digitalwallet/
│   │
│   ├── Config/
│   │   └── Application configuration
│   │
│   ├── Services/
│   │   └── Business logic and service layer
│   │
│   ├── controller/
│   │   └── REST API controllers
│   │
│   ├── dtos/
│   │   └── Data Transfer Objects
│   │
│   ├── entity/
│   │   └── JPA database entities
│   │
│   ├── enums/
│   │   └── Application and transaction enums
│   │
│   ├── exception/
│   │   └── Custom exceptions and exception handling
│   │
│   ├── repository/
│   │   └── Database repositories
│   │
│   ├── util/
│   │   └── Utility and helper classes
│   │
│   └── DigitalWalletApplication.java
│
├── resources/
│   └── db/
│       └── migration/
│           ├── V1__create_users_table.sql
│           ├── V2__create_wallets_table.sql
│           └── V3__create_transactions_table.sql
│
└── test/
    └── java/com/digitalwallet/
🛠️ Tech Stack
Technology	Purpose
☕ Java	Programming Language
🌱 Spring Boot	Backend Framework
🔐 Spring Security	Authentication & Authorization
🔑 JWT	Token-Based Authentication
🐘 PostgreSQL	Relational Database
⚡ Redis	Caching / Fast Data Access
🔄 Flyway	Database Migration
🗃️ Spring Data JPA	Database Access
📦 Maven	Dependency Management
🧪 JUnit	Testing
🐳 Docker	Containerization
🗄️ Database Design

The application uses PostgreSQL as the primary relational database.

Database schema evolution is managed using Flyway migrations.

Current migrations
V1__create_users_table.sql
        │
        ▼
V2__create_wallets_table.sql
        │
        ▼
V3__create_transactions_table.sql

This approach ensures that database changes are:

Version controlled
Repeatable
Trackable
Consistent across environments
⚡ Redis

Redis is integrated into the application to provide fast in-memory data access.

Potential use cases include:

Caching frequently accessed data
Reducing database load
Improving API response time
Temporary data storage
Authentication/session-related use cases

Redis is used as a supporting infrastructure component while PostgreSQL remains the primary persistent database.

🔐 Security

The application uses Spring Security to secure backend resources.

Authentication is based on JWT (JSON Web Tokens).

The general authentication flow is:

Client
   │
   │ Login
   ▼
Authentication API
   │
   ▼
Validate Credentials
   │
   ▼
Generate JWT
   │
   ▼
Return Token
   │
   ▼
Client
   │
   │ Authorization: Bearer <JWT>
   ▼
Protected API
   │
   ▼
Spring Security
   │
   ▼
Controller
🔄 Request Flow

A typical request follows this architecture:

HTTP Request
     │
     ▼
Controller
     │
     ▼
DTO Validation
     │
     ▼
Service Layer
     │
     ▼
Business Logic
     │
     ▼
Repository
     │
     ▼
PostgreSQL
     │
     ▼
Response DTO
     │
     ▼
HTTP Response

This separation keeps the application maintainable and makes individual layers easier to test.

⚙️ Configuration

Sensitive configuration values are intentionally excluded from Git.

Examples include:

Database credentials
Redis credentials
JWT secrets
Environment variables
Local configuration

The project uses environment-specific configuration files.

Example configuration

Create your local configuration with values appropriate for your environment.

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/digital_wallet
    username: postgres
    password: YOUR_PASSWORD

  data:
    redis:
      host: localhost
      port: 6379

⚠️ Never commit real passwords, API keys, JWT secrets, or other sensitive credentials to GitHub.

🧑‍💻 Getting Started
1. Clone the repository
git clone https://github.com/adhamgamal22/digital-wallet.git
cd digital-wallet
2. Requirements

Make sure you have the following installed:

Java 17
Maven
PostgreSQL
Redis
Git


3. Create PostgreSQL Database

Create a database named:

digital_wallet

Example:

CREATE DATABASE digital_wallet;
4. Start Redis

Make sure Redis is running on:

localhost:6379
5. Configure the Application

Create your local configuration and provide:

PostgreSQL URL
PostgreSQL username
PostgreSQL password
Redis host
Redis port
JWT secret

Do not commit sensitive values.

6. Run the Application

Using Maven Wrapper:

Linux / macOS
./mvnw spring-boot:run
Windows
mvnw.cmd spring-boot:run

Or using Maven:

mvn spring-boot:run

📡 API

The application exposes RESTful APIs for core digital wallet operations.

The API is organized around resources such as:

Authentication
Users
Wallets
Transactions

Example API structure:

/api/v1

API endpoints and documentation can be expanded as the project evolves.

📈 Future Improvements

Planned improvements may include:

 Swagger / OpenAPI documentation
 Docker Compose environment
 Complete integration test coverage
 Transaction idempotency
 Transaction locking and concurrency handling
 Rate limiting
 Refresh token mechanism
 Audit logging
 Transaction history pagination
 Wallet-to-wallet transfers
 Role-based access control
 Monitoring and observability
 CI/CD pipeline with GitHub Actions
 Production-ready Docker setup
 Prometheus & Grafana monitoring
🔒 Security Considerations

Because this project deals with financial-like operations, security and consistency are important design considerations.

The application should ensure:

Authentication before accessing protected resources
Proper authorization
Secure password hashing
JWT validation
Input validation
Global exception handling
Database transaction management
Protection against duplicate transactions
Proper handling of concurrent wallet updates
No sensitive information in Git history
🧠 Design Principles

The project aims to follow common backend engineering principles:

Separation of Concerns
Single Responsibility Principle
Clean Layered Architecture
DTO Pattern
Repository Pattern
Dependency Injection
Centralized Exception Handling
Database Migration Management
Secure Configuration Management
📌 Project Status

🚧 Currently under development

The project is being actively developed and additional features, tests, documentation, and infrastructure components will be added over time.

👨‍💻 Author

Adham Gamal

Java Backend Developer focused on:

Java
Spring Boot
Spring Security
PostgreSQL
Redis
DevOps
Fintech Backend Systems
⭐ Support

If you find this project useful, consider giving it a ⭐ on GitHub.

📄 License

This project is currently intended for educational and portfolio purposes.
