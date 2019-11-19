alter sequence roles_seq restart with 3;
insert into roles (id, name) values (1, 'ADMIN');
insert into roles (id, name) values (2, 'CLIENT');
