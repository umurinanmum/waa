package edu.mum.waa.controller;

import edu.mum.waa.entity.Student;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.mum.waa.dto.StudentDto;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        System.out.println("Students - findAll");
        //return studentService.findAll();
        return studentService.findAllStudent();
    }

    @GetMapping("/{studentid}")
    public StudentDto getStudent(@PathVariable Long studentid) {
        System.out.println("Students - findById: " + studentid);
        StudentDto student = studentService.findById(studentid);
        System.out.println("Students - findById - findByIdstudent: " + student);
        return student;
    }

    //GetMapping("/{}")

    @PostMapping
    public boolean save(@RequestBody StudentDto studentDto) {
        System.out.println("Students - save");
        return studentService.save(studentDto);
    }

    @PutMapping
    public boolean update(@RequestBody StudentDto studentDto) {
        System.out.println("Students - update: " + studentDto.getId());
        return studentService.update(studentDto);
    }

    @DeleteMapping("/{studentid}")
    public boolean delete(@PathVariable Long studentid) {
        System.out.println("Students - delete");
        return studentService.delete(studentid);
    }

    @GetMapping(value = "/student-lookup", produces = "application/json")
    public List<Student> lookup(@RequestParam("q") String query) {
        System.out.println("looking =====");
        return studentService.lookupStudentByStudentId(query);
    }
}
