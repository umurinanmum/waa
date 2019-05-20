package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class StudentModel {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.firstName}")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.lastName}")
    private String lastName;

    @NotEmpty
    //@Size(min = 2, max = 50, message = "${Size.barcode}")
    private String barcode;

    @NotEmpty
    @Size(min = 6, max = 6, message = "${Size.studentId}")
    private String studentId;

    private Entry entry;
}
