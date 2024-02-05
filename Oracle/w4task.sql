--1
SELECT e.first_name, e.last_name, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id
AND e.first_name LIKE 'A%';

--2
SELECT e.first_name || ' ' || e.last_name "Full Name", e.job_id "Job Title", d.department_name "Department Name"
FROM employees e, departments d
WHERE e.department_id = d.department_id
ORDER BY e.last_name;

--3
SELECT e.first_name || ' ' || e.last_name "Full Name", d.department_name "Department Name", e.salary "Salary", l.city "City"
FROM employees e, departments d, locations l
WHERE e.department_id = d.department_id
AND d.location_id = l.location_id
AND e.salary > 12000;

--4
SELECT d.department_id, d.department_name, l.street_address, l.postal_code, l.city, l.state_province, r.region_name
FROM departments d, locations l, regions r, countries
WHERE d.location_id = l.location_id
AND l.country_id = countries.country_id
AND countries.region_id = r.region_id
AND r.region_id IN (1,4);

--5
SELECT e.first_name || ' ' || e.last_name "Full Name", e.email || '@MEGACORP.COM' "Email", d.department_name "Department Name", l.city || ', ' || c.country_name "Full Address"
FROM employees e, departments d, locations l, countries c
WHERE e.department_id = d.department_id
AND d.location_id = l.location_id
AND l.country_id = c.country_id
ORDER BY e.last_name;

--6
SELECT e.first_name || ' ' || e.last_name "Full Name", e.email || '@MEGACORP.COM' "Email", d.department_name "Department Name", l.city || ', ' || c.country_name "Full Address"
FROM employees e, departments d, locations l, countries c
WHERE e.department_id = d.department_id
AND d.location_id = l.location_id
AND l.country_id = c.country_id
AND c.country_name = '&country'
ORDER BY e.last_name;

--7
SELECT e.first_name || ' ' || e.last_name "Full Name", d.department_name
FROM employees e, departments d
WHERE e.department_id(+) = d.department_id;

--8
SELECT e.first_name || ' '|| e.last_name "Employee Name", e.employee_id "Employee #", 
m.employee_id "Manager #", m.first_name || ' ' || m.last_name "Manager Name"
FROM employees e, employees m
WHERE e.manager_id = m.employee_id;

--9
--because steven king doesn't have a manager 
--10
SELECT e.first_name || ' '|| e.last_name "Employee Name", e.employee_id "Employee #", 
m.employee_id "Manager #", m.first_name || ' ' || m.last_name "Manager Name"
FROM employees e, employees m
WHERE e.manager_id = m.employee_id(+);