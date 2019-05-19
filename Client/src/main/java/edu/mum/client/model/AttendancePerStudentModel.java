package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
public class AttendancePerStudentModel {


    private int daysPresent;
    private int percentageAttended;
    private int availableDays;


}
