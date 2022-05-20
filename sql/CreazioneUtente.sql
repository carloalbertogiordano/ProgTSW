USE Ecommerce;
DROP USER IF EXISTS 'user1'@'localhost';
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'VoW!S$!h3udC#W';
grant all on Ecommerce.* to 'user1'@'localhost';
flush privileges;