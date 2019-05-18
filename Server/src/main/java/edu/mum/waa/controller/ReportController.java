package edu.mum.waa.controller;

import edu.mum.waa.entity.EntryReport;
import edu.mum.waa.entity.FacultyReport;
import edu.mum.waa.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

//{"http://localhost:8080", "http://localhost:8081"}
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"}, maxAge = 6000, allowedHeaders = "*")
@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    Environment environment;

    @GetMapping("/faculty-report/{sectionId}")
    public List<FacultyReport> loadFacultyReport(@PathVariable("sectionId") Long sectionId) {
        return reportService.exportFacultyReport(sectionId);
    }

    @GetMapping("/report/entries/{entry}")
    public List<EntryReport> loadEntryReport(@PathVariable("entry") Long entry
            , @RequestParam(value = "from", required = false, defaultValue = "0") Integer from
            , @RequestParam(value = "to", required = false) Integer to) {

        Integer pageSize = (to == null) ? Integer.valueOf(environment.getProperty("pageSize", "20")) : to;
        Pageable pageable = PageRequest.of(from, pageSize);

        System.out.println("pageable.getPageSize(): " + pageable.getPageSize());

        return reportService.exportEntryReport(entry, pageable);
    }
    @GetMapping("/report/entries/all/{entry}")
    public List<EntryReport> loadAllEntryReport(@PathVariable("entry") Long entry ) {

        Integer pageSize =  reportService.retrieveSizeOfEntryReport(entry);
        if (pageSize < 1) {
            return Collections.emptyList();
        }
        Pageable pageable = PageRequest.of(0, pageSize);

        System.out.println("pageable.getPageSize(): " + pageable.getPageSize());

        return reportService.exportEntryReport(entry, pageable);
    }
}
