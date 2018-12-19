SELECT * FROM student s;

show create table student;

desc student;

create table users
(
	userId int primary key AUTO_INCREMENT,
	username varchar(32),
	passwd varchar(32),
	email varchar(32),
	grade int
);

drop table users;

desc  users;

SELECT * FROM users u;
--MySQL数据库插入自增的id需要写NULL
insert into users values
(null,'admin','admin','admin@sohu.com',1);

insert into users values
(null,'admin2','admin2','admin2@sohu.com',1);

insert into users values
(null,'admin3','admin3','admin3@sohu.com',2);

insert into users values
(null,'admin4','admin4','admin4@sohu.com',2);

insert into users values
(null,'admin5','admin5','admin5@sohu.com',2);


insert into users values
(null,'admin6','admin','admin@sohu.com',1);

insert into users values
(null,'admin7','admin2','admin2@sohu.com',1);

insert into users values
(null,'admin8','admin3','admin3@sohu.com',2);

insert into users values
(null,'admin9','admin4','admin4@sohu.com',2);

insert into users values
(null,'admin10','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin11','admin','admin@sohu.com',1);

insert into users values
(null,'admin12','admin2','admin2@sohu.com',1);

insert into users values
(null,'admin13','admin3','admin3@sohu.com',2);

insert into users values
(null,'admin14','admin4','admin4@sohu.com',2);

insert into users values
(null,'admin15','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin16','admin','admin@sohu.com',1);

insert into users values
(null,'admin17','admin2','admin2@sohu.com',1);

insert into users values
(null,'admin18','admin3','admin3@sohu.com',2);

insert into users values
(null,'admin19','admin4','admin4@sohu.com',2);

insert into users values
(null,'admin20','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin21','admin','admin@sohu.com',1);

insert into users values
(null,'admin22','admin2','admin2@sohu.com',1);

insert into users values
(null,'admin23','admin3','admin3@sohu.com',2);

insert into users values
(null,'admin24','admin4','admin4@sohu.com',2);

insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);
insert into users values
(null,'admin25','admin5','admin5@sohu.com',2);

insert into users values
(null,'12admin25234','admin5','admin5@sohu.com',2);
insert into users values
(null,'234admin25234','admin5','admin5@sohu.com',2);
insert into users values
(null,'234admin25234','admin5','admin5@sohu.com',2);