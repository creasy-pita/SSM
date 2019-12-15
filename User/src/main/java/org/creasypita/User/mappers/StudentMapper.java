package org.creasypita.User.mappers;

import org.apache.ibatis.annotations.*;
import org.creasypita.User.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by creasypita on 9/3/2019.
 *
 * @ProjectName: MybatisTutorial
 */
//@Component
@Mapper
public interface StudentMapper {
    List<Student> GetAll();

    Student getById(int id);

    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);
}
