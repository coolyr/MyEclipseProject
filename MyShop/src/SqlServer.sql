create table users(
userid bigint primary key identity,--�û�id
username varchar(30) not null unique,--�û���
truename varchar(30) not null,--��ʵ����
password varchar(30) not null,--����
email varchar(30) not null,--�����ʼ�
phone varchar(30) not null,--�绰����
address varchar(30) not null,--�û���ַ
postcode char(6) not null,--�ʱ�
grade int default 5--�û�����
);

insert into users values('xiaohong','С��','123','763010522@qq.com','13635336789','����','123456',2);
insert into users values('xiaoming','С��','123','763010522@qq.com','13635336789','����','123456',2);

select * from users;

create table goods(
goodsId bigint primary key identity,
goodsName varchar(40),
goodsIntro varchar(500),
goodsPrice float,
goodsNum int,
publisher varchar(40),
photo varchar(40),
type varchar(10)
)

insert into goods values('��Ů�˹�','����һ���õ�Ӱ',60.0,2,'��ۼκ̳�Ʒ','03.jpg','��Ӱ');
insert into goods values('��������','����һ���õ�Ӱ',75.0,2,'��ۼκ̳�Ʒ','04.jpg','��Ӱ');
insert into goods values('����','����һ���õ�Ӱ',68.0,2,'��ۼκ̳�Ʒ','05.jpg','��Ӱ');
insert into goods values('�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');
insert into goods values('�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');
insert into goods values('�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');


create table orders(
	ordersId bigint primary key identity(1,1),--������
	userId bigint constraint fk_users_id references users(userid),--�ĸ��û�����
	orderDate datetime default getDate(),--�µ�ʱ��
	payMode varchar(20) check (payMode in('��������','֧��������')) default '��������',--���ʽ
	isPayed bit check(isPayed in(0,1)),--o��ʾ��û���1��ʾ�Ѿ�����
	totalPrice float not null--�ܼ۸�
)

create table orderDetail(
	orderIid bigint constraint fk_orders references orders(ordersId),--������(����һ�����)ָ��orders��
	goodsId bigint constraint fk_goods references goods(goodsId),--��Ʒ�ţ�����һ�������ָ��orders��
	nums int not null --��Ʒ����
)