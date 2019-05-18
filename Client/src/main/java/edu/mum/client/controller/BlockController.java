package edu.mum.client.controller;


import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
@RequestMapping("/block")
public class BlockController {


    private String api_url = "http://localhost:8081/api/v1/blocks"; //Constants.URL + "students";

    private final TokenHelper tokenHelper;

    @Autowired
    public BlockController(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @GetMapping
    public String getAll(Model model){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List> exchange =restTemplate.exchange(api_url, HttpMethod.GET, entity, List.class);

        //ResponseEntity<String> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, String.class);

        System.out.println("response: " + exchange.getBody());

        model.addAttribute("blocks", exchange.getBody());

        return "blocks/block-list";
    }


    @GetMapping("/show-add-form")
    public String showAddForm(@ModelAttribute BlockReportModel blockReportModel, Model model){
        return "block-add";
    }

    @PostMapping("/save-block")
    public String saveBlock(@ModelAttribute BlockReportModel blockReportModel){

        return "welcome";
    }

}
