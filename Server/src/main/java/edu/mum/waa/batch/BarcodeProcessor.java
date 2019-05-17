package edu.mum.waa.batch;

import edu.mum.waa.dto.AttendanceDto;
import edu.mum.waa.service.interfaces.BlockService;
import edu.mum.waa.service.interfaces.LocationService;
import edu.mum.waa.service.interfaces.StudentService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class BarcodeProcessor implements ItemProcessor<BarcodeModel, AttendanceDto> {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private LocationService locationService;


    @Override
    public AttendanceDto process(BarcodeModel barcodeModel) throws Exception {

        //Set Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setDateTime(LocalDate.parse(barcodeModel.getDate(), formatter));


        //Set Student
        attendanceDto.setStudent(studentService.findStudentByBarcode(barcodeModel.getBarcode()));

        //Set Block
        attendanceDto.setBlock(blockService.findBlockByStartDateBeforeAndEndDateAfter(attendanceDto.getDateTime()));


        //Set Time
        attendanceDto.setTime(barcodeModel.getTime());


        //Set Location
        attendanceDto.setLocation(locationService.findByLocationCode(barcodeModel.getLocation()));


        return attendanceDto;
    }
}
