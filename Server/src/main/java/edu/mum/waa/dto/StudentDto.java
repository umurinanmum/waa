package edu.mum.waa.dto;


import edu.mum.waa.entity.Section;
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
        dtoClass = StudentDto.class;
        entityClass = Student.class;
    }

    private long id;

    private String barcode;

    private String studentId;

    private EntryDto entry;

    private List<SectionDto> sections;

}
