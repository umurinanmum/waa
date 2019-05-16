package edu.mum.waa.batch;

import edu.mum.waa.dto.AttendanceDto;
import edu.mum.waa.service.interfaces.BlockService;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ManualEntryProcessor implements ItemProcessor<ManualModel, AttendanceDto> {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BlockService blockService;


    @Override
    public AttendanceDto process(ManualModel manualModel) throws Exception {

        //Set Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setDateTime(LocalDate.parse(manualModel.getDate(), formatter));


        //Set Student
        attendanceDto.setStudent(studentService.findStudentByStudentId(manualModel.getStudentId()));

        //Set Block
        attendanceDto.setBlock(blockService.findBlockByStartDateBeforeAndEndDateAfter(attendanceDto.getDateTime()));


        return attendanceDto;
    }
}
