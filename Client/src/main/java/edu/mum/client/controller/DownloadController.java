package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockModel;
import edu.mum.client.model.StudentReportModel;
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

/*
        try {
            File file = new File("report.csv");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileContent);
            fileWriter.flush();
            fileWriter.close();

            Path path = Paths.get("./report.csv");
            Files.write(path, bytes);



            byte[] bytes = file.getBytes();



        } catch (IOException e) {
            e.printStackTrace();
        }*/



        //If user is not authorized - he should be thrown out from here itself

        //Authorized user will download the file
/*        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        int a = 45;*/


    }

}
