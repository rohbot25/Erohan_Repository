--1
SELECT first_name || ' ' || last_name "Full Name", commission_pct, job_id
FROM employees
WHERE commission_pct > 0.25;

--2
SELECT first_name || ' ' || last_name "Full Name", job_id, salary
FROM employees
WHERE salary BETWEEN 7000 AND 9000;

--3
SELECT first_name || ' ' || last_name "Full Name", job_id, salary
FROM employees
WHERE salary NOT BETWEEN 7000 AND 9000;

-- 4 the difference is in 2, 
--it will display all employees between 7,000 and 9,000 inclusive
--3 displays all employees outside of 7,000 to 9,000 exclusive
--this is changed with the NOT keyword

-- 5
SELECT first_name || ' ' || last_name "Full Name",job_id,hire_date
FROM employees
WHERE last_name = 'Atkinson' 
OR last_name = 'Greenberg';

--6
SELECT street_address, postal_code, city
FROM locations
WHERE country_id IN ('UK' , 'BR');

--7
SELECT first_name, last_name,'Email: ' || email || ' Phone Number: ' || phone_number  "Contact Details"
FROM employees 
WHERE hire_date BETWEEN '01-Aug-1998' AND '31-Aug-1999'
ORDER BY first_name ASC;

--8
SELECT first_name || ' ' || last_name "Full Name",'Email: ' || email || ' Phone Number: ' || phone_number  "Contact Details",hire_date,job_id
FROM employees 
WHERE hire_date > '31-Dec-1999'
ORDER BY job_id ASC, hire_date DESC;

--9
SELECT *
FROM employees
WHERE first_name LIKE 'S%n';

--10
SELECT first_name,last_name,job_id
FROM employees
WHERE job_id IN ('AC_MGR','AD_VP','FI_MGR','HR_REP','PR_REP');

--11
SELECT employee_id,last_name,salary,department_id
FROM employees
WHERE department_id = &department_num;

--dep 100/6r, 60/5r, 40/1r, 50/45r