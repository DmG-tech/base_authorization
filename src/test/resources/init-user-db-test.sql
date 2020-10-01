delete from user_roles;
delete from users;

insert into users (id, name, password, email, enabled, registered)
values (10, 'TestUser', '$2a$04$jiaLLjl5w09zLp3rirCzquI4gTTaVzvBc4JLP9AjUsW7Rl5bZRvKC', 'user@mail.ru', true, '2020-01-01 00:00:00');

insert into user_roles(user_id, role)
values (10, 'USER'),
       (10, 'ADMIN');

