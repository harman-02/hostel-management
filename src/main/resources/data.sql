

Insert into hostel values("1","Visweswaraya","IIT BHU HG");
Insert into hostel values("2","SC DEY","IIT BHU Lanka");


Insert into User values("admin1","1234","admin");


show tables;
select * from session;
select * from hostel;
select * from User;
select * from hostel_registration;
select * from student;
select * from studentusermapping;
select * from notice;
ALTER TABLE student
MODIFY COLUMN phone VARCHAR(15);

drop table notice;