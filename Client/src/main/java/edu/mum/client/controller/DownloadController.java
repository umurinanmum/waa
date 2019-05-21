package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
import edu.mum.client.model.StudentReportModel;
import edu.mum.client.model.StudentReportModelForFaculty;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession httpSession;

    @PostMapping(value = "/studentBlockReportToExcel" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody  byte[] studentBlockReportToExcel() {
        StudentReportModel studentReportModelSes=null;
        Object studentReportModelSesObj =  httpSession.getAttribute("studentReportModelSes");

        if(studentReportModelSesObj!=null){
            studentReportModelSes = (StudentReportModel) studentReportModelSesObj;

        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity<ResponseEntity<InputStreamResource>> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(Constants.URL + "download/studentBlockReportToExcel?blockName="+studentReportModelSes.getSelectedBlock()
                , HttpMethod.GET, entity, String.class);

        String fileContent="Empty";
        fileContent = response.getBody();


        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
        byte[] result = null;
        try {
            result=IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }


    @PostMapping(value = "/studentBlockReportToExcelByStudent" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody  byte[] studentBlockReportToExcelByStudent() {
        StudentReportModelForFaculty studentReportModelSes=null;
        Object studentReportModelSesObj =  httpSession.getAttribute("studentReportModelForFacultySes");

        if(studentReportModelSesObj!=null){
            studentReportModelSes = (StudentReportModelForFaculty) studentReportModelSesObj;

        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + tokenHelper.getToken());

        HttpEntity<ResponseEntity<InputStreamResource>> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(Constants.URL + "download/studentBlockReportToExcelForFaculty?blockName="+studentReportModelSes.getSelectedBlock()
                        +"&id="+studentReportModelSes.getSelectedStudent()
                , HttpMethod.GET, entity, String.class);

        String fileContent="Empty";
        fileContent = response.getBody();


        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
        byte[] result = null;
        try {
            result=IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

}
