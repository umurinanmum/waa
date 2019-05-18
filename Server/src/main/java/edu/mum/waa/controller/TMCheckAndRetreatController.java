package edu.mum.waa.controller;

import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.service.interfaces.TmCheckAndRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//{"http://localhost:8080", "http://localhost:8081"}
@CrossOrigin(origins = "*", maxAge = 6000, allowedHeaders = "*")
@RestController
public class TMCheckAndRetreatController {

    @Autowired
    TmCheckAndRetreatService tmCheckAndRetreatService;

    @PostMapping(value = "/retreat-checking", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TmCheckAndRetreat save(@RequestBody TmCheckAndRetreat tmCheckAndRetreat
        , HttpServletRequest request, Model model) {
        System.out.println("======" + tmCheckAndRetreat.getStudent());
        System.out.println("======" + tmCheckAndRetreat.getLocalDateTime());
      //   request.getParameterNames()

        tmCheckAndRetreatService.save(tmCheckAndRetreat);

        return tmCheckAndRetreat;
    }

    @GetMapping("/retreat-checking/{id}")
    public TmCheckAndRetreat getById(@PathVariable("id") Long id) {
        return tmCheckAndRetreatService.findTmCheckAndRetreatOrderById(id).orElse(null);
    }

}
