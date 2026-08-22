# 🏥 SmartCare HMS · Hospital Management System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue.svg)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An enterprise-grade, Object-Oriented Software Solution for Hospital Management built with **Java Spring Boot**, **Spring Data JPA**, **MySQL**, and an interactive **Single-Page Application (SPA) Web Dashboard**. Designed for Sri Lankan healthcare operations with native **LKR (Sri Lankan Rupee)** billing integration.

---

## 🌟 Key Features

### 👨‍⚕️ Clinical Management
- **Patient Registration & Records:** Full demographic tracking, medical history, blood group categorization, and emergency contacts.
- **Doctor Directory & Scheduling:** Doctor profiles with qualifications, specializations, assigned departments, and consultation fees.
- **Appointment Booking System:** Conflict-free scheduling with doctor availability verification and automated room allocation.
- **Inpatient Admissions:** Real-time tracking of patient admissions, room occupancy status, and discharge workflows.
- **Clinical Treatments & Prescriptions:** Digital diagnosis logging, medication prescriptions, and detailed clinical notes.
- **Laboratory Information System (LIS):** Lab test orders, sample tracking, technician assignment, and result verification.

### 💳 Finance & Billing Operations (LKR Support)
- **Itemized Patient Billing:** Automated calculation of consultation, room charges, laboratory investigations, and medication fees.
- **Local Currency Integration:** Configured for Sri Lankan Rupee (**LKR / Rs.**) across all invoices and transactions.
- **Multi-Method Payments:** Seamless recording of **Cash**, **Card**, and **Online Bank Transfers**.
- **Real-Time Analytics:** Visual tracking of Paid, Pending, and Partial payment statuses via interactive charts.

### 🏨 Infrastructure & Operations
- **Room & Ward Management:** Real-time occupancy management across **General Wards**, **Private Rooms**, and **ICUs**.
- **Department Structure:** Hierarchy of hospital departments linked to Head Doctors and consulting specialists.

---

## 🏗️ System Architecture & Tech Stack

### **Backend**
- **Language:** Java (JDK 17+)
- **Framework:** Spring Boot (RESTful Web APIs, Spring Data JPA, Hibernate ORM)
- **Database:** MySQL / MariaDB (`smartcare_db`)
- **Build Tool:** Maven

### **Frontend & UI**
- **Architecture:** Embedded Single Page Application (SPA)
- **Styling:** Bootstrap 5, Plus Jakarta Sans & Inter Typography, Custom Modern CSS
- **Data Visualizations:** Chart.js (Appointment trends, Gender demographics, Billing ratios)
- **Icons:** Bootstrap Icons

---

## 📁 Project Structure

```text
SmartCare-Hospital-Management-System/
├── src/
│   └── main/
│       ├── java/com/smartcare/hospital_api/
│       │   ├── controller/      # REST API Endpoints (Patients, Doctors, Bills, etc.)
│       │   ├── entity/          # JPA Domain Entities (Patient, Doctor, Bill, Room, etc.)
│       │   ├── enums/           # Domain Enums (PaymentStatus, RoomType, Gender, etc.)
│       │   ├── exception/       # Global Exception Handlers & Business Exceptions
│       │   ├── repository/     # Spring Data JPA Data Access Layer
│       │   └── service/        # Business Logic & Transaction Management
│       └── resources/
│           ├── static/          # Single Page Web Dashboard (index.html, JS, CSS)
│           └── application.properties # Spring & Database Configuration
├── pom.xml                      # Maven Dependencies
└── README.md                    # Project Documentation
```

---

## 🚀 Getting Started

### 1. Prerequisites
- **Java Development Kit (JDK 17 or later)** installed.
- **MySQL / MariaDB Server** (e.g., via XAMPP or standalone MySQL).
- **Git** installed.

### 2. Database Setup
1. Start MySQL Server (via XAMPP or command line).
2. Open phpMyAdmin (`http://localhost/phpmyadmin`) or MySQL CLI.
3. Create a database named `smartcare_db`:
   ```sql
   CREATE DATABASE smartcare_db;
   ```
4. Import the database dump (`smartcare_db.sql`) to load default tables and sample records.

### 3. Application Configuration
Check `src/main/resources/application.properties` to ensure your database credentials match:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smartcare_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build & Run Application
Open your terminal or IDE in the project root:

```bash
# Using Maven Wrapper (Windows PowerShell / CMD)
.\mvnw spring-boot:run

# Or package and run the JAR
.\mvnw clean package
java -jar target/hospital_api-0.0.1-SNAPSHOT.jar
```

### 5. Access the System
Open your web browser and navigate to:
```text
http://localhost:8080
```

---

## 📡 Key REST API Endpoints

| Resource | Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Patients** | `GET` / `POST` | `/api/patients` | Retrieve all patients or register a new patient |
| **Doctors** | `GET` / `POST` | `/api/doctors` | List doctors (filter by specialization) or add doctor |
| **Appointments** | `POST` | `/api/appointments/book` | Book a consultation slot with conflict checks |
| **Admissions** | `POST` | `/api/admissions/admit` | Admit patient to an available room |
| **Bills** | `GET` / `POST` | `/api/bills` | Fetch billing records or generate itemized invoice |
| **Payments** | `POST` | `/api/payments/{method}` | Process payment (Cash, Card, Online) in LKR |
| **Rooms** | `GET` / `PUT` | `/api/rooms` | View room inventory and toggle availability |

---

## 👨‍💻 Author & Contributors

- **Developer:** Hasindu Senarathna ([@hasiya2004](https://github.com/hasiya2004))
- **Project Repository:** [SmartCare-Hospital-Management-System](https://github.com/hasiya2004/SmartCare-Hospital-Management-System)

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).