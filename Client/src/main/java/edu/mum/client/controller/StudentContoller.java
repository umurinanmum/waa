package edu.mum.client.controller;

<<<<<<< HEAD
import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
=======
>>>>>>> master
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

    private String api_url = Constants.URL + "students";

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
            HttpEntity<StudentModel[]> entity = new HttpEntity<StudentModel[]>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<StudentModel[]> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, StudentModel[].class);
            final List<StudentModel> students = Arrays.stream(response.getBody()).collect(Collectors.toList());

            model.addAttribute("students", students);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "students/student-list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute StudentModel studentModel){
        System.out.println("students/student-add");
        return "students/student-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute StudentModel studentModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      Model model){
        String result_str = "saved";
        try {
            if (bindingResult.hasErrors()) {
                return "students/student-add";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<StudentModel> entity = new HttpEntity<>(studentModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.postForEntity(api_url, entity, String.class);
            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "students/student-add";
            }

            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("studentModel", studentModel);
            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "students/student-add";
        }
        return "redirect:list";
    }

    @GetMapping("/edit/{studentid}")
    public String edit(@PathVariable("studentid") Long studentid,
                       Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<StudentModel> response = restTemplate.exchange(api_url + "/" + studentid.toString(), HttpMethod.GET, entity, StudentModel.class);

            StudentModel student = response.getBody();
            System.out.println("response: " + student);

            model.addAttribute("studentModel", student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return "students/student-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute StudentModel studentModel,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "students/student-edit";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity<StudentModel> entity = new HttpEntity<>(studentModel, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> result = restTemplate.exchange(api_url, HttpMethod.PUT, entity, String.class);

            System.out.println("result: " + result.getBody());
            if (result.getBody() == null || result.getBody().trim().isEmpty()) {
                return "students/student-edit";
            }

            String result_str = "edited";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("result", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("result", e);
            return "students/student-edit";
        }
        return "redirect:list";
    }

    @GetMapping("/delete/{studentid}")
    public String delete(@PathVariable("studentid") Long studentid,
                         RedirectAttributes redirectAttributes,
                         Model model){

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(api_url + "/" + studentid, HttpMethod.DELETE, entity, String.class);
            System.out.println("result: " + result.getBody());

            String result_str = "Deleted";
            if(!result.getBody().equalsIgnoreCase("true"))
                result_str = result.getBody();

            redirectAttributes.addFlashAttribute("resultInfo", result_str);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "result";
    }

    @GetMapping("/detail/{studentid}")
    public String detail(@PathVariable("studentid") Long studentid,
                         Model model){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<StudentModel> response = restTemplate.exchange(api_url + "/" + studentid.toString(), HttpMethod.GET, entity, StudentModel.class);

            StudentModel student = response.getBody();
            System.out.println("response: " + student);

            model.addAttribute("studentModel", student);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "students/student-detail";
    }
}
