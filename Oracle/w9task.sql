CREATE TABLE student
    (student_no VARCHAR2(7) PRIMARY KEY,
    student_name VARCHAR(20) NOT NULL,
    student_address VARCHAR2(50));

DESCRIBE student;

CREATE TABLE course
    (course_code VARCHAR2(4) PRIMARY KEY,
    course_name VARCHAR2(40) NOT NULL,
    course_details VARCHAR2(50));
    
DESCRIBE course;
drop Table grade;
CREATE TABLE grade
    (course_code  VARCHAR2(4),
    student_no VARCHAR2(7),
    grade NUMBER(3) NOT NULL,
    FOREIGN KEY(student_no) REFERENCES student (student_no),
    FOREIGN KEY(course_code) REFERENCES course(course_code),
    PRIMARY KEY(student_no,course_code));

DESCRIBE grade;




    