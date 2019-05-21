package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockReportModel;
import edu.mum.client.model.StudentDataModel;
import edu.mum.client.model.StudentDtoForCrud;
import edu.mum.client.model.StudentReportModelForFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("studentReportModelForFacultySes")
@RequestMapping("/blockReportByStudent")

public class BlockReportByStudentController {

    private final TokenHelper tokenHelper;

    @Autowired

    private HttpServlet servlet;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    HttpSession httpSession;

    @Autowired
    public BlockReportByStudentController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public String showForm(@ModelAttribute("studentReportModelForFaculty") StudentReportModelForFaculty studentReportModelForFaculty, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        StudentReportModelForFaculty  studentReportModelForFacultySes ;
        String blockReq=null;
        Long selectedStudent=null;



        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);

       /* if(test ==null || test.trim().isEmpty()){
            test ="2016-November";
        }*/


        Object studentReportModelForFacultySesObj =  httpSession.getAttribute("studentReportModelForFacultySes");
        if(studentReportModelForFacultySesObj!=null){
            studentReportModelForFacultySes = (StudentReportModelForFaculty) studentReportModelForFacultySesObj;
            blockReq = studentReportModelForFacultySes.getSelectedBlock();
            selectedStudent = studentReportModelForFacultySes.getSelectedStudent();


        }else{
            studentReportModelForFacultySes = new StudentReportModelForFaculty();
            blockReq = "2016-November";
            selectedStudent =1L;
        }
String uri = request.getRequestURI();
        if (request.getRequestURI().split("/").length == 3) {
            blockReq = request.getRequestURI().split("/")[2];
        }else{

        }

        //System.out.println(request.getRequestURI().split("/")[2]);

        ResponseEntity<StudentReportModelForFaculty> response = restTemplate
                .exchange(Constants.URL + "attendances/student/?blockName="+ blockReq +"&studentId="+selectedStudent ,
                HttpMethod.GET, entity, StudentReportModelForFaculty.class);


        ResponseEntity<List<BlockReportModel>> blockResponse = restTemplate.exchange(Constants.URL + "blocks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BlockReportModel>>() {
                });

        if (blockResponse != null && blockResponse.getBody() != null) {
            model.addAttribute("blocks", blockResponse.getBody());

        }


        ResponseEntity<List<StudentDtoForCrud>> studentResponse = restTemplate.exchange(Constants.URL + "students",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<StudentDtoForCrud>>() {
                });


        if (studentResponse != null && studentResponse.getBody() != null) {
            model.addAttribute("students", studentResponse.getBody());

        }


        if (response != null && response.getBody() != null) {

            //studentReportModelForFaculty=response.getBody();
            studentReportModelForFacultySes.setSelectedBlock(blockReq);
            studentReportModelForFacultySes.setSelectedStudent(selectedStudent);


            studentReportModelForFacultySes.setTotalSessionsPossible(response.getBody().getTotalSessionsPossible());
            studentReportModelForFacultySes.setTotalSessionsAttended(response.getBody().getTotalSessionsAttended());
            studentReportModelForFacultySes.setPercentageAttendedInTotal(response.getBody().getPercentageAttendedInTotal());


            studentReportModelForFacultySes.setSessionsInBlock(response.getBody().getSessionsInBlock());
            studentReportModelForFacultySes.setDaysPresent(response.getBody().getDaysPresent());
            studentReportModelForFacultySes.setPercentageAttended(response.getBody().getPercentageAttended());
            studentReportModelForFacultySes.setExtraCredits(response.getBody().getExtraCredits());

            studentReportModelForFacultySes.setDatePresentDtoList(response.getBody().getDatePresentDtoList());

            model.addAttribute("studentReportModelForFacultySes",studentReportModelForFacultySes);
        }


        return "reports/faculty-block-report-by-student";


    }
}
