package edu.mum.client.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class StudentModel {

    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.firstName}")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.lastName}")
    private String lastName;

    @NotEmpty
    //@Size(min = 2, max = 50, message = "${Size.barCode}")
    private String barCode;

    @NotEmpty
    @Size(min = 6, max = 6, message = "${Size.studentId}")
    private String studentId;

}
