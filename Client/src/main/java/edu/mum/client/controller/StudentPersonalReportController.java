package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.StudentReportModel;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

@Controller
public class StudentPersonalReportController {

    private final TokenHelper tokenHelper;

    @Autowired
    public StudentPersonalReportController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public String showForm(@ModelAttribute StudentReportModel studentReportModel) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<StudentReportModel> response = restTemplate.exchange(Constants.URL + "attendances/" + "2016-November",
                HttpMethod.GET, entity, StudentReportModel.class);


        if (response != null && response.getBody() != null) {
            studentReportModel.setDaysPresent(response.getBody().getDaysPresent());
            studentReportModel.setSessionsInBlock(response.getBody().getSessionsInBlock());
            studentReportModel.setPercentageAttended(response.getBody().getPercentageAttended());
        }

        return "student-personal-report";
    }

}
