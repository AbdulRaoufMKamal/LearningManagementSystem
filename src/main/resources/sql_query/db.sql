drop table account;
drop table file;
drop table access;
drop table file_status;
drop table course_file;
create table account(
	email varchar(50),
    password varchar(50),
    type varchar(50)
);

create table file(
	file_name varchar(50),
    file_data longblob,
    file_type varchar(50)
);

create table access(
	email varchar(50),
    file_name varchar(50)
);

create table file_status(
	email varchar(50),
    status varchar(50),
    file_name varchar(50)
);

create table course_file(
    course_name varchar(50),
    file_no int,
    file_name varchar(50)
);

insert into account values("root", "root", "admin");
insert into account values("user", "user", "user");


select * from file;
select * from access;
select * from file_status;
select * from course_file;

select course_file.course_name, course_file.file_no, file.file_name 
from course_file, file 
where file.file_name = course_file.file_name 
and file.file_type = "quiz";

select access.email, file.file_name, file_status.status from access, file, file_status where file.file_name = access.file_name and access.file_name = file_status.file_name and access.email = "user" and file.file_type = "quiz";

select access.email, file.file_name, file_status.status from access, file, file_status where file.file_name = access.file_name and access.file_name = file_status.file_name and access.email = "null" and file.file_type = "quiz"
