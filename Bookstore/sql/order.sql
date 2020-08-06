USE bookstore;

DROP TABLE IF EXISTS t_order;

CREATE TABLE t_order
(
    order_id    VARCHAR(50) PRIMARY KEY,
    create_time TIMESTAMP,
    price       DECIMAL(11, 2),
    status      VARCHAR(20),
    user_id     INT,
    FOREIGN KEY (user_id) REFERENCES t_user (id)
);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567891, '2020-05-23', 10.00, '已发货', 137);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567892, '2020-05-23', 10.00, '未发货', 137);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567893, '2020-05-23', 10.00, '已付款', 137);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567894, '2020-05-23', 10.00, '未付款', 137);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567895, '2020-05-23', 10.00, '已签收', 137);

INSERT INTO t_order (order_id, create_time, price, status, user_id)
VALUES (1234567896, '2020-05-23', 10.00, '未签收', 137);

ALTER TABLE t_order
    CHANGE status status VARCHAR(50) CHARACTER SET utf8;

SELECT *
FROM t_order;
