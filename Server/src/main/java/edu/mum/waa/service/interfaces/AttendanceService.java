package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.AttendanceByEntryDto;
import edu.mum.waa.dto.AttendanceDatePresentDto;
import edu.mum.waa.dto.AttendanceDto;
import edu.mum.waa.dto.ExtraCreditModel;

import java.util.List;

public interface AttendanceService extends BaseService<AttendanceDto> {

    AttendanceDatePresentDto getStudentAttendanceByStudentIdAndBlock(String blockName, Long idStudent);

    List<AttendanceByEntryDto> getReportByEntry(String entry);

    ExtraCreditModel getExtraCreditsByBlock(String blockName);


}
