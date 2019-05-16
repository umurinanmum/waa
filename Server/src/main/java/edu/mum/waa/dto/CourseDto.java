package edu.mum.waa.dto;

import edu.mum.waa.entity.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CourseDto extends BaseDto<CourseDto, Course> {

    public CourseDto(){
        super.dtoClass =CourseDto.class;
        super.entityClass=Course.class;
    }


    private long id;
    private String courseName;

}
