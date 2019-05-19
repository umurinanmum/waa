package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.EntryModel;
import edu.mum.client.model.EntryReportModelPerStudent;
import edu.mum.client.model.EntryReportModelTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/entryReport")
@SessionAttributes("entryReportModelTotalSes")
public class EntryReportController {

    private final TokenHelper tokenHelper;

    @Autowired
    private HttpServlet servlet;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public EntryReportController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public String showForm(@ModelAttribute EntryReportModelTotal entryReportModelTotal, Model model) {

        EntryReportModelTotal entryReportModelTotalSes ;

        String selectedEntry="";
        boolean isSession=false;

        Object entryReportModelTotalObj =  httpSession.getAttribute("entryReportModelTotalSes");
        if(entryReportModelTotalObj!=null){
            entryReportModelTotalSes = (EntryReportModelTotal) entryReportModelTotalObj;
            selectedEntry = entryReportModelTotalSes.getSelectedEntry();
            isSession=true;

        }else{
            entryReportModelTotalSes = new EntryReportModelTotal();
            //blockReq = "2016-November";

        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);


        ResponseEntity<List<EntryModel>> entryResponse = restTemplate.exchange(Constants.URL + "entries",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<EntryModel>>() {
                });


        //If we have entries:
        if (entryResponse != null && entryResponse.getBody() != null) {
            //By default, show last entry's report
            if(!isSession) selectedEntry = entryResponse.getBody().get(entryResponse.getBody().size() - 1).getName();
            String entryNameFromRequest = request.getParameter("entryName");
            if (entryNameFromRequest != null && !entryNameFromRequest.trim().isEmpty()) {
                 selectedEntry = entryNameFromRequest.trim();
            }

            ResponseEntity<List<EntryReportModelPerStudent>> entryReportModelPerStudent =
            restTemplate.exchange(Constants.URL + "attendances?entryName=" + selectedEntry,
                    HttpMethod.GET, entity,new ParameterizedTypeReference<List<EntryReportModelPerStudent>>(){});




            if (entryResponse != null && entryResponse.getBody() != null) {
                model.addAttribute("entries", entryResponse.getBody());
            }

            if (entryReportModelPerStudent != null && entryReportModelPerStudent.getBody() != null) {
                entryReportModelTotal.setEntryReportModelPerStudentList(entryReportModelPerStudent.getBody());
                model.addAttribute("entryReportModelTotal", entryReportModelTotal);
            }
            entryReportModelTotalSes = entryReportModelTotal;
            entryReportModelTotalSes.setSelectedEntry(selectedEntry);

            model.addAttribute("entryReportModelTotalSes",entryReportModelTotalSes);
        }


        return "reports/entry-report";
    }

}
