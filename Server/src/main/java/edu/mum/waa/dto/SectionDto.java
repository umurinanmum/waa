package edu.mum.waa.dto;

import edu.mum.waa.entity.Section;

public class SectionDto extends BaseDto<SectionDto, Section> {


    public SectionDto(){
        dtoClass =SectionDto.class;
        entityClass=Section.class;
    }

    private long id;

    private BlockDto block;

    private CourseDto course;

    private FacultyDto faculty;



}
