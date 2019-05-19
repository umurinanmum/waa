package edu.mum.client.controller;


import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.*;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/facultyExtraPoints")
@SessionAttributes("extraCreditModelSes")
public class FacultyExtraCreditController {

    private final TokenHelper tokenHelper;


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public FacultyExtraCreditController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public String showForm(@ModelAttribute ExtraCreditModel extraCreditModel, Model model) {

        ExtraCreditModel extraCreditModelSes;

        String selectedBlock = "";
        boolean isSession = false;

        Object extraCreditModelTotalObj = httpSession.getAttribute("extraCreditModelSes");
        if (extraCreditModelTotalObj != null) {
            extraCreditModelSes = (ExtraCreditModel) extraCreditModelTotalObj;
            selectedBlock = extraCreditModelSes.getSelectedBlock();
            isSession = true;

        } else {
            extraCreditModelSes = new ExtraCreditModel();
            //blockReq = "2016-November";

        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<BlockReportModel>> blockResponse = restTemplate.exchange(Constants.URL + "blocks",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<BlockReportModel>>() {
                });

        if (blockResponse != null && blockResponse.getBody() != null) {
            model.addAttribute("blocks", blockResponse.getBody());


            //By default, show last entry's report
            if (!isSession) selectedBlock = blockResponse.getBody().get(blockResponse.getBody().size() - 1).getName();
            String blockNameFromRequest = request.getParameter("blockName");
            if (blockNameFromRequest != null && !blockNameFromRequest.trim().isEmpty()) {
                selectedBlock = blockNameFromRequest.trim();
            }

            //selectedBlock="2016-November";

            ResponseEntity<ExtraCreditModel> extraCreditModelResponseEntity=null;

            try {
                extraCreditModelResponseEntity =
    restTemplate.exchange(Constants.URL + "attendances/faculty?blockName=" + selectedBlock,
                                HttpMethod.GET, entity, ExtraCreditModel.class);
            } catch (RestClientException e) {
                e.printStackTrace();
            }


            if (blockResponse != null && blockResponse.getBody() != null) {
                model.addAttribute("blocks", blockResponse.getBody());
            }

            if (extraCreditModelResponseEntity != null && extraCreditModelResponseEntity.getBody() != null) {
                extraCreditModel.setData(extraCreditModelResponseEntity.getBody().getData());
                extraCreditModel.setSelectedBlock(extraCreditModelResponseEntity.getBody().getSelectedBlock());
                //model.addAttribute("entryReportModelTotal", entryReportModelTotal);
            }

            extraCreditModelSes = extraCreditModel;
            extraCreditModelSes.setSelectedBlock(selectedBlock);

            model.addAttribute("extraCreditModelSes", extraCreditModelSes);
        }


        return "faculty-extra-credit-report";
    }

}
