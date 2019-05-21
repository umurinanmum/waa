package edu.mum.waa.controller;

import edu.mum.waa.download.ExcelGenerator;
import edu.mum.waa.security.SecurityHelper;
import edu.mum.waa.service.interfaces.AttendanceService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        Long id = securityHelper.getCurrentUserId();
        var res = attendanceService.getStudentAttendanceByStudentIdAndBlock(blockName, id);
        String m = "Block: " + blockName + "\r\n" +

                "Total sessions possible: " + res.getTotalSessionsPossible() + "\r\n" +
                "Total sessions attended: " + res.getTotalSessionsAttended() + "\r\n" +
                "Percent attended in total: " + res.getPercentageAttendedInTotal() + "\r\n" +
                "Session in block: " + res.getSessionsInBlock() + "\r\n" +
                "Days present: " + res.getDaysPresent() + "\r\n" +
                "Percentage attended: " + res.getPercentageAttended() + "\r\n" +
                "Extra points: " + res.getExtraCredits() + "\r\n\r\n" ;


                String r = res.getDatePresentDtoList().stream().map(l -> l.getDate() + "," + l.isPresent()).collect(Collectors.joining("\r\n"));
        return m.concat(r);


    }

    @GetMapping(value = "/studentBlockReportToExcelForFaculty")
    public String studentBlockReportToExcel(HttpServletResponse response, @RequestParam String blockName,@RequestParam Long id) throws IOException {

        var res = attendanceService.getStudentAttendanceByStudentIdAndBlock(blockName, id);
        String m = "Block: " + blockName + "\r\n" +

                "Total sessions possible: " + res.getTotalSessionsPossible() + "\r\n" +
                "Total sessions attended: " + res.getTotalSessionsAttended() + "\r\n" +
                "Percent attended in total: " + res.getPercentageAttendedInTotal() + "\r\n" +
                "Session in block: " + res.getSessionsInBlock() + "\r\n" +
                "Days present: " + res.getDaysPresent() + "\r\n" +
                "Percentage attended: " + res.getPercentageAttended() + "\r\n" +
                "Extra points: " + res.getExtraCredits() + "\r\n\r\n" ;


        String r = res.getDatePresentDtoList().stream().map(l -> l.getDate() + "," + l.isPresent()).collect(Collectors.joining("\r\n"));
        return m.concat(r);


    }



}
