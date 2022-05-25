drop table account;
drop table file;
create table account(
	email varchar(50),
    password varchar(50),
    type varchar(50)
);

create table file(
	file_name varchar(50),
    file_data longblob
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