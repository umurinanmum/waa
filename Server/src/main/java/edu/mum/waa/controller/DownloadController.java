package edu.mum.waa.controller;

import edu.mum.waa.download.ExcelGenerator;
import edu.mum.waa.security.SecurityHelper;
import edu.mum.waa.service.interfaces.AttendanceService;
import lombok.var;
import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/download")
public class DownloadController {

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SecurityHelper securityHelper;

    @GetMapping(value = "/studentBlockReportToExcel")
    public String studentBlockReportToExcel(HttpServletResponse response, @RequestParam String blockName) throws IOException {
        Long id= securityHelper.getCurrentUserId();
        var res = attendanceService.getStudentAttendanceByStudentIdAndBlock(blockName, id);
        String r = res.getDatePresentDtoList().stream().map(l -> l.getDate() + "," + l.isPresent()).collect(Collectors.joining("\r\n"));
        return r;



/*        OutputStream out = response.getOutputStream();

        File file = new File("test1.csv");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(r);
        fileWriter.flush();
        fileWriter.close();

        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();*/


  /*      response.setContentType("application/octet-stream");
        response.getOutputStream().write(r.getBytes());
        response.flushBuffer();*/


//        List<String> headers = Arrays.asList("Date", "Status");
//        new SimpleExporter().gridExport(headers, res.getDatePresentDtoList(), "date, isPresent, ", response.getOutputStream());
//        response.flushBuffer();

//        ByteArrayInputStream in = excelGenerator.studentBlockReportToExcel(res);
//
//        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
//        response.setContentType(mediaType.getType());
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "studentBlockReportToExcel.xlsx");
//       // response.setContentLength(in.);
//
//        BufferedInputStream inStream = new BufferedInputStream(in);
//        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
//
//        byte[] buffer = new byte[1024];
//        int bytesRead = 0;
//        while ((bytesRead = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//        outStream.flush();
//        inStream.close();


//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=studentBlockReportToExcel.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new InputStreamResource(in));
    }

}
