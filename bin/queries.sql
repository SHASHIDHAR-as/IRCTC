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
 
 select * from trains;
 
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
from `100` as t1 
cross join `100` as t2 
where t1.stop_no < t2.stop_no) as t 
where t.source='ypr' and t.destination='ken');

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
(100,'n','y','y','n','y','n','n'),
(100,'y','n','y','n','n','y','y');

select * from schedule;

select * from searching;

-- get time and cost of travel
select t1.time,t2.time , (t2.cost-t1.cost) from `100` as t1,`100` as t2
where t1.station_id='ypr' and t2.station_id='ken';


create table Passenger(
Name varchar(50),
Age varchar(10),
Gender varchar(20),
Pnr_num numeric(10));

SET SQL_SAFE_UPDATES = 0;
delete from Passenger;
select *from Passenger;
drop table passenger;