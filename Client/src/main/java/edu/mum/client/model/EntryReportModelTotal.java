package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class EntryReportModelTotal {

    private List<EntryReportModelPerStudent> entryReportModelPerStudentList;
    private String selectedEntry;


}