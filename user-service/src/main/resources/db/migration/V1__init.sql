create table users (
    id bigint primary key,
    name varchar(50),
    password varchar(100),
    email varchar(50)
);

create table user_actions (
    id bigserial primary key,
    user_id bigint,
    action varchar,
    foreign key (user_id) references users(id)
)

