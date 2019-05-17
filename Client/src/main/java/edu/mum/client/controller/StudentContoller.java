package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.model.BlockModel;
import edu.mum.client.model.StudentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentContoller {

    private String api_url = "http://localhost:8081/api/v1/students"; //Constants.URL + "students";

    @GetMapping("/list")
    public String list(Model model){

        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(api_url);
        ResponseEntity<String> result = restTemplate.getForEntity(api_url, String.class);
        System.out.println(result.getBody());
//        if (result.getBody() != null && !result.getBody().trim().isEmpty()) {
//            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
//            return "welcome";
//        }
        return "students/student-list";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute StudentModel studentModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      Model model){
        if(bindingResult.hasErrors()){
            return "students/student-add";
        }

        System.out.println("studentModel: " + studentModel);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(api_url, studentModel, String.class);
        System.out.println("result: " + result.getBody());
        if (result.getBody() == null || result.getBody().trim().isEmpty()) {
            return "students/student-add";
        }
        redirectAttributes.addFlashAttribute("studentModel", studentModel);
        return "students/student-details";
    }
}
