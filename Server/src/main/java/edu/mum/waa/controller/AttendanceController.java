package edu.mum.waa.controller;


import edu.mum.waa.dto.AttendanceByEntryDto;
import edu.mum.waa.dto.AttendanceDatePresentDto;
import edu.mum.waa.security.SecurityHelper;
import edu.mum.waa.service.interfaces.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private AttendanceService attendanceService;
    private SecurityHelper securityHelper;

    public AttendanceController(AttendanceService attendanceService, SecurityHelper securityHelper) {
        this.attendanceService = attendanceService;
        this.securityHelper = securityHelper;
    }


    @GetMapping("/{blockName}")
    public AttendanceDatePresentDto getAttendanceByBlock(@PathVariable String blockName) {


        return attendanceService.getStudentAttendanceByStudentIdAndBlock(blockName, securityHelper.getCurrentUserId());
    }


    @GetMapping
    public List<AttendanceByEntryDto> getAttendanceByEntry(@RequestParam String entryName) {

        return attendanceService.getReportByEntry(entryName);
    }


}
