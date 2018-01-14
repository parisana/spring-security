alter table user alter column id restart with 10;
alter table user_authority alter column id restart with 10;

insert into user(id,email,password,first_name,last_name, account_non_expired, enabled, account_non_locked, credentials_non_expired) values (0,'rob@example.com','password','Rob','Winch', true, true, true, true);
insert into user(id,email,password,first_name,last_name, account_non_expired, enabled, account_non_locked, credentials_non_expired) values (1,'joe@example.com','password','Joe','Grandja', true, true, true, true);
insert into user(id,email,password,first_name,last_name, account_non_expired, enabled, account_non_locked, credentials_non_expired) values (2,'a@example.com','password','Apple','Head', true, true, true, true);
insert into user(id,email,password,first_name,last_name, account_non_expired, enabled, account_non_locked, credentials_non_expired) values (3,'pari@example.com','password','Pari','Ngangom', true, true, true, true);
insert into user(id,email,password,first_name,last_name, account_non_expired, enabled, account_non_locked, credentials_non_expired) values (4,'admin@example.com','password','Messaging','Admin', true, true, true, true);

insert into user_authority(id,user_id,authority) values (0,0,'ROLE_USER');
insert into user_authority(id,user_id,authority) values (1,1,'ROLE_USER');
insert into user_authority(id,user_id,authority) values (2,2,'ROLE_USER');
insert into user_authority(id,user_id,authority) values (3,3,'ROLE_USER');
insert into user_authority(id,user_id,authority) values (4,4,'ROLE_USER');
insert into user_authority(id,user_id,authority) values (5,4,'ROLE_ADMIN');

insert into message(id,created,to_id,from_id,summary,text) values (100,'2017-09-20 08:00:00',0,1,'Hello Rob','This message is for Rob');
insert into message(id,created,to_id,from_id,summary,text) values (101,'2017-09-20 09:00:00',0,3,'Hello Rob','This message is for Rob');
insert into message(id,created,to_id,from_id,summary,text) values (102,'2017-09-20 09:10:00',0,2,'Hello Rob','This message is for Rob');
insert into message(id,created,to_id,from_id,summary,text) values (103,'2017-09-20 10:00:00',0,1,'How are you Rob?','This message is for Rob');
insert into message(id,created,to_id,from_id,summary,text) values (104,'2017-09-21 14:00:00',0,1,'Is this secure?','This message is for Rob');

insert into message(id,created,to_id,from_id,summary,text) values (110,'2017-09-21 10:00:00',1,0,'Hello Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (111,'2017-09-21 10:00:00',1,2,'Hello Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (112,'2017-09-21 10:00:00',1,3,'Hello Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (113,'2017-09-21 20:00:00',1,0,'Greetings Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (114,'2017-09-21 20:00:00',1,2,'Greetings Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (115,'2017-09-21 20:00:00',1,3,'Greetings Joe','This message is for Joe');
insert into message(id,created,to_id,from_id,summary,text) values (116,'2017-09-24 14:00:00',1,0,'Is this secure?','This message is for Joe');

insert into message(id,created,to_id,from_id,summary,text) values (120,'2017-11-15 10:00:00',3,0,'Hello Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (121,'2017-11-11 11:00:00',3,2,'Hello Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (122,'2017-11-09 09:00:00',3,1,'Hello Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (123,'2017-11-18 08:00:00',3,0,'Greetings Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (124,'2017-11-22 12:00:00',3,2,'Greetings Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (125,'2017-11-23 15:00:00',3,1,'Greetings Pari','This message is for Pari');
insert into message(id,created,to_id,from_id,summary,text) values (126,'2017-11-24 14:00:00',3,0,'Is this secure?','This message is for Pari');

insert into message(id,created,to_id,from_id,summary,text) values (130,'2017-09-21 10:00:00',2,0,'Hello Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (131,'2017-09-21 11:00:00',2,1,'Hello Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (132,'2017-09-21 09:00:00',2,3,'Hello Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (133,'2017-09-21 20:00:00',2,0,'Greetings Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (134,'2017-09-21 17:00:00',2,1,'Greetings Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (135,'2017-09-21 13:00:00',2,3,'Greetings Apple','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (136,'2017-11-24 19:00:00',2,0,'Is this secure?','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (137,'2017-12-24 18:00:00',2,1,'Is this secure?','This message is for Apple');
insert into message(id,created,to_id,from_id,summary,text) values (138,'2018-01-01 20:00:00',2,3,'Is this secure?','This message is for Apple');

insert into message(id,created,to_id,from_id,summary,text) values (300,'2017-09-22 02:00:00',2,0,'Hello Admin','This message is for Admin');
insert into message(id,created,to_id,from_id,summary,text) values (301,'2017-09-22 08:00:00',2,1,'Greetings Admin','This message is for Admin');


