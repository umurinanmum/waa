package edu.mum.waa.controller;

import edu.mum.waa.entity.Student;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 6000, allowedHeaders = "*")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/student-lookup", produces = "application/json")
    public List<Student> lookup(@RequestParam("q") String query) {
        System.out.println("looking =====");
        return studentService.lookupStudentByStudentId(query);
    }
}
