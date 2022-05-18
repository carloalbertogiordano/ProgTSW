USE Ecommerce;
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'password1';
grant all on Ecommerce.* to 'user1'@'localhost';
flush privileges;