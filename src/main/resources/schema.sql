alter table if exists message
    drop constraint if exists message_user_fk;


alter table if exists user_role
    drop constraint if exists user_role_fk;


drop table if exists message cascade;


drop table if exists user_role cascade;


drop table if exists usr cascade;


drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;


create table message
(
    id_message int8          not null,
    filename   text,
    tag        varchar(255),
    text       varchar(2048) not null,
    user_id    int8,
    primary key (id_message)
);


create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);


create table usr
(
    id_user         int8         not null,
    activation_code varchar(255),
    email           varchar(255),
    enabled         boolean      not null,
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id_user)
);


alter table if exists message
    add constraint message_user_fk
        foreign key (user_id) references usr;


alter table if exists user_role
    add constraint user_role_fk
        foreign key (user_id) references usr;

insert into usr(id_user, username, password, enabled)
values (1, 'admin', '$2a$10$0wRgGhla.wXD1rZS.IxNIOAxAczqBWe45BCCe2Shl2Y.WOAHFkZMG', true);
insert into user_role(user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');
