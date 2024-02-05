--1
INSERT INTO course VALUES 
('LDD','Logical Database Design','Data Science');
INSERT INTO course VALUES
       ('ML','Machine Learning','Data mining');
INSERT INTO course VALUES
       ('P1','Programming 1',NULL);

INSERT INTO student VALUES
('S-2054','Tony Vegas','Auckland');
INSERT INTO student VALUES
('S-7895','John Little','Auckland');
INSERT INTO student VALUES
('S-3695','James Ship','Hamilton');
INSERT INTO student VALUES
('S-3982','Allis Ship','Hamilton');
INSERT INTO student VALUES
('S-1478','Carol Wang','Thames');

INSERT INTO grade VALUES
('LDD','S-2054',77);
INSERT INTO grade VALUES
('ML','S-2054',75);
INSERT INTO grade VALUES
('ML','S-7895',65);
INSERT INTO grade VALUES
('P1','S-7895',70);
INSERT INTO grade VALUES
('LDD','S-1478',80);
INSERT INTO grade VALUES
('P1','S-3982',85);
INSERT INTO grade VALUES
('P1','S-3695',88);
INSERT INTO grade VALUES
('LDD','S-3695',90);
INSERT INTO grade VALUES
('ML','S-1478',77);
INSERT INTO grade VALUES
('P1','S-1478',70);

COMMIT;

SELECT s.student_no,s.student_name,c.course_code,c.course_name,g.grade
FROM student s, course c, grade g
WHERE s.student_no = g.student_no
AND c.course_code = g.course_code;

--2
INSERT INTO countries VALUES
('NZ','New Zealand','3');
INSERT INTO locations VALUES
(3300,'AUT City Campus, WT Building',1010,'Auckland CBD','Auckland','NZ');
INSERT INTO departments VALUES
(280,'Research and Development',100,3300);
INSERT INTO employees VALUES
(207,'Evan','Rohan','EROHAN','123.123.1234', TO_DATE('01-MAR-2016', 'dd-MON-yyyy'),'IT_PROG',5000,NULL,100,280); 
INSERT INTO employees VALUES
(208,'Bill','Gates','BGATES','124.123.1234', TO_DATE('01-MAR-2016', 'dd-MON-yyyy'),'IT_PROG',5000,NULL,100,280); 
INSERT INTO employees VALUES
(209,'Jeff','Bazos','JBAZOS','125.123.1234', TO_DATE('01-MAR-2016', 'dd-MON-yyyy'),'IT_PROG',5000,NULL,100,280); 
INSERT INTO employees VALUES
(210,'Steve','Jobs','SJOBS','126.123.1234', TO_DATE('01-MAR-2016', 'dd-MON-yyyy'),'IT_PROG',5000,NULL,100,280); 
INSERT INTO employees VALUES
(211,'Elon','Musk','EMUSK','127.123.1234', TO_DATE('01-MAR-2016', 'dd-MON-yyyy'),'IT_PROG',5000,NULL,100,280); 

COMMIT;

--3
UPDATE employees
SET commission_pct = .05
WHERE employee_id = 207;

COMMIT;

SELECT * 
FROM employees
WHERE employee_id = 207;
