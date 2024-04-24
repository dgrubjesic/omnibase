CREATE TYPE status_enum AS ENUM ('NA', 'FREE');

create table subscriptions (
    id varchar(19) primary key,
    status status_enum not null
)