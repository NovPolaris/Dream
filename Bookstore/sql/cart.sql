#创建购物车表
USE bookstore;

DROP TABLE IF EXISTS t_cart;

CREATE TABLE t_cart
(
    id          INT(11) PRIMARY KEY AUTO_INCREMENT, ## 主键
    sku         VARCHAR(50)    NOT NULL,            ## SKU
    name        VARCHAR(50)    NOT NULL,            ## 书名
    username    VARCHAR(50)    NOT NULL,            # 用户名
    count       INT(11)        NOT NULL,            ## 数量
    price       DECIMAL(11, 2) NOT NULL,            ## 价格
    total_price DECIMAL(11, 2) NOT NULL             ## 总价
);

SELECT *
FROM t_cart;