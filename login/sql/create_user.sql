use userdb;
go

create table user (
    ID int(11) unique not null auto_increment,
    username varchar(50) unique not null,
    forename varchar(50) not null,
    surname varchar(50) not null,
    password varchar(60) not null,
    email varchar(50) not null,
    created_at datetime default current_timestamp,
    primary key (ID)
);
go