create schema dam2tm06uf2p2;
use dam2tm06uf2p2;


create table Users (
	id int auto_increment primary key,
    user_name varchar(100),
    password varchar(100),
    create_date date,
    create_user varchar(100),
    modify_date date,
    modify_user varchar(100)
);