DROP TABLE locations cascade constraints;
DROP TABLE roads cascade constraints;
DROP TABLE atstaff cascade constraints;
DROP TABLE projects cascade constraints;
DROP TABLE atroles cascade constraints;
DROP TABLE contracts cascade constraints;
DROP TABLE contractcomp cascade constraints;
DROP TABLE currentproject cascade constraints;
DROP TABLE currentrole cascade constraints;

CREATE TABLE locations
(location_id VARCHAR2(5) PRIMARY KEY,
location_name VARCHAR2(40),
latitude FLOAT(15) NOT NULL,
longitude FLOAT(15) NOT NULL);

CREATE TABLE roads
(road_id NUMBER(3) PRIMARY KEY,
road_name VARCHAR2(30),
road_description VARCHAR2(100),
road_category VARCHAR2(20),
road_length NUMBER(5),
start_location VARCHAR2(5),
end_location VARCHAR2(5),
subroad_to NUMBER(3));

ALTER TABLE roads
ADD ( CONSTRAINT road_start_location_fk
                 FOREIGN KEY (start_location)
                 REFERENCES locations(location_id)
    , CONSTRAINT road_end_location_fk
                 FOREIGN KEY (end_location)
                 REFERENCES locations(location_id)
    , CONSTRAINT road_subroad_to_fk
                 FOREIGN KEY (subroad_to)
                 REFERENCES roads(road_id)
    );


CREATE TABLE atstaff
(employee_id VARCHAR2(5) PRIMARY KEY,
at_first VARCHAR2(10),
at_last VARCHAR2(10),
date_employeed DATE DEFAULT SYSDATE,
DOB DATE DEFAULT SYSDATE,
postal_address VARCHAR2(30),
phone_number NUMBER(11),
email VARCHAR2(10));


CREATE TABLE projects
(project_code VARCHAR2(5) PRIMARY KEY,
project_name VARCHAR2(40),
project_description VARCHAR2(40));

CREATE TABLE atroles
(role_id VARCHAR2(5) PRIMARY KEY,
role_name VARCHAR2(15),
role_description VARCHAR2(60));

CREATE TABLE contractcomp
(comp_name VARCHAR2(15) PRIMARY KEY,
street_address VARCHAR2(20),
contact_details VARCHAR2(20));

CREATE TABLE contracts
(contract_id VARCHAR2(5) PRIMARY KEY,
contract_name VARCHAR2(30),
contract_description VARCHAR2(40),
estimated_cost NUMBER(7),
actual_cost NUMBER(7),
start_date DATE DEFAULT SYSDATE,
end_date DATE DEFAULT SYSDATE,
project_code VARCHAR2(5),
contractor VARCHAR2(15));

ALTER TABLE contracts
ADD    ( CONSTRAINT con_project_fk
            FOREIGN KEY (project_code)
            REFERENCES projects(project_code)
    , CONSTRAINT con_contractor_fk
            FOREIGN KEY (contractor)
            REFERENCES contractcomp(comp_name)
    );

CREATE TABLE currentproject
(road_id NUMBER(3),
project_code VARCHAR2(5),
date_start DATE DEFAULT SYSDATE,
date_end DATE DEFAULT SYSDATE,
PRIMARY KEY(road_id,project_code));

ALTER TABLE currentproject
ADD ( CONSTRAINT cur_road_id_fk
            FOREIGN KEY (road_id)
            REFERENCES roads(road_id)
    , CONSTRAINT cur_project_code_fk
            FOREIGN KEY (project_code)
            REFERENCES projects(project_code)
    );

CREATE TABLE currentrole
(date_start DATE DEFAULT SYSDATE,
date_end DATE DEFAULT SYSDATE,
employee_id VARCHAR2(5),
role_id VARCHAR2(5),
project_code VARCHAR2(5),
contract_id VARCHAR2(5),
PRIMARY KEY(employee_id,role_id,project_code));

ALTER TABLE currentrole
ADD ( CONSTRAINT curr_role_id_fk
            FOREIGN KEY(role_id)
            REFERENCES atroles(role_id)
    , CONSTRAINT curr_emp_id_fk
            FOREIGN KEY(employee_id)
            REFERENCES atstaff(employee_id)
    , CONSTRAINT curr_project_code_fk
            FOREIGN KEY (project_code)
            REFERENCES projects(project_code)
    , CONSTRAINT curr_cont_id_fk
            FOREIGN KEY (contract_id)
            REFERENCES contracts(contract_id)
    );

INSERT INTO locations VALUES
('LOC1','Tirau',-37.974384,175.759455);
INSERT INTO locations VALUES
('LOC2','Bay View',-39.429424,176.871994);
INSERT INTO locations VALUES
('LOC3','Cape Reinga',-34.504684,172.797549);
INSERT INTO locations VALUES
('LOC4','Rongotai',-41.32344036,174.803893);
INSERT INTO locations VALUES
('LOC5','Kumeu',-36.776832,174.557048);
INSERT INTO locations VALUES
('LOC6','Pukekohe',-37.200377,174.901048);
INSERT INTO locations VALUES
('LOC7','Auckland Central',-36.907633,174.918284);

INSERT INTO roads VALUES
(1,'SH5','One of New Zealands eight national highways','main highway',262,'LOC1','LOC2',NULL);
INSERT INTO roads VALUES
(2,'SH1','Longest and most significant road in the new zealand road network','main highway',1081,'LOC3','LOC4',NULL);
INSERT INTO roads VALUES
(3,'Northwestern Motorway','major western route and secondary northern route out of Auckland','Highway Subsection',21,'LOC7','LOC5',1);
INSERT INTO roads VALUES
(4,'Stanley Street','Secondary Street in Auckland Central','secondary street',1,'LOC7','LOC7',3);
INSERT INTO roads VALUES
(5,'Auckland Southern Motorway','Major route south out of the Auckland Region of new zealand','Highway Subsection',46,'LOC7','LOC6',1);

INSERT INTO atstaff VALUES
('EG163','Evelyn','Green', '14-MAY-2021','12-JUN-1997','5 Manning Rise',226913674,'evelyng');
INSERT INTO atstaff VALUES
('AB784','Andrew','Booker','14-AUG-2021','16-MAR-1980','109 Holdsworth Avenue',221257385,'andrewb');
INSERT INTO atstaff VALUES
('IN947','Ivan','Nelson','11-FEB-2022','17-JAN-1983','87 village park drive',215289631,'ivann');
INSERT INTO atstaff VALUES
('SH457','Serena','Hart','17-AUG-2022','18-JUL-1976','234 Blanche Way',218361802,'serenah');
INSERT INTO atstaff VALUES
('RW888','Ray','Wolf','22-JAN-2023','6-NOV-1995','162 Queen Street',221817411,'rayw');

INSERT INTO projects VALUES
('PC001','Highway Expansion','Widening a highway');
INSERT INTO projects VALUES
('PC002','Public Transport Infrastructure','COnstruct bus lands, trams tracks etc');
INSERT INTO projects VALUES
('PC003','Rounabout installation',NULL);
INSERT INTO projects VALUES
('PC004','Tunnel Construction',NULL);
INSERT INTO projects VALUES
('PC005','Bridge Construction',NULL);

INSERT INTO atroles VALUES
('R1','Manager','Oversees contracts and manages team');
INSERT INTO atroles VALUES
('R2','Planning','reponsible for planning and scheduling the project');
INSERT INTO atroles VALUES
('R3','Design','creates blueprints');
INSERT INTO atroles VALUES
('R4','Construction','Involved in physical construction of project');
INSERT INTO atroles VALUES
('R5','Engineer','Ensures project is safe and sound');

INSERT INTO contractcomp VALUES
('ABC','13 luplau crescent','abc.gov');
INSERT INTO contractcomp VALUES
('XYZ','224 centre street','xyz.cont');
INSERT INTO contractcomp VALUES
('DEF','91 Puriri Park Road','def.org');
INSERT INTO contractcomp VALUES
('GHI','95 kauri street','ghi.co.nz');
INSERT INTO contractcomp VALUES
('ZZZ','16 Stonebridge Way','zzz.net');

INSERT INTO contracts VALUES
('C1','Maintenance contract','routine maintenance',10000,9500,'11-FEB-2022','29-MAR-2023','PC001','ABC');
INSERT INTO contracts VALUES
('C2','Greenfield contract','new facilities',100000,95000,'11-FEB-2022','29-MAR-2023','PC001','XYZ');
INSERT INTO contracts VALUES
('C3','Brownfield contract','redevelopment of existing facilities',50000,45000,'05-NOV-2022','16-FEB-2024','PC004','DEF');
INSERT INTO contracts VALUES
('C4','Design-Build contract',NULL,150000,140000,'11-OCT-2023','03-MAR-2026','PC005','GHI');
INSERT INTO contracts VALUES
('C5','Lump sum contract',NULL,50000,40000,'05-NOV-2022','16-FEB-2024','PC004','ZZZ');


INSERT INTO currentrole VALUES
('09-FEB-2022','29-MAY-2024','EG163','R1','PC001','C1');
INSERT INTO currentrole VALUES
('02-JUN-2022','19-AUG-2023','AB784','R2','PC002',NULL);
INSERT INTO currentrole VALUES
('03-SEP-2021','11-JUN-2022','IN947','R3','PC002',NULL);
INSERT INTO currentrole VALUES
('04-NOV-2022','16-FEB-2024','SH457','R4','PC004',NULL);
INSERT INTO currentrole VALUES
('07-SEP-2022','03-MAR-2024','RW888','R5','PC005',NULL);

INSERT INTO currentproject VALUES
(1,'PC001','11-FEB-2022','29-MAR-2023');
INSERT INTO currentproject VALUES
(1,'PC002','11-JUN-2022','19-AUG-2024');
INSERT INTO currentproject VALUES
(2,'PC002','12-SEP-2021','11-JUN-2025');
INSERT INTO currentproject VALUES
(4,'PC004','05-NOV-2022','16-FEB-2024');
INSERT INTO currentproject VALUES
(5,'PC005','11-OCT-2023','03-MAR-2026');

COMMIT;

SELECT * FROM locations;
SELECT * FROM roads;
SELECT * FROM projects;
SELECT * FROM atstaff;
SELECT * FROM atroles;
SELECT * FROM contracts;
SELECT * FROM contractcomp;
SELECT * FROM currentrole;
SELECT * FROM currentproject;