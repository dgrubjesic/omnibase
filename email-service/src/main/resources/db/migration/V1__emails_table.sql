CREATE TYPE status_enum AS ENUM ('UNCONFIRMED', 'CONFIRMED', 'DEACTIVATED');

create table emails (
    id bigint primary key,
    email varchar(50),
    user_id bigint,
    status status_enum
)