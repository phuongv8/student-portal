CREATE DATABASE university_db;

USE university_db;

CREATE TABLE Programs (
    program_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    required_credits_to_graduation INT NOT NULL,
    field_of_study VARCHAR(255) NOT NULL
);

INSERT INTO Programs (required_credits_to_graduation, field_of_study) VALUES
(50, 'Hamster Wheel Engineering');
INSERT INTO Programs (required_credits_to_graduation, field_of_study) VALUES
(50, 'Nest Architecture');
INSERT INTO Programs (required_credits_to_graduation, field_of_study) VALUES
(50, 'Seed Analysis and Foraging');

CREATE TABLE Courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    number_of_credits INT
);

INSERT INTO Courses (title, description, number_of_credits) VALUES
('Wheel Running 101', 'Master the hamster wheel.', 5),
('Burrow Building Basics', 'Learn safe burrow construction.', 5),
('Guide to Hoarding', 'Manage and prioritize stash.', 5),
('Nap Navigator', 'Optimize sleep cycles.', 4),
('Treat Tasting', 'Critique hamster snacks.', 5),
('Escape Artistry', 'Become a hamster Houdini.', 3),
('Sniffing Strategies', 'Refine your scent detection skills.', 3),
('Chew Toy Critique', 'Evaluate the best chew toys.', 3),
('Fur Grooming Fundamentals', 'Achieve a glossy coat.', 2),
('Hamstertainment 101', 'Explore hamster playtime activities.', 5);

CREATE TABLE LearnerProfiles (
    profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number_of_credits INT,
    gpa DECIMAL(3, 2),
	is_graduated BOOLEAN,
    start_year SMALLINT
);

INSERT INTO LearnerProfiles (number_of_credits, gpa, is_graduated, start_year) VALUES
(0, 0.0, FALSE, 2023);
INSERT INTO LearnerProfiles (number_of_credits, gpa, is_graduated, start_year) VALUES
(10, 3.8, FALSE, 2023);
INSERT INTO LearnerProfiles (number_of_credits, gpa, is_graduated, start_year) VALUES
(50, 3.5, True, 2022);

CREATE TABLE Students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    date_of_birth DATE,
    profile_id BIGINT,
    program_id BIGINT,
    FOREIGN KEY (profile_id) REFERENCES LearnerProfiles (profile_id),
    FOREIGN KEY (program_id) REFERENCES Programs (program_id)
);

INSERT INTO Students (first_name, last_name, email, date_of_birth, profile_id, program_id) VALUES
('Hammy', 'Hamster', 'hammy@universityofhamster.edu', '2021-01-01', 1, 1);
INSERT INTO Students (first_name, last_name, email, date_of_birth, profile_id, program_id) VALUES
('Hampter', 'Cheese', 'hampter@universityofhamster.edu', '2021-02-02', 2, 2);
INSERT INTO Students (first_name, last_name, email, date_of_birth, profile_id, program_id) VALUES
('Buttercup', 'Squeakins', 'buttercup.squeakins@universityofhamster.edu', '2021-10-15', 3, 3);

CREATE TABLE CourseEnrollments (
    course_id BIGINT,
    enrolled_student_id BIGINT,
    PRIMARY KEY (course_id, enrolled_student_id),
    FOREIGN KEY (course_id) REFERENCES Courses (course_id),
    FOREIGN KEY (enrolled_student_id) REFERENCES Students (id)
);

INSERT INTO CourseEnrollments (course_id, enrolled_student_id) VALUES (1, 1);
INSERT INTO CourseEnrollments (course_id, enrolled_student_id) VALUES (2, 1);
INSERT INTO CourseEnrollments (course_id, enrolled_student_id) VALUES (2, 2);

