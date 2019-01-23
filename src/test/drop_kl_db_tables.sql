USE kl_db;
DROP TABLE IF EXISTS products_orders;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_reviews;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;



UPDATE users
SET first_name = 'John', last_name = 'Smith', address1 = "123 Bakers Ln",
    city = 'San Antonio', state = 'TX', postal_code = 78254,  phone_number = 2106574596
    WHERE id = 1;


SELECT * FROM users;