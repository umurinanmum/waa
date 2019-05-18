/*INSERT INTO session (start_Week, end_Week, start_Day,end_Day) VALUES
  ('1', '3', '1','3'),
  ('3', '4', '1','3');*/


--ENTRYs
INSERT INTO entry (id,name) VALUES (1,'2016-November');
INSERT INTO entry (id,name) VALUES (2,'2016-December');


--STUDENTs
INSERT INTO student (id,barcode, student_Id,entry_id,first_name,last_name) VALUES(1 ,'7888', '987012',1,'Eren','Ozturk' );
INSERT INTO student (id,barcode, student_Id,entry_id,first_name,last_name) VALUES(2 ,'456', '986814',1,'Umur','Inan' );



--FACULTYs
INSERT INTO faculty (id,first_name,last_name) VALUES (1 ,'Asaad','Saad' );
INSERT INTO faculty (id,first_name,last_name) VALUES (2 ,'Tina','Xing' );

--CREDENTIALs
INSERT INTO credential (id,email, password,student_id) VALUES (1 ,'eozturk@mum.edu', '12345',1);
INSERT INTO credential (id,email, password,student_id) VALUES (2 ,'tinan@mum.edu', '12345',2);

INSERT INTO credential (id,email, password,faculty_id) VALUES (3 ,'asaad@mum.edu', '12345',1);
INSERT INTO credential (id,email, password,faculty_id) VALUES (4 ,'tina@mum.edu', '12345',2);



--ROLEs
INSERT INTO role (id,name) VALUES (1 ,'GENERATE_REPORT');
INSERT INTO role (id,name) VALUES (2 ,'SAVE_ENTRY');
INSERT INTO role (id,name) VALUES (3 ,'SAVE_BLOCK');


--CREDENTIAL ROLEs
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (1 ,1);

INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,1);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,2);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,3);



--BLOCKs
INSERT INTO block (id,start_Date,end_Date,name) VALUES(1 ,'2016-10-01', '2016-10-30','2016-October' );
INSERT INTO block (id,start_Date, end_Date,name) VALUES(2 ,'2016-11-01', '2016-11-30','2016-November' );
INSERT INTO block (id,start_Date, end_Date,name) VALUES (3,'2016-12-01', '2016-12-30','2016-December' );



--LOCATIONs
INSERT INTO location (id,location_name,location_code) VALUES (1 ,'Dolby Hall','DB' );
INSERT INTO location (id,location_name,location_code) VALUES (2 ,'Art Center','AC' );


--COURSEs
INSERT INTO course (id,course_name) VALUES (1 ,'MWA' );
INSERT INTO course (id,course_name) VALUES (2 ,'EA' );
INSERT INTO course (id,course_name) VALUES (3 ,'WAA' );
INSERT INTO course (id,course_name) VALUES (4 ,'WAP' );
INSERT INTO course (id,course_name) VALUES (5 ,'Algorithms' );
INSERT INTO course (id,course_name) VALUES (6 ,'ASD' );
INSERT INTO course (id,course_name) VALUES (7 ,'MPP' );
INSERT INTO course (id,course_name) VALUES (8 ,'FPP' );





--SECTIONs
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (1 ,1, 1,1,0,22);
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (2 ,1, 3,2,0,22);
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (3 ,2, 3,2,0,22);


--STUDENT SECTIONs
INSERT INTO student_sections (student_list_id,sections_id) VALUES (1 ,1);
INSERT INTO student_sections (student_list_id,sections_id) VALUES (2 ,1);
INSERT INTO student_sections (student_list_id,sections_id) VALUES (1 ,3);


