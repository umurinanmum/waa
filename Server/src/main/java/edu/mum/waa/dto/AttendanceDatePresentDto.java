package edu.mum.waa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@Setter
public class AttendanceDatePresentDto {

    private List<DatePresentDto> datePresentDtoList;

    private int sessionsInBlock;
    private int daysPresent;
    private int percentageAttended;
    private double extraCredits;

    private int totalSessionsPossible;
    private int totalSessionsAttended;
    private int percentageAttendedInTotal;


}
