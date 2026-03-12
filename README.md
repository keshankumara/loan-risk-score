# Loan Risk Assessment API

A Spring Boot application for calculating loan risk scores using Clean Architecture principles.

## 📋 Table of Contents
- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Postman Setup](#postman-setup)
- [Business Logic](#business-logic)
- [Database Schema](#database-schema)

---

## 🎯 Overview

This REST API evaluates loan applications and calculates risk scores based on:
- Monthly Income
- Existing Debt (Debt-to-Income Ratio)
- Late Payment History
- Employment Years

The system assigns a risk level (LOW/MEDIUM/HIGH) and provides a recommendation (APPROVE/REVIEW/REJECT).

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Security** (Basic Auth)
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

### Architecture
- **Clean Architecture** (Hexagonal)
- Domain layer: Pure business logic (no framework dependencies)
- Application layer: Use cases and ports
- Infrastructure layer: Spring, JPA, Security
- Presentation layer: REST controllers

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- PostgreSQL 18.2+
- Maven 3.x

### Database Setup

1. Create PostgreSQL database:
```sql
CREATE DATABASE loan_risk_db;
```

2. Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/loan_risk_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Application

```bash
# Using Maven wrapper
.\mvnw.cmd spring-boot:run

# Or build and run
.\mvnw.cmd clean install
java -jar target/loanrisk-0.0.1-SNAPSHOT.jar
```

Application runs on: **http://localhost:8080**

---

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

---

### 1. Calculate Loan Risk

**Endpoint:** `POST /loan-risk/calculate`

**Authentication:** Required (ADMIN, ANALYST, or USER role)

**Description:** Calculates risk score for a loan application and stores it in the database.

**Request Headers:**
```
Content-Type: application/json
Authorization: Basic {base64(username:password)}
```

**Request Body:**
```json
{
  "applicantName": "John Doe",
  "monthlyIncome": 120000,
  "existingDebt": 30000,
  "latePayments": 0,
  "employmentYears": 6
}
```

**Field Validations:**
- `applicantName`: Required, cannot be blank
- `monthlyIncome`: Must be positive (> 0)
- `existingDebt`: Cannot be negative (≥ 0)
- `latePayments`: Cannot be negative (≥ 0)
- `employmentYears`: Cannot be negative (≥ 0)

**Success Response (200 OK):**
```json
{
  "riskScore": 85,
  "riskLevel": "LOW",
  "recommendation": "APPROVE"
}
```

**Risk Levels:**
- `LOW`: Score ≥ 75 → Recommendation: APPROVE
- `MEDIUM`: Score 50-74 → Recommendation: REVIEW
- `HIGH`: Score < 50 → Recommendation: REJECT

**Error Response (400 Bad Request):**
```json
{
  "timestamp": "2026-03-12T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Monthly income must be positive"
}
```

**Error Response (401 Unauthorized):**
```json
{
  "timestamp": "2026-03-12T12:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource"
}
```

---

## 🔐 Authentication

### Current Authentication: Basic Auth (In-Memory Users)

The application uses HTTP Basic Authentication with pre-configured users stored in memory.

### Available Users

| Username | Password    | Role    | Description                    |
|----------|-------------|---------|--------------------------------|
| admin    | admin123    | ADMIN   | Full access to all endpoints   |
| analyst  | analyst123  | ANALYST | Can calculate and view risks   |
| user     | user123     | USER    | Can calculate risks            |

### Using Basic Auth

**In Postman:**
1. Go to **Authorization** tab
2. Type: Select **Basic Auth**
3. Username: `admin`
4. Password: `admin123`

**In cURL:**
```bash
curl -X POST http://localhost:8080/api/v1/loan-risk/calculate \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "applicantName": "John Doe",
    "monthlyIncome": 120000,
    "existingDebt": 30000,
    "latePayments": 0,
    "employmentYears": 6
  }'
```

**In Code (JavaScript):**
```javascript
const username = 'admin';
const password = 'admin123';
const credentials = btoa(`${username}:${password}`);

fetch('http://localhost:8080/api/v1/loan-risk/calculate', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': `Basic ${credentials}`
  },
  body: JSON.stringify({
    applicantName: 'John Doe',
    monthlyIncome: 120000,
    existingDebt: 30000,
    latePayments: 0,
    employmentYears: 6
  })
});
```

---

## 📮 Postman Setup

### Quick Start

1. **Create New Request**
   - Method: `POST`
   - URL: `http://localhost:8080/api/v1/loan-risk/calculate`

2. **Set Authorization**
   - Tab: **Authorization**
   - Type: **Basic Auth**
   - Username: `admin`
   - Password: `admin123`

3. **Set Body**
   - Tab: **Body**
   - Select: **raw** → **JSON**
   - Paste sample request (see examples below)

4. **Send Request**

### Sample Test Cases

#### ✅ Test Case 1: LOW Risk (Should APPROVE)
```json
{
  "applicantName": "Jane Smith",
  "monthlyIncome": 150000,
  "existingDebt": 20000,
  "latePayments": 0,
  "employmentYears": 7
}
```
**Expected Response:**
```json
{
  "riskScore": 100,
  "riskLevel": "LOW",
  "recommendation": "APPROVE"
}
```

#### ⚠️ Test Case 2: MEDIUM Risk (Should REVIEW)
```json
{
  "applicantName": "Bob Johnson",
  "monthlyIncome": 80000,
  "existingDebt": 40000,
  "latePayments": 1,
  "employmentYears": 3
}
```
**Expected Response:**
```json
{
  "riskScore": 55,
  "riskLevel": "MEDIUM",
  "recommendation": "REVIEW"
}
```

#### ❌ Test Case 3: HIGH Risk (Should REJECT)
```json
{
  "applicantName": "Alice Brown",
  "monthlyIncome": 50000,
  "existingDebt": 60000,
  "latePayments": 3,
  "employmentYears": 1
}
```
**Expected Response:**
```json
{
  "riskScore": 25,
  "riskLevel": "HIGH",
  "recommendation": "REJECT"
}
```

#### ❌ Test Case 4: Validation Error
```json
{
  "applicantName": "",
  "monthlyIncome": -5000,
  "existingDebt": -1000,
  "latePayments": -1,
  "employmentYears": -2
}
```
**Expected:** 400 Bad Request with validation messages

---

## 📊 Business Logic

### Risk Scoring Algorithm

The system calculates a total score (0-100) based on four factors:

#### 1. Debt-to-Income Ratio (DTI) - Max 40 points
```
DTI = Existing Debt / Monthly Income

- DTI < 30%:  40 points
- DTI 30-50%: 25 points
- DTI > 50%:  10 points
```

#### 2. Late Payment History - Max 25 points
```
- 0 late payments:  25 points
- 1 late payment:   15 points
- 2+ late payments:  5 points
```

#### 3. Employment Stability - Max 20 points
```
- > 5 years:   20 points
- 2-5 years:   15 points
- < 2 years:    5 points
```

#### 4. Income Level - Max 15 points
```
- > $150,000:  15 points
- $80k-150k:   10 points
- < $80,000:    5 points
```

### Risk Level Determination

| Total Score | Risk Level | Recommendation |
|-------------|-----------|----------------|
| 75-100      | LOW       | APPROVE        |
| 50-74       | MEDIUM    | REVIEW         |
| 0-49        | HIGH      | REJECT         |

---

## 🗄️ Database Schema

### Tables

#### 1. `loan_application`
Stores loan application details.

| Column           | Type         | Constraints      |
|------------------|--------------|------------------|
| id               | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| applicant_name   | VARCHAR(255) | NOT NULL         |
| monthly_income   | DOUBLE       | NOT NULL         |
| existing_debt    | DOUBLE       | NOT NULL         |
| late_payments    | INT          | NOT NULL         |
| employment_years | INT          | NOT NULL         |

#### 2. `risk_assessment`
Stores calculated risk assessments.

| Column         | Type         | Constraints                    |
|----------------|--------------|--------------------------------|
| id             | BIGINT       | PRIMARY KEY, AUTO_INCREMENT    |
| score          | INT          | NOT NULL                       |
| risk_level     | VARCHAR(20)  | NOT NULL (LOW/MEDIUM/HIGH)     |
| recommendation | VARCHAR(20)  | NOT NULL (APPROVE/REVIEW/REJECT)|
| loan_id        | BIGINT       | FOREIGN KEY → loan_application(id) |

### Relationship
- **One-to-One:** Each loan application has one risk assessment

### Sample SQL Queries

**View all loan applications with risk assessments:**
```sql
SELECT 
    la.applicant_name,
    la.monthly_income,
    la.existing_debt,
    ra.score,
    ra.risk_level,
    ra.recommendation
FROM loan_application la
LEFT JOIN risk_assessment ra ON la.id = ra.loan_id
ORDER BY la.id DESC;
```

**Count applications by risk level:**
```sql
SELECT risk_level, COUNT(*) as count
FROM risk_assessment
GROUP BY risk_level;
```

**Find high-risk applications:**
```sql
SELECT la.applicant_name, ra.score, ra.recommendation
FROM loan_application la
JOIN risk_assessment ra ON la.id = ra.loan_id
WHERE ra.risk_level = 'HIGH';
```

---

## 🧪 Testing

### Run Tests
```bash
# Run all tests
.\mvnw.cmd test

# Run with coverage
.\mvnw.cmd clean test jacoco:report
```

### Test Structure
```
src/test/java/
└── com.keshan.loanrisk/
    ├── LoanriskApplicationTests.java        # Context loading test
    └── domain/
        └── service/
            └── RiskCalculatorTest.java      # Domain logic tests
```

---

## 📁 Project Structure

```
src/main/java/com/keshan/loanrisk/
├── LoanriskApplication.java                 # Spring Boot entry point
├── domain/                                   # ✅ Pure business logic (no framework)
│   ├── model/
│   │   ├── LoanApplication.java
│   │   ├── RiskAssessment.java
│   │   ├── RiskLevel.java (enum)
│   │   ├── Recommendation.java (enum)
│   │   └── RiskScore.java
│   └── service/
│       └── RiskCalculator.java              # Core risk calculation
├── application/                              # Use cases
│   ├── dto/
│   │   ├── LoanRiskRequestDTO.java
│   │   └── LoanRiskResponseDTO.java
│   ├── mapper/
│   │   └── LoanMapper.java
│   ├── port/
│   │   ├── LoanApplicationRepository.java
│   │   └── RiskAssessmentRepository.java
│   └── usecase/
│       └── CalculateRiskUseCase.java
├── infrastructure/                           # Framework integration
│   ├── config/
│   │   └── AppConfig.java                   # Bean configuration
│   ├── persistence/
│   │   ├── adapter/
│   │   │   ├── LoanRepositoryImpl.java
│   │   │   └── RiskAssessmentRepositoryImpl.java
│   │   ├── entity/
│   │   │   ├── LoanApplicationEntity.java
│   │   │   └── RiskAssessmentEntity.java
│   │   └── repository/
│   │       ├── JpaLoanRepository.java
│   │       └── JpaRiskAssessmentRepository.java
│   └── security/
│       ├── SecurityConfig.java
│       ├── JwtUtil.java
│       └── Role.java
└── presentation/                             # REST API
    ├── controller/
    │   └── LoanRiskController.java
    └── exception/
        ├── CustomExceptions.java
        └── GlobalExceptionHandler.java
```

---

## 🔧 Configuration

### Application Properties

**Location:** `src/main/resources/application.properties`

```properties
# Application
spring.application.name=loanrisk

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/loan_risk_db
spring.datasource.username=postgres
spring.datasource.password=Sql@123#456

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server (optional)
# server.port=8080
```

### Security Configuration

Security is configured in `SecurityConfig.java`:
- CSRF disabled (for testing)
- Basic Authentication enabled
- All `/api/v1/loan-risk/**` endpoints require authentication
- In-memory user store (configurable for production)

---

## 🚧 Future Enhancements

### Planned Features

**Phase 1: Query Endpoints**
- [ ] GET `/loan-applications` - List all applications
- [ ] GET `/loan-applications/{id}` - Get single application
- [ ] GET `/risk-assessments` - List all assessments
- [ ] GET `/risk-assessments/statistics` - Risk statistics

**Phase 2: CRUD Operations**
- [ ] PUT `/loan-applications/{id}` - Update application
- [ ] DELETE `/loan-applications/{id}` - Delete application

**Phase 3: User Management**
- [ ] POST `/auth/register` - User registration
- [ ] POST `/auth/login` - JWT authentication
- [ ] Database-backed user storage

**Phase 4: Advanced Features**
- [ ] Batch risk calculation
- [ ] Export to PDF/CSV
- [ ] Email notifications
- [ ] Audit logging
- [ ] Rate limiting

---

## 📝 License

This project is for educational purposes.

---

## 👥 Contact

For questions or support, please contact the development team.

---

## 📚 Additional Resources

### Related Documentation
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Clean Architecture Principles](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

### Testing Tools
- [Postman](https://www.postman.com/)
- [cURL](https://curl.se/)
- [HTTPie](https://httpie.io/)

---

**Last Updated:** March 12, 2026
