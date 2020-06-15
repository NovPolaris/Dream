DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
    id        BIGINT AUTO_INCREMENT,
    loginName VARCHAR(255),
    loginPwd  VARCHAR(255),
    realName  VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO t_user(loginName, loginPwd, realName)
VALUES ('zhangsan', '123', '张三');
INSERT INTO t_user(loginName, loginPwd, realName)
VALUES ('jack', '123', '杰克');

COMMIT;
SELECT *
FROM t_user;