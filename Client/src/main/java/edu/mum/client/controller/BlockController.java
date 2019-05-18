package edu.mum.client.controller;

import edu.mum.client.model.BlockReportModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/block")
public class BlockController {

    @GetMapping("/show-add-form")
    public String showAddForm(@ModelAttribute BlockReportModel blockReportModel, Model model){

        return "block-add";
    }

    @PostMapping("/save-block")
    public String saveBlock(@ModelAttribute BlockReportModel blockReportModel){



        return "welcome";
    }

}
