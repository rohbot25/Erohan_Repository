--1.1
SELECT * FROM employees;
DESCRIBE employees;
DESCRIBE locations;
DESCRIBE countries;
DESCRIBE jobs;
DESCRIBE job_history;
DESCRIBE regions;

--1.2
SELECT first_name, job_id, salary
FROM employees;

--1.3 true
--1.4 false
--1.5
SELECT last_name, salary, commission_pct, 12*salary*commission_pct AnnSal
FROM employees;
--if there commission is null, their ANNSAL will be null

--1.6
SELECT employee_id "EMP#", first_name, last_name, job_id "Job Title", department_id "Department ID"
FROM employees;

SELECT DISTINCT job_id 
FROM employees;

SELECT first_name || ' ' || last_name "Full Name", 'Phone Number: ' || phone_number || ' Email: ' || email "Contact Details"
FROM employees;

SELECT *
FROM employees
WHERE department_id = 60;

SELECT last_name, job_id
FROM employees
WHERE job_id = 'IT_PROG';