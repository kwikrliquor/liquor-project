USE kl_db;
DROP TABLE IF EXISTS products_orders;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_reviews;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM categories;
SELECT * FROM orders;
SELECT * FROM order_status;
SELECT * FROM products_orders;
SELECT * FROM user_reviews;


UPDATE users
SET first_name = 'John', last_name = 'Smith', address1 = "123 Bakers Ln",
    city = 'San Antonio', state = 'TX', postal_code = 78254,  phone_number = 2106574596
    WHERE id = 1;



INSERT INTO order_status (status)
VALUES ('New Order'),
       ('Ready for Driver'),
       ('On Route'),
       ('Delivered');

INSERT INTO orders (date, order_status_id, user_id)
VALUES (11111111, 1, 2),
       (22222222, 2, 2),
       (33333333, 2, 3),
       (44444444, 3, 3),
       (55555555, 1, 3),
       (66666666, 1, 1);
