package edu.mum.waa.dto;


import edu.mum.waa.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class StudentDto extends BaseDto<StudentDto, Student> {


    public StudentDto() {
        super.dtoClass = StudentDto.class;
        super.entityClass = Student.class;
    }

    private long id;

    private String barcode;

    private String studentId;

    private EntryDto entry;

    private List<SectionDto> sections;

    private String firstName;

    private String lastName;

}
