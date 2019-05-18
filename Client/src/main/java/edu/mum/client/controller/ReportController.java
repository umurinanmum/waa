package edu.mum.client.controller;

import edu.mum.client.model.Section;
import edu.mum.client.model.TmCheckAndRetreat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {

    @GetMapping("/report/faculty-report")
    public String loadFacultyReport(@ModelAttribute Section section, Model model) {
        
        return "report/facultyReport";
    }

    @GetMapping("/report/entries/{entry}")
    public String loadEntryReport(@PathVariable("entry") Long entry) {
        return "report/entryReport";
    }
}
