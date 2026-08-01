

DROP DATABASE IF EXISTS matthew_school_management;
CREATE DATABASE matthew_school_management 
CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE matthew_school_management;

-- ---------------------------------------------------------------------
-- 1. Academic calendar (session -> term). Time-bound records hang off a term.
-- ---------------------------------------------------------------------
CREATE TABLE AcademicSession (
    session_id   INT AUTO_INCREMENT PRIMARY KEY,
    session_name VARCHAR(20) NOT NULL,            -- e.g. '2024/2025'
    start_date   DATE NOT NULL,
    end_date     DATE NOT NULL,
    is_current   BOOLEAN NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_session_name (session_name),
    CONSTRAINT chk_session_dates CHECK (end_date > start_date)
) ENGINE=InnoDB;

CREATE TABLE Term (
    term_id    INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT NOT NULL,
    term_name  ENUM('First','Second','Third') NOT NULL,
    start_date DATE NOT NULL,
    end_date   DATE NOT NULL,
    is_current BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_session_term (session_id, term_name),
    CONSTRAINT fk_term_session FOREIGN KEY (session_id)
        REFERENCES AcademicSession(session_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_term_dates CHECK (end_date > start_date)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 2. Authentication / authorization — backbone of a "management system"
-- ---------------------------------------------------------------------
CREATE TABLE Users (
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL,
    email         VARCHAR(120) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,          -- store bcrypt/argon2 hash, NEVER plaintext
    role          ENUM('admin','teacher','student','parent','staff') NOT NULL,
    is_active     BOOLEAN NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_users_username (username),
    UNIQUE KEY uq_users_email (email)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 3. People — contact info inlined (no separate 1:1 ContactInfo table)
-- ---------------------------------------------------------------------
CREATE TABLE Teacher (
    teacher_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NULL,                     -- optional login account
    employee_number VARCHAR(20) NOT NULL,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    gender          ENUM('Male','Female','Other'),
    specialization  VARCHAR(100),
    email           VARCHAR(120),
    phone           VARCHAR(20),
    address         VARCHAR(255),
    hire_date       DATE,
    is_active       BOOLEAN NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_teacher_empno (employee_number),
    UNIQUE KEY uq_teacher_email (email),
    CONSTRAINT fk_teacher_user FOREIGN KEY (user_id)
        REFERENCES Users(user_id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE Guardian (
    guardian_id  INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT NULL,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    relationship VARCHAR(30),                     -- Father, Mother, Uncle...
    email        VARCHAR(120),
    phone        VARCHAR(20) NOT NULL,
    address      VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_guardian_user FOREIGN KEY (user_id)
        REFERENCES Users(user_id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 4. Academic structure
-- ---------------------------------------------------------------------
CREATE TABLE Class (
    class_id    INT AUTO_INCREMENT PRIMARY KEY,
    class_name  VARCHAR(50) NOT NULL,             -- e.g. 'JSS1', 'Grade 2'
    grade_level VARCHAR(20) NOT NULL,
    section     VARCHAR(10),                      -- 'A', 'B'...
    teacher_id  INT NULL,                         -- form / class teacher
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_class (class_name, section),
    CONSTRAINT fk_class_teacher FOREIGN KEY (teacher_id)
        REFERENCES Teacher(teacher_id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

-- Subject is a catalog entity only; who teaches it lives in Timetable.
CREATE TABLE Subject (
    subject_id   INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL,
    subject_code VARCHAR(20),
    description  VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_subject_name (subject_name),
    UNIQUE KEY uq_subject_code (subject_code)
) ENGINE=InnoDB;

CREATE TABLE Student (
    student_id       INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT NULL,
    admission_number VARCHAR(20) NOT NULL,
    first_name       VARCHAR(50) NOT NULL,
    last_name        VARCHAR(50) NOT NULL,
    gender           ENUM('Male','Female','Other'),
    date_of_birth    DATE NOT NULL,
    enrollment_date  DATE NOT NULL DEFAULT (CURRENT_DATE),
    class_id         INT NULL,
    email            VARCHAR(120),
    phone            VARCHAR(20),
    address          VARCHAR(255),
    is_active        BOOLEAN NOT NULL DEFAULT TRUE,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_student_admno (admission_number),
    CONSTRAINT fk_student_class FOREIGN KEY (class_id)
        REFERENCES Class(class_id) ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_student_user FOREIGN KEY (user_id)
        REFERENCES Users(user_id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

-- A student can have several guardians and a guardian several students (M:N).
CREATE TABLE StudentGuardian (
    student_id  INT NOT NULL,
    guardian_id INT NOT NULL,
    is_primary  BOOLEAN NOT NULL DEFAULT FALSE,   -- main contact for the child
    PRIMARY KEY (student_id, guardian_id),
    CONSTRAINT fk_sg_student FOREIGN KEY (student_id)
        REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_sg_guardian FOREIGN KEY (guardian_id)
        REFERENCES Guardian(guardian_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 5. Term-scoped academic records
-- ---------------------------------------------------------------------
CREATE TABLE Enrollment (
    enrollment_id   INT AUTO_INCREMENT PRIMARY KEY,
    student_id      INT NOT NULL,
    subject_id      INT NOT NULL,
    term_id         INT NOT NULL,
    enrollment_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    status          ENUM('active','dropped','completed') NOT NULL DEFAULT 'active',
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_enrollment (student_id, subject_id, term_id),
    CONSTRAINT fk_enr_student FOREIGN KEY (student_id)
        REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_enr_subject FOREIGN KEY (subject_id)
        REFERENCES Subject(subject_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_enr_term FOREIGN KEY (term_id)
        REFERENCES Term(term_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Attendance (
    attendance_id   INT AUTO_INCREMENT PRIMARY KEY,
    student_id      INT NOT NULL,
    class_id        INT NOT NULL,
    term_id         INT NOT NULL,
    attendance_date DATE NOT NULL,
    status          ENUM('present','absent','late','excused') NOT NULL DEFAULT 'present',
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- one attendance record per student per class per day
    UNIQUE KEY uq_attendance (student_id, class_id, attendance_date),
    CONSTRAINT fk_att_student FOREIGN KEY (student_id)
        REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_att_class FOREIGN KEY (class_id)
        REFERENCES Class(class_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_att_term FOREIGN KEY (term_id)
        REFERENCES Term(term_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Exam (
    exam_id    INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    term_id    INT NOT NULL,
    exam_type  ENUM('CA','Midterm','Final') NOT NULL DEFAULT 'Final',
    exam_date  DATE NOT NULL,
    score      DECIMAL(5,2) NOT NULL,
    grade      VARCHAR(2),                        -- optional: could be a generated column
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_exam (student_id, subject_id, term_id, exam_type),
    CONSTRAINT fk_exam_student FOREIGN KEY (student_id)
        REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id)
        REFERENCES Subject(subject_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_exam_term FOREIGN KEY (term_id)
        REFERENCES Term(term_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_exam_score CHECK (score >= 0 AND score <= 100)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 6. Scheduling — UNIQUE keys prevent double-booking a class or a teacher
-- ---------------------------------------------------------------------
CREATE TABLE Timetable (
    timetable_id INT AUTO_INCREMENT PRIMARY KEY,
    class_id     INT NOT NULL,
    subject_id   INT NOT NULL,
    teacher_id   INT NOT NULL,
    day_of_week  ENUM('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') NOT NULL,
    start_time   TIME NOT NULL,
    end_time     TIME NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_class_slot   (class_id,   day_of_week, start_time),
    UNIQUE KEY uq_teacher_slot (teacher_id, day_of_week, start_time),
    CONSTRAINT fk_tt_class FOREIGN KEY (class_id)
        REFERENCES Class(class_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_tt_subject FOREIGN KEY (subject_id)
        REFERENCES Subject(subject_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_tt_teacher FOREIGN KEY (teacher_id)
        REFERENCES Teacher(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_tt_time CHECK (end_time > start_time)
) ENGINE=InnoDB;
