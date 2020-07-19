USE bookstore;

DROP TABLE IF EXISTS t_order_item;

CREATE TABLE t_order_item
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100),
    count       INT,
    price       DECIMAL(11, 2),
    total_price DECIMAL(11, 2),
    order_id    VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES t_order (order_id)
);

SELECT *
FROM t_order_item;