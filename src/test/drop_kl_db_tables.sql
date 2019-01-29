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
VALUES ('Awaiting Fulfillment'),
       ('Delivery Pending'),
       ('Out for Delivery'),
       ('Delivered');

INSERT INTO orders (date, order_status_id, user_id)
VALUES ('12/23/2018', 1, 2),
       ('12/24/2018', 2, 2),
       ('12/24/2018', 2, 3),
       ('01/03/2019', 5, 3),
       ('01/07/2019', 1, 3),
       ('01/15/2019', 1, 1),
       ('12/23/2018', 1, 2),
       ('12/24/2018', 5, 2),
       ('12/24/2018', 2, 3),
       ('01/03/2019', 5, 3),
       ('01/07/2019', 5, 3),
       ('01/15/2019', 5, 1);
