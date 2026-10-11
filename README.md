# 🏆 Player Management System

A full-stack web application featuring a robust **Java Spring Boot** RESTful API, a **PostgreSQL** database, and a responsive **Tailwind CSS** dashboard for managing player profiles, tracking sports data, and enforcing strict data validation.

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Hibernate
- **Database:** PostgreSQL
- **Frontend:** HTML5, Tailwind CSS, JavaScript (Fetch API)

---

## 🚀 Features

- **Responsive Frontend Dashboard:** Modern single-page interface styled with Tailwind CSS for seamless player management and search.
- **Complete CRUD Operations:** Create, Read, Update, and Delete player records seamlessly.
- **Custom Queries:** Fetch players dynamically by name using JPQL and by sport using native SQL queries.
- **Centralized Exception Handling:** Custom runtime exceptions handled globally via `@ControllerAdvice`.

---

## 🔌 API Endpoints Reference

| Method | Endpoint | Description | Request Body / Variable |
| :--- | :--- | :--- | :--- |
| **POST** | `/player` | Add a new player | `PlayerDto` (JSON) |
| **GET** | `/players` | Retrieve all players | None |
| **GET** | `/player/id/{id}` | Get player by unique ID | Path Variable (`id`) |
| **GET** | `/name/{name}` | Get players by name | Path Variable (`name`) |
| **GET** | `/sport/{sport}` | Get players by sport | Path Variable (`sport`) |
| **PUT** | `/update/{id}` | Update existing player details | Path Variable (`id`) & `PlayerDto` |
| **DELETE** | `/delete/{id}` | Remove player by ID | Path Variable (`id`) |

---

## 🛡️ Validation & Exception Handling Rules

Inputs are validated before database commitment and respond with structured error messages and appropriate HTTP status codes:

- **Empty Name:** Name cannot be empty (`400 Bad Request`)
- **Mobile Number Length:** Must be exactly 10 digits (`400 Bad Request`)
- **Email Format:** Must contain `@` and `.com` (`400 Bad Request`)
- **Age Range:** Must be between 2 and 69 years (`400 Bad Request`)
- **Date of Birth:** Cannot be a future date (`400 Bad Request`)
- **Duplicate Entries:** Email or Mobile Number already registered (`409 Conflict`)
- **Not Found:** Lookup by ID, name, or sport yields no results (`400 Bad Request`)

---

## 💻 How to Run

1. **Configure Database:** Ensure PostgreSQL is running and update database credentials in `src/main/resources/application.properties`.
2. **Start Backend:** Run the Spring Boot application on `http://localhost:8080`.
3. **Launch Frontend:** Open `index.html` directly in your web browser.
