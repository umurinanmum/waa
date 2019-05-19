package edu.mum.waa.dto;

import edu.mum.waa.entity.Section;
import edu.mum.waa.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class SectionDto extends BaseDto<SectionDto, Section> {


    public SectionDto(){
        super.dtoClass =SectionDto.class;
        super.entityClass=Section.class;
    }

    private long id;

    private BlockDto block;

    private CourseDto course;

    private FacultyDto faculty;

    private int canceledDays = 0;

    private int availableDays = 22;

    private List<StudentDto> studentList;


}
