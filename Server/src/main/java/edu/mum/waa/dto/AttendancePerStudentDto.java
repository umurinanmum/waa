package edu.mum.waa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@Setter
public class AttendancePerStudentDto {


    private int daysPresent;
    private int percentageAttended;
    private int availableDays;


}
