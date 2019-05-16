package edu.mum.waa.batch;

import edu.mum.waa.dto.AttendanceDto;
import edu.mum.waa.service.interfaces.AttendanceService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceWriter implements ItemWriter<AttendanceDto> {

    @Autowired
    AttendanceService attendanceService;

    @Override
    public void write(List<? extends AttendanceDto> list) throws Exception {
        for (AttendanceDto attendanceDto : list) {
            attendanceService.save(attendanceDto);
        }
    }
}
