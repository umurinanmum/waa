package edu.mum.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class EntryReportModelPerStudent {

    private String firstName;

    private String lastName;

    private HashMap<String, AttendancePerStudentModel> attendancePerBlock;



}