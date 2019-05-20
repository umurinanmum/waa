package edu.mum.client.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Student {

    private long id;

    private String barcode;

    private String studentId;

    private Entry entry;

    private List<Section> sections;

}
