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

insert into users_shop values(null,'xiaohong','小红','123','763010522@qq.com','13635336789','北京','123456',2);
insert into users_shop values(null,'xiaoming','小明','123','763010522@qq.com','13635336789','北京','123456',2);

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

insert into goods_shop values(null,'靓女菜馆','这是一部好电影',60.0,2,'香港嘉禾出品','03.jpg','电影');
insert into goods_shop values(null,'布衣神相','这是一部好电影',75.0,2,'香港嘉禾出品','04.jpg','电影');
insert into goods_shop values(null,'洛神','这是一部好电影',68.0,2,'香港嘉禾出品','05.jpg','电影');
insert into goods_shop values(null,'黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');
insert into goods_shop values(null,'黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');
insert into goods_shop values(null,'黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');



create table orders_shop(
	ordersId bigint primary key AUTO_INCREMENT,
	userId bigint,
	orderDate timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	payMode varchar(20) default '货到付款',
	isPayed int,
	totalPrice float not null
);

create table orderDetail_shop(
	orderIid bigint,
	goodsId bigint,
	nums int not null
);










