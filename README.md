# Capstone Project
Github link: https://github.com/phuongv8/capstone

## Features
- **Student Management**: View student profile.
- **Course Management**: View courses; enroll and drop courses.
- **Program Management**: View academic programs.
- **Login & Registration**: Handle student login and registration processes.
- **Views**: Public views for listing all students, courses, and programs.

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
Once you've updated and saved your application.properties file, you can start the application with:
```bash
./mvnw spring-boot:run
```
or if you have a pre-packaged .jar:
```bash
java -jar capstone.jar
```