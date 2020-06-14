DROP DATABASE IF EXISTS bookstore;

CREATE DATABASE bookstore;

USE bookstore;

CREATE TABLE t_user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    email    VARCHAR(200)
);

INSERT INTO t_user(username, password, email)
VALUES ('admin', 'admin', 'admin@atguigu.com');

SELECT *
FROM t_user;