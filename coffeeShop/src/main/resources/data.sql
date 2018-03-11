insert into role(role) values ('ADMIN');
insert into role(role) values ('USER');

insert into person(email, last_name, first_name, enable) values('admin@coffeeshop.com', 'Admin', 'Admin', 1);
insert into user(email, password, active) values('admin@coffeeshop.com', '$2a$10$R1kmVEHTHJ90GcejrpmTdOirALp9ZJ7ugMkPSDRKlOhTc1j92WClW', 1);
insert into user_role (user_id, role_id) values (1,1);

insert into person(email, last_name, first_name, enable) values('test@test.com', 'Test', 'Test', 1);
insert into user(email, password, active) values('test@test.com', '$2a$10$R1kmVEHTHJ90GcejrpmTdOirALp9ZJ7ugMkPSDRKlOhTc1j92WClW', 1);
insert into user_role (user_id, role_id) values (2,1);

insert into product(description, price, product_name, product_type, product_image) values('Grilled Chicken', 2.5, 'Menu 1', 'BREAKFAST', '/images/product1.jpg');
insert into product(description, price, product_name, product_type, product_image) values('Chicken Tacos', 1.5, 'Menu 2', 'BREAKFAST', '/images/product2.jpg');
insert into product(description, price, product_name, product_type, product_image) values('Gilled Sweetted Salmon', 7.5, 'Menu 3', 'LUNCH', '/images/product3.jpg');
insert into product(description, price, product_name, product_type, product_image) values('Grilled Chicken Special', 2.5, 'Menu 4', 'DINNER', '/images/product4.jpg');
insert into product(description, price, product_name, product_type, product_image) values('Chicken Tacos Special', 1.5, 'Menu 5', 'DINNER', '/images/product5.jpg');
insert into product(description, price, product_name, product_type, product_image) values('Gilled Sweetted Salmon Special', 7.5, 'Menu 6', 'DINNER', '/images/product6.jpg');