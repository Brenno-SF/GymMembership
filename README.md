
# Gym Membership API 🏋️

Advanced and scalable API for managing gym members, plans, classes, and attendance, built with **Java 17**, **Spring Boot 3**, **PostgreSQL**, **Flyway**, and organized according to **Clean Architecture** principles.

> **Goal**: provide a clean, testable, and sustainable backend, with clear business rules: **plan validity control**, **class capacity limits**, and **attendance registration**.

---

## 🔎 How to Run

1. Start the database:
   ```bash
   docker compose up -d
   ```
2. Configure the `application.yml` (example below) and run the app:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the API: `http://localhost:8080/gym/v1`.

---

## 🧱 Architecture (Clean Architecture)

Layers and responsibilities:

- **core (domain)**: domain entities and business rules (POJOs/records, no framework annotations).
- **application (use cases/services)**: orchestrates use cases, applies business validations, coordinates repositories.
- **infrastructure (adapters)**: persistence (JPA), `Domain ↔ Entity` mappings, configurations, Flyway migrations.
- **presentation (web)**: REST controllers, DTOs (request/response), validation, and JSON serialization.

```
[ Presentation / Web ]  →  DTOs  →  (Application) Services / Use Cases
                                 ↓
                        [ Core / Domain Models ]
                                 ↓
           (Infrastructure) Persistence Adapters (JPA/Repositories)
                                 ↓
                            PostgreSQL (Flyway)
```

📌 **Dependency rule**: outer layers know the inner layers; the **domain knows nothing** outside of it.

---

## 🧩 Main Entities (Domain)

- **Plan**: `planId`, `name`, `description`, `maxCapacity`, `durationMonth`.
- **Member**: `memberId`, `name`, `email`, `planId`, `startDate`, `endDate`, `active`.
- **Class**: `classId`, `name`, `description`, `dateHour`.
- **Presence**: `presenceId`, `memberId`, `classId`, `presence` (bool), `register` (date).

> The domain uses **IDs** (strings/UUID) for relationships. The persistence layer translates this into JPA entities.

---

## ✅ Business Rules

1. **Active plan**: a member can only register attendance if the current date ∈ [`startDate`, `endDate`] and `active = true`.
2. **Class capacity**: the number of registered attendances (or bookings) for a `Class` **cannot exceed** `Plan.maxCapacity` **of the member's plan** *or* (recommended alternative) a **per-class capacity field** (e.g., `class.capacityMax`).
3. **Attendance registration**:
   - Only valid if the member is **eligible** (active plan) and the **class has not yet occurred** (`now() <= dateHour`).
   - Avoid duplicates: a `(memberId, classId)` combination **must not** exist twice.

> **Note**: the proposed schema records attendance directly. If you want **prior enrollment** (booking) + attendance on the day, create a separate `Enrollment` resource.

---

## 🧰 Stack & Dependencies

- Java 17, Spring Boot 3 (Web, Validation), Spring Data JPA
- PostgreSQL, Flyway
- Lombok (boilerplate), MapStruct (optional for mappers)
- Testing: JUnit 5 + Mockito

---

## 🗄️ Database & Migrations (Flyway)

**docker-compose.yml** (PostgreSQL):
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:latest
    container_name: postgres_db
    restart: always
    environment:
      POSTGRES_USER: 
      POSTGRES_PASSWORD:
      POSTGRES_DB: GymMembership
    ports:
      - "5431:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
volumes:
  postgres_data:
```

**application.yml** (Spring):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5431/GymMembership
    username:
    password: 
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
    open-in-view: false

  flyway:
    enabled: true
    locations: classpath:db/migration
```

---

## 🗂️ Folder Structure

```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───bsf
│   │   │           └───GymMembership
│   │   │               ├───core
│   │   │               │   ├───entity
│   │   │               │   ├───gateway
│   │   │               │   └───usecases
│   │   │               │       ├───member
│   │   │               │       └───plan
│   │   │               └───infrastructure
│   │   │                   ├───bean
│   │   │                   ├───exception
│   │   │                   ├───gateway
│   │   │                   ├───persistence
│   │   │                   │   ├───dto
│   │   │                   │   ├───entitiy
│   │   │                   │   ├───mapper
│   │   │                   │   └───repository
│   │   │                   └───presentation
│   │   └───resources
│   │       ├───db
│   │       │   └───migration
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───bsf
│                   └───GymMembership
```

---

## 🔁 Mappings (real project examples)

- **PlanEntityMapper**: `Plan ↔ PlanEntity`
- **MemberEntityMapper**: `Member ↔ MemberEntity` (receives `PlanEntity` in `toEntity`)
- **ClassEntityMapper**: `Class ↔ ClassEntity`
- **PresenceEntityMapper**: `Presence ↔ PresenceEntity` (receives `MemberEntity` and `ClassEntity` in `toEntity`)

**DTO Mappers (presentation)**: convert **DTOs ↔ Entities** without accessing the database.

---

## 🌐 API (Suggested Design)

Base: `/gym/v1`

### Plans
- `POST /plans` — create plan
- `GET /plans` — list (paginate/filter by name)
- `GET /plans/{id}` — get
- `PUT /plans/{id}` — update
- `DELETE /plans/{id}` — remove

**Request**
```json
{
  "name": "Premium",
  "description": "Premium plan",
  "maxCapacity": 30,
  "durationMonth": 3
}
```
**Response**
```json
{
  "planId": "4f7e...",
  "name": "Premium",
  "description": "Premium plan",
  "maxCapacity": 30,
  "durationMonth": 3
}
```

### Members
- `POST /members` — create (requires existing `planId`)
- `GET /members` — list (paginate/filter by name/email)
- `GET /members/{id}` — get
- `PUT /members/{id}` — update (change plan, dates)
- `DELETE /members/{id}` — remove
- `POST /members/{id}/renew` — **renew plan** (extends `endDate` according to plan's `durationMonth`)

**Request**
```json
{
  "name": "Ana Silva",
  "email": "ana@example.com",
  "planId": "4f7e...",
  "startDate": "2025-01-01",
  "endDate": "2025-03-31",
  "active": true
}
```

### Classes
- `POST /classes` — create
- `GET /classes` — list
- `GET /classes/{id}` — get
- `PUT /classes/{id}` — update
- `DELETE /classes/{id}` — remove

### Presences
- `POST /presences` — register/update attendance (blocks duplicates by `(memberId, classId)`, validates plan and schedule)
- `GET /presences` — list (filter by `memberId`, `classId`, `date`)

**Request**
```json
{
  "memberId": "2acd...",
  "classId": "9a11...",
  "presence": true,
  "register": "2025-08-21"
}
```

---

## 🧪 Tests (To Do)

- **Unit (core/application)**: isolated business rules.
- **Slice Tests (web/data)**: `@WebMvcTest` for controllers; `@DataJpaTest` for repositories.
- **Integration**: full context with in-memory DB or Testcontainers.

Examples:
- Active plan validation.
- Prevent duplicate attendance.
- Respect class capacity.

---

## 🔒 Validation & Security (To Do)

- Bean Validation (`@Valid`) in DTOs.
- Exception handling with `@ControllerAdvice` + **RFC 7807** (Problem Details).
- (Optional) **JWT** + RBAC for administrative endpoints.

---

## 🧹 Quality & Productivity

- **Lombok** for getters/setters/constructors.
- **Conventional Commits** and **Git Hooks** (pre-commit with tests/format).

---

## 🗺️ Roadmap

- [ ] **Enrollment** feature (prior registration) separate from attendance.
- [ ] Per-class capacity (field `capacityMax` in `class_tb`).
- [ ] Email notifications for attendance confirmation/cancellation.
- [ ] Plan payment and upgrade.
- [ ] Attendance reports over a period.

---

## 📄 License

Distributed under the **MIT** license. Feel free to use/modify.

---

## 📚 References

- Clean Architecture — layer separation and dependencies.
- Java Time API (`LocalDate`, `LocalDateTime`) for dates.
- Flyway — schema versioning.

---

> Questions or improvements? Open an issue or reach out! 😉
