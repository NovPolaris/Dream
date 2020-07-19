DROP DATABASE IF EXISTS mybatis;

CREATE DATABASE mybatis;

USE mybatis;

DROP TABLE IF EXISTS t_student;

CREATE TABLE t_student
(
    id    INT PRIMARY KEY,
    name  VARCHAR(20) NOT NULL UNIQUE,
    age   INT         NOT NULL,
    email VARCHAR(200)
);

INSERT INTO t_student(id, name, age, email)
VALUES (1001, '张三', 28, 'zs@sina.com');

INSERT INTO t_student(id, name, age, email)
VALUES (1002, '李四', 20, 'lisi@qq.com');

SELECT *
FROM t_student;