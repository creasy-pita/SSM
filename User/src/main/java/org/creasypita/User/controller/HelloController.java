package org.creasypita.User.controller;

import org.creasypita.User.model.Student;
import org.creasypita.User.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by creasypita on 12/13/2019.
 *
 * @ProjectName: SSM
 */
@Controller
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "hello")
    @ResponseBody
    public String inputEmployee() {

        List<Student> studentList = studentService.GetAll();
        return "hello"+studentList.get(0).getName();
    }
}
