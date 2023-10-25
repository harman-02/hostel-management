use ${MYSQL_DATABASE};

show tables;

CREATE TABLE IF NOT EXISTS User (
                                    username varchar(55) PRIMARY KEY,
                                    password VARCHAR(255) NOT NULL,
                                    role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Hostel (
                                      hostel_id INT AUTO_INCREMENT PRIMARY KEY,
                                      hostel_name VARCHAR(255),
                                      hostel_address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Session (
                                     session_id INT AUTO_INCREMENT PRIMARY KEY,
                                     start_date Date
);

CREATE TABLE IF NOT EXISTS Hostel_registration (
                                                   hostel_registration_id INT AUTO_INCREMENT PRIMARY KEY,
                                                   hostel_id INT,
                                                   session INT,
                                                   FOREIGN KEY (hostel_id) REFERENCES Hostel(hostel_id) ON DELETE CASCADE ,
                                                   FOREIGN KEY (session) REFERENCES Session(session_id)  ON DELETE SET NULL
);



# CREATE TABLE IF NOT EXISTS HostelFacilities (
#                                                 facility_id INT AUTO_INCREMENT PRIMARY KEY,
#                                                 hostel_registration_id INT,
#                                                 Description VARCHAR(255),
#                                                 Condition_status INT,
#                                                 FOREIGN KEY (hostel_registration_id) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE SET NULL
# );

CREATE TABLE IF NOT EXISTS Room (
    hostelId int,
    roomNo int,
    primary key (hostelId, roomNo),
    foreign key (hostelId) references Hostel(hostel_id) on delete set null
);
drop table Student;
CREATE TABLE IF NOT EXISTS Student (
                                       roll INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255),
                                       email VARCHAR(255),
                                       phone INT,
                                       branch VARCHAR(255),
                                       balance INT,
                                       Dob INT
);

CREATE TABLE IF NOT EXISTS StudentUserMapping (
    username varchar(55) primary key,
    rollNo int,
    hostelRegistrationId int,
    foreign key (hostelRegistrationId) references Hostel_registration(hostel_registration_id) on delete set null,
    foreign key (username) references User(username) on delete cascade,
    foreign key (rollNo) references Student(roll) on delete cascade
);

CREATE TABLE IF NOT EXISTS RoomRegistration (
                                    hostel_registration_id INT NOT NULL,
                                    roomNo INT NOT NULL,
                                    rollNo int,
                                    PRIMARY KEY (hostel_registration_id, roomNo),
                                    FOREIGN KEY (hostel_registration_id) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE CASCADE,
                                    foreign key (rollNo) REFERENCES Student(roll) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Complaint (
                                        complaint_id INT AUTO_INCREMENT PRIMARY KEY,
                                        hostel_registration_id INT NOT NULL,
                                        RollNo INT,
                                        Description VARCHAR(255),
                                        TimeStamp TIMESTAMP,
                                        Status VARCHAR(255),
                                        FOREIGN KEY (hostel_registration_id) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE CASCADE,
                                        FOREIGN KEY (RollNo) REFERENCES Student(Roll) ON DELETE SET NULL

);

CREATE TABLE IF NOT EXISTS Notice (
                                      notice_id INT AUTO_INCREMENT PRIMARY KEY,
                                      hostel_registration_id INT,
                                      Description VARCHAR(255),
                                      date DATETIME,
                                      FOREIGN KEY (hostel_registration_id) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Employee (
                                        employee_id INT AUTO_INCREMENT PRIMARY KEY,
                                        employee_name CHAR(255),
                                        employee_type VARCHAR(255),
                                        employee_details VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS EmployeeUserMapping (
    username varchar(55) primary key,
    employee_id int,
    hostelRegistrationId int,
    foreign key (hostelRegistrationId) references Hostel_registration(hostel_registration_id) on delete set null,
    foreign key (username) references User(username) on delete cascade,
    foreign key (employee_id) references Employee(employee_id) on delete cascade
);

CREATE TABLE IF NOT EXISTS EmployeeTypeSalary (
    employeeType int,
    salary int,
    foreign key (employeeType) references Employee(employee_type) on delete set null
);
# CREATE TABLE IF NOT EXISTS Mess (
#                                     entry_no INT AUTO_INCREMENT PRIMARY KEY,
#                                     Roll INT,
#                                     Date DATETIME,
#                                     Slot INT,
#                                     Cost INT,
#                                     FOREIGN KEY (Roll) REFERENCES Student(Roll) ON DELETE SET NULL
# );


CREATE TABLE IF NOT EXISTS MessCancellations (
    entryNo INT AUTO_INCREMENT PRIMARY KEY,
    hostelRegistrationId int,
    rollNo int,
    date_ date,
    foreign key (rollNo) references Student(roll) on delete set null,
    FOREIGN KEY (hostelRegistrationId) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Transaction (
   transaction_id INT AUTO_INCREMENT PRIMARY KEY,
   Roll INT,
   amount INT,
   date DATETIME,
   description VARCHAR(255),
   FOREIGN KEY (Roll) REFERENCES Student(Roll) ON DELETE SET NULL
);

# CREATE TABLE IF NOT EXISTS Visitor (
#                                        visitor_id INT AUTO_INCREMENT PRIMARY KEY,
#                                        hostel_registration_id INT,
#                                        visitor_name VARCHAR(255),
#                                        visitor_purpose VARCHAR(255),
#                                        entry_time INT,
#                                        exit_time INT,
#                                        FOREIGN KEY (hostel_registration_id) REFERENCES Hostel_registration(hostel_registration_id) ON DELETE CASCADE
# );

