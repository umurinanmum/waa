package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentReportModelForFaculty {

    private List<DatePresentModel> datePresentDtoList;

    private int sessionsInBlock;
    private int daysPresent;
    private int percentageAttended;
    private double extraCredits;

    private int totalSessionsPossible;
    private int totalSessionsAttended;
    private int percentageAttendedInTotal;



    private String selectedBlock;
    private String selectedStudent;


}
