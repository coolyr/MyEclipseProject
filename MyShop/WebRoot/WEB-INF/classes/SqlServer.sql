create table users(
userid bigint primary key identity,--用户id
username varchar(30) not null unique,--用户名
truename varchar(30) not null,--真实姓名
password varchar(30) not null,--密码
email varchar(30) not null,--电子邮件
phone varchar(30) not null,--电话号码
address varchar(30) not null,--用户地址
postcode char(6) not null,--邮编
grade int default 5--用户级别
);

insert into users values('xiaohong','小红','123','763010522@qq.com','13635336789','北京','123456',2);
insert into users values('xiaoming','小明','123','763010522@qq.com','13635336789','北京','123456',2);

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

insert into goods values('靓女菜馆','这是一部好电影',60.0,2,'香港嘉禾出品','03.jpg','电影');
insert into goods values('布衣神相','这是一部好电影',75.0,2,'香港嘉禾出品','04.jpg','电影');
insert into goods values('洛神','这是一部好电影',68.0,2,'香港嘉禾出品','05.jpg','电影');
insert into goods values('黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');
insert into goods values('黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');
insert into goods values('黑白森林','这是一部好电影',45.0,2,'香港嘉禾出品','01.jpg','电影');


create table orders(
	ordersId bigint primary key identity(1,1),--订单号
	userId bigint constraint fk_users_id references users(userid),--哪个用户定的
	orderDate datetime default getDate(),--下单时间
	payMode varchar(20) check (payMode in('货到付款','支付宝付款')) default '货到付款',--付款方式
	isPayed bit check(isPayed in(0,1)),--o表示还没付款，1表示已经付款
	totalPrice float not null--总价格
)

create table orderDetail(
	orderIid bigint constraint fk_orders references orders(ordersId),--订单号(并是一个外键)指向orders表
	goodsId bigint constraint fk_goods references goods(goodsId),--商品号（并是一个外键）指向orders表
	nums int not null --商品数量
)