package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
import edu.mum.client.model.StudentModel;
import edu.mum.client.model.StudentReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentContoller {

    private String api_url = "http://localhost:8081/api/v1/students"; //Constants.URL + "students";

    private final TokenHelper tokenHelper;

    @Autowired
    public StudentContoller(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping("/list")
    public String list(Model model){

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            //HttpEntity entity = new HttpEntity(headers);
            // HttpEntity<Employee[]>: To get result as Employee[].
            HttpEntity<StudentModel[]> entity = new HttpEntity<StudentModel[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<StudentModel[]> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, StudentModel[].class);
            //System.out.println("response: " + response.getStatusCode());
            final List<StudentModel> students = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("students", students);
        }
        catch (Exception e){

        }

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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<StudentModel> response = restTemplate.exchange(api_url, HttpMethod.POST, entity, StudentModel.class);

        System.out.println("response: " + response);
//        ResponseEntity<String> result = restTemplate.postForEntity(api_url, studentModel, String.class);
//        System.out.println("result: " + result.getBody());
//        if (result.getBody() == null || result.getBody().trim().isEmpty()) {
//            return "students/student-add";
//        }
        redirectAttributes.addFlashAttribute("studentModel", studentModel);
        return "students/student-details";
    }

//    @DeleteMapping("/delete/{studentid}")
////    public String delete(){
////
////    }
}
