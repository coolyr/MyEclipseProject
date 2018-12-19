--建立学生表(Oracle)
create table student
(sid number primary key , --这是学生的学号
sname varchar2(45) not null, --学生姓名
ssex  char(2) not null,--性别
sdept varchar2(10) not null, --所在系
sage  number(3) ,--年龄
saddress varchar2(45) --住址
)
--建立学生表(MySQL)
create table student
(sid int primary key ,
sname varchar(45) not null,
ssex  char(2) not null,
sdept varchar(10) not null,
sage  int,
saddress varchar(45)
)
desc student;
select * from student;
--学生表中的数据
insert into student values(20040001,'林青霞','F','计算机系',22,'上海');
insert into student values(20040002,'刘德华','M','外语系',23,'南京');
insert into student values(20050003,'成龙','M','化学系',21,'山东');
insert into student values(20050004,'林可欣','F','计算机系',22,'北京');
insert into student values(20050005,'周华健','M','生物系',24,'山东');
insert into student values(20050006,'周润发','M','数学系',20,'湖北');

--建立课程表(Oracle)
create table course
(
cid number primary key ,--这是课程号
cname varchar2(50) not null,--课程名
ccredit number(3) --课程学分
)

--建立课程表(MySQL)
create table course
(
cid int primary key,
cname varchar(50) not null,
ccredit int
)

desc course;

select * from course;


insert into course values(11,'java编程',6);
insert into course values(21,'c++课程',4);
insert into course values(31,'oracle',3);
insert into course values(41,'javaEE',100);
insert into course values(51,'linux',1);


--建立选课表(Oracle)
create table studCourse
(
stuCourseId number primary key ,--这是一个自增的,表示一次选课
sid number  references student(sid),--学生号
cid number references course(cid),--课程号
grade number not null--成绩
)
--建立选课表(MySQL)
create table studCourse
(
stuCourseId int primary key AUTO_INCREMENT,
sid int  references student(sid),
cid int references course(cid),
grade float not null,
CONSTRAINT `FK_studCourse_student` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
CONSTRAINT `FK_studCourse_course` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
)

drop table studCourse;
desc studCourse;
show create table studCourse;
select * from studCourse;

--初始化数据(Oracle)
insert into studCourse values(stucourse_seq.nextval,20040001,11,90);
insert into studCourse values(stucourse_seq.nextval,20040001,21,19);
insert into studCourse values(stucourse_seq.nextval,20050003,21,45);
insert into studCourse values(stucourse_seq.nextval,20050004,41,99);
insert into studCourse values(stucourse_seq.nextval,20050006,11,39);
--初始化数据(MySQL)
insert into studCourse values(NULL,20040001,11,90);
insert into studCourse values(NULL,20040001,21,19);
insert into studCourse values(NULL,20050003,21,45);
insert into studCourse values(NULL,20050004,41,99);
insert into studCourse values(NULL,20050006,11,39);




--为完成一个相当完整的学生选课系统，我们重新建表，和前面的有些区别呀

--系统登录表(包括所有的可以登录到系统的成员(包括学生和管理员))[可以添加学生信息和课程信息]

create table login
(loginId number primary key,--登录的编号
 loginPass varchar2(50) not null,--登录的密码
 grade number(1) not null--不同的级别不一样,比如论坛就分 版主，管理员,副版主..(这里规定1:表示超级管理员,9表示是学生)
 ) 

insert into login values(10000,'10000',1);
--添加老师表[老师可以给选自己课程的学生成绩进行管理]--这里肯定需要大家对上面的表进行一个改进




--建立学生表
create table student
(sid number primary key , --这是学生的学号
sname varchar2(45) not null, --学生姓名
ssex  char(2) not null,--性别
sdept varchar2(10) not null, --所在系
sage  number(3) ,--年龄
saddress varchar2(45) --住址
)

--建立老师表
create table teacher
(tid number primary key,--这是老师的学号
tname varchar2(40) not null,--老师的名字
sex  char(2) ,--老师的性别
age number(3),--老师的年龄
intro varchar2(3000) --老师的介绍
)


--建立课程表
create table course
(
cid number primary key ,--这是课程号
cname varchar2(50) not null,--课程名
ccredit number(3), --课程学分
tid number references teacher(tid)--授课老师编号.
)


--建立选课表
create table studCourse
(
stuCourseId number primary key ,--这是一个自增的,表示一次选课
sid number  references student(sid),--学生号
cid number references course(cid),--课程号
tid number references teacher(tid),--授课老师编号
grade number not null--成绩
)


