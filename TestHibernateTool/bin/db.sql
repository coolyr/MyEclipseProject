--����ѧ����(Oracle)
create table student
(sid number primary key , --����ѧ����ѧ��
sname varchar2(45) not null, --ѧ������
ssex  char(2) not null,--�Ա�
sdept varchar2(10) not null, --����ϵ
sage  number(3) ,--����
saddress varchar2(45) --סַ
)
--����ѧ����(MySQL)
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
--ѧ�����е�����
insert into student values(20040001,'����ϼ','F','�����ϵ',22,'�Ϻ�');
insert into student values(20040002,'���»�','M','����ϵ',23,'�Ͼ�');
insert into student values(20050003,'����','M','��ѧϵ',21,'ɽ��');
insert into student values(20050004,'�ֿ���','F','�����ϵ',22,'����');
insert into student values(20050005,'�ܻ���','M','����ϵ',24,'ɽ��');
insert into student values(20050006,'����','M','��ѧϵ',20,'����');

--�����γ̱�(Oracle)
create table course
(
cid number primary key ,--���ǿγ̺�
cname varchar2(50) not null,--�γ���
ccredit number(3) --�γ�ѧ��
)

--�����γ̱�(MySQL)
create table course
(
cid int primary key,
cname varchar(50) not null,
ccredit int
)

desc course;

select * from course;


insert into course values(11,'java���',6);
insert into course values(21,'c++�γ�',4);
insert into course values(31,'oracle',3);
insert into course values(41,'javaEE',100);
insert into course values(51,'linux',1);


--����ѡ�α�(Oracle)
create table studCourse
(
stuCourseId number primary key ,--����һ��������,��ʾһ��ѡ��
sid number  references student(sid),--ѧ����
cid number references course(cid),--�γ̺�
grade number not null--�ɼ�
)
--����ѡ�α�(MySQL)
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

--��ʼ������(Oracle)
insert into studCourse values(stucourse_seq.nextval,20040001,11,90);
insert into studCourse values(stucourse_seq.nextval,20040001,21,19);
insert into studCourse values(stucourse_seq.nextval,20050003,21,45);
insert into studCourse values(stucourse_seq.nextval,20050004,41,99);
insert into studCourse values(stucourse_seq.nextval,20050006,11,39);
--��ʼ������(MySQL)
insert into studCourse values(NULL,20040001,11,90);
insert into studCourse values(NULL,20040001,21,19);
insert into studCourse values(NULL,20050003,21,45);
insert into studCourse values(NULL,20050004,41,99);
insert into studCourse values(NULL,20050006,11,39);




--Ϊ���һ���൱������ѧ��ѡ��ϵͳ���������½�������ǰ�����Щ����ѽ

--ϵͳ��¼��(�������еĿ��Ե�¼��ϵͳ�ĳ�Ա(����ѧ���͹���Ա))[��������ѧ����Ϣ�Ϳγ���Ϣ]

create table login
(loginId number primary key,--��¼�ı��
 loginPass varchar2(50) not null,--��¼������
 grade number(1) not null--��ͬ�ļ���һ��,������̳�ͷ� ����������Ա,������..(����涨1:��ʾ��������Ա,9��ʾ��ѧ��)
 ) 

insert into login values(10000,'10000',1);
--������ʦ��[��ʦ���Ը�ѡ�Լ��γ̵�ѧ���ɼ����й���]--����϶���Ҫ��Ҷ�����ı�����һ���Ľ�




--����ѧ����
create table student
(sid number primary key , --����ѧ����ѧ��
sname varchar2(45) not null, --ѧ������
ssex  char(2) not null,--�Ա�
sdept varchar2(10) not null, --����ϵ
sage  number(3) ,--����
saddress varchar2(45) --סַ
)

--������ʦ��
create table teacher
(tid number primary key,--������ʦ��ѧ��
tname varchar2(40) not null,--��ʦ������
sex  char(2) ,--��ʦ���Ա�
age number(3),--��ʦ������
intro varchar2(3000) --��ʦ�Ľ���
)


--�����γ̱�
create table course
(
cid number primary key ,--���ǿγ̺�
cname varchar2(50) not null,--�γ���
ccredit number(3), --�γ�ѧ��
tid number references teacher(tid)--�ڿ���ʦ���.
)


--����ѡ�α�
create table studCourse
(
stuCourseId number primary key ,--����һ��������,��ʾһ��ѡ��
sid number  references student(sid),--ѧ����
cid number references course(cid),--�γ̺�
tid number references teacher(tid),--�ڿ���ʦ���
grade number not null--�ɼ�
)

