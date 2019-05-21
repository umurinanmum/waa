package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private TokenHelper tokenHelper;

    @PostMapping("/studentBlockReportToExcel")
    public void studentBlockReportToExcel() {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity<ResponseEntity<InputStreamResource>> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(Constants.URL + "download/studentBlockReportToExcel?blockName=2016-November&id=1"
                , HttpMethod.GET, entity, void.class);


    }

}
