/*INSERT INTO session (start_Week, end_Week, start_Day,end_Day) VALUES
  ('1', '3', '1','3'),
  ('3', '4', '1','3');*/

INSERT INTO block (id,start_Date, end_Date,canceled_days,available_days) VALUES(1 ,'2017-08-04', '2017-08-04',0,22 );

INSERT INTO student (id,barcode, student_Id) VALUES(1 ,'123', '987012' );
INSERT INTO student (id,barcode, student_Id) VALUES(2 ,'456', '986814' );


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