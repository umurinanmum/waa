package edu.mum.client.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class StudentDtoForCrud {


    private long id;

    private String barcode;

    private String studentId;

    private String firstName;

    private String lastName;

}
