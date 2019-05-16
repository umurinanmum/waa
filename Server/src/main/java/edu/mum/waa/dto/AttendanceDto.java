package edu.mum.waa.dto;

import edu.mum.waa.entity.Attendance;
import edu.mum.waa.entity.Block;
import edu.mum.waa.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Getter
@Setter
public class AttendanceDto extends BaseDto<AttendanceDto, Attendance> {

    public AttendanceDto(){
        super.dtoClass =AttendanceDto.class;
        super.entityClass=Attendance.class;
    }

    private long id;

    private StudentDto student;

    private LocalDate dateTime;

    private BlockDto block;

    private LocationDto location;

    private String time;

}
