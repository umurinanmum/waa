package edu.mum.client.controller;

import edu.mum.client.model.Section;
import edu.mum.client.model.TmCheckAndRetreat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TMCheckAndRetreatController {

    @GetMapping("/retreat-checking")
    public String load(@ModelAttribute TmCheckAndRetreat tmCheckAndRetreat) {
        return "tmCheckAndRetreat";
    }

}
