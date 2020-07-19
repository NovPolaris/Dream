package com.puyang.dao;

import com.puyang.types.Student;

import java.util.List;

public interface StudentDao {
    /**
     * Query all students from database
     *
     * @return A list of Students that in the database
     */
    List<Student> queryStudents();

    /**
     * Insert the data of student into database
     *
     * @param student the data to be inserted
     * @return The number of lines that are impacted
     */
    int insertStudent(Student student);
}
