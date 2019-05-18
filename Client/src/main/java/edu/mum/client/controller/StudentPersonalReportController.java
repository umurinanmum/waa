package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockReportModel;
import edu.mum.client.model.StudentReportModel;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("studentReportModelSes")
public class StudentPersonalReportController {

    private final TokenHelper tokenHelper;

    @Autowired
    private HttpServlet servlet;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    HttpSession httpSession;

    @Autowired
    public StudentPersonalReportController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
        public String showForm(@ModelAttribute("studentReportModel") StudentReportModel studentReportModel, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        StudentReportModel  studentReportModelSes ;
        String blockReq;


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);

       /* if(test ==null || test.trim().isEmpty()){
            test ="2016-November";
        }*/


       Object studentReportModelSesObj =  httpSession.getAttribute("studentReportModelSes");
       if(studentReportModelSesObj!=null){
           studentReportModelSes = (StudentReportModel) studentReportModelSesObj;
           blockReq = studentReportModelSes.getSelectedBlock();

       }else{
           studentReportModelSes = new StudentReportModel();
           blockReq = "2016-November";


       }

        if (request.getRequestURI().split("/").length == 3) {
            blockReq = request.getRequestURI().split("/")[2];
        }else{

        }

        //System.out.println(request.getRequestURI().split("/")[2]);

        ResponseEntity<StudentReportModel> response = restTemplate.exchange(Constants.URL + "attendances/" + blockReq,
                HttpMethod.GET, entity, StudentReportModel.class);


        ResponseEntity<List<BlockReportModel>> blockResponse = restTemplate.exchange(Constants.URL + "blocks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BlockReportModel>>() {
                });

        if (blockResponse != null && blockResponse.getBody() != null) {
            model.addAttribute("blocks", blockResponse.getBody());

        }

        if (response != null && response.getBody() != null) {

            //studentReportModel=response.getBody();
            studentReportModelSes.setSelectedBlock(blockReq);


            studentReportModelSes.setTotalSessionsPossible(response.getBody().getTotalSessionsPossible());
            studentReportModelSes.setTotalSessionsAttended(response.getBody().getTotalSessionsAttended());
            studentReportModelSes.setPercentageAttendedInTotal(response.getBody().getPercentageAttendedInTotal());


            studentReportModelSes.setSessionsInBlock(response.getBody().getSessionsInBlock());
            studentReportModelSes.setDaysPresent(response.getBody().getDaysPresent());
            studentReportModelSes.setPercentageAttended(response.getBody().getPercentageAttended());
            studentReportModelSes.setExtraCredits(response.getBody().getExtraCredits());

            studentReportModelSes.setDatePresentDtoList(response.getBody().getDatePresentDtoList());

            model.addAttribute("studentReportModelSes",studentReportModelSes);
        }


        return "student-personal-report";
    }

}
