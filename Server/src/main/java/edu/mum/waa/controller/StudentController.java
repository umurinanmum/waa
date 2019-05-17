package edu.mum.waa.controller;

import edu.mum.waa.dto.StudentDto;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDto> findAll(){
        System.out.println("Students - findAll");
        return studentService.findAll();
    }

    @GetMapping("/{studentid}")
    public StudentDto getStudent(@PathVariable Long studentid){
        System.out.println("Students - findById");
        return studentService.findById(studentid);
    }

    //GetMapping("/{}")

    @PostMapping
    public boolean save(@RequestBody StudentDto studentDto){
        System.out.println("Students - save");
        return studentService.save(studentDto);
    }

    @PutMapping
    public boolean update(@RequestBody StudentDto studentDto){
        System.out.println("Students - update");
        return studentService.save(studentDto);
    }

    @DeleteMapping("/{studentid}")
    public boolean delete(@PathVariable Long studentid){
        System.out.println("Students - delete");
        return studentService.delete(studentid);
    }
}
