create database socks;

use socks;
create table user (
userid int not null AUTO_INCREMENT,
fullname varchar(50),
username varchar(50),
password varchar(50),
phonenumber varchar(50),
email varchar(50),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date,
CONSTRAINT PK_user PRIMARY KEY (userid)
);

create table role(
roleid int not null AUTO_INCREMENT,
rolename varchar(50),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date,
CONSTRAINT PK_role PRIMARY KEY (roleid)
);

create table user_role(
userid int,
roleid int,
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date, 

FOREIGN KEY (userid) REFERENCES user(userid),
FOREIGN KEY (roleid) REFERENCES role(roleid)
);

create table product(
productid int not null PRIMARY KEY AUTO_INCREMENT,
categoryid int,
nameproduct varchar(50),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date,

FOREIGN KEY (categoryid) REFERENCES category(categoryid)
);

create table size(
sizeid int not null PRIMARY KEY AUTO_INCREMENT,
sizename varchar(50),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date
);

create table category(
categoryid int not null PRIMARY KEY AUTO_INCREMENT,
categoryname varchar(50),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date
);

create table productetails(
productid int not null,
shottitle varchar (500),
price varchar (50),
comment varchar (50),
imagelist varchar(50),
content varchar(2000),
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date,

FOREIGN KEY (productid) REFERENCES product(productid)
);

create table product_size(
productid int,
sizeid int ,
createby varchar(50),
createdate date,
modifyby varchar(50),
modifydate date,
FOREIGN KEY (productid) REFERENCES product(productid),
FOREIGN KEY (sizeid) REFERENCES size(sizeid)
);