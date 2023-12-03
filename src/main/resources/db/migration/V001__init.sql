create table users (
    id varchar(100) primary key,
    email varchar(33) unique,
    password varchar(33)
)