package edu.mum.waa.dto;

import edu.mum.waa.entity.Faculty;

public class FacultyDto extends BaseDto<FacultyDto, Faculty> {

    public FacultyDto(){
        dtoClass =FacultyDto.class;
        entityClass=Faculty.class;
    }


    private long id;

    private String firstName;

    private String lastName;
}
