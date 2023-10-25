

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

select m.entryNo, H.hostel_id, H.hostel_name, m.rollNo, S.name, m.date_, s2.session_id , s2.start_date
from MessCancellations m
         inner join Student S on m.rollNo = S.roll
         inner join Hostel_registration Hr on m.hostelRegistrationId = Hr.hostel_registration_id
         inner join Hostel H on Hr.hostel_id = H.hostel_id
         inner join Session S2 on Hr.session = S2.session_id;
