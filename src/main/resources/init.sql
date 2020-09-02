drop table if exists user_role;
drop table if exists usr;
drop table if exists message;

create table IF NOT EXISTS usr
(
    id_user  serial primary key   not null,
    username varchar(2000) unique not null,
    password varchar(2000)        not null,
    enabled  boolean
);

create table IF NOT EXISTS user_role
(
    roles   varchar(2000) not null,
    user_id int4          not null REFERENCES usr (id_user) on delete cascade
);

create table IF NOT EXISTS message
(
    id_message serial primary key not null,
    text       varchar(2000)      not null,
    tag        varchar(2000)      not null
);
insert into message(text, tag)
VALUES ('da', 'one'),
       ('dd', 'one'),
       ('xx', 'two'),
       ('xy', 'two'),
       ('xy', 'three');
