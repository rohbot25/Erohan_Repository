-- all roads that have projects on them right now
SELECT r.road_name "Road", c.project_code "Project #", TO_CHAR(c.date_start, 'DD Month YYYY'), TO_CHAR(c.date_end, 'DD Month YYYY')
FROM roads r, currentproject c
WHERE r.road_id = c.road_id
AND c.date_end >= '11-oct-2023'
ORDER BY c.project_code ASC;

--avg road length of all subroads to a certain highway
SELECT subroad_to, ROUND(AVG(road_length),2) "Average SubRoad Length"
FROM roads
GROUP BY subroad_to;

-- contracts under a each project and which contract company is sponsoring
SELECT p.project_name "Project", c.contract_name "Contract", cc.comp_name "Contractor"
FROM projects p, contracts c,contractcomp cc
WHERE p.project_code = c.project_code
AND cc.comp_name = c.contractor
ORDER BY p.project_code;

-- full name of all employees and there role ordered by first name in alphabetical order
SELECT e.at_first || ' ' || e.at_last "Full Name", r.role_name "Role", p.project_name "Project"
FROM atstaff e, atroles r, projects p, currentrole c
WHERE e.employee_id = c.employee_id
AND r.role_id = c.role_id
AND p.project_code = c.project_code
ORDER BY e.at_first;

--enter a road and all locations that the road starts or ends enter 1 for example
SELECT r.road_name "road", l.location_name "Location"
FROM locations l, roads r
WHERE road_id = &starting
AND (l.location_id = r.start_location or l.location_id = r.end_location);