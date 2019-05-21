package edu.mum.waa.controller;

import edu.mum.waa.dto.SearchResultDto;
import edu.mum.waa.dto.WaaPageable;
import edu.mum.waa.entity.Student;
import edu.mum.waa.entity.TmCheckAndRetreat;
import edu.mum.waa.exceptions.StudentException;
import edu.mum.waa.service.interfaces.StudentService;
import edu.mum.waa.service.interfaces.TmCheckAndRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"
        , "http://ec2-18-218-112-35.us-east-2.compute.amazonaws.com:8080", "http://ec2-18-218-112-35.us-east-2.compute.amazonaws.com:8082"}, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TMCheckAndRetreatController {

    @Autowired
    TmCheckAndRetreatService tmCheckAndRetreatService;

    @Autowired
    private StudentService studentService;

    @Autowired
    Environment environment;

    @PostMapping(value = "/retreat-checking", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TmCheckAndRetreat save(@RequestBody @Valid TmCheckAndRetreat tmCheckAndRetreat
            , HttpServletRequest request, Model model) throws StudentException {

        // create & update
        tmCheckAndRetreatService.save(tmCheckAndRetreat);

        return tmCheckAndRetreat;
    }

    @GetMapping("/retreat-checking/{id}")
    public TmCheckAndRetreat getById(@PathVariable("id") Long id) {
        return tmCheckAndRetreatService.findTmCheckAndRetreatOrderById(id).orElse(null);
    }

    @DeleteMapping("/retreat-checking/{id}")
    public void delete(@PathVariable("id") Long id) {
        tmCheckAndRetreatService.deleteById(id);
    }

    @GetMapping("/retreat-checking/student/{stuId}")
    public SearchResultDto<TmCheckAndRetreat> findTmCheckAndRetreatOrderByStudent(@PathVariable("stuId") Long stuId
            , @RequestParam(value = "page", defaultValue = "0") Integer page
            , @RequestParam(value = "pageSize", defaultValue = "10") Integer size) {

        Integer pageSize = (size==null) ? Integer.valueOf(environment.getProperty("pageSize", "10")) + 1 : size;

        int pageNum = page.intValue();
        WaaPageable pageable = WaaPageable.of(page, pageSize);

        return tmCheckAndRetreatService.findTmCheckAndRetreatOrderByStudent(stuId, pageable);
    }


    @GetMapping(value = "/student-lookup", produces = "application/json")
    public List<Student> lookup(@RequestParam("q") String query) {
        System.out.println("looking =====");
        return studentService.lookupStudentByStudentId(query);
    }

}
