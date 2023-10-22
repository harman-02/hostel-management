

Insert into hostel values('1','Visweswaraya', 'IIT BHU HG');
Insert into hostel values('2','SC DEY','IIT BHU Lanka');

select * from hostel;
select * from User;
insert into student(name, email, phone, branch, balance, Dob) values('harman','harman@gmail.com', 90239, 'CSE', '15000', 20/02/2002);
insert into session(start_date) values ('2016-02-02');
Insert into User(username, password, role) values('admin1','1234', 'admin');
insert into hostel_registration(hostel_id, session) values (1,1);
insert into messcancellations(hostelRegistrationId, rollNo, date_) values (1,1, '2020-07-12');
insert into messcancellations(hostelRegistrationId, rollNo, date_) values (1,1, '2020-07-01');
select * from messcancellations;
update messcancellations set date_ = '2014-03-16 00:00:00.000'  where entryNo = 2;
