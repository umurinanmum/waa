package edu.mum.waa.dto;

import edu.mum.waa.entity.Course;

public class CourseDto extends BaseDto<CourseDto, Course> {

    public CourseDto(){
        dtoClass =CourseDto.class;
        entityClass=Course.class;
    }


    private long id;
    private String courseName;

}
