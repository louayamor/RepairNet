# 🔧 RepairNet — Maintenance Management System

> A powerful Spring Boot-based application for managing technicians, equipment, faults, and interventions in any maintenance-driven environment.

---

## 🌟 Overview

**RepairNet** simplifies and automates the maintenance lifecycle. From assigning technicians with the right skills to tracking interventions and handling faults, this system offers a modular and secure backend solution with well-defined REST APIs.

---

## 🧰 Tech Stack

| Layer         | Technology                            |
|---------------|----------------------------------------|
| **Backend**   | Spring Boot, Spring Security, JPA (Hibernate) |
| **Database**  | MySQL              |
| **Security**  | Spring Security, Custom Auth EntryPoint |
| **Build Tool**| Gradle                                |
| **Logging**   | SLF4J + Logback                       |

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 17+
- Gradle
- MySQL

### ⚙️ Run Locally

```bash
git clone https://github.com/louayamor/repairnet.git
cd repairnet
./gradlew bootRun
```

Visit: [http://localhost:8081](http://localhost:8081)

---

## 🔐 Authentication

- In-memory login setup (for demo):

```txt
Username: louayspring
Password: springlouay
```

- Form-based login: `/login`

---

## 🌐 API Endpoints

### 👨‍🔧 Technician

- `GET    /api/technicians` — Get all technicians  
- `GET    /api/technicians/{id}` — Get technician by ID  
- `POST   /api/technicians` — Add new technician (+skills)  
- `DELETE /api/technicians/{id}` — Delete a technician  
- `GET    /api/technicians/uuid/{uuid}` — Find by UUID  
- `GET    /api/technicians/search?name=...` — Search by name  
- `POST   /api/technicians/skills` — Find by skills  
- `GET    /api/technicians/available` — Available technicians  

### 🛠️ Interventions

- `GET    /api/interventions` — Get all interventions  
- `POST   /api/interventions` — Create a new intervention  
- `GET    /api/interventions/technician/{id}` — Get by technician  
- `GET    /api/interventions/equipment/{id}` — Get by equipment  

---

## 📥 Example Request (Technician)

```json
{
  "name": "Louay Amor",
  "available": true,
  "skills": [
    {
      "name": "HVAC"
    },
    {
      "name": "Electrical"
    }
  ]
}
```

---

## 🧪 Testing with Postman

You can test endpoints using Postman or cURL. Ensure you're sending:

- `Content-Type: application/json`

---

## 🗂️ Project Structure

```
repairnet/
├── bean/               # JPA Entities
├── controller/         # REST Controllers
├── dto/                # Data Transfer Object
├── Enumerator/         # Enumerators
├── repository/         # Spring Data Repos
├── security/           # Auth & Security Config
├── service/            # Interfaces & Implementations
└── RepairNetApplication.java
```

---

## 👨‍💻 Author

**Louay Amor**  
Spring Developer | Deep Learning Enthusiast | Firebase Fan  
• 💻 [GitHub](https://github.com/louayamor)

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

