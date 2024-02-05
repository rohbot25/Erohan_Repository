--1
SELECT last_name || ', ' || first_name "Full Name" , email || '@megacorp.com' "Email"
FROM employees
ORDER BY last_name ASC;

--2
SELECT last_name, 'NZD ' || salary, commission_pct "Commission %", commission_pct * salary "Commission"
FROM employees
ORDER BY last_name;

--3
SELECT first_name || ' ' || last_name "Full Name", LENGTH(CONCAT(first_name,last_name)) || ' Char.' "Name Length",TO_CHAR(hire_date, 'DAY') "Hire Day", TO_CHAR(hire_date, 'DD Month YYYY') "Hire Date"
FROM employees
WHERE NOT job_id = 'SA_MAN';

--4
SELECT first_name || ' ' || last_name "Full Name", job_id "Job Title", email || '@megacorp.com' "Email"
FROM employees
WHERE LOWER(first_name) = LOWER('&first');

--5
SELECT MAX(salary) "MAX", MIN(salary) "MIN", ROUND(AVG(salary),2) "AVERAGE", ROUND(STDDEV(salary),2) "STANDARD DEVIATION"
FROM employees;

--6
SELECT DISTINCT job_id "Job Title", COUNT(*) "Number of Staff"
FROM employees
GROUP BY job_id
ORDER BY COUNT(*) DESC;

--7
SELECT MAX(salary) "MAX", MIN(salary) "MIN", ROUND(AVG(salary),2) "AVERAGE", ROUND(STDDEV(salary),2) "STANDARD DEVIATION"
FROM employees
WHERE department_id = 80;

--8
SELECT e.department_id "Department No", d.department_name "Department Name", ROUND(AVG(e.salary),2) "Average Salary"
FROM employees e, departments d
WHERE e.department_id = d.department_id
GROUP BY e.department_id,d.department_name;

--9
SELECT e.department_id "Department No", d.department_name "Department Name", ROUND(AVG(e.salary),2) "Average Salary"
FROM employees e, departments d
WHERE e.department_id = d.department_id
GROUP BY e.department_id,d.department_name
HAVING AVG(e.salary)>6000;

--10
SELECT e.first_name || ' ' || e.last_name "Full Name", j.job_title "Job Title", d.department_name "Department Name", TO_CHAR(jh.start_date, 'DD/MM/YY') "Start Date", TO_CHAR(jh.end_date, 'DD/MM/YY') "End Date", TRUNC(MONTHS_BETWEEN(jh.end_date,jh.start_date),0) "# of months employeed" 
FROM employees e, departments d, jobs j, job_history jh
WHERE e.employee_id = jh.employee_id
AND e.job_id = j.job_id
AND e.department_id = d.department_id;
