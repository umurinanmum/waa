/*INSERT INTO session (start_Week, end_Week, start_Day,end_Day) VALUES
  ('1', '3', '1','3'),
  ('3', '4', '1','3');*/
INSERT INTO entry (ID , NAME) VALUES (1, 'February 2018');
INSERT INTO entry (ID , NAME) VALUES (2, 'April 2018');
INSERT INTO entry (ID , NAME) VALUES (3, 'August 2018');
INSERT INTO entry (ID , NAME) VALUES (4, 'October 2018');
INSERT INTO entry (ID , NAME) VALUES (5, 'February 2019');
INSERT INTO entry (ID , NAME) VALUES (6, 'April 2019');
INSERT INTO entry (ID , NAME) VALUES (7, 'August 2019');
INSERT INTO entry (ID , NAME) VALUES (8, 'October 2019');

INSERT INTO block (id,start_Date, end_Date,canceled_days,available_days) VALUES(1 ,'2017-08-04', '2017-08-04',0,22 );
INSERT INTO block (id,start_Date, end_Date,canceled_days,available_days) VALUES(2 ,'2019-01-04', '2019-02-03',1,21 );

INSERT INTO COURSE (id, course_name) VALUES (1, 'WAA');
INSERT INTO COURSE (id, course_name) VALUES (2, 'WAP');
INSERT INTO COURSE (id, course_name) VALUES (3, 'EA');


INSERT INTO FACULTY(id, first_name, last_name) VALUES (1, 'Tina', 'Xing');
INSERT INTO FACULTY(id, first_name, last_name) VALUES (2, 'Bruen', 'Bruen');
INSERT INTO FACULTY(id, first_name, last_name) VALUES (3, 'Steve', 'Nolle');


INSERT INTO SECTION (ID, BLOCK_ID, 	COURSE_ID, 	FACULTY_ID ) VALUES (1, 1, 1, 1);
INSERT INTO SECTION (ID, BLOCK_ID, 	COURSE_ID, 	FACULTY_ID ) VALUES (2, 2, 1, 1);

INSERT INTO student (id,barcode, student_Id, entry_id) VALUES(1 ,'123', '987012' ,1);
INSERT INTO student (id,barcode, student_Id, entry_id) VALUES(2 ,'456', '986814', 1);
INSERT INTO student (id,barcode, student_Id, entry_id) VALUES(3 ,'456', '986803' ,1);
INSERT INTO student (id,barcode, student_Id, entry_id) VALUES(4 ,'456', '986804', 2);


INSERT INTO credential (id,email, password,student_id) VALUES (1 ,'eozturk@mum.edu', '12345',1);
INSERT INTO credential (id,email, password,student_id) VALUES (2 ,'tinan@mum.edu', '12345',2);

INSERT INTO role (id,name) VALUES (1 ,'GENERATE_REPORT');
INSERT INTO role (id,name) VALUES (2 ,'SAVE_ENTRY');
INSERT INTO role (id,name) VALUES (3 ,'SAVE_BLOCK');



INSERT INTO credential_roles (credential_id,roles_id) VALUES  (1 ,1);

INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,1);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,2);
INSERT INTO credential_roles (credential_id,roles_id) VALUES  (2 ,3);


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
