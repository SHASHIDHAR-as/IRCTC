-- create our irctc database
create database irctc;

-- set it as active
use irctc;

-- user table for storing user details 
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

-- user_login for storing login data 
create table user_login(
user_name varchar(50),
password varchar(20),
email varchar(50),
foreign key (user_name) references user(user_name)
);

-- insert values in user table
insert into user(user_name,first_name,last_name,gender,address,nationality,dob,phone)
 values ('suchith','suchith','kumar','male','al;kd','indian',';','k;asflk');
 
 -- insert values in user_login table
 insert into user_login(user_name,password,email) values('suchith','s','adsf');
 
 -- fetch user details
 select * from user;
 
 -- fetch user_login details
 select * from user_login;
 
 -- query to check whether user can login or not
 SELECT COUNT(user_name) FROM user_login WHERE user_name='suchith' and password='s';
 
 -- table to store train details
 create table trains (
 train_no int primary key,
 train_name varchar(50),
 seats_available int
 );
 
 -- insert values into train table
 insert into trains values(100,'hampi express',10);
 insert into trains values(200,'kar express',0);
 insert into trains values(300,'kannada express',40);
  update  trains set seats_available=40 where train_no =200;
 select * from trains;
 use irctc;
 drop table trains;
 -- table to store station details
 create table station(
 station_id char(3) primary key,
 station_name varchar(50)
 );
 
 -- insert values in station table
 insert into station values('ksr','ksr bengaluru');
 insert into station values('ypr','yeshwantpur');
 insert into station values('dev','devanahalli');
 insert into station values('yel','yelahanka');
 insert into station values('ken','kengeri');
 insert into station values('bay','bellary');
 insert into station values('sol','solapur');
 insert into station values('del','delhi');
 insert into station values('chi','chintamani');
 
 select * from station;
 
 -- create table for each train_no
  create table `100`(
  train_no int ,
 stop_no int,
 station_id varchar(30),
 time varchar(20),
 cost int ,
 foreign key (station_id) references station(station_id),
 foreign key (train_no) references trains(train_no)
 );
 
 create table `200`(
 train_no int ,
 stop_no int,
 station_id varchar(30),
 time varchar(20),
 cost int,
 foreign key (station_id) references station(station_id),
  foreign key (train_no) references trains(train_no)
 );
 select *from `200`;
 
 
  create table `300`(
 train_no int ,
 stop_no int,
 station_id varchar(30),
 time varchar(20),
 cost int,
 foreign key (station_id) references station(station_id),
  foreign key (train_no) references trains(train_no)
 );
 
 -- insert values for each train
 
 insert into `100` values 
 (100,1,'ksr','1:00',0),
 (100,2,'ypr','2:00',10),
 (100,3,'yel','3:00',20),
 (100,4,'ken','4:00',30),
 (100,5,'dev','5:00',40),
 (100,6,'bay','6:00',50);
 
 select * from `100`;
 
  insert into `200` values 
  (200,1,'ypr','1:00',0),
  (200,2,'chi','1:00',10),
  (200,3,'bay','1:00',20),
  (200,4,'sol','1:00',30),
  (200,5,'del','1:00',40);
 use irctc;
 select * from `200`;
 
 insert into `300` values 
 (300,1,'del','1:00',0),
 (300,2,'sol','1:00',10),
 (300,3,'bay','1:00',20),
 (300,4,'chi','1:00',30),
 (300,5,'ypr','1:00',40);
 
 select * from `300`;
 
-- create tupes of source and destination
 select t1.station_id,t2.station_id
 from `100` as t1
 cross join `100` as t2
 where
 t1.stop_no < t2.stop_no;
 
 -- select train_no where source and destination mathces
select t.train_no from (select t1.train_no,t1.station_id as source,t2.station_id as destination
 from `100` as t1
 cross join `100` as t2
 where
 t1.stop_no < t2.stop_no) as t where t.source='ypr' and t.destination='yel';

-- select train_no which travels from the given source to destination
select train_no,train_name
 from trains where trains.train_no= 
(select t.train_no from 
(select t1.train_no,t1.station_id as source,t2.station_id as destination 
from `200` as t1 
cross join `200` as t2 
where t1.stop_no < t2.stop_no) as t 
where t.source='ypr' and t.destination='sol');

-- create a schedule table 
create table schedule(
train_no int,
Monday char(1),
Tuesday char(1),
Wednesday char(1),
Thursday char(1),
Friday char(1),
Saturday char(1),
Sunday char(1),
foreign key (train_no) references trains(train_no)
);

insert into schedule values
(100,'y','y','y','y','y','n','n'),
(200,'n','y','y','n','y','n','n'),
(300,'y','n','y','n','n','y','y');

select * from schedule;
drop table schedule;

select * from searching;

-- get time and cost of travel
select t1.time,t2.time , (t2.cost-t1.cost) from `200` as t1,`200` as t2
where t1.station_id='ypr' and t2.station_id='sol';

select distinct(t1.time) as arrival,t2.time as destination, (t2.cost-t1.cost) as cost 
from (`200` as t1,`200` as t2) inner join schedule
on t1.station_id='ypr' and t2.station_id='sol' and t1.stop_no<t2.stop_no
and schedule.train_no=200 and schedule.wednesday='y';

create table Passenger(
Name varchar(50),
Age varchar(10),
Gender varchar(20),
Pnr_num varchar(10));

SET SQL_SAFE_UPDATES = 0;
delete from Passenger;
select *from Passenger; 
drop table passenger;

create table pnr_status(
pnr_no varchar(10) primary key,
train_no int, 
train_name varchar(20),
from_station varchar(20),
to_station varchar(20),
foreign key (train_no) references trains(train_no)
);

drop  table pnr_status;

insert into pnr_status values(4565644,100,'hampi','ksr','ypr');
select *from pnr_status;
select *from pnr_status where pnr_no=2279320292;
select *from user where user_name='shashi';
select user_name ,concat(first_name,last_name) as Name,gender,address,nationality,dob,phone from user where user_name='shashi';
desc user;
show tables;

create table bookings(
booking_id int primary key,
pnr_no varchar(10),
user_name varchar(50),
date varchar(50),
ticket_cost int,
foreign key (user_name) references user_login(user_name),
foreign key(pnr_no) references pnr_status(pnr_no)
);
drop table bookings;
select *from pnr_status;
desc pnr_status;
desc passenger;
select Name ,age,gender from passenger where pnr_num=2413536473;
select *from `200`;
show tables;
select *from bookings;

use irctc;
create table admin(
login_id varchar(10) primary key,
first_name varchar(20),
last_name varchar(20),
gender varchar(10),
address varchar(100)
);
insert into admin values('11111','shashidhar','as','male','aa');
insert into admin values('22222','suchith','kumar','male','aa');
insert into admin values('33333','suchith','kumar','male','aa');
insert into admin values('44444','suchith','kumar','male','aa');

create table admin_login(
login_id varchar(10) primary key,
password varchar(20),
email_id varchar(50),
foreign key (login_id) references admin(login_id)
);
insert into admin_login values(11111,'12345','shashidhar.shivaraj715@gmail.com');
insert into admin_login values(22222,'12345','suchithkumar2910@gmail.com');
insert into admin_login values(33333,'12345','suchithkumaryt@gmail.com');
insert into admin_login values(44444,'12345','sukanyam287@gmail.com');
