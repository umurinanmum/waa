package edu.mum.client.controller;

import edu.mum.client.model.BlockModel;
import edu.mum.client.model.StudentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentContoller {

    @GetMapping("/list")
    public String student(@ModelAttribute StudentModel studentModel, Model model){

        return "students/student-list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute StudentModel studentModel, Model model){

        return "students/student-add";
    }
}
