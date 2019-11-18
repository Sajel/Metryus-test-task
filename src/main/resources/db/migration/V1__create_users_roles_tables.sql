create table roles (id bigint not null, name varchar(255) not null unique, primary key (id));
create table users (id bigint not null,
                    first_name varchar(255),
                    last_name varchar(255),
                    login varchar(50) not null unique,
                    password varchar(50) not null,
                    primary key (id));
create table user_roles (user_id bigint not null,
                         role_id bigint not null,
                         primary key (user_id, role_id));

create sequence roles_seq start with 1 increment by 1;
create sequence users_seq start with 1 increment by 1;

alter table user_roles add constraint user_roles_role_fk foreign key (role_id) references roles;
alter table user_roles add constraint user_roles_user_fk foreign key (user_id) references users;