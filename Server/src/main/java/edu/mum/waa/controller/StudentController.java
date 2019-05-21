package edu.mum.waa.controller;

import edu.mum.waa.dto.StudentDtoForCrud;
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
    public List<StudentDtoForCrud> findAllForCrud() {
        System.out.println("Students - findAll");
        return studentService.findAllForCrud();
    }

    @GetMapping("/{studentid}")
    public StudentDto getStudent(@PathVariable Long studentid) {
        System.out.println("Students - findById");
        return studentService.findById(studentid);
    }

    //GetMapping("/{}")

    @PostMapping
    public boolean save(@RequestBody StudentDto studentDto) {
        System.out.println("Students - save");
        System.out.println(studentDto);
        return studentService.save(studentDto);
    }

    @PutMapping
    public boolean update(@RequestBody StudentDto studentDto) {
        System.out.println("Students - update");
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
