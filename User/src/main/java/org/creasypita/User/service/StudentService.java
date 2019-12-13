package org.creasypita.User.service;

import org.creasypita.User.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by creasypita on 9/3/2019.
 *
 * @ProjectName: MybatisTutorial
 */

public interface StudentService {

    List<Student> GetAll();
    Student getById(int id);
    void insertStudent(Student student);
    void updateStudent(Student student);

    void deleteStudent(int id);
    void modifyNoTranactional(Student student1, Student student2);
    void modifyTranactional(Student student1, Student student2);
}
