# AirList - Java Servlets, JSP and JDBC Project

## Overview
This project demonstrates the use of Java Servlets, JSP, and JDBC to create a web application that lists different companies. The application includes user authentication and CRUD operations for managing company records.

## Database Setup
Create a database with the following schema:

### Users Table
| Column    | Type        | Description         |
|-----------|-------------|---------------------|
| id        | INT         | Primary Key         |
| username  | VARCHAR(50) | User's username     |
| password  | VARCHAR(50) | User's password     |

### Companies Table
| Column        | Type         | Description                                   |
|---------------|--------------|-----------------------------------------------|
| id            | SERIAL(10)   | Primary Key                                   |
| arrival_date  | VARCHAR(255) | Company listed its opening                    |
| company_name  | VARCHAR(255) | Company's name                                |
| next_date     | VARCHAR(255) | Next assessment/round date                    |
| status        | VARCHAR(255) | Is the drive finished/ongoing/upcoming        |
| tag           | INT4(10)     | Integer value showing the status              |
| role          | VARCHAR(255) | Role for the drive                            |
| description   | VARCHAR(255) | Additional description                        |

## Endpoints

### Root Endpoint `/`
This is the root endpoint and contains a form for login with username and password.

**Example Request:**
```html
<form action="login" method="post">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type="submit">Login</button>
</form>
```

### Login Endpoint `/login`
Protected route that processes the login form and redirects to the root.

**Example Request:**
```http
POST /login
Content-Type: application/x-www-form-urlencoded

username=user1&password=pass123
```

### Home Endpoint `/home`
Displays the list of companies.

**Example Request:**
```http
GET /home
```

### Update Endpoint `/update`
Updates the existing record of a company.

**Example Request:**
```http
POST /update
Content-Type: application/x-www-form-urlencoded

id=1&arrival_date=2024-01-01&company_name=NewCompanyName&next_date=2024-02-01&status=ongoing&tag=1&role=Developer&description=UpdatedDescription
```

### Insert Endpoint `/insert`
Inserts a new company into the current list.

**Example Request:**
```http
POST /insert
Content-Type: application/x-www-form-urlencoded

arrival_date=2024-01-01&company_name=CompanyName&next_date=2024-02-01&status=upcoming&tag=2&role=Engineer&description=DescriptionText
```

### Error 404 Page `/(any wrong url)`
Displays an error 404 page showing "Resource Not Found".

**Example Request:**
```http
GET /nonexistentpage
```

## Running the Project
1. Clone the repository
    ```sh
    git clone https://github.com/yourusername/yourproject.git
    ```
2. Import the project into your preferred IDE (e.g., IntelliJ IDEA).
3. Configure your database connection settings.
4. Run the application server.
5. Access the application at `http://localhost:8080`.

## Technologies Used
- Java Servlets
- JSP (JavaServer Pages)
- JDBC (Java Database Connectivity)
- PostgreSQL Cloud (or any preferred relational database)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
