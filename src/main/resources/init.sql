drop table if exists user_role;
drop table if exists usr;
drop table if exists message;

create table IF NOT EXISTS usr
(
    id_user  int8 primary key     not null,
    username varchar(2000) unique not null,
    password varchar(2000)        not null,
    enabled  boolean
);
insert into usr(id_user, username, password, enabled)
values (1, 'a', '1', true),
       (2, 'b', '1', true),
       (3, 'c', '1', true);
create table IF NOT EXISTS user_role
(
    roles   varchar(2000) not null,
    user_id int8          not null REFERENCES usr (id_user) on delete cascade
);
insert into user_role(roles, user_id)
VALUES ('ADMIN', 1),
       ('USER', 1),
       ('USER', 2),
       ('USER', 3);
create table IF NOT EXISTS message
(
    id_message serial primary key not null,
    text       varchar(2000)      not null,
    tag        varchar(2000)      not null,
    user_id    int8               not null REFERENCES usr (id_user) on delete cascade
);
insert into message(text, tag, user_id)
VALUES ('aaa', 'one', 1),
       ('aaa', 'one', 1),
       ('bbb', 'two', 2),
       ('bbb', 'two', 2),
       ('ccc', 'three', 3);
