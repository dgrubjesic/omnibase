create table users (
    id bigint primary key,
    name varchar(50),
    password varchar(100),
    status varchar(50)
);

create table user_actions (
    id bigserial primary key,
    user_id bigint,
    status varchar,
    date_time date,
    foreign key (user_id) references users(id)
)

