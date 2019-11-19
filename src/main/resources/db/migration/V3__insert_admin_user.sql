insert into users(id, first_name, last_name, login, password) values (1, 'fn', 'ln' , 'Login-login', '$2a$10$Eql1o5vfFU4bCTaQFdgG1.mihZUFj9Fxjh.fWm9LbjYva.doCrDeO');
insert into user_roles(user_id, role_id) values ( 1, 1 );
alter sequence users_seq restart with 2;