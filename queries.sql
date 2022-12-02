create database irctc;

use irctc;

create table user(
user_name varchar(50) primary key,
first_name varchar(20),
last_name varchar(20),
gender varchar(10),
address varchar(100),
nationality varchar(10),
dob varchar(20),
phone varchar(15)
);

create table user_login(
user_name varchar(50),
password varchar(20),
email varchar(50),
foreign key (user_name) references user(user_name)
);

insert into user(user_name,first_name,last_name,gender,address,nationality,dob,phone)
 values ('suchith','suchith','kumar','male','al;kd','indian',';','k;asflk');
 
 insert into user_login(user_name,password,email) values('suchith','s','adsf');
 
 select * from user;
 select * from user_login;
 
 SELECT COUNT(user_name) FROM user_login WHERE user_name='suchith' and password='s';
