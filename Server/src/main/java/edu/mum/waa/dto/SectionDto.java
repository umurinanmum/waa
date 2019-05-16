package edu.mum.waa.dto;

import edu.mum.waa.entity.Section;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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



}
