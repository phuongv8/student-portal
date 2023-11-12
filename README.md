# Capstone Project
Github link: https://github.com/phuongv8/capstone

## Overview

The Student Portal is a Java-based web application designed to manage student enrollments and academic information at a university. It uses Spring Boot, JPA/Hibernate, and Thymeleaf for its implementation, and it is designed to work with a MySQL database.

## Features
- **Student Registration**: New students can register, providing their personal and academic details.
- **Course Enrollment**: Registered students can enroll in courses.
- **Learner Profiles**: Each student has a learner profile that tracks their credits, GPA, and graduation status.
- **Program Management**: Administrators can create and manage academic programs and courses.
- **Data Persistence**: Student and course data are persisted in a MySQL database.
- **Security**: The application includes basic login and authentication features.

## Technologies Used
- **Spring Boot**: For creating the web application framework.
- **Java Persistence API (JPA) with Hibernate**: For object-relational mapping and data handling.
- **Thymeleaf**: For server-side rendering of HTML views.
- **MySQL**: As the backend database.
- **Spring Security**: For basic authentication.

### Prerequisites
- Java JDK 11 or higher
- Spring Boot 2.x
- MySQL 5.7 or higher
- Maven 3.6 or higher

# Database setup
Before running the application, you need to create the database.   
To create the required database within MySQL:
```sql 
CREATE DATABASE IF NOT EXISTS university_db;
EXIT;
```

Replace username and password with your MySQL username and password.
```properties
spring.datasource.username=username
spring.datasource.password=password
```

## Automatic Initialization
This application uses Spring Boot's automatic schema initialization feature.  
To enable it, open the application.properties file and uncomment the following lines:
```properties
spring.datasource.initialization-mode=always
spring.sql.init.schema-locations=classpath:schema.sql
spring.sql.init.data-locations=classpath:data.sql
```

## Manual Initialization
If you prefer to manually initialize the database, you can use the provided SQL scripts in src/main/resources/sql_queries.  

# Running the application
Open a web browser and navigate to `http://localhost:8080`.
