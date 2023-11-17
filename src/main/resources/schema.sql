CREATE TABLE IF NOT EXISTS Programs (
    program_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    required_credits_to_graduation INT NOT NULL,
    field_of_study VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Courses (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    number_of_credits INT
);

CREATE TABLE IF NOT EXISTS LearnerProfiles (
    profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number_of_credits INT,
    gpa DECIMAL(3, 2),
	is_graduated BOOLEAN,
    start_year SMALLINT
);

CREATE TABLE IF NOT EXISTS Students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    date_of_birth DATE,
    password VARCHAR(255),
    profile_id BIGINT,
    program_id BIGINT,
    FOREIGN KEY (profile_id) REFERENCES LearnerProfiles (profile_id),
    FOREIGN KEY (program_id) REFERENCES Programs (program_id)
);

CREATE TABLE IF NOT EXISTS CourseEnrollments (
    course_id BIGINT,
    enrolled_student_id BIGINT,
    PRIMARY KEY (course_id, enrolled_student_id),
    FOREIGN KEY (course_id) REFERENCES Courses (course_id),
    FOREIGN KEY (enrolled_student_id) REFERENCES Students (id)
);