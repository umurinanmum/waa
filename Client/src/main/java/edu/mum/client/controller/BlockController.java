package edu.mum.client.controller;

import edu.mum.client.model.BlockModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlockController {

    @GetMapping("/block/show-add-form")
    public String showAddForm(@ModelAttribute BlockModel blockModel, Model model){

        return "block-add";
    }

    @PostMapping("/save-block")
    public String saveBlock(@ModelAttribute BlockModel blockModel){



        return "welcome";
    }

}
