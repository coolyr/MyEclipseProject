create table users_shop(
userid bigint primary key AUTO_INCREMENT,
username varchar(30) not null unique,
truename varchar(30) not null,
password varchar(30) not null,
email varchar(30) not null,
phone varchar(30) not null,
address varchar(30) not null,
postcode char(6) not null,
grade int default 5
);

insert into users_shop values(null,'xiaohong','С��','123','763010522@qq.com','13635336789','����','123456',2);
insert into users_shop values(null,'xiaoming','С��','123','763010522@qq.com','13635336789','����','123456',2);

create table goods_shop(
goodsId bigint primary key AUTO_INCREMENT,
goodsName varchar(40),
goodsIntro varchar(500),
goodsPrice float,
goodsNum int,
publisher varchar(40),
photo varchar(40),
type varchar(10)
);

insert into goods_shop values(null,'��Ů�˹�','����һ���õ�Ӱ',60.0,2,'��ۼκ̳�Ʒ','03.jpg','��Ӱ');
insert into goods_shop values(null,'��������','����һ���õ�Ӱ',75.0,2,'��ۼκ̳�Ʒ','04.jpg','��Ӱ');
insert into goods_shop values(null,'����','����һ���õ�Ӱ',68.0,2,'��ۼκ̳�Ʒ','05.jpg','��Ӱ');
insert into goods_shop values(null,'�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');
insert into goods_shop values(null,'�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');
insert into goods_shop values(null,'�ڰ�ɭ��','����һ���õ�Ӱ',45.0,2,'��ۼκ̳�Ʒ','01.jpg','��Ӱ');



create table orders_shop(
	ordersId bigint primary key AUTO_INCREMENT,
	userId bigint,
	orderDate timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	payMode varchar(20) default '��������',
	isPayed int,
	totalPrice float not null
);

create table orderDetail_shop(
	orderIid bigint,
	goodsId bigint,
	nums int not null
);










