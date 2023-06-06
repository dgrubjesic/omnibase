create table emails (
    id bigint primary key,
    email varchar(50),
    user_id bigint,
    status varchar(50),
    confirmation_id varchar(50)
)