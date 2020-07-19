package com.puyang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    public static final String MYBATIS_CONFIGURATION_FILE = "resource/mybatis.xml";

    public static SqlSession getSession() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIGURATION_FILE);
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(inputStream);
        return factory.openSession();
    }

    public static void close(SqlSession sqlSession) {
        sqlSession.close();
    }
}
