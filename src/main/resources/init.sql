drop table if exists user_role;
drop table if exists usr;
create table IF NOT EXISTS usr
(
    id_user         int8 primary key     not null,
    username        varchar(2000) unique not null,
    password        varchar(2000)        not null,
    enabled         boolean,
    email           varchar(255),
    activation_code varchar(255)

);

drop table if exists message;
insert into usr(id_user, username, password, email, activation_code, enabled)
values (1, 'admin', '1', 'admin@a.com', null, true),
       (2, 'bob', '1', 'bob@a.com', null, true),
       (3, 'sos', '1', 'sos@a.com', null, true);
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
    filename   varchar(20000)     not null,
    user_id    int8               not null REFERENCES usr (id_user) on delete cascade
);
insert into message(text, tag, filename, user_id)
VALUES ('hi!', 'meeting', 'japan.png', 1),
       ('hi', 'meeting', 'japan.png', 1),
       ('hi', 'meeting', 'usa.png', 2),
       ('who are you?', 'here', 'japan.png', 2),
       ('i am god', 'here', 'usa.png', 2);
