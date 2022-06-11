create database LMS;
use LMS;
CREATE TABLE Account (Username varchar(50), Password varchar(50), Type varchar(50));
CREATE TABLE Course (Course_name varchar(50), Lecturer varchar(50));
CREATE TABLE File (submission_id integer auto_increment, file_name varchar(50), file_data longblob, file_type varchar(50), Course_name varchar(50), Lecturer varchar(10), Username varchar(50), feedback varchar(100), file_status varchar(100), primary key(submission_id));
CREATE TABLE Taken_Courses (Username varchar(50), Course_name varchar(50));

-- INSERT statements
insert into account values ("19P4442","abdulraouf", "student");
insert into account values ("19P6458","abdulrahman", "student");
insert into account values ("19P8912","younes", "student");
insert into account values ("19P2435","ahmad", "student");
insert into account values ("Gamal","Gamal", "lecturer");
insert into account values ("Hoda","Hoda", "lecturer");
insert into account values ("Ammar","Ammar", "lecturer");
insert into account values ("Eslam","Eslam", "lecturer");

insert into course values ("Algorithms","Gamal");
insert into course values ("Database","Hoda");
insert into course values ("AI","Ammar");
insert into course values ("Compilers","Eslam");

insert into taken_courses values ("19P4442","Algorithms");
insert into taken_courses values ("19P4442","Database");
insert into taken_courses values ("19P4442","AI");
insert into taken_courses values ("19P4442","Compilers");
insert into taken_courses values ("19P6458","Database");
insert into taken_courses values ("19P8912","AI");
insert into taken_courses values ("19P2435","Compilers");




