package com.puyang.app;

import com.puyang.types.Student;
import com.puyang.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        SqlSession session = MyBatisUtils.getSession();
        String sqlId = "com.puyang.dao.StudentDao" + "." + "queryStudents";
        List<Student> list = session.selectList(sqlId);
        list.forEach(System.out::println);
    }
}
