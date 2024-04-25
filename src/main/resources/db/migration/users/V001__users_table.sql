create table users (
    id varchar(19) primary key,
    unique_name varchar(33) unique not null ,
    password varchar(100) not null,
    name varchar(33),
    surname varchar
)