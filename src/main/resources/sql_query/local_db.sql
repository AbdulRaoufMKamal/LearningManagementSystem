create database LMS;
use LMS;
CREATE TABLE Account (Username varchar(50), Password varchar(50), Type varchar(50));
CREATE TABLE Course (Course_name varchar(50), Lecturer varchar(50));
CREATE TABLE File (submission_id integer auto_increment, file_name varchar(50), file_data longblob, file_type varchar(50), Course_name varchar(50), Lecturer varchar(10), Username varchar(50), feedback varchar(100), file_status varchar(100), primary key(submission_id));
CREATE TABLE Taken_Courses (Username varchar(50), Course_name varchar(50));


-- View Courses for students
select course_name from taken_courses where username = ?;

-- Lecture & Assignment & Quiz Tables for students
select file_name from file where course_name = ? and file_type ="lecture";
select file_name from file where course_name = ? and file_type ="assignment";
select file_name from file where course_name = ? and file_type ="quiz";

-- Submit assignment & quiz for students
select lecturer from course where course.course_name = ?;
insert into file(file_name,file_data,file_type,course_name,lecturer,username) values (?,?,?,?,?,?);

-- Lecture Table for lecturers
select file_name from file where lecturer = ? and file_type ="lecture";

-- Assignment & Quiz Tables for lecturers
select username, file_name from file where file.lecturer = ? and file_type ="submit_assignment";
select username, file_name from file where file.lecturer = ? and file_type ="submit_quiz";

-- Upload Lecture & Assignment & Quiz
select Course_name from course where course.lecturer = ? limit 1;
insert into file(file_name,file_data,file_type,course_name,lecturer) values (?,?,?,?,?);

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

-- DROP statements
drop table Account;
drop table course;
drop table file;
drop table taken_courses;

-- SELECT statements
select * from file;

-- DELETE statements
delete from file;
delete from account;
delete from course;
delete from taken_course;

-- ALTER statement
alter table file add feedback varchar(100);

