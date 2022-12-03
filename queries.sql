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
 
 create table trains (
 train_no int primary key,
 train_name varchar(50)
 );
 
 insert into trains values(100,'hampi express');
 insert into trains values(200,'kar express');
 insert into trains values(300,'kannada express');
 
 select * from trains;
 
 create table station(
 station_id int primary key,
 station_name varchar(50)
 );
 
 insert into station values(1,'maj');
 insert into station values(2,'ypr');
 insert into station values(3,'dev');
 insert into station values(4,'kar');
 
 select * from station;
 
 create table `100`(
 stop_no int,
 station_name varchar(30)
 );
 
 show tables;

 create table `200`(
 stop_no int,
 station_name varchar(30)
 );
 
 insert into `100` values (1,'ksr'),(2,'ypr'),(3,'yel'),(4,'ken'),(5,'dev');
 
 select * from `100`;
 
  insert into `200` values (1,'ypr'),(2,'ksr'),(3,'dd'),(4,'ken'),(5,'chi');
 
 select * from `200`;
 
 select train_no from trains where exists(select * from `100` where station_name in ('yrp','dev'));

select exists(select * from `100` where station_name='ypr' and 'dev');

SELECT * FROM `100` WHERE CONTAINS (station_name,'ypr');

SELECT * FROM `100`
WHERE station_name = 'ypr'
  AND station_name = 'dev';