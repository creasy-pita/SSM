package org.creasypita.User.service;

import org.creasypita.User.mappers.StudentMapper;
import org.creasypita.User.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by creasypita on 9/3/2019.
 *
 * @ProjectName: MybatisTutorial
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> GetAll() {
        return studentMapper.GetAll();
    }

    public Student getById(int id) {
        return studentMapper.getById(id);
    }

    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentMapper.deleteStudent(id);
    }

    /**
     * no Tranaction  just persistant the first updateStudent modification when the Percentage greater than 10
     * @param student1
     * @param student2
     */
    public void modifyNoTranactional(Student student1, Student student2) {
        studentMapper.updateStudent(student1);
        if(student1.getPercentage()>10)
            throw new RuntimeException("111");
        studentMapper.updateStudent(student2);
    }

    /**
     * Tranaction  : will not persistant the first updateStudent modification when the Percentage greater than 10 too;
     * @param student1
     * @param student2
     */
    @Transactional
    public void modifyTranactional(Student student1, Student student2) {
        studentMapper.updateStudent(student1);
        if(student1.getPercentage()>10)
            throw new RuntimeException("111");
        studentMapper.updateStudent(student2);
    }
}
