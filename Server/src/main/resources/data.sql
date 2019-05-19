/*INSERT INTO session (start_Week, end_Week, start_Day,end_Day) VALUES
  ('1', '3', '1','3'),
  ('3', '4', '1','3');*/
INSERT INTO entry (id,name) VALUES (1,'2016-November');
INSERT INTO entry (id,name) VALUES (2,'2016-December');
INSERT INTO entry (ID , NAME) VALUES (3, 'February 2018');
INSERT INTO entry (ID , NAME) VALUES (4, 'April 2018');
INSERT INTO entry (ID , NAME) VALUES (5, 'August 2018');
INSERT INTO entry (ID , NAME) VALUES (6, 'October 2018');
INSERT INTO entry (ID , NAME) VALUES (7, 'February 2019');
INSERT INTO entry (ID , NAME) VALUES (8, 'April 2019');
INSERT INTO entry (ID , NAME) VALUES (9, 'August 2019');
INSERT INTO entry (ID , NAME) VALUES (10, 'October 2019');


INSERT INTO block (id,start_Date, end_Date,canceled_days,available_days) VALUES(1 ,'2017-08-04', '2017-08-04',0,22 );
INSERT INTO block (id,start_Date, end_Date,canceled_days,available_days) VALUES(2 ,'2019-01-04', '2019-02-03',1,21 );

--BLOCKs
INSERT INTO block (id,start_Date,end_Date,name) VALUES(1 ,'2016-10-01', '2016-10-30','2016-October' );
INSERT INTO block (id,start_Date, end_Date,name) VALUES(2 ,'2016-11-01', '2016-11-30','2016-November' );
INSERT INTO block (id,start_Date, end_Date,name) VALUES (3,'2016-12-01', '2016-12-30','2016-December' );

--COURSEs
INSERT INTO course (id,course_name) VALUES (1 ,'MWA' );
INSERT INTO course (id,course_name) VALUES (2 ,'EA' );
INSERT INTO course (id,course_name) VALUES (3 ,'WAA' );
INSERT INTO course (id,course_name) VALUES (4 ,'WAP' );
INSERT INTO course (id,course_name) VALUES (5 ,'Algorithms' );
INSERT INTO course (id,course_name) VALUES (6 ,'ASD' );
INSERT INTO course (id,course_name) VALUES (7 ,'MPP' );
INSERT INTO course (id,course_name) VALUES (8 ,'FPP' );


INSERT INTO FACULTY(id, first_name, last_name) VALUES (1, 'Tina', 'Xing');
INSERT INTO FACULTY(id, first_name, last_name) VALUES (2, 'Bruen', 'Bruen');
INSERT INTO FACULTY(id, first_name, last_name) VALUES (3, 'Steve', 'Nolle');


-- INSERT INTO SECTION (ID, BLOCK_ID, 	COURSE_ID, 	FACULTY_ID ) VALUES (1, 1, 1, 1);
-- INSERT INTO SECTION (ID, BLOCK_ID, 	COURSE_ID, 	FACULTY_ID ) VALUES (2, 2, 1, 1);

--SECTIONs
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (1 ,1, 1,1,0,22);
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (2 ,1, 3,2,0,22);
INSERT INTO section (id,block_id, course_id,faculty_id,canceled_days,available_days) VALUES (3 ,2, 3,1,0,22);


--STUDENTs

INSERT INTO student (id,barcode, student_Id,entry_id,first_name,last_name) VALUES(1 ,'7888', '987012',1,'Eren','Ozturk' );
INSERT INTO student (id,barcode, student_Id,entry_id,first_name,last_name) VALUES(2 ,'456', '986814',1,'Umur','Inan' );

INSERT INTO student (id,barcode, student_Id, entry_id,first_name,last_name) VALUES(3 ,'123', '987012' ,1,'Tuy','Vo');
INSERT INTO student (id,barcode, student_Id, entry_id,first_name,last_name) VALUES(4 ,'456', '986814', 1,'Tuugii','Tuugii');
INSERT INTO student (id,barcode, student_Id, entry_id,first_name,last_name) VALUES(5 ,'456', '986803' ,1,'Tony','Mar');
INSERT INTO student (id,barcode, student_Id, entry_id,first_name,last_name) VALUES(6 ,'456', '986804', 2,'Mick','Jupi');


--FACULTYs
INSERT INTO faculty (id,first_name,last_name) VALUES (1 ,'Asaad','Saad' );
INSERT INTO faculty (id,first_name,last_name) VALUES (2 ,'Tina','Xing' );

--CREDENTIALs
INSERT INTO credential (id,email, password,student_id) VALUES (1 ,'eozturk@mum.edu', '12345',1);
INSERT INTO credential (id,email, password,student_id) VALUES (2 ,'tinan@mum.edu', '12345',2);

INSERT INTO credential (id,email, password,faculty_id) VALUES (3 ,'asaad@mum.edu', '12345',1);
INSERT INTO credential (id,email, password,faculty_id) VALUES (4 ,'tina@mum.edu', '12345',2);

INSERT INTO credential (id,email, password,student_id) VALUES (5 ,'tvo@mum.edu', '12345',3);
INSERT INTO credential (id,email, password,student_id) VALUES (6 ,'tuugii@mum.edu', '12345',4);

--ROLEs
INSERT INTO role (id,name) VALUES (1 ,'GENERATE_REPORT');
INSERT INTO role (id,name) VALUES (2 ,'SAVE_ENTRY');
INSERT INTO role (id,name) VALUES (3 ,'SAVE_BLOCK');
INSERT INTO role (id,name) VALUES (4 ,'VIEW_BLOCK_REPORT');
INSERT INTO role (id,name) VALUES (5 ,'VIEW_ENTRY_REPORT');
INSERT INTO role (id,name) VALUES (6 ,'VIEW_EXTRA_CREDIT_REPORT');
INSERT INTO role (id,name) VALUES (7 ,'DATA_IMPORT');


--CREDENTIAL ROLEs
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (1 ,1);

INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,1);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,2);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,3);

INSERT INTO credential_roles (credential_id,roles_id) VALUES  (3 ,6);

INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,6);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,5);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,4);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,3);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,2);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (4 ,1);



/*  INSERT INTO entry (id,start_Date, end_Date,canceled_days) VALUES
  (1,'2017-08-04', '2017-08-04',0);*/

INSERT INTO TM_CHECK_AND_RETREAT (ID, LOCAL_DATE_TIME, RETREAT, STUDENT_ID   )
VALUES (1, '2018-08-04', FALSE , 1);
INSERT INTO TM_CHECK_AND_RETREAT (ID, LOCAL_DATE_TIME, RETREAT, STUDENT_ID   )
VALUES (2, '2018-09-04',FALSE, 1);
INSERT INTO TM_CHECK_AND_RETREAT (ID, LOCAL_DATE_TIME, RETREAT, STUDENT_ID   )
VALUES (3, '2018-10-04', FALSE, 1);
INSERT INTO TM_CHECK_AND_RETREAT (ID, LOCAL_DATE_TIME, RETREAT, STUDENT_ID   )
VALUES (4, '2018-11-04',true, 1);

INSERT INTO TM_CHECK_AND_RETREAT (ID, LOCAL_DATE_TIME, RETREAT, STUDENT_ID   )
VALUES (5, '2018-08-10', 0, 2);


--STUDENT SECTIONs
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (1, 1);
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (2, 1);
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (3, 1);
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (4, 1);
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (1, 2);
INSERT INTO STUDENT_SECTIONS (STUDENT_ID, SECTIONS_ID  ) VALUES (2, 2);


INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (1, '2018-08-04', 1, 1);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (2, '2018-08-05', 1, 1);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (3, '2018-08-06', 1, 1);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (4, '2018-08-07', 1, 1);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (5, '2018-08-08', 1, 1);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (6, '2018-08-04', 1, 2);
INSERT INTO ATTENDANCE (ID, DATE_TIME,	BLOCK_ID, STUDENT_ID  )  VALUES (7, '2018-08-05', 1, 2);



--LOCATIONs
INSERT INTO location (id,location_name,location_code) VALUES (1 ,'Dolby Hall','DB' );
INSERT INTO location (id,location_name,location_code) VALUES (2 ,'Art Center','AC' );

