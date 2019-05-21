package edu.mum.waa.dto;


import edu.mum.waa.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StudentDtoForCrud extends BaseDto<StudentDtoForCrud,Student>{


    public StudentDtoForCrud() {
        super.dtoClass = StudentDtoForCrud.class;
        super.entityClass = Student.class;
    }

    private long id;

    private String barcode;

    private String studentId;

    private String firstName;

    private String lastName;

}
